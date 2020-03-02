package com.shoe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoe.dto.ShoeDto;
import com.shoe.dto.ShoeResponse;
import com.shoe.service.ShoeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/shoe")
@Api(value = "Shoe True Size Management System", description = "Operations pertaining to different true sizes in Shoe managemenet system")
public class ShoeController {

	private static final Logger logger = LoggerFactory.getLogger(ShoeController.class);

	@Autowired
	private ShoeService shoeService;

	@GetMapping("/allBrands")
	@ApiOperation(value = "View a list of available brands", response = Iterable.class)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public List<ShoeResponse> getAllBrands() {
		return shoeService.getAvailableBrands();
	}
	
	
	@GetMapping("/getTrueSize")
	@ApiOperation(value = "Get trueSize for a given shoeBrand and size.", response = ShoeResponse.class)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Successfully retrieved details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ShoeResponse getShoeTrueSize(@ApiParam( value = "Brand cannot be empty.", required = true) @RequestParam("brand") String brandName,
			@ApiParam(value = "shoeSize cannot be empty.", required = true) @RequestParam("shoeSize") String shoeSize) {

		logger.info("In method gettruesize for ShoeBrand:{} shoeSize:{}",brandName,shoeSize);
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName(brandName);
		dto.setShoeSize(shoeSize);

		return shoeService.getTrueSizeAvg(dto);
	}

	@PostMapping("/addTrueSize")
	@ApiOperation(value = "add new trueSize to shoeBrand and size.", response = ShoeResponse.class)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Successfully retrieved details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ShoeResponse addShoeTrueSize(@ApiParam(value = "Brand cannot be empty.", required = true) @RequestParam("brand") String brandName,
			@ApiParam(value = "shoeSize cannot be empty.", required = true) @RequestParam("shoeSize") String shoeSize,
			@ApiParam(value = "trueSize cannot be empty.", required = true) @RequestParam("trueSize") String trueSize) {

		logger.info("Add new true size {} for ShoeBrand: {} ShoeSize: {} ", trueSize,brandName,shoeSize);
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName(brandName);
		dto.setShoeSize(shoeSize);
		return shoeService.addNewTrueSize(dto, Integer.parseInt(trueSize));
	}
	
//	@PostMapping("/addBrand")
//	@ApiOperation(value = "add new brands to list of existing brands ", response = ShoeResponse.class)
//	@ApiResponses( value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved details"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
//			@ApiResponse(code = 409, message = "The resource already exists by the given name.")
//	})
//	public ShoeResponse addShoeBrand(@RequestBody ShoeDto dto) {
//
//		logger.info("adding a new shoe brand {}",dto.getShoeBrandName());
//		return shoeService.addShoeBrand(dto);
//
//	}

	@PostMapping("/addBrand")
	@ApiOperation(value = "add new brands to list of existing brands ", response = ShoeResponse.class)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Successfully retrieved details"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 402, message = "The resource already exists by the given name.")
	})
	public ShoeResponse addShoeBrand(@ApiParam(value = "Brand cannot be empty.", required = true) @RequestParam("brand") String brandName) {

		logger.info("adding a new shoe brand {}",brandName);
		return shoeService.addShoeBrand(brandName);

	}

}
