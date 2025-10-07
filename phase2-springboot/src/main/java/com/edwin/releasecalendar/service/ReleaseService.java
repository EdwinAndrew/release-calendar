package com.edwin.releasecalendar.service;

import com.edwin.releasecalendar.model.Release;
import com.edwin.releasecalendar.repository.ReleaseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final MilestoneService milestoneService;
    public ReleaseService(ReleaseRepository releaseRepository, MilestoneService milestoneService) {
        this.releaseRepository = releaseRepository;
        this.milestoneService = milestoneService;
    }

    public List<Release> getAllReleases() {
        return releaseRepository.findAll();
    }

    public Optional<Release> getReleaseById(Long id){
        return releaseRepository.findById(id);
    }

    @Transactional
    public Release createRelease(Release release){
        validateRelease(release);

        return releaseRepository.save(release);
    }

    @Transactional
    public Release updateRelease(Long id, Release updatedRelease){
        Release existingRelease = releaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Release not found with id: " + id));
        validateRelease(updatedRelease);
        existingRelease.setReleaseWindow(updatedRelease.getReleaseWindow());
        existingRelease.setStartDate(updatedRelease.getStartDate());
        existingRelease.setEndDate(updatedRelease.getEndDate());
        return releaseRepository.save(existingRelease);

    }

    @Transactional
    public void deleteRelease(Long id) {
        if (!releaseRepository.existsById(id)) {
            throw new RuntimeException("Release not found with id: " + id);
        }


        milestoneService.deleteMilestonesByReleaseId(id);
        releaseRepository.deleteById(id);

    }

    private void validateRelease(Release release) {
        if (release.getReleaseWindow() == null || release.getReleaseWindow().trim().isEmpty()) {
            throw new IllegalArgumentException("Release window cannot be empty");
        }

        if (release.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (release.getEndDate() == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }

        if (release.getStartDate().isAfter(release.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }
    }

}
