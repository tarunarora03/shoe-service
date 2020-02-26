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
		ShoeDto dto = addShoeBrandData("nike");
		service.addShoeBrand(dto);

		// Fetch the brand
		List<ShoeResponse> list = service.getAvailableBrands();
		Assertions.assertEquals(1, list.size());
		Assertions.assertEquals("nike", list.get(0).getBrandName());
	}

	@Test
	public void testGetAvg() {
		//1.0 Register brand
		ShoeDto dto = addShoeBrandData("adidas");
		service.addShoeBrand(dto);
		
		//2.0 add details and calculate avg
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
