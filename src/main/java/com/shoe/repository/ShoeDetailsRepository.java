package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoe.entity.ShoeDetails;

public interface ShoeDetailsRepository extends JpaRepository<ShoeDetails, Integer> {

	@Query("SELECT * FROM SHOE_DETAILS sd WHERE sd.shoeId= :shoeId and s.shoeSize = :shoeSize")
	ShoeDetails getAvgbyShoe(@Param("shoeId") Integer shoeId, @Param("shoeSize") String shoeSize);
	
	@Query("Update SHOE_DETAILS sd SET sd.trueSizeAvg= ?1, sd.trueSizeCount=?2 where sd.id=?3")
	void setAvgInfoById(double avg, Integer count, Integer id);
}
