package com.shoe.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.shoe.dto.ShoeDto;
import com.shoe.dto.ShoeResponse;
import com.shoe.service.ShoeService;
import com.shoe.util.RecordNotFoundException;

@WebMvcTest(ShoeController.class)
public class ShoeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ShoeService service;

	@Test
	public void testGetBrands() throws Exception {
		List<ShoeResponse> list = new ArrayList<>();
		ShoeResponse dto = new ShoeResponse();
		dto.setBrandName("adidas");
		dto.setBrandId(1);
		list.add(dto);

		when(service.getAvailableBrands()).thenReturn(list);

		MvcResult response = mvc.perform(get("/shoe/allBrands").contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		JSONArray jsonArray = new JSONArray(response.getResponse().getContentAsString());
		Assertions.assertEquals(1, jsonArray.length());
		Assertions.assertEquals("adidas", jsonArray.getJSONObject(0).get("brandName"));
	}

	@Test
	public void testGetTrueSize() throws Exception {
		ShoeDto dto = new ShoeDto();
		dto.setShoeBrandName("nike");
		dto.setShoeSize("9");

		ShoeResponse resp = new ShoeResponse();
		resp.setShoeSize("9");
		resp.setAvgTrueSize(2.0);
		resp.setBrandName("adidas");
		resp.setBrandId(1);

		when(service.getTrueSizeAvg(dto)).thenReturn(resp);

		MvcResult response = mvc.perform(get("/shoe/getTrueSize?brand=nike&shoeSize=9")).andExpect(status().isOk())
				.andReturn();
		JSONObject jobject = new JSONObject(response.getResponse().getContentAsString());

		double val = (double) jobject.get("avgTrueSize");

		Assertions.assertEquals(2.0, val);

	}

	@Test
	public void whenNoBrands_throw404() throws Exception {

		when(service.getAvailableBrands())
				.thenThrow(new RecordNotFoundException("No Brands exists. Please add some brands first", ""));
		MvcResult resp = mvc.perform(get("/shoe/allBrands").contentType(APPLICATION_JSON)).andExpect(status().isNotFound())
				.andReturn();

		JSONObject jobject = new JSONObject(resp.getResponse().getContentAsString());
		
		Assertions.assertEquals("No Brands exists. Please add some brands first:".trim(), jobject.get("error").toString().trim());

	}
}
