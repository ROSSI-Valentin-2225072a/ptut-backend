package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.QuoteRepository;
import jfc.isis.dashboard.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteDao;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteDao = quoteRepository;
    }

    @Transactional
    public Quote saveQuote(String quote) {
        var quoteExists = quoteDao.findByQuote(quote);

        if(quote == null || quote.isEmpty()) {
            throw new IllegalArgumentException("Quote cannot be empty");
        }
        if(quoteExists != null) {
            throw new IllegalArgumentException("Quote already exists");
        }

        var newQuote = new Quote();
        newQuote.setQuote(quote);
        quoteDao.save(newQuote);

        return newQuote;
    }

    @Transactional
    public Quote findQuoteById(Integer quoteId) {
        return quoteDao.findById(quoteId).orElseThrow(() -> new IllegalArgumentException("Quote not found"));
    }

    @Transactional
    public Quote findQuoteByQuote(String quote) {
        return quoteDao.findByQuote(quote);
    }

    @Transactional
    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

}
