package com.example.assignment.controller;

import com.example.assignment.model.QuotesDto;
import com.example.assignment.service.QuotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuotesController {

  private final QuotesService quotesService;

  public QuotesController(QuotesService quotesService) {
    this.quotesService = quotesService;
  }

  @Operation(summary = "Get quotes", description = "Get list of quotes. If author present, filter by author, else return all.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
      @ApiResponse(responseCode = "204", description = "No quotes available")
  })
  @GetMapping
  public ResponseEntity<List<QuotesDto>> getQuotes(@RequestParam(required = false) String author) {
    List<QuotesDto> quotes = quotesService.getQuotes(author);

    if (CollectionUtils.isEmpty(quotes))
      return ResponseEntity.noContent().build();
    else
      return ResponseEntity.ok(quotes);
  }

  @PostMapping
  @Operation(summary = "Create a new quote", description = "Create a new quote if not already present.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Quote created successfully"/* ,
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Quote.class)) } */),
      @ApiResponse(responseCode = "400", description = "Invalid request"),
      @ApiResponse(responseCode = "209", description = "Quote already present")
  })
  @ResponseStatus(HttpStatus.CREATED)
  public void createQuote(@Valid @RequestBody QuotesDto quote) {
    quotesService.createQuote(quote);
  }

}
