package com.edwin.releasecalendar.repository;

import com.edwin.releasecalendar.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    List<Release> findByReleaseWindow(String releaseWindow);
    List<Release> findByStartDateBetween(LocalDate start, LocalDate end);

}
