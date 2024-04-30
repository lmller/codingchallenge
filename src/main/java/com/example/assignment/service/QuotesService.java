package com.example.assignment.service;

import com.example.assignment.dal.repository.QuotesEntityRepository;
import com.example.assignment.model.QuotesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotesService {

  private final QuotesEntityRepository quotesEntityRepository;

  public QuotesService(QuotesEntityRepository quotesEntityRepository) {
    this.quotesEntityRepository = quotesEntityRepository;
  }

  public List<QuotesDto> getAllQuotes() {
    return quotesEntityRepository.findAll()
        .stream()
        .map(e -> QuotesDto.builder()
            .id(e.getId())
            .author(e.getAuthor())
            .quote(e.getQuote())
            .build())
        .toList();
  }

  public List<QuotesDto> getQuotesByAuthor(String author) {
    return quotesEntityRepository.findAllByAuthor(author)
        .stream()
        .map(e -> QuotesDto.builder()
            .id(e.getId())
            .author(e.getAuthor())
            .quote(e.getQuote())
            .build())
        .toList();
  }

}
