package com.bugil.hellosmallworld.repository;

import com.bugil.hellosmallworld.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {


}
