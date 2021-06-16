package org.synoptic.sms;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Harness for SMSServiceSimulator class
 *
 * @see SMSServiceSimulator
 * @author Harry Whitelam
 */
class SMSServiceSimulatorTest {

    /**
     * Should create a list of localTimes with the provided time stored in it
     *
     * @author Harry Whitelam
     */
    @Test
    void formatTimes() {
        SMSServiceSimulator testSMSServiceSimulator = new SMSServiceSimulator("07904823541");

        List<LocalTime> testTimeList = new ArrayList<>();
        testTimeList.add(LocalTime.of(9, 0));
        testTimeList.add(LocalTime.of(15, 0));

        List<LocalTime> funcTimes = testSMSServiceSimulator.formatTimes("09:00 - 15:00");

        assertAll(()-> assertEquals(testTimeList.get(0), funcTimes.get(0)),
                ()-> assertEquals(testTimeList.get(1), funcTimes.get(1)));
    }

    /**
     * Should return a string holding all days of the week and their opening hours
     *
     * @author Harry Whitelam
     */
    @Test
    void timesToString() {
        SMSServiceSimulator testSMSServiceSimulator = new SMSServiceSimulator("777");
        List<LocalTime> currOpenTimes = Database.getOpeningTimes("777");
        String testString = """
                Monday: 09:00 - 16:30
                Tuesday: 03:00 - 04:00
                Wednesday: 09:00 - 16:30
                Thursday: 07:01 - 08:45
                Friday: 09:00 - 10:30
                Saturday: 10:00 - 16:00
                Sunday: closed
                """;
        assertEquals(testString, testSMSServiceSimulator.timesToString(currOpenTimes));
    }
}