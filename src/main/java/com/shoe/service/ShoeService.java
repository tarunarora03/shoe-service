package com.shoe.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dto.ShoeDto;
import com.shoe.dto.ShoeResponse;
import com.shoe.entity.ShoeBrand;
import com.shoe.entity.ShoeDetails;
import com.shoe.entity.ShoeSubCategraoryDtls;
import com.shoe.entity.ShoeTrueSizeDetails;
import com.shoe.repository.ShoeBrandRepository;
import com.shoe.repository.ShoeDetailsRepository;
import com.shoe.repository.ShoeTrueSizeRepository;
import com.shoe.util.RecordNotFoundException;

@Service
public class ShoeService {

	private static final Logger logger = LoggerFactory.getLogger(ShoeService.class);
	@Autowired
	private ShoeBrandRepository shoeBrandRepository;
	@Autowired
	private ShoeTrueSizeRepository trueSizeRepository;
	@Autowired
	private ShoeDetailsRepository shoeDetailsRepository;

	// Add new Shoe Brands
	public ShoeResponse addShoeBrand(ShoeDto dto) {
		ShoeBrand brand = new ShoeBrand();
		brand.setShoeBrandName(dto.getShoeBrandName());

		int brandId = shoeBrandRepository.saveAndFlush(brand).getShoeBrandId();
		ShoeResponse resp = new ShoeResponse();
		resp.setBrandId(brandId);
		resp.setBrandName(dto.getShoeBrandName());
		resp.setStatus("Record added successfully.");

		logger.info("New shoe brand added succesfully. brandName:{}",dto.getShoeBrandName());
		return resp;
	}

	// Return a list of avaiable shoe brands
	public List<ShoeResponse> getAvailableBrands() {

		logger.info("In method get Available brands.");
		List<ShoeBrand> shoeBrands = shoeBrandRepository.findAll();

		logger.info("ShoeBrands: " + shoeBrands);
		if (shoeBrands.size() != 0) {
			List<ShoeResponse> shoeResponseList = shoeBrands.stream().map(brands -> {
				ShoeResponse dto = new ShoeResponse();
				dto.setBrandId(brands.getShoeBrandId());
				dto.setBrandName(brands.getShoeBrandName());
				return dto;
			}).collect(Collectors.toList());

			return shoeResponseList;
		} else {
			throw new RecordNotFoundException("No Brands exists. Please add some brands first", "");
		}
	}

	@Transactional
	public ShoeResponse addNewTrueSize(ShoeDto dto, int trueSize) {
		// Implementation needs to be updated in case shoe size doesn't exists
		ShoeTrueSizeDetails trueSizeDetails = new ShoeTrueSizeDetails();
		ShoeDetails shoeDetails = new ShoeDetails();
		ShoeResponse resp = new ShoeResponse();

		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		if (shoeBrand != null) {
			// add the new data into the truesize table
			trueSizeDetails.setShoeBrandId(shoeBrand.getShoeBrandId());
			trueSizeDetails.setShoeSize(dto.getShoeSize());
			trueSizeDetails.setTrueSize(trueSize);
			
			logger.info("Inserting records for {} in the trueSizetable", shoeBrand.getShoeBrandId());
			logger.debug("trueSizeDetails:"+trueSizeDetails.toString());
			
			trueSizeRepository.saveAndFlush(trueSizeDetails);
			logger.info("Record inserted successfully in the table");
			
			// get the exiting avg for that shoe and size
			shoeDetails = shoeDetailsRepository.findByShoeBrandIdAndShoeSize(shoeBrand.getShoeBrandId(),
					dto.getShoeSize());

			if (shoeDetails != null) {
				logger.debug("ShoeDetails:" + shoeDetails.toString());

				double exitingAvg = shoeDetails.getTrueSizeAvg();
				double newAvg = ((exitingAvg * shoeDetails.getTrueSizeCount()) + trueSizeDetails.getTrueSize())
						/ (shoeDetails.getTrueSizeCount() + 1);

				shoeDetailsRepository.setAvgInfoById(newAvg, shoeDetails.getTrueSizeCount() + 1, shoeDetails.getId());
				logger.debug("Updated avg in the shoedetails table. new Avg is {}", newAvg);
			} else {
				ShoeDetails details = new ShoeDetails();

				details.setShoeBrandId(shoeBrand.getShoeBrandId());
				details.setShoeSize(dto.getShoeSize());
				details.setShoeSubCatId(1);
				details.setTrueSizeAvg(trueSize);
				details.setTrueSizeCount(1);

				shoeDetailsRepository.save(details);
			}
			resp.setStatus("Record added succesfully and avg were updated.");
		} else {
			logger.error("The requested shoe brand doesnt exists.");
			throw new RecordNotFoundException("No Brand exists for the given Name in the request",
					dto.getShoeBrandName());
		}

		return resp;
	}

	// Return existing true size avg for shoe size for a brand and size
	public ShoeResponse getTrueSizeAvg(ShoeDto dto) {
		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		if (shoeBrand != null) {
			logger.debug("ShoeBrand:" +shoeBrand.toString());
			ShoeDetails shoeDetails = shoeDetailsRepository.findByShoeBrandIdAndShoeSize(shoeBrand.getShoeBrandId(),
					dto.getShoeSize());
			if (shoeDetails != null) {
				logger.debug("ShoeDetails:" + shoeDetails.toString());

				ShoeResponse dtoResponse = new ShoeResponse();
				dtoResponse.setAvgTrueSize(shoeDetails.getTrueSizeAvg());
				dtoResponse.setBrandId(shoeDetails.getShoeBrandId());
				dtoResponse.setBrandName(dto.getShoeBrandName());
				dtoResponse.setShoeSize(dto.getShoeSize());
				return dtoResponse;
			} else {
				logger.error("The requested shoe details doesnt exists.");
				throw new RecordNotFoundException("No shoe size exists for the given brand Name in the request",
						dto.getShoeBrandName() + "|" + dto.getShoeSize());
			}
		} else {
			logger.error("The requested shoe brand doesnt exists.");
			throw new RecordNotFoundException("No Brand exists for the given Name in the request",
					dto.getShoeBrandName());
		}

	}

	// Add new shoe subcategory for later
	public void addShoeSubCategory(ShoeDto dto) {
		ShoeSubCategraoryDtls subCategraoryDtls = new ShoeSubCategraoryDtls();
		// get the Shoe ID
		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);

		subCategraoryDtls.setShoeBrandId(shoeBrand.getShoeBrandId());
		subCategraoryDtls.setShoeName(dto.getShoeName());
		subCategraoryDtls.setShoeType(dto.getShoeType());
	}

	/**
	 * @param dto
	 * @return
	 */
	private ShoeBrand getShoeIdFromBrand(ShoeDto dto) {
		logger.debug("Getting shoeId for Brand:{}", dto.getShoeBrandName());
		ShoeBrand shoeBrand = shoeBrandRepository.findByShoeBrandName(dto.getShoeBrandName());
		return shoeBrand;
	}

	public void addShoeDetails(ShoeDto dto) {

		ShoeDetails details = new ShoeDetails();

		ShoeBrand shoeBrand = getShoeIdFromBrand(dto);
		details.setShoeBrandId(shoeBrand.getShoeBrandId());
		details.setShoeSize(dto.getShoeSize());
		details.setShoeSubCatId(1);
		details.setTrueSizeAvg(0);
		details.setTrueSizeCount(0);

		shoeDetailsRepository.save(details);

	}
}
