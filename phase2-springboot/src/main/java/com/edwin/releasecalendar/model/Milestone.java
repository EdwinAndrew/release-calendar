package com.edwin.releasecalendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    @JsonIgnore
    private Release release;

    @Column(nullable = false, length = 200)
    private String milestoneName;

    @Column(nullable = false)
    private LocalDate keyDate;


    protected Milestone() {
    }


    public Milestone(Release release, String milestoneName, LocalDate keyDate) {
        this.release = release;
        this.keyDate = keyDate;
        this.milestoneName = milestoneName;
    }


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
        return release != null ? release.getId() : null;
    }

    public Release getRelease(){
        return release;
    }


    public void updateKeyDate(LocalDate newDate) {
        this.keyDate = newDate;
    }

    public void updateMilestoneName(String updatedName) {
        this.milestoneName = updatedName;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-30s | %s | Release: %d",
                "#" + id, milestoneName, keyDate, getReleaseId());
    }
}