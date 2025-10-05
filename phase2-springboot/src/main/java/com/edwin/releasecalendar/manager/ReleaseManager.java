package com.edwin.releasecalendar.manager;

import com.edwin.releasecalendar.model.Release;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class ReleaseManager {

    private ArrayList<Release> releases;

    public ReleaseManager(){
        this.releases = new ArrayList<>();
    }

    public void addRelease(Release r){
        releases.add(r);
    }

    public Release getRelease(Long id){
        for (Release release: releases){
            if (release.getId() == id){
                return release;
            }
        }
        return null;
    }

    public ArrayList<Release> getAllReleases(){
        return releases;
    }

    public void updateRelease(Long id, String newName, LocalDate newStart, LocalDate newEnd){
        Release targetRelease =  getRelease(id);
        if (targetRelease != null){
            targetRelease.setReleaseWindow(newName);
            targetRelease.setStartDate(newStart);
            targetRelease.setEndDate(newEnd);
        } else{
            System.out.println("Error: Release with ID " + id + " not found.");
        }

    }

    public void deleteRelease(Long id){
        Release targetRelease = getRelease(id);
        if (targetRelease != null){
            releases.remove(targetRelease);
        }else {
            System.out.println("Error: Release with ID " + id + " not found.");
        }

    }
}
