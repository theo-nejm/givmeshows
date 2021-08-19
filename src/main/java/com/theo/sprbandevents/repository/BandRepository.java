package com.theo.sprbandevents.repository;

import com.theo.sprbandevents.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from band where id = :band_id", nativeQuery = true)
    public void removeBand(@Param("band_id") int bandId);

    @Transactional
    @Modifying
    @Query(value = "delete from band_event where band_id = :band_id", nativeQuery = true)
    public void removeFromRelated(@Param("band_id") int bandId);
}
