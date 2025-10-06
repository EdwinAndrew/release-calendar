package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.model.Release;
import com.edwin.releasecalendar.repository.ReleaseRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/releases")
public class ReleaseController {

    private final ReleaseRepository releaseRepository;


    public ReleaseController(ReleaseRepository releaseRepository){
        this.releaseRepository = releaseRepository;
    }

    @GetMapping
    public List<Release> getAllReleases(){
        return releaseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> getSingleReleaseById(@PathVariable("id") Long releaseId){
        Optional<Release> release = releaseRepository.findById(releaseId);
        if (release == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(release.get());
    }

    @PostMapping()
    public ResponseEntity<Release> createRelease(@RequestBody Release newRelease){
        Release savedRelease = releaseRepository.save(newRelease);
        return ResponseEntity.status(201).body(savedRelease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Release> updateReleaseById(@PathVariable("id") Long id, @RequestBody Release updatedRelease){
        Optional<Release> existingRelease = releaseRepository.findById(id);

        if (existingRelease.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Release release = existingRelease.get();
        release.setReleaseWindow(updatedRelease.getReleaseWindow());
        release.setStartDate(updatedRelease.getStartDate());
        release.setEndDate(updatedRelease.getEndDate());

        Release saved = releaseRepository.save(release);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(@PathVariable("id") Long releaseId){
        if (!releaseRepository.existsById(releaseId)) {
            return ResponseEntity.notFound().build();
        }

        releaseRepository.deleteById(releaseId);
        return ResponseEntity.noContent().build();
    }




}
