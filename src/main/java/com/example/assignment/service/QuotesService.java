package com.example.assignment.service;

import com.example.assignment.dal.model.QuotesEntity;
import com.example.assignment.dal.repository.QuotesEntityRepository;
import com.example.assignment.exception.DuplicateEntryException;
import com.example.assignment.model.QuotesDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class QuotesService {

  private final QuotesEntityRepository quotesEntityRepository;

  public QuotesService(QuotesEntityRepository quotesEntityRepository) {
    this.quotesEntityRepository = quotesEntityRepository;
  }

  /**
   * Get all quotes filtered by author if provided
   * @param author (Optional) Name of Author to filter
   * @return List of {@link QuotesDto}
   */
  public List<QuotesDto> getQuotes(String author) {
    // If author param not provided, return all quotes
    if (StringUtils.hasText(author)) {
      return quotesEntityRepository.findAllByAuthor(author)
          .stream()
          .map(e -> QuotesDto.builder()
              .id(e.getId())
              .author(e.getAuthor())
              .quote(e.getQuote())
              .build())
          .toList();
    } else {
      return quotesEntityRepository.findAll()
          .stream()
          .map(e -> QuotesDto.builder()
              .id(e.getId())
              .author(e.getAuthor())
              .quote(e.getQuote())
              .build())
          .toList();
    }
  }

  public void createQuote(QuotesDto quote) {
    if (quotesEntityRepository.existsByAuthorAndQuote(quote.getAuthor(), quote.getQuote()))
      throw new DuplicateEntryException();
    quotesEntityRepository.save(QuotesEntity.builder()
        .author(quote.getAuthor())
        .quote(quote.getQuote())
        .build());
  }

}
