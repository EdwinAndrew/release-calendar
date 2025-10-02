import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReleaseManagerTest {

    private ReleaseManager manager;
    private Release release1;
    private Release release2;

    @BeforeEach
    void setUp() {
        manager = new ReleaseManager();
        release1 = new Release("v1.0", LocalDate.now(), LocalDate.now().plusDays(30));
        release2 = new Release("v2.0", LocalDate.now().plusDays(35), LocalDate.now().plusDays(60));
    }

    @Test
    @DisplayName("Test adding one release increases list size")
    void testAddOneRelease() {
        manager.addRelease(release1);
        assertEquals(1, manager.getAllReleases().size());
    }

    @Test
    @DisplayName("Test adding multiple releases")
    void testAddMultipleReleases() {
        manager.addRelease(release1);
        manager.addRelease(release2);
        assertEquals(2, manager.getAllReleases().size());
    }

    @Test
    @DisplayName("Test getRelease returns correct release by ID")
    void testGetReleaseById() {
        manager.addRelease(release1);
        Release found = manager.getRelease(release1.getId());

        assertNotNull(found);
        assertEquals(release1.getId(), found.getId());
        assertEquals(release1.getReleaseWindow(), found.getReleaseWindow());
    }

    @Test
    @DisplayName("Test getRelease with non-existent ID returns null")
    void testGetNonExistentRelease() {
        Release found = manager.getRelease(9999);
        assertNull(found);
    }

    @Test
    @DisplayName("Test getAllReleases on empty manager")
    void testGetAllReleasesEmpty() {
        assertEquals(0, manager.getAllReleases().size());
    }

    @Test
    @DisplayName("Test getAllReleases returns all releases")
    void testGetAllReleases() {
        manager.addRelease(release1);
        manager.addRelease(release2);

        assertEquals(2, manager.getAllReleases().size());
        assertTrue(manager.getAllReleases().contains(release1));
        assertTrue(manager.getAllReleases().contains(release2));
    }

    @Test
    @DisplayName("Test updateRelease changes release properties")
    void testUpdateRelease() {
        manager.addRelease(release1);
        LocalDate newStart = LocalDate.now().plusDays(5);
        LocalDate newEnd = LocalDate.now().plusDays(25);

        manager.updateRelease(release1.getId(), "v1.1", newStart, newEnd);

        Release updated = manager.getRelease(release1.getId());
        assertEquals("v1.1", updated.getReleaseWindow());
        assertEquals(newStart, updated.getStartDate());
        assertEquals(newEnd, updated.getEndDate());
    }

    @Test
    @DisplayName("Test updateRelease with non-existent ID shows error")
    void testUpdateNonExistentRelease() {
        // Should print error message but not crash
        assertDoesNotThrow(() ->
                manager.updateRelease(9999, "test", LocalDate.now(), LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("Test deleteRelease removes release")
    void testDeleteRelease() {
        manager.addRelease(release1);
        manager.addRelease(release2);

        manager.deleteRelease(release1.getId());

        assertEquals(1, manager.getAllReleases().size());
        assertNull(manager.getRelease(release1.getId()));
    }

    @Test
    @DisplayName("Test deleteRelease with non-existent ID shows error")
    void testDeleteNonExistentRelease() {
        manager.addRelease(release1);

        assertDoesNotThrow(() -> manager.deleteRelease(9999));
        assertEquals(1, manager.getAllReleases().size());
    }

    @Test
    @DisplayName("Test deleting all releases leaves empty list")
    void testDeleteAllReleases() {
        manager.addRelease(release1);
        manager.addRelease(release2);

        manager.deleteRelease(release1.getId());
        manager.deleteRelease(release2.getId());

        assertEquals(0, manager.getAllReleases().size());
    }

    @Test
    @DisplayName("Integration test: add, update, then delete")
    void testIntegration() {
        manager.addRelease(release1);
        assertEquals(1, manager.getAllReleases().size());

        manager.updateRelease(release1.getId(), "v1.5", LocalDate.now(), LocalDate.now().plusDays(20));
        assertEquals("v1.5", manager.getRelease(release1.getId()).getReleaseWindow());

        manager.deleteRelease(release1.getId());
        assertEquals(0, manager.getAllReleases().size());
    }
}