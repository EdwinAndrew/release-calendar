package com.edwin.releasecalendar.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="releases")
@JsonPropertyOrder({"id", "releaseWindow", "startDate", "endDate"})
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String releaseWindow;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReleaseStatus status = ReleaseStatus.PLANNED;

    @OneToMany(
            mappedBy ="release",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Milestone> milestones = new ArrayList<>();

    protected Release(){

    }

    public Release(String releaseWindow, LocalDate startDate, LocalDate endDate){
        // Validate inputs before assignment
        if (releaseWindow == null || releaseWindow.trim().isEmpty()) {
            throw new IllegalArgumentException("Release window cannot be null or empty!");
        }

        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null!");
        }

        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null!");
        }

        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Start date must be before or equal to end date!");
        }

        this.releaseWindow = releaseWindow;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Milestone> getMilestones(){
        return milestones;
    }

    public Long getId(){
        return id;
    }

    public String getReleaseWindow(){
        return releaseWindow;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setReleaseWindow(String newName){
        this.releaseWindow = newName;
    }

    public void setStartDate(LocalDate newStart){
        this.startDate = newStart;
    }

    public void setEndDate(LocalDate newEnd){
        this.endDate = newEnd;
    }

    public void setStatus(ReleaseStatus status){
        this.status = status;
    }

    public ReleaseStatus getStatus(){
        return status;
    }


    public String toString(){
        return String.format("Release #%-3d | %-10s | %s to %s",
                id, releaseWindow, startDate, endDate);
    }

}
