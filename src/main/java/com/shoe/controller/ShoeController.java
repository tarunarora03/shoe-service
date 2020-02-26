package com.shoe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoe.dto.ShoeDto;
import com.shoe.dto.ShoeResponse;
import com.shoe.service.ShoeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/shoe")
@Api(value = "shoeService", description = "Operations pertaining to Shoe Service")
public class ShoeController {

	private static final Logger logger = LoggerFactory.getLogger(ShoeController.class);

	@Autowired
	private ShoeService shoeService;

	@GetMapping("/getTrueSize")
	@ApiOperation(value = "Get trueSize for a given shoeBrand and size.")
	public ShoeResponse getShoeTrueSize(@ApiParam("Brand cannot be empty.") @RequestParam("brand") String brandName,
			@ApiParam("shoeSize cannot be empty.") @RequestParam("shoeSize") String shoeSize) {

		logger.info("In method gettruesize for ShoeBrand:{} shoeSize:{}",brandName,shoeSize);
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName(brandName);
		dto.setShoeSize(shoeSize);

		return shoeService.getTrueSizeAvg(dto);
	}

	@GetMapping("/addTrueSize")
	@ApiOperation(value = "add new trueSize to shoeBrand and size.")
	public ShoeResponse addShoeTrueSize(@ApiParam("Brand cannot be empty.") @RequestParam("brand") String brandName,
			@ApiParam("shoeSize cannot be empty.") @RequestParam("shoeSize") String shoeSize,
			@ApiParam("trueSize cannot be empty.") @RequestParam("trueSize") String trueSize) {

		logger.info("Add new true size {} for ShoeBrand: {} ShoeSize: {} ", trueSize,brandName,shoeSize);
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName(brandName);
		dto.setShoeSize(shoeSize);
		return shoeService.addNewTrueSize(dto, Integer.parseInt(trueSize));
	}

	@ApiOperation(value = "add new brands to list of existing brands ", response = Iterable.class)
	@PostMapping("/addBrand")
	public ShoeResponse addShoeBrand(ShoeDto dto) {

		logger.info("adding a new shoe brand {}",dto.getShoeBrandName());
		return shoeService.addShoeBrand(dto);

	}

	@ApiOperation(value = "View a list of available brands", response = Iterable.class)
	@GetMapping("/allBrands")
	public List<ShoeResponse> getAllBrands() {
		return shoeService.getAvailableBrands();
	}
}
