package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoe.entity.ShoeDetails;

public interface ShoeDetailsRepository extends JpaRepository<ShoeDetails, Integer>{

}
