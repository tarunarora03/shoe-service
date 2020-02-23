package com.shoe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dto.ShoeDto;
import com.shoe.entity.ShoeBrand;
import com.shoe.entity.ShoeDetails;
import com.shoe.entity.ShoeSubCategraoryDtls;
import com.shoe.entity.ShoeTrueSizeDetails;
import com.shoe.repository.ShoeBrandRepository;
import com.shoe.repository.ShoeDetailsRepository;
import com.shoe.repository.ShoeTrueSizeRepository;

@Service
public class ShoeService {

	@Autowired
	private ShoeBrandRepository shoeBrandRepository;
	@Autowired
	private ShoeTrueSizeRepository trueSizeRepository;
	@Autowired
	private ShoeDetailsRepository shoeDetailsRepository;

	public int addShoeBrand(ShoeDto dto) {
		ShoeBrand brand = new ShoeBrand();
		brand.setShoeBrandName(dto.getShoeBrandName());
		return shoeBrandRepository.saveAndFlush(brand).getShoeId();
	}

	@Transactional
	public void addNewTrueSize(ShoeDto dto) {
		ShoeTrueSizeDetails trueSizeDetails = new ShoeTrueSizeDetails();
		ShoeDetails shoeDetails = new ShoeDetails();

		// get the Shoe ID
		ShoeBrand shoeBrand = shoeBrandRepository.findByShoeBrandName(dto.getShoeBrandName());
		System.out.println("ShoeId:" + shoeBrand.getShoeId());

		// get the exiting avg for that shoe and size
		shoeDetails = shoeDetailsRepository.getAvgbyShoe(shoeBrand.getShoeId(), dto.getShoeSize());
		System.out.println("ShoeAvg for Size:" + shoeDetails.getTrueSizeAvg() + "|" + shoeDetails.getShoeSize());

		trueSizeDetails.setShoeId(shoeBrand.getShoeId());
		trueSizeDetails.setShoeSize(dto.getShoeSize());
		trueSizeDetails.setTrueSize(dto.getTrueSize());

		trueSizeRepository.saveAndFlush(trueSizeDetails);

		double exitingAvg = shoeDetails.getTrueSizeAvg();
		double newAvg = ((exitingAvg * shoeDetails.getTrueSizeCount()) + trueSizeDetails.getTrueSize())
				/ (shoeDetails.getTrueSizeCount() + 1);
		
		shoeDetailsRepository.setAvgInfoById(newAvg, shoeDetails.getTrueSizeCount() + 1, shoeDetails.getId());
	}
	
	public double getTrueSize(ShoeDto dto) {
		// get the Shoe ID
		ShoeBrand shoeBrand = shoeBrandRepository.findByShoeBrandName(dto.getShoeBrandName());
		System.out.println("ShoeId:" + shoeBrand.getShoeId());

		ShoeDetails shoeDetails = shoeDetailsRepository.getAvgbyShoe(shoeBrand.getShoeId(), dto.getShoeSize());
		System.out.println("ShoeAvg for Size:" + shoeDetails.getTrueSizeAvg() + "|" + shoeDetails.getShoeSize());
		
		return shoeDetails.getTrueSizeAvg();
	}
	
	public void addShoeSubCategory(ShoeDto dto) {
		ShoeSubCategraoryDtls subCategraoryDtls = new ShoeSubCategraoryDtls();
		// get the Shoe ID
		ShoeBrand shoeBrand = shoeBrandRepository.findByShoeBrandName(dto.getShoeBrandName());
		System.out.println("ShoeId:" + shoeBrand.getShoeId());

		subCategraoryDtls.setShoeId(shoeBrand.getShoeId());
		subCategraoryDtls.setShoeName(dto.getShoeName());
		subCategraoryDtls.setShoeType(dto.getShoeType());
	}
}
