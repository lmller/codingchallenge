package com.example.assignment.dal.repository;

import com.example.assignment.dal.model.QuotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotesEntityRepository extends JpaRepository<QuotesEntity, Long> {

  List<QuotesEntity> findAllByAuthor(String author);

  boolean existsByAuthorAndQuote(String author, String quote);

}
