import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MilestoneTest {

    private Milestone milestone1;
    private Milestone milestone2;

    @BeforeEach
    void setUp() {
        milestone1 = new Milestone(1, "Code Freeze", LocalDate.of(2025, 10, 15));
        milestone2 = new Milestone(1, "Deployment", LocalDate.of(2025, 10, 20));
    }

    @Test
    @DisplayName("Validate milestone is created with correct parameters")
    void testMilestoneCreation() {
        assertAll("Milestone",
                () -> assertEquals("Code Freeze", milestone1.getMilestoneName()),
                () -> assertEquals(LocalDate.of(2025, 10, 15), milestone1.getKeyDate()),
                () -> assertEquals(1, milestone1.getReleaseId())
        );
    }

    @Test
    @DisplayName("Validate ID auto-increments for multiple milestones")
    void testAutoIncrementId() {
        assertTrue(milestone2.getId() > milestone1.getId());
        assertEquals(milestone1.getId() + 1, milestone2.getId());
    }

    @Test
    @DisplayName("Test updateKeyDate changes the date")
    void testUpdateKeyDate() {
        LocalDate newDate = LocalDate.of(2025, 10, 25);
        milestone1.updateKeyDate(newDate);
        assertEquals(newDate, milestone1.getKeyDate());
    }

    @Test
    @DisplayName("Test updateMilestoneName changes the name")
    void testUpdateMilestoneName() {
        milestone1.updateMilestoneName("Production Freeze");
        assertEquals("Production Freeze", milestone1.getMilestoneName());
    }

    @Test
    @DisplayName("Test getId returns correct value")
    void testGetId() {
        assertNotNull(milestone1.getId());
        assertTrue(milestone1.getId() > 0);
    }

    @Test
    @DisplayName("Test getReleaseId returns correct release ID")
    void testGetReleaseId() {
        assertEquals(1, milestone1.getReleaseId());
    }

    @Test
    @DisplayName("Test toString contains milestone name")
    void testToStringContainsName() {
        String result = milestone1.toString();
        assertTrue(result.contains("Code Freeze"));
    }

    @Test
    @DisplayName("Test toString contains key date")
    void testToStringContainsDate() {
        String result = milestone1.toString();
        assertTrue(result.contains("2025-10-15"));
    }

    @Test
    @DisplayName("Test toString contains release ID")
    void testToStringContainsReleaseId() {
        String result = milestone1.toString();
        assertTrue(result.contains("1"));
    }
}