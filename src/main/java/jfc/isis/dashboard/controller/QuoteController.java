package jfc.isis.dashboard.controller;

import jakarta.validation.constraints.Positive;
import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.QuoteDTO;
import jfc.isis.dashboard.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard/quote")
public class QuoteController {

    private final QuoteService quoteService;
    private final ModelMapper mapper;

    public QuoteController(QuoteService quoteService, ModelMapper mapper) {
        this.quoteService = quoteService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveQuote(
            @RequestBody String quote) {
        try {
            var result = quoteService.saveQuote(quote);
            var body = mapper.map(result, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("id={quoteId}")
    public ResponseEntity<?> getQuoteById(
            @PathVariable @Positive Integer quoteId) {
        try {
            var result = quoteService.findQuoteById(quoteId);
            var body = mapper.map(result, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllQuotes() {
        try {
            var result = quoteService.findAllQuotes();
            var body = result.stream()
                    .map(d -> mapper.map(d, QuoteDTO.class))
                    .toList();
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @DeleteMapping("id={quoteId}")
    public ResponseEntity<?> deleteQuoteById(
            @PathVariable @Positive Integer quoteId) {
        try {
            quoteService.deleteQuoteById(quoteId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @PutMapping("id={quoteId}")
    public ResponseEntity<?> updateQuote(
            @PathVariable @Positive Integer quoteId,
            @RequestBody String quote) {
        try {
            var result = quoteService.updateQuote(quoteId, quote);
            var body = mapper.map(result, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

}
