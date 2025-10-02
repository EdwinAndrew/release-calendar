import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MilestoneManagerTest {

    private MilestoneManager manager;
    private Milestone milestone1;
    private Milestone milestone2;
    private Milestone milestone3;

    @BeforeEach
    void setUp() {
        manager = new MilestoneManager();
        milestone1 = new Milestone(1, "Code Freeze", LocalDate.of(2025, 10, 15));
        milestone2 = new Milestone(1, "Deploy", LocalDate.of(2025, 10, 20));
        milestone3 = new Milestone(2, "Testing", LocalDate.of(2025, 11, 5));
    }

    @Test
    @DisplayName("Test adding one milestone")
    void testAddOneMilestone() {
        manager.addMilestone(milestone1);
        assertEquals(1, manager.getMilestones().size());
    }

    @Test
    @DisplayName("Test adding multiple milestones")
    void testAddMultipleMilestones() {
        manager.addMilestone(milestone1);
        manager.addMilestone(milestone2);
        manager.addMilestone(milestone3);
        assertEquals(3, manager.getMilestones().size());
    }

    @Test
    @DisplayName("Test adding null milestone shows error")
    void testAddNullMilestone() {
        manager.addMilestone(null);
        assertEquals(0, manager.getMilestones().size());
    }

    @Test
    @DisplayName("Test getMilestone returns correct milestone by ID")
    void testGetMilestoneById() {
        manager.addMilestone(milestone1);
        Milestone found = manager.getMilestone(milestone1.getId());

        assertNotNull(found);
        assertEquals(milestone1.getId(), found.getId());
        assertEquals("Code Freeze", found.getMilestoneName());
    }

    @Test
    @DisplayName("Test getMilestone with non-existent ID returns null")
    void testGetNonExistentMilestone() {
        Milestone found = manager.getMilestone(9999);
        assertNull(found);
    }

    @Test
    @DisplayName("Test getMilestonesByRelease returns correct milestones")
    void testGetMilestonesByRelease() {
        manager.addMilestone(milestone1);
        manager.addMilestone(milestone2);
        manager.addMilestone(milestone3);

        ArrayList<Milestone> release1Milestones = manager.getMilestonesByRelease(1);
        assertEquals(2, release1Milestones.size());
        assertTrue(release1Milestones.contains(milestone1));
        assertTrue(release1Milestones.contains(milestone2));
        assertFalse(release1Milestones.contains(milestone3));
    }

    @Test
    @DisplayName("Test getMilestonesByRelease with no milestones returns empty list")
    void testGetMilestonesByReleaseEmpty() {
        ArrayList<Milestone> result = manager.getMilestonesByRelease(999);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Test updateMilestoneDate changes the date")
    void testUpdateMilestoneDate() {
        manager.addMilestone(milestone1);
        LocalDate newDate = LocalDate.of(2025, 10, 18);

        manager.updateMilestoneDate(milestone1.getId(), newDate);

        assertEquals(newDate, manager.getMilestone(milestone1.getId()).getKeyDate());
    }

    @Test
    @DisplayName("Test updateMilestoneDate with non-existent ID shows error")
    void testUpdateNonExistentMilestoneDate() {
        assertDoesNotThrow(() ->
                manager.updateMilestoneDate(9999, LocalDate.now())
        );
    }

    @Test
    @DisplayName("Test updateMilestoneName changes the name")
    void testUpdateMilestoneName() {
        manager.addMilestone(milestone1);

        manager.updateMilestoneName(milestone1.getId(), "Production Freeze");

        assertEquals("Production Freeze", manager.getMilestone(milestone1.getId()).getMilestoneName());
    }

    @Test
    @DisplayName("Test updateMilestoneName with non-existent ID shows error")
    void testUpdateNonExistentMilestoneName() {
        assertDoesNotThrow(() ->
                manager.updateMilestoneName(9999, "test")
        );
    }

    @Test
    @DisplayName("Test deleteMilestone removes milestone")
    void testDeleteMilestone() {
        manager.addMilestone(milestone1);
        manager.addMilestone(milestone2);

        manager.deleteMilestone(milestone1.getId());

        assertEquals(1, manager.getMilestones().size());
        assertNull(manager.getMilestone(milestone1.getId()));
    }

    @Test
    @DisplayName("Test deleteMilestone with non-existent ID shows error")
    void testDeleteNonExistentMilestone() {
        manager.addMilestone(milestone1);

        assertDoesNotThrow(() -> manager.deleteMilestone(9999));
        assertEquals(1, manager.getMilestones().size());
    }

    @Test
    @DisplayName("Integration test: filter by release after adding milestones")
    void testIntegrationFilterByRelease() {
        manager.addMilestone(milestone1);
        manager.addMilestone(milestone2);
        manager.addMilestone(milestone3);

        ArrayList<Milestone> release1 = manager.getMilestonesByRelease(1);
        ArrayList<Milestone> release2 = manager.getMilestonesByRelease(2);

        assertEquals(2, release1.size());
        assertEquals(1, release2.size());
    }

    @Test
    @DisplayName("Integration test: add, update date and name, verify changes")
    void testIntegrationUpdateBoth() {
        manager.addMilestone(milestone1);

        LocalDate newDate = LocalDate.of(2025, 10, 22);
        manager.updateMilestoneDate(milestone1.getId(), newDate);
        manager.updateMilestoneName(milestone1.getId(), "Final Freeze");

        Milestone updated = manager.getMilestone(milestone1.getId());
        assertEquals(newDate, updated.getKeyDate());
        assertEquals("Final Freeze", updated.getMilestoneName());
    }
}