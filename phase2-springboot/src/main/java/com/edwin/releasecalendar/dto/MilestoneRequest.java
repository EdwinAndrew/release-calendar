package com.edwin.releasecalendar.dto;

import java.time.LocalDate;

public class MilestoneRequest {
        private Long releaseId;
        private String milestoneName;
        private LocalDate keyDate;

        public MilestoneRequest() {
        }

        public Long getReleaseId() {
            return releaseId;
        }

        public void setReleaseId(Long releaseId) {
            this.releaseId = releaseId;
        }

        public String getMilestoneName() {
            return milestoneName;
        }

        public void setMilestoneName(String milestoneName) {
            this.milestoneName = milestoneName;
        }

        public LocalDate getKeyDate() {
            return keyDate;
        }

        public void setKeyDate(LocalDate keyDate) {
            this.keyDate = keyDate;
        }
}

