package com.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoe.entity.ShoeTrueSizeDetails;

public interface ShoeTrueSizeRepository extends JpaRepository<ShoeTrueSizeDetails, Integer>{

}
