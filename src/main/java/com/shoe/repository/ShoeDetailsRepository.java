package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoe.entity.ShoeDetails;

public interface ShoeDetailsRepository extends JpaRepository<ShoeDetails, Integer> {

	//@Query("SELECT * FROM SHOE_DETAILS sd WHERE sd.shoeId= :shoeId and s.shoeSize = :shoeSize")
	//ShoeDetails getAvgbyShoeIdAndSize(@Param("shoeId") Integer shoeId, @Param("shoeSize") String shoeSize);

	ShoeDetails findByShoeId(Integer shoeId);
	
	ShoeDetails findByShoeSize(String shoeSize);
	
	//@Query("SELECT sd FROM SHOE_DETAILS sd WHERE sd.shoeId= :shoeId and sd.shoeSize = :shoeSize")
	ShoeDetails findByShoeIdAndShoeSize(Integer shoeId, String shoeSize);

	@Query(value = "Update SHOE_DETAILS sd SET sd.trueSizeAvg= :avg, sd.trueSizeCount=:count where sd.id=:id", nativeQuery = true)
	void setAvgInfoById(double avg, Integer count, Integer id);
}
