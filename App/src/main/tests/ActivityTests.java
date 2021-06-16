import org.synoptic.Activity;

/**
 * Testing Harness for the Activity class
 *
 * @author Harry Whitelam
 * @see org.synoptic.Activity
 */
public class ActivityTests {
    /**
     * Runs all tests in the test harness, printing appropriate error messages for failures and successes
     *
     * @author Harry Whitelam
     * @see org.synoptic.Activity
     */
    public static void main() {
        System.out.println("---- TEST STARTED: Activity class:");
        if (gettersAndSettersTest() == 1)
            System.out.println("TEST FAILED - Getters and Setters test!");
        else
            System.out.println("TEST PASSED - Getters and Setters test!");

        System.out.println("---- TEST COMPLETE\n");
    }

    /**
     * Tests all getters and setters in the Activity class
     * All attributes should be returned or changed applicably
     *
     * @author Harry Whitelam
     * @return Returns 1 if any tests fail, returns 0 if all tests succeed
     * @see org.synoptic.DirectoryEntry
     */
    public static int gettersAndSettersTest() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        Activity testWalkingTrail = new Activity(Activity.Type.WALKING_TRAIL, "testName", "testAddress", "testDesc", "testEndAddress");

        testAttraction.setPhoneNumber("07904897812");
        testAttraction.setName("newName");
        testAttraction.setAddress("newAddress");
        testAttraction.setDescription("newDesc");
        testWalkingTrail.setEndAddress("newEndAddress");
        testAttraction.setType(Activity.Type.WALKING_TRAIL);

        if (!testAttraction.getType().equals(Activity.Type.WALKING_TRAIL))
            return 1;
        else if (!testAttraction.getPhoneNumber().equals("07904897812"))
            return 1;
        else if (!testAttraction.getName().equals("newName"))
            return 1;
        else if (!testAttraction.getAddress().equals("newAddress"))
            return 1;
        else if (!testAttraction.getDescription().equals("newDesc"))
            return 1;
        else if (!testWalkingTrail.getEndAddress().equals("newEndAddress"))
            return 1;
        else
            return 0;
    }
}
