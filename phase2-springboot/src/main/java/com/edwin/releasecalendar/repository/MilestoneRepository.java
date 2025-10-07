package com.edwin.releasecalendar.repository;

import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findByRelease(Release release);

}
