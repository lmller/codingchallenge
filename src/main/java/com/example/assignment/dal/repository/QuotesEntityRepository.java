package com.example.assignment.dal.repository;

import com.example.assignment.dal.model.QuotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotesEntityRepository extends JpaRepository<QuotesEntity, Long> {
}
