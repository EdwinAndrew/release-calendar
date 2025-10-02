import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReleaseTest {

    private Release testRelease1;
    private Release testRelease2;
    private Release testRelease3;

    @BeforeEach
    void setUp(){
        testRelease1 = new Release("R25.1.0", LocalDate.of(2025,10,1), LocalDate.of(2025,10,10));
        testRelease2 = new Release("R25.2.0", LocalDate.of(2025,10,10), LocalDate.of(2025,10,20));
        testRelease3 = new Release("R25.3.0",LocalDate.of(2025,10,15), LocalDate.of(2025, 10, 15));
    }

    @Test
    @DisplayName("Validate that a Release is successfully created with the input parameters")
    void setTestRelease(){
        assertAll("Release", () -> assertEquals("R25.1.0",testRelease1.getReleaseWindow()),
                () -> assertEquals(LocalDate.of(2025,10,1), testRelease1.getStartDate()),
                () -> assertEquals(LocalDate.of(2025,10,10), testRelease1.getEndDate()),
                        () -> assertEquals(1,testRelease1.getId())
        );
    }

    @Test
    @DisplayName("Validate that ID auto-increments for multiple releases")
    void testAutoIncrementId(){
        assertTrue((testRelease2.getId() > testRelease1.getId()),"Second release should have higher ID than first");
        assertEquals(testRelease2.getId()-1, testRelease1.getId(),"Release ID increments by 1");
    }

    @Test
    @DisplayName("Validate that if start date is after end date throw exception")
    void testInvalidDates(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Release("R25.3.0",LocalDate.of(2025,10,15),LocalDate.of(2025, 10, 1)) );
        assertEquals("Start date must be before or equal to end date!", exception.getMessage());
    }

    @Test
    @DisplayName("Validate that if start date and end date are equal should generate Release")
    void testEqualDates(){
        assertEquals(LocalDate.of(2025,10,15), testRelease3.getStartDate());
        assertEquals(LocalDate.of(2025,10,15), testRelease3.getEndDate());
        assertEquals(testRelease3.getStartDate(), testRelease3.getEndDate(),
                "Start and end dates should be equal");
    }

    @Test
    @DisplayName("Should throw exception when releaseWindow is null")
    void testNullReleaseWindow(){
        assertThrows(IllegalArgumentException.class,
                () -> new Release(null, LocalDate.now(), LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("Should throw exception when releaseWindow is empty")
    void testEmptyReleaseWindow(){
        assertThrows(IllegalArgumentException.class,
                () -> new Release("", LocalDate.now(), LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("Should throw exception when startDate is null")
    void testNullStartDate(){
        assertThrows(IllegalArgumentException.class,
                () -> new Release("v1.0", null, LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("Should throw exception when endDate is null")
    void testNullEndDate(){
        assertThrows(IllegalArgumentException.class,
                () -> new Release("v1.0", LocalDate.now(), null)
        );
    }

    @Test
    @DisplayName("get ReleaseWindow returns string")
    void testGetReleaseWindow(){
        assertEquals("R25.1.0", testRelease1.getReleaseWindow());
    }


    @Test
    @DisplayName("get startDate returns LocalDate")
    void testGetStartDate(){
        assertEquals(LocalDate.of(2025,10,1), testRelease1.getStartDate());
    }


    @Test
    @DisplayName("get endDate returns LocalDate")
    void testGetEndDate(){
        assertEquals(LocalDate.of(2025,10,10), testRelease1.getEndDate());
    }

    @Test
    @DisplayName("Test ID is read-only - verify no setter exists")
    void testIdIsReadOnly(){
        int originalId = testRelease1.getId();
        assertEquals(originalId, testRelease1.getId());
    }

    @Test
    @DisplayName("Test setReleaseWindow changes the name")
    void testSetReleaseWindow(){
        String originalName = testRelease1.getReleaseWindow();
        testRelease1.setReleaseWindow("R25.1.1");

        assertEquals("R25.1.1", testRelease1.getReleaseWindow());
        assertNotEquals(originalName, testRelease1.getReleaseWindow());
    }

    @Test
    @DisplayName("Test setStartDate changes the start date")
    void testSetStartDate(){
        LocalDate newStartDate = LocalDate.of(2025, 10, 5);
        testRelease1.setStartDate(newStartDate);

        assertEquals(newStartDate, testRelease1.getStartDate());
    }

    @Test
    @DisplayName("Test setEndDate changes the end date")
    void testSetEndDate(){
        LocalDate newEndDate = LocalDate.of(2025, 10, 15);
        testRelease1.setEndDate(newEndDate);

        assertEquals(newEndDate, testRelease1.getEndDate());
    }

    @Test
    @DisplayName("Test toString contains release window")
    void testToStringContainsReleaseWindow(){
        String result = testRelease1.toString();
        assertTrue(result.contains("R25.1.0"), "toString should contain release window");
    }

    @Test
    @DisplayName("Test toString contains start date")
    void testToStringContainsStartDate(){
        String result = testRelease1.toString();
        assertTrue(result.contains("2025-10-01"), "toString should contain start date");
    }

    @Test
    @DisplayName("Test toString contains end date")
    void testToStringContainsEndDate(){
        String result = testRelease1.toString();
        assertTrue(result.contains("2025-10-10"), "toString should contain end date");
    }

    @Test
    @DisplayName("Test toString contains ID")
    void testToStringContainsId(){
        String result = testRelease1.toString();
        assertTrue(result.contains(String.valueOf(testRelease1.getId())), "toString should contain ID");
    }

    @Test
    @DisplayName("Test with dates far in the past")
    void testOldDates(){
        Release oldRelease = new Release("Legacy", LocalDate.of(1900, 1, 1), LocalDate.of(1900, 12, 31));

        assertEquals("Legacy", oldRelease.getReleaseWindow());
        assertEquals(LocalDate.of(1900, 1, 1), oldRelease.getStartDate());
        assertEquals(LocalDate.of(1900, 12, 31), oldRelease.getEndDate());
    }

    @Test
    @DisplayName("Test with dates far in the future")
    void testFutureDates(){
        Release futureRelease = new Release("Future", LocalDate.of(2100, 1, 1), LocalDate.of(2100, 12, 31));

        assertEquals("Future", futureRelease.getReleaseWindow());
        assertEquals(LocalDate.of(2100, 1, 1), futureRelease.getStartDate());
        assertEquals(LocalDate.of(2100, 12, 31), futureRelease.getEndDate());
    }

    @Test
    @DisplayName("Should throw exception when releaseWindow is only whitespace")
    void testWhitespaceReleaseWindow(){
        assertThrows(IllegalArgumentException.class,
                () -> new Release("   ", LocalDate.now(), LocalDate.now().plusDays(1))
        );
    }











}

