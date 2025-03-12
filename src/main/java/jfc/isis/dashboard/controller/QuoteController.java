package jfc.isis.dashboard.controller;

import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.QuoteDTO;
import jfc.isis.dashboard.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
            @RequestParam String quote) {
        try {
            var dashboard = quoteService.saveQuote(quote);
            var body = mapper.map(dashboard, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("id={quoteId}")
    public ResponseEntity<?> getQuoteById(
            @PathVariable Integer quoteId) {
        try {
            var dashboard = quoteService.findQuoteById(quoteId);
            var body = mapper.map(dashboard, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/quote={quote}")
    public ResponseEntity<?> getQuoteByQuote(
            @PathVariable String quote) {
        try {
            var dashboard = quoteService.findQuoteByQuote(quote);
            var body = mapper.map(dashboard, QuoteDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllQuotes() {
        try {
            var dashboard = quoteService.findAllQuotes();
            var body = dashboard.stream()
                    .map(d -> mapper.map(d, QuoteDTO.class))
                    .toList();
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

}
