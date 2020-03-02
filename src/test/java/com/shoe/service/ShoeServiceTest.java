package com.shoe.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shoe.dto.ShoeDto;
import com.shoe.dto.ShoeResponse;

@SpringBootTest
public class ShoeServiceTest {

	@Autowired
	private ShoeService service;

	@Test
	public void testAddBrands() {
		//ShoeDto dto = addShoeBrandData("nike");
		String brandName = "asics";
		service.addShoeBrand(brandName);

		// Fetch the brand
		List<ShoeResponse> list = service.getAvailableBrands();
		Assertions.assertEquals(3, list.size());
		
		String tempName="";
		for (ShoeResponse shoeResponse : list) {
			if(brandName.equalsIgnoreCase(shoeResponse.getBrandName())) {
				tempName = shoeResponse.getBrandName();
			}
		}
		Assertions.assertEquals(brandName, tempName);
	}

	@Test
	public void testGetAvg() {
		//1.0 Register brand
		String brandName = "reebok";
		
		service.addShoeBrand(brandName);
		
		//2.0 add details and calculate avg
		ShoeDto dto = addShoeBrandData(brandName);
		dto.setShoeSize("9");
		service.addNewTrueSize(dto, 2);
		
		//3.0 fetch the avg
		ShoeResponse resp = service.getTrueSizeAvg(dto);
		Assertions.assertEquals(2, resp.getAvgTrueSize());
		Assertions.assertEquals(dto.getShoeSize(), resp.getShoeSize());
		Assertions.assertEquals(dto.getShoeBrandName(), resp.getBrandName());
	}
	
	private ShoeDto addShoeBrandData(String brandName) {
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName(brandName);

		return dto;
	}
}
