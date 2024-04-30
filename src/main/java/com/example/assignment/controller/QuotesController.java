package com.example.assignment.controller;

import com.example.assignment.model.QuotesDto;
import com.example.assignment.service.QuotesService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuotesController {

  private final QuotesService quotesService;

  public QuotesController(QuotesService quotesService) {
    this.quotesService = quotesService;
  }

  @GetMapping
  public List<QuotesDto> getQuotes(@RequestParam(required = false) String author) {
    if (StringUtils.hasText(author))
      return quotesService.getQuotesByAuthor(author);
    else
      return quotesService.getAllQuotes();
  }

}
