package com.shoe.service;

import java.util.List;
import java.util.stream.Collectors;

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

	// Add new Shoe Brands
	public Integer addShoeBrand(ShoeDto dto) {
		ShoeBrand brand = new ShoeBrand();
		brand.setShoeBrandName(dto.getShoeBrandName());
		return shoeBrandRepository.saveAndFlush(brand).getShoeId();
	}
	
	public List<ShoeDto> getAvailableBrands(){
		List<ShoeBrand> shoeBrands = shoeBrandRepository.findAll();
		
		List<ShoeDto> shoeBrandList = shoeBrands.stream().map(brands -> {
			ShoeDto dto = new ShoeDto();
			dto.setShoeId(brands.getShoeId());
			dto.setShoeBrandName(brands.getShoeBrandName());
			return dto;
		}).collect(Collectors.toList());
		
		return shoeBrandList;
	}

	@Transactional
	public void addNewTrueSize(ShoeDto dto) {
		// Implemention needs to be updated in case shoe size doesnt exists
		ShoeTrueSizeDetails trueSizeDetails = new ShoeTrueSizeDetails();
		ShoeDetails shoeDetails = new ShoeDetails();

		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		// get the exiting avg for that shoe and size
		shoeDetails = shoeDetailsRepository.findByShoeIdAndShoeSize(shoeBrand.getShoeId(), dto.getShoeSize());
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

	// Return existing true size avg for shoe size for a brand and size
	public double getTrueSizeAvg(ShoeDto dto) {
		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		ShoeDetails shoeDetails = shoeDetailsRepository.findByShoeIdAndShoeSize(shoeBrand.getShoeId(), dto.getShoeSize());
		System.out.println("ShoeAvg for Size:" + shoeDetails.getTrueSizeAvg() + "|" + shoeDetails.getShoeSize());

		return shoeDetails.getTrueSizeAvg();
	}

	// Add new shoe subcategory for later
	public void addShoeSubCategory(ShoeDto dto) {
		ShoeSubCategraoryDtls subCategraoryDtls = new ShoeSubCategraoryDtls();
		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		subCategraoryDtls.setShoeId(shoeBrand.getShoeId());
		subCategraoryDtls.setShoeName(dto.getShoeName());
		subCategraoryDtls.setShoeType(dto.getShoeType());
	}

	/**
	 * @param dto
	 * @return
	 */
	private ShoeBrand getShoeIdFromBrand(ShoeDto dto) {
		ShoeBrand shoeBrand = shoeBrandRepository.findByShoeBrandName(dto.getShoeBrandName());
		System.out.println("ShoeId:" + shoeBrand.getShoeId());
		return shoeBrand;
	}

	public void addShoeDetails(ShoeDto dto) {

		ShoeDetails details = new ShoeDetails();
		
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);
		details.setShoeId(shoeBrand.getShoeId());
		details.setShoeSize(dto.getShoeSize());
		details.setShoeSubCatId(1);
		details.setTrueSizeAvg(0);
		details.setTrueSizeCount(0);
		
		shoeDetailsRepository.save(details);

	}
}
