package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.manager.MilestoneManager;
import com.edwin.releasecalendar.manager.ReleaseManager;
import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.model.Release;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {
    private final MilestoneManager milestoneManager;
    private final ReleaseManager releaseManager;
    
    public MilestoneController(MilestoneManager milestoneManager, ReleaseManager releaseManager){
        this.milestoneManager = milestoneManager;
        this.releaseManager = releaseManager;
    }
    
    @GetMapping()
    public ArrayList<Milestone> getAllMilestones(){
        return milestoneManager.getMilestones();
    }

    @GetMapping("/{releaseId}")
    public ResponseEntity <ArrayList<Milestone>> getMilestoneByReleaseId(@PathVariable("releaseId") Long id){
        Release r = releaseManager.getRelease(id);
        if (r == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(milestoneManager.getMilestonesByRelease(id));

    }

    @PostMapping()
    public ResponseEntity<Void> createNewMilestone(@RequestBody Milestone milestone){
        Release r = releaseManager.getRelease(milestone.getReleaseId());
        if (r == null){
            return ResponseEntity.badRequest().build();
        }
        milestoneManager.addMilestone(milestone);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}/date")
    public ResponseEntity<Void> updateMilestoneDate(@PathVariable("id") Long id, @RequestBody LocalDate newDate){
        Milestone m = milestoneManager.getMilestone(id);
        if (m == null){
            return ResponseEntity.notFound().build();
        }
        milestoneManager.updateMilestoneDate(id, newDate);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> updateMilestoneName(@PathVariable("id") Long id, @RequestBody String newName){
        Milestone m = milestoneManager.getMilestone(id);
        if (m == null){
            return ResponseEntity.notFound().build();
        }
        milestoneManager.updateMilestoneName(id, newName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMilestone(@PathVariable("id") Long id, @RequestBody Milestone updatedMilestone){
        Milestone m = milestoneManager.getMilestone(id);
        if (m == null){
            return ResponseEntity.notFound().build();
        }
        milestoneManager.updateMilestoneDate(id, updatedMilestone.getKeyDate());
        milestoneManager.updateMilestoneName(id, updatedMilestone.getMilestoneName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestoneById(@PathVariable("id") Long milestoneId){
        Milestone m = milestoneManager.getMilestone(milestoneId);
        if (m == null){
            return ResponseEntity.notFound().build();
        }
        milestoneManager.deleteMilestone(milestoneId);
        return ResponseEntity.noContent().build();
    }

}
