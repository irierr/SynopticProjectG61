import org.synoptic.POI;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Testing Harness for the POI class
 *
 * @author Harry Whitelam
 * @see org.synoptic.POI
 */
public class POITests {
    /**
     * Runs all tests in the test harness, printing appropriate error messages for failures and successes
     *
     * @author Harry Whitelam
     * @see org.synoptic.POI
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
     * Tests all getters and setters in the POI class
     * All attributes should be returned or changed applicably
     *
     * @author Harry Whitelam
     * @return Returns 1 if any tests fail, returns 0 if all tests succeed
     * @see org.synoptic.POI
     */
    public static int gettersAndSettersTest() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        testMap.put(2, new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(15, 0)});
        testMap.put(3, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});

        POI testPOI = new POI(POI.Type.SHOP, "07412107823", "testName", "testAddress", "testDesc", testMap);
        POI trailPOI = new POI("07412107823", "testName", "testAddress", "testDesc", "testEndAddress");

        HashMap<Integer, LocalTime[]> newMap = new HashMap<>();
        newMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(10, 0)});

        testPOI.setType(POI.Type.RESTAURANT);
        testPOI.setPhoneNumber("07904897812");
        testPOI.setName("newName");
        testPOI.setAddress("newAddress");
        testPOI.setDescription("newDesc");
        testPOI.setOpeningHours(newMap);
        trailPOI.setEndAddress("newEndAddress");

        if (!testPOI.getType().equals(POI.Type.RESTAURANT))
            return 1;
        else if (!testPOI.getPhoneNumber().equals("07904897812"))
            return 1;
        else if (!testPOI.getName().equals("newName"))
            return 1;
        else if (!testPOI.getAddress().equals("newAddress"))
            return 1;
        else if (!testPOI.getDescription().equals("newDesc"))
            return 1;
        else if (!testPOI.getOpeningHours().equals(newMap))
            return 1;
        else if (!trailPOI.getEndAddress().equals("newEndAddress"))
            return 1;
        else
            return 0;
    }

    /**
     * Tests the printOpeningHours method in the POI class
     * A string of formatted opening hours should be returned
     *
     * @author Harry Whitelam
     * @return Returns 1 if the test fails, returns 0 if the test succeeds
     * @see org.synoptic.POI
     */
    public static int printOpeningHoursTest() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        testMap.put(2, new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(15, 0)});

        POI testPOI = new POI(POI.Type.SHOP, "07412107823", "testName", "testAddress", "testDesc", testMap);

        if (!testPOI.printOpeningHours().equals("Monday: 09:00 - 17:00\nTuesday: 07:00 - 15:00\n"))
            return 1;
        else
            return 0;
    }
}
