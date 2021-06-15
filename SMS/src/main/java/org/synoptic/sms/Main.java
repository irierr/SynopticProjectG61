package org.synoptic.sms;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        String input;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Scanner in = new Scanner(System.in);
        String phoneNumber = "0123456789";

        while(true){
            input = in.nextLine();
            if(input.equalsIgnoreCase("start")) {
                System.out.println("""
                        Please input one the following:\s
                        opening times\s
                        announcement\s
                        """);
            }
            else if(input.equalsIgnoreCase("opening times")) {
                LocalTime[] openingTimes = new LocalTime[2];
                System.out.println("Please input your opening time for Monday to Sunday in the format HH:MM - HH:MM in individual texts for each day");
                for (int i = 1; i < 8; i++) {
                    input = in.nextLine();
                    if(input.isEmpty()) {
                        System.out.println("Please re-enter your opening times for " + (i == 1 ? "Monday" : (i == 2 ? "Tuesday" : (i == 3 ? "Wednesday" : (i == 4 ? "Thursday" : (i == 5 ? "Friday" : (i== 6 ? "Saturday" : "Sunday")))))));
                    }
                    else {
                        String[] times = input.split(" - ");
                        openingTimes[0] = LocalTime.parse(times[0], timeFormatter);
                        openingTimes[1] = LocalTime.parse(times[1], timeFormatter);
                        Database.updateOpeningTimes(phoneNumber, i, openingTimes);
                    }
                }
            }
            else if(input.equalsIgnoreCase("announcement")){
                System.out.println("Please input your new announcement to display to customers");
                input = in.nextLine();
                Database.updateAnnouncement(phoneNumber, input);
            }
        }
    }
}
