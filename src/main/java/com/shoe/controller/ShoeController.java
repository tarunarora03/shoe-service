package com.shoe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoe.dto.ShoeDto;
import com.shoe.service.ShoeService;

@RestController
@RequestMapping("/shoe")
public class ShoeController {

	private static final Logger logger = LoggerFactory.getLogger(ShoeController.class);

	@Autowired
	private ShoeService shoeService;

	public void getShoeTrueSize() {

	}

	public void addShoeTrueSize() {

	}

	@PostMapping("/addBrand")
	public void addShoeBrand(ShoeDto dto) {
		int shoeId = shoeService.addShoeBrand(dto);
		logger.info("Shoe Brand added successfully." + shoeId);
	}

	public void addShoeSubCat() {

	}

	@GetMapping("/allBrands")
	public List<ShoeDto> getAllBrands(){
		return shoeService.getAvailableBrands();
	}
}
