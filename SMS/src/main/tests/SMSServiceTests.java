import org.synoptic.sms.SMSServiceSimulator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SMSServiceTests {
    public static void main() {
        System.out.println("---- TEST STARTED: SMS Service Simulator class:");
        if (formatTimesTest() == 1)
            System.out.println("TEST FAILED - Format Times test!");
        else
            System.out.println("TEST PASSED - Format Times test!");
        if (validateDayTest() == 1)
            System.out.println("TEST FAILED - Validate Day test!");
        else
            System.out.println("TEST PASSED - Validate Day test!");

        System.out.println("---- TEST COMPLETE\n");
    }

    public static int formatTimesTest() {
        SMSServiceSimulator testSMSServiceSimulator = new SMSServiceSimulator("07904823541");

        List<LocalTime> testTimeList = new ArrayList<>();
        testTimeList.add(LocalTime.of(9, 0));
        testTimeList.add(LocalTime.of(15, 0));

        List<LocalTime> funcTimes = testSMSServiceSimulator.formatTimes("09:00 - 15:00");

        for (int i = 0; i < 2; i++)
            if (!(funcTimes.get(i) == testTimeList.get(i)))
                return 1;
        return 0;
    }

    public static int validateDayTest() {
        SMSServiceSimulator testSMSServiceSimulator = new SMSServiceSimulator("07904823541");

        if (!testSMSServiceSimulator.validateDay("MONDAY"))
            return 1;
        else
            return 0;
    }
}
