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
        SMSServiceSimulator testSMSServiceSimulator = new SMSServiceSimulator("51946446843");
        List<LocalTime> currOpenTimes = Database.getOpeningTimes("51946446843");
        String testString = """
                Monday: closed
                Tuesday: closed
                Wednesday: closed
                Thursday: closed
                Friday: 10:00 - 19:00
                Saturday: 10:00 - 19:00
                Sunday: 10:00 - 19:00
                """;
        assertEquals(testString, testSMSServiceSimulator.timesToString(currOpenTimes));
    }
}