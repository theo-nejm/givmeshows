package com.theo.sprbandevents.repository;

import com.theo.sprbandevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into band_event (band_id, event_id) values(:band_id, :event_id)", nativeQuery = true)
    public void addBandEvent(@Param("band_id") int bandId, @Param("event_id") int eventId);

    @Transactional
    @Modifying
    @Query(value = "delete from band_event where event_id = :event_id", nativeQuery = true)
    public void removeEventFromRelationship(@Param("event_id") int eventId);

    @Transactional
    @Modifying
    @Query(value = "delete from event where id = :event_id", nativeQuery = true)
    public void removeEvent(@Param("event_id") int eventId);
}
