package com.example.assignment.service;

import com.example.assignment.dal.model.QuotesEntity;
import com.example.assignment.dal.repository.QuotesEntityRepository;
import com.example.assignment.exception.DuplicateEntryException;
import com.example.assignment.model.QuotesDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuotesServiceTest {

  @Mock
  private QuotesEntityRepository repository;

  @InjectMocks
  private QuotesService quotesService;

  private AutoCloseable closeable;

  @BeforeEach
  public void openMocks() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  public void releaseMocks() throws Exception {
    closeable.close();
  }

  @Test
  void testGetAllQuotes() {
    quotesService.getQuotes(null);
    Mockito.verify(repository).findAll();
    Mockito.verify(repository, Mockito.times(0)).findAllByAuthor(Mockito.any());
  }

  @Test
  void testGetQuotesByAuthor() {
    String author = "Author1";
    quotesService.getQuotes(author);
    Mockito.verify(repository).findAllByAuthor(author);
    Mockito.verify(repository, Mockito.times(0)).findAll();
  }

  @Test
  void testCreateQuoteSuccess() {
    Mockito.when(repository.existsByAuthorAndQuote("Author", "Quote")).thenReturn(false);
    quotesService.createQuote(QuotesDto.builder().quote("Quote").author("Author").build());
    Mockito.verify(repository).save(QuotesEntity.builder().author("Author").quote("Quote").build());
  }

  @Test
  void testCreateDuplicateQuote() {
    Mockito.when(repository.existsByAuthorAndQuote("Author", "Quote")).thenReturn(true);
    assertThatThrownBy(() -> quotesService.createQuote(QuotesDto.builder().quote("Quote").author("Author").build()))
        .isInstanceOf(DuplicateEntryException.class);
    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
  }

}
