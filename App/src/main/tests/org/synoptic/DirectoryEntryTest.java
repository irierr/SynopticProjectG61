package org.synoptic;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryEntryTest {
    @Test
    void getName() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("testName", testDE.getName());
    }

    @Test
    void getAddress() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("testAddress", testDE.getAddress());
    }

    @Test
    void getDescription() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("testDesc", testDE.getDescription());
    }

    @Test
    void getOpeningHours() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals(testMap, testDE.getOpeningHours());
    }

    @Test
    void getAnnouncement() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("testAnnouncement", testDE.getAnnouncement());
    }

    @Test
    void getPhoneNumber() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("07412107823", testDE.getPhoneNumber());
    }

    @Test
    void setName() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testDE.setName("newName");
        assertEquals("newName", testDE.getName());
    }

    @Test
    void setAddress() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testDE.setAddress("newAddress");
        assertEquals("newAddress", testDE.getAddress());
    }

    @Test
    void setDescription() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testDE.setDescription("newDesc");
        assertEquals("newDesc", testDE.getDescription());
    }

    @Test
    void setOpeningHours() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testMap.put(2, new LocalTime[]{LocalTime.of(10, 30), LocalTime.of(16, 45)});
        testDE.setOpeningHours(testMap);
        assertEquals(testMap, testDE.getOpeningHours());
    }

    @Test
    void setAnnouncement() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testDE.setAnnouncement("newAnnouncement");
        assertEquals("newAnnouncement", testDE.getAnnouncement());
    }

    @Test
    void setPhoneNumber() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        testDE.setPhoneNumber("1234");
        assertEquals("1234", testDE.getPhoneNumber());
    }

    @Test
    void printOpeningHours() {
        HashMap<Integer, LocalTime[]> testMap = new HashMap<>();
        testMap.put(1, new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(17, 0)});
        testMap.put(2, new LocalTime[]{LocalTime.of(7, 0), LocalTime.of(15, 0)});
        DirectoryEntry testDE = new DirectoryEntry("07412107823", "testName", "testAddress", "testDesc", testMap, "testAnnouncement");

        assertEquals("Monday: 09:00 - 17:00\nTuesday: 07:00 - 15:00\n", testDE.printOpeningHours());
    }
}