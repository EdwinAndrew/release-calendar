package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.dto.MilestoneRequest;
import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.service.MilestoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService){
        this.milestoneService = milestoneService;
    }
    
    @GetMapping()
    public List<Milestone> getAllMilestones(){
        return milestoneService.getAllMilestones();
    }

    @GetMapping("/release/{releaseId}")
    public ResponseEntity <List<Milestone>> getMilestoneByReleaseId(@PathVariable("releaseId") Long releaseId){
        try {
            List<Milestone> milestoneList = milestoneService.getMilestonesByReleaseId(releaseId);
            return ResponseEntity.ok().body(milestoneList);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping()
    public ResponseEntity<Milestone> createNewMilestone(@RequestBody MilestoneRequest request) {
        try {
            Milestone newMilestone = milestoneService.createMilestone(request.getReleaseId(), request.getMilestoneName(), request.getKeyDate());
            return ResponseEntity.status(201).body(newMilestone);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable("id") Long id, @RequestBody Milestone updatedMilestone) {
        try {
            Milestone saved = milestoneService.updateMilestone(id, updatedMilestone);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            // ✅ Validation error → 400
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            // ✅ Not found → 404
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestoneById(@PathVariable("id") Long milestoneId){
        try{
            milestoneService.deleteMilestone(milestoneId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
