package com.example.assignment.controller;

import com.example.assignment.model.QuotesDto;
import com.example.assignment.service.QuotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuotesController {

  private final QuotesService quotesService;

  public QuotesController(QuotesService quotesService) {
    this.quotesService = quotesService;
  }

  @GetMapping
  public ResponseEntity<List<QuotesDto>> getQuotes(@RequestParam(required = false) String author) {
    List<QuotesDto> quotes = null;
    // If author param not provided, return all quotes
    if (StringUtils.hasText(author))
      quotes = quotesService.getQuotesByAuthor(author);
    else
      quotes = quotesService.getAllQuotes();

    if (CollectionUtils.isEmpty(quotes))
      return ResponseEntity.noContent().build();
    else
      return ResponseEntity.ok(quotes);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createQuote(@Valid @RequestBody QuotesDto quote) {
    quotesService.createQuote(quote);
  }

}
