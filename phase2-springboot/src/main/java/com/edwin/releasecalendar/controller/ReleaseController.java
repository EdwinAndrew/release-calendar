package com.edwin.releasecalendar.controller;

import com.edwin.releasecalendar.manager.ReleaseManager;
import com.edwin.releasecalendar.model.Release;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/releases")
public class ReleaseController {

    private final ReleaseManager releaseManager;

    public ReleaseController(ReleaseManager releaseManager){
        this.releaseManager = releaseManager;
    }

    @GetMapping
    public ArrayList<Release> getAllReleases(){
        return releaseManager.getAllReleases();
    }

    @GetMapping("/{id}")
    public Release getSingleReleaseById(@PathVariable("id") int releaseId){
        return releaseManager.getRelease(releaseId);
    }

    @PostMapping()
    public void createRelease(@RequestBody Release newRelease){
        releaseManager.addRelease(newRelease);
    }

    @PutMapping("/{id}")
    public void updateReleaseById(@PathVariable("id") int id, @RequestBody Release updatedRelease){
        releaseManager.updateRelease(id,
                                    updatedRelease.getReleaseWindow(),
                                    updatedRelease.getStartDate(),
                                    updatedRelease.getEndDate());
    }

    @DeleteMapping("/{id}")
    public void deleteRelease(@PathVariable("id") int releaseId){
        releaseManager.deleteRelease(releaseId);
    }




}
