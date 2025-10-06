package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.model.Milestone;
import com.edwin.releasecalendar.model.Release;
import com.edwin.releasecalendar.repository.MilestoneRepository;
import com.edwin.releasecalendar.repository.ReleaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    private final MilestoneRepository milestoneRepository;
    private final ReleaseRepository releaseRepository;

    public MilestoneController(MilestoneRepository milestoneRepository, ReleaseRepository releaseRepository){
        this.milestoneRepository = milestoneRepository;
        this.releaseRepository = releaseRepository;
    }
    
    @GetMapping()
    public List<Milestone> getAllMilestones(){
        return milestoneRepository.findAll();
    }

    @GetMapping("/release/{releaseId}")
    public ResponseEntity <List<Milestone>> getMilestoneByReleaseId(@PathVariable("releaseId") Long releaseId){
        Optional<Release> r = releaseRepository.findById(releaseId);
        if (r.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Milestone> milestoneList = milestoneRepository.findByReleaseId(releaseId);
        return ResponseEntity.ok().body(milestoneList);
    }

    @PostMapping()
    public ResponseEntity<Milestone> createNewMilestone(@RequestBody Milestone milestone){
        Optional<Release> r = releaseRepository.findById(milestone.getReleaseId());
        if (r.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Milestone newMilestone = milestoneRepository.save(milestone);
        return ResponseEntity.status(201).body(newMilestone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable("id") Long id, @RequestBody Milestone updatedMilestone){
        Optional<Milestone> existingMilestone = milestoneRepository.findById(id);
        if (existingMilestone.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Milestone milestone = existingMilestone.get();
        milestone.updateMilestoneName(updatedMilestone.getMilestoneName());
        milestone.updateKeyDate(updatedMilestone.getKeyDate());
        Milestone saved = milestoneRepository.save(milestone);
        return ResponseEntity.ok(saved);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestoneById(@PathVariable("id") Long milestoneId){
        Optional<Milestone> existingMilestone = milestoneRepository.findById(milestoneId);
        if (existingMilestone.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        milestoneRepository.deleteById(milestoneId);
        return ResponseEntity.noContent().build();
    }

}
