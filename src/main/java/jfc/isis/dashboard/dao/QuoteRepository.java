package jfc.isis.dashboard.dao;

import jfc.isis.dashboard.entity.Quote;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    /*
    @Query("""
        SELECT q.quoteId, q.quote, q.quoteLastUse
        FROM Quote q
        WHERE q.quoteId = :quoteId
    """)
    Quote findById(int quoteId);
     */

    @Query("""
        SELECT q.quoteId, q.quote, q.quoteLastUse
        FROM Quote q
        WHERE q.quote = :quote
    """)
    Quote findByQuote(String quote);

    @Query("""
        SELECT q.quoteId, q.quote, q.quoteLastUse
        FROM Quote q
        ORDER BY q.quoteLastUse DESC
    """)
    @NonNull
    List<Quote> findAll();

}
