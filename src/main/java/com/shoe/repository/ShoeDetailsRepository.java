package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shoe.entity.ShoeDetails;

public interface ShoeDetailsRepository extends JpaRepository<ShoeDetails, Integer> {

	ShoeDetails findByShoeBrandId(Integer shoeId);
	
	ShoeDetails findByShoeSize(String shoeSize);
	
	ShoeDetails findByShoeBrandIdAndShoeSize(Integer shoeId, String shoeSize);

	@Modifying
	@Query(value = "Update SHOE_DETAILS SET TRUE_SIZE_AVG = :avg, TRUE_SIZE_COUNT =:count where id=:id", nativeQuery = true)
	void setAvgInfoById(double avg, Integer count, Integer id);
}
