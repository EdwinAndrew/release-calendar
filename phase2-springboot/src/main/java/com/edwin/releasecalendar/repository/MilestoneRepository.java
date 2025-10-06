package com.edwin.releasecalendar.repository;

import com.edwin.releasecalendar.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findByReleaseId(Long releaseId);

}
