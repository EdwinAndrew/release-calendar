package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.model.Release;
import com.edwin.releasecalendar.service.ReleaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping
    public List<Release> getAllReleases() {
        return releaseService.getAllReleases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> getSingleReleaseById(@PathVariable("id") Long releaseId) {
        Optional<Release> release = releaseService.getReleaseById(releaseId);

        if (release.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(release.get());
    }

    @PostMapping()
    public ResponseEntity<Release> createRelease(@RequestBody Release newRelease) {
        try {
            Release savedRelease = releaseService.createRelease(newRelease);
            return ResponseEntity.status(201).body(savedRelease);
        } catch (IllegalArgumentException e) {
            // Validation error from service
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Release> updateReleaseById(@PathVariable("id") Long id,
                                                     @RequestBody Release updatedRelease) {
        try {
            Release saved = releaseService.updateRelease(id, updatedRelease);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            // ✅ Validation error → 400 Bad Request
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            // ✅ Not found → 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(@PathVariable("id") Long releaseId) {
        try {
            releaseService.deleteRelease(releaseId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Release not found
            return ResponseEntity.notFound().build();
        }
    }
}