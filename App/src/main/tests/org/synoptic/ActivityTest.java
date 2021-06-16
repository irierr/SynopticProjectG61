package org.synoptic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test harness for Activity class
 *
 * @see Activity
 * @author Harry Whitelam
 */
class ActivityTest {

    /**
     * Getters, should return the value requested
     * Available for name, address, end address, description, type and phone number
     *
     * @author Harry Whitelam
     */
    @Test
    void getName() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        assertEquals("testName", testAttraction.getName());
    }

    @Test
    void getAddress() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        assertEquals("testAddress", testAttraction.getAddress());
    }

    @Test
    void getEndAddress() {
        Activity testWalkingTrail = new Activity(Activity.Type.WALKING_TRAIL, "testName", "testAddress", "testDesc", "testEndAddress");
        assertEquals("testEndAddress", testWalkingTrail.getEndAddress());
    }

    @Test
    void getDescription() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        assertEquals("testDesc", testAttraction.getDescription());
    }

    @Test
    void getType() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        assertEquals(Activity.Type.ATTRACTION, testAttraction.getType());
    }

    @Test
    void getPhoneNumber() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        assertEquals("07412107823", testAttraction.getPhoneNumber());
    }

    /**
     * Setters, should replace the value with the given parameter
     * Available for name, address, end address, description, type and phone number
     *
     * @author Harry Whitelam
     */
    @Test
    void setName() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        testAttraction.setName("newName");
        assertEquals("newName", testAttraction.getName());
    }

    @Test
    void setAddress() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        testAttraction.setAddress("newAddress");
        assertEquals("newAddress", testAttraction.getAddress());
    }

    @Test
    void setEndAddress() {
        Activity testWalkingTrail = new Activity(Activity.Type.WALKING_TRAIL, "testName", "testAddress", "testDesc", "testEndAddress");
        testWalkingTrail.setEndAddress("newEndAddress");
        assertEquals("newEndAddress", testWalkingTrail.getEndAddress());
    }

    @Test
    void setDescription() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        testAttraction.setDescription("newDesc");
        assertEquals("newDesc", testAttraction.getDescription());
    }

    @Test
    void setPhoneNumber() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        testAttraction.setPhoneNumber("1234");
        assertEquals("1234", testAttraction.getPhoneNumber());
    }

    @Test
    void setType() {
        Activity testAttraction = new Activity(Activity.Type.ATTRACTION, "testName", "testAddress", "testDesc", "07412107823");
        testAttraction.setType(Activity.Type.WALKING_TRAIL);
        assertEquals(Activity.Type.WALKING_TRAIL, testAttraction.getType());
    }
}