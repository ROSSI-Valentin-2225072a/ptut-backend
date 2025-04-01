package jfc.isis.dashboard.dao;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.*;

@Transactional
public interface EventRepository extends JpaRepository<Event, Integer> {

    /*
    @Modifying
    @Query(value = "insert into ", nativeQuery = true)
    Event findByNomEvent(String nomEvent);


     */
}
