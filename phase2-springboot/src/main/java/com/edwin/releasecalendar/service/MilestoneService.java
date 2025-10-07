package com.edwin.releasecalendar.service;

import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.model.Release;
import com.edwin.releasecalendar.repository.MilestoneRepository;
import com.edwin.releasecalendar.repository.ReleaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ReleaseRepository releaseRepository;

    public MilestoneService(MilestoneRepository milestoneRepository,
                            ReleaseRepository releaseRepository) {
        this.milestoneRepository = milestoneRepository;
        this.releaseRepository = releaseRepository;
    }


    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }


    public List<Milestone> getMilestonesByReleaseId(Long releaseId) {
        Release release = releaseRepository.findById(releaseId).
                orElseThrow(()-> new RuntimeException("Release not found with id: " + releaseId));
        return milestoneRepository.findByRelease(release);
    }


    public Optional<Milestone> getMilestoneById(Long id) {
        return milestoneRepository.findById(id);
    }


    @Transactional
    public Milestone createMilestone(Long releaseId, String milestoneName, LocalDate keyDate) {
        Release release = releaseRepository.findById(releaseId).
                orElseThrow(() -> new IllegalArgumentException("Release was not found with id: " + releaseId));

        Milestone milestone = new Milestone(release,milestoneName,keyDate);

        validateMilestone(milestone);

        return milestoneRepository.save(milestone);
    }


    @Transactional
    public Milestone updateMilestone(Long id, Milestone updatedMilestone) {

        Milestone existingMilestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found with id: " + id));


        validateMilestone(updatedMilestone);

        existingMilestone.updateMilestoneName(updatedMilestone.getMilestoneName());
        existingMilestone.updateKeyDate(updatedMilestone.getKeyDate());

        return milestoneRepository.save(existingMilestone);
    }


    @Transactional
    public void deleteMilestone(Long id) {
        if (!milestoneRepository.existsById(id)) {
            throw new RuntimeException("Milestone not found with id: " + id);
        }

        milestoneRepository.deleteById(id);
    }


    @Transactional
    public void deleteMilestonesByReleaseId(Long releaseId) {
        Release release = releaseRepository.findById(releaseId).
                orElseThrow( () -> new RuntimeException("Release not found with id: " + releaseId) );
        List<Milestone> milestones = milestoneRepository.findByRelease(release);
        milestoneRepository.deleteAll(milestones);
    }

    private void validateMilestone(Milestone milestone) {
        if (milestone.getMilestoneName() == null || milestone.getMilestoneName().trim().isEmpty()) {
            throw new IllegalArgumentException("Milestone name cannot be empty");
        }

        if (milestone.getKeyDate() == null) {
            throw new IllegalArgumentException("Key date cannot be null");
        }
    }
}