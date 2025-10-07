package com.edwin.releasecalendar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseTest {

    private Release testRelease;

    @BeforeEach  // ✅ Runs before EACH test
    void setUp() {
        testRelease = new Release("R25.5.1",  // ✅ No "Release" keyword (assign to field)
                LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 21));
    }

    @Test
    @DisplayName("Validate that a Release is successfully created with the input parameters")
    void shouldCreateReleaseWithValidParameters() {
        assertAll("Release",
                () -> assertEquals("R25.5.1", testRelease.getReleaseWindow()),  // ✅ Matches actual data
                () -> assertEquals(LocalDate.of(2025, 10, 20), testRelease.getStartDate()),  // ✅ Correct date
                () -> assertEquals(LocalDate.of(2025, 10, 21), testRelease.getEndDate()),  // ✅ Correct date
                () -> assertNull(testRelease.getId())  // ✅ ID is null in unit tests (no database)
        );
    }

    @Test
    @DisplayName("Should throw exception when release window is null")
    void shouldThrowExceptionWhenReleaseWindowIsNull() {
       assertThrows(IllegalArgumentException.class, () -> {
           new Release(
                   null,
                   LocalDate.of(2025, 10, 20),
                   LocalDate.of(2025, 10, 21));
       });
    }

    @Test
    @DisplayName("Should throw exception when start date is after end date")
    void shouldThrowExceptionWhenStartDateAfterEndDate() {
        assertThrows(IllegalArgumentException.class, () ->  {
            new Release(
                    "R25.6.1",
                    LocalDate.of(2025, 10, 20),
                    LocalDate.of(2025, 10, 19));
        });
    }

    @Test
    @DisplayName("Should accept equal start and end dates")
    void shouldAcceptEqualStartAndEndDates() {
        Release startDateEqualEndDate = new Release("R25.6.1", LocalDate.of(2025, 10, 20), LocalDate.of(2025, 10, 20));
        assertInstanceOf(Release.class,startDateEqualEndDate );

    }

    @Test
    @DisplayName("Should throw an exception when the release window is empty")
    void shouldThrowExceptionWhenReleaseWindowIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Release(
                    "",
                    LocalDate.of(2025, 10, 20),
                    LocalDate.of(2025, 10, 21));
        });
    }

    @Test
    @DisplayName("Show throw an exception when the release window is spaces")
    void shouldThrowExceptionWhenReleaseWindowIsWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Release(
                    "  ",
                    LocalDate.of(2025, 10, 20),
                    LocalDate.of(2025, 10, 21));
        });
    }

    @Test
    @DisplayName("Should throw an exception when the start date is null")
    void shouldThrowExceptionWhenStartDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Release(
                    "R25.6.1",
                   null,
                    LocalDate.of(2025, 10, 21));
        });
    }

    @Test
    @DisplayName("Should throw an exception when the end date is null")
    void shouldThrowExceptionWhenEndDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Release(
                    "R25.6.1",
                    LocalDate.of(2025, 10, 21),
                    null);
        });
    }

    @Test
    @DisplayName("Should update the release window with the setter")
    void shouldUpdateReleaseWindowWithSetter() {
        testRelease.setReleaseWindow("R25.6.0");
        assertEquals("R25.6.0", testRelease.getReleaseWindow());
    }

    @Test
    @DisplayName("Should update the start date with the setter")
    void shouldUpdateStartDateWithSetter() {
        LocalDate newStart = LocalDate.of(2025, 11, 1);
        testRelease.setStartDate(newStart);
        assertEquals(newStart, testRelease.getStartDate());
    }

    @Test
    @DisplayName("Should update the end date with the setter")
    void shouldUpdateEndDateWithSetter() {
        LocalDate newEnd = LocalDate.of(2025, 11, 30);
        testRelease.setEndDate(newEnd);
        assertEquals(newEnd, testRelease.getEndDate());
    }

    @Test
    @DisplayName("Should return empty list for milestones initially")
    void shouldReturnEmptyMilestonesListInitially() {
        assertNotNull(testRelease.getMilestones());
        assertTrue(testRelease.getMilestones().isEmpty());
        assertEquals(0, testRelease.getMilestones().size());
    }

    @Test
    @DisplayName("Should return release window in the toString method")
    void shouldIncludeReleaseWindowInToString() {
        String result = testRelease.toString();
        assertTrue(result.contains("R25.5.1"));
    }

    @Test
    @DisplayName("Should include dates in the toString method")
    void shouldIncludeDatesInToString() {
        String result = testRelease.toString();
        assertTrue(result.contains("2025-10-20"));
        assertTrue(result.contains("2025-10-21"));
    }


}