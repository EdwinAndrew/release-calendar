package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.manager.MilestoneManager;
import com.edwin.releasecalendar.manager.ReleaseManager;
import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.model.Release;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/releases")
public class ReleaseController {

    private final ReleaseManager releaseManager;
    private final MilestoneManager milestoneManager;

    public ReleaseController(ReleaseManager releaseManager, MilestoneManager milestoneManager){
        this.releaseManager = releaseManager;
        this.milestoneManager = milestoneManager;
    }

    @GetMapping
    public ArrayList<Release> getAllReleases(){
        return releaseManager.getAllReleases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> getSingleReleaseById(@PathVariable("id") Long releaseId){
        Release release = releaseManager.getRelease(releaseId);
        if (release == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(release);
    }

    @PostMapping()
    public void createRelease(@RequestBody Release newRelease){
        releaseManager.addRelease(newRelease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReleaseById(@PathVariable("id") Long id, @RequestBody Release updatedRelease){
        if (releaseManager.getRelease(id) == null){
             return ResponseEntity.badRequest().build();
        }
        releaseManager.updateRelease(id,
                                    updatedRelease.getReleaseWindow(),
                                    updatedRelease.getStartDate(),
                                    updatedRelease.getEndDate());
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(@PathVariable("id") Long releaseId){
        Release release = releaseManager.getRelease(releaseId);
        if (release == null){
            return ResponseEntity.notFound().build();
        }

        ArrayList<Milestone> milestones = milestoneManager.getMilestonesByRelease(releaseId);
        for (Milestone m: milestones){
            milestoneManager.deleteMilestone(m.getId());
        }
        releaseManager.deleteRelease(releaseId);
        return ResponseEntity.noContent().build();
    }




}
