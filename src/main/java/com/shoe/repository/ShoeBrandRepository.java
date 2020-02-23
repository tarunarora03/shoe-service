package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoe.entity.ShoeBrand;

public interface ShoeBrandRepository extends JpaRepository<ShoeBrand, Integer>{

	ShoeBrand findByShoeBrandName(String shoeBrandName);
}
