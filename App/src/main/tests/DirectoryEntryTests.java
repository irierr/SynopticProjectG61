import org.synoptic.DirectoryEntry;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Testing Harness for the DirectoryEntry class
 *
 * @author Harry Whitelam
 * @see org.synoptic.DirectoryEntry
 */
public class DirectoryEntryTests {
    /**
     * Runs all tests in the test harness, printing appropriate error messages for failures and successes
     *
     * @author Harry Whitelam
     * @see org.synoptic.DirectoryEntry
     */
    public static void main() {
        if (gettersAndSettersTest() == 1)
            System.out.println("TEST FAILED - Getters and Setters test!");
        else
            System.out.println("TEST PASSED - Getters and Setters test!");
        if (printOpeningHoursTest() == 1)
            System.out.println("TEST FAILED - Print Opening Hours test!");
        else
            System.out.println("TEST PASSED - Print Opening Hours test!");
    }

    /**
     * Tests all getters and setters in the DirectoryEntry class
     * All attributes should be returned or changed applicably
     *
     * @author Harry Whitelam
     * @return Returns 1 if any tests fail, returns 0 if all tests succeed
     * @see org.synoptic.DirectoryEntry
     */
    public static int gettersAndSettersTest() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        testMap.put(2, new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(15, 0)});
        testMap.put(3, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});

        DirectoryEntry testDirectoryEntry = new DirectoryEntry(DirectoryEntry.Type.SHOP, "07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        HashMap<Integer, LocalTime[]> newMap = new HashMap<>();
        newMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(10, 0)});

        testDirectoryEntry.setType(DirectoryEntry.Type.RESTAURANT);
        testDirectoryEntry.setPhoneNumber("07904897812");
        testDirectoryEntry.setName("newName");
        testDirectoryEntry.setAddress("newAddress");
        testDirectoryEntry.setDescription("newDesc");
        testDirectoryEntry.setOpeningHours(newMap);
        testDirectoryEntry.setAnnouncement("newAnnouncement");

        if (!testDirectoryEntry.getType().equals(DirectoryEntry.Type.RESTAURANT))
            return 1;
        else if (!testDirectoryEntry.getPhoneNumber().equals("07904897812"))
            return 1;
        else if (!testDirectoryEntry.getName().equals("newName"))
            return 1;
        else if (!testDirectoryEntry.getAddress().equals("newAddress"))
            return 1;
        else if (!testDirectoryEntry.getDescription().equals("newDesc"))
            return 1;
        else if (!testDirectoryEntry.getOpeningHours().equals(newMap))
            return 1;
        else if (!testDirectoryEntry.getAnnouncement().equals("newAnnouncement"))
            return 1;
        else
            return 0;
    }

    /**
     * Tests the printOpeningHours method in the DirectoryEntry class
     * A string of formatted opening hours should be returned
     *
     * @author Harry Whitelam
     * @return Returns 1 if the test fails, returns 0 if the test succeeds
     * @see org.synoptic.DirectoryEntry
     */
    public static int printOpeningHoursTest() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        testMap.put(2, new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(15, 0)});

        DirectoryEntry testDirectoryEntry = new DirectoryEntry(DirectoryEntry.Type.SHOP, "07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        if (!testDirectoryEntry.printOpeningHours().equals("Monday: 09:00 - 17:00\nTuesday: 07:00 - 15:00\n"))
            return 1;
        else
            return 0;
    }
}
