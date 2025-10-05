package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.manager.MilestoneManager;
import com.edwin.releasecalendar.model.Milestone;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {
    private final MilestoneManager milestoneManager;
    
    public MilestoneController(MilestoneManager milestoneManager){
        this.milestoneManager = milestoneManager;
    }
    
    @GetMapping()
    public ArrayList<Milestone> getAllMilestones(){
        return milestoneManager.getMilestones();
    }

    @GetMapping("/{releaseId}")
    public ArrayList<Milestone> getMilestoneByReleaseId(@PathVariable("releaseId") int id){
        return milestoneManager.getMilestonesByRelease(id);
    }

    @PostMapping()
    public void createNewMilestone(@RequestBody Milestone milestone){
        milestoneManager.addMilestone(milestone);
    }

    @PutMapping("/{id}/date")
    public void updateMilestoneDate(@PathVariable("id") int id, @RequestBody LocalDate newDate){
        milestoneManager.updateMilestoneDate(id, newDate);
    }

    @PutMapping("/{id}/name")
    public void updateMilestoneName(@PathVariable("id") int id, @RequestBody String newName){
        milestoneManager.updateMilestoneName(id, newName);
    }

    @PutMapping("/{id}")
    public void updateMilestone(@PathVariable("id") int id, @RequestBody Milestone updatedMilestone){
        milestoneManager.updateMilestoneDate(id, updatedMilestone.getKeyDate());
        milestoneManager.updateMilestoneName(id, updatedMilestone.getMilestoneName());
    }

    @DeleteMapping("/{id}")
    public void deleteMilestoneById(@PathVariable("id") int milestoneId){
        milestoneManager.deleteMilestone(milestoneId);
    }

}
