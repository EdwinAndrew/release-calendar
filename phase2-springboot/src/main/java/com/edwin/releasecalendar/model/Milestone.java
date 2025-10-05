package com.edwin.releasecalendar.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "milestones")
@JsonPropertyOrder({"id", "releaseId", "milestoneName", "keyDate"})
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long releaseId;

    @Column(nullable = false, length = 200)
    private String milestoneName;

    @Column(nullable = false)
    private LocalDate keyDate;

    // Default constructor (required by JPA)
    protected Milestone() {
    }

    // Parameterized constructor
    public Milestone(Long releaseId, String milestoneName, LocalDate keyDate) {
        this.releaseId = releaseId;
        this.keyDate = keyDate;
        this.milestoneName = milestoneName;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDate getKeyDate() {
        return keyDate;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    // Update methods
    public void updateKeyDate(LocalDate newDate) {
        this.keyDate = newDate;
    }

    public void updateMilestoneName(String updatedName) {
        this.milestoneName = updatedName;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-30s | %s | Release: %d",
                "#" + id, milestoneName, keyDate, releaseId);
    }
}