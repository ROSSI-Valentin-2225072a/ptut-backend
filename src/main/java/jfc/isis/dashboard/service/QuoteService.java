package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.QuoteRepository;
import jfc.isis.dashboard.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteDao;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteDao = quoteRepository;
    }

    @Transactional
    public Quote saveQuote(String quote) {

        if(quote == null || quote.isEmpty()) {
            throw new IllegalArgumentException("Quote cannot be empty");
        }

        var newQuote = new Quote();
        newQuote.setQuote(quote);
        newQuote.setQuoteLastUse(Date.from(java.time.Instant.now()));
        quoteDao.save(newQuote);

        return newQuote;
    }

    @Transactional
    public Quote findQuoteById(Integer quoteId) {
        return quoteDao.findById(quoteId).orElseThrow(() -> new IllegalArgumentException("Quote not found"));
    }

    @Transactional
    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

    @Transactional
    public void deleteQuoteById(Integer quoteId) {
        quoteDao.deleteById(quoteId);
    }

    @Transactional
    public Quote updateQuote(Integer quoteId, String quote) {
        quoteDao.deleteById(quoteId);

        var newQuote = new Quote();
        newQuote.setQuote(quote);
        newQuote.setQuoteLastUse(Date.from(java.time.Instant.now()));

        return quoteDao.save(newQuote);
    }

}
