package com.demo.assessment.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.assessment.entity.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {


}
