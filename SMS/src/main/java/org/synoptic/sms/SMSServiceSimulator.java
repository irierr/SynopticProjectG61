package org.synoptic.sms;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simulates an SMS Service for owners of businesses/ organisations to update opening times and announcements for display
 * on the app.
 * @author Irie Railton
 */
public class SMSServiceSimulator
{
    String phoneNumber;
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public SMSServiceSimulator(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Listens for input from the command line and takes the user down a tree of options for updating their announcement
     * or each day's opening times. Connects to the database and updates their business/ organisation tables based on the
     * phone number used when constructing the simulator.
     * @author Irie Railton
     */
    private void listenForTexts(){
        String input;
        Scanner in = new Scanner(System.in);

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
                boolean updatingTimes = true;
                while(updatingTimes){
                    List<LocalTime> currOpenTimes = Database.getOpeningTimes(phoneNumber);
                    System.out.println("Your current opening times are:\n " + currOpenTimes + "\n What day would you like to change the times for?");
                    String day = in.nextLine();
                    if(validateDay(day)){
                        System.out.println("Enter your times for " + day);
                        input = in.nextLine();
                        List<LocalTime> newOpenTime = formatTimes(input);
                        if(Database.updateOpeningTimes(phoneNumber, day.toLowerCase(), newOpenTime)){
                            System.out.println("Successfully updated the opening times for " + day + " to " + input + ". If you would like to update another day's times send the name of the day otherwise send stop to stop updating times");
                            input = in.nextLine();
                            if(input.equalsIgnoreCase("stop")){
                                updatingTimes = false;
                            }
                        }
                    }
                }
            }
            else if(input.equalsIgnoreCase("announcement")){
                System.out.println("Please input your new announcement to display to customers (Must be under 160 characters");
                input = in.nextLine();
                if(Database.updateAnnouncement(phoneNumber, input)){
                    System.out.println("Announcement successfully updated");
                }
            }
        }
    }

    /**
     * Formats passed input into LocalTime list.
     * @param input String that should be in the form "HH:mm - HH:mm"
     * @return the input formatted into two LocalTime times in an ArrayList
     * @author Irie Railton
     */
    public List<LocalTime> formatTimes(String input){
        List<LocalTime> openingTimes = new ArrayList<>();
        String[] times = input.split(" - ");
        for (int i = 0; i < 2; i++) {
            if(times[i].length() < 5){
                times[i] = "0" + times[1];
            }
//            openingTimes.set(i, LocalTime.parse(times[i], timeFormatter));
            openingTimes.add(LocalTime.parse(times[i], timeFormatter));
        }
        return openingTimes;
    }

    /**
     * Checks if the passed string is a valid day of the week
     * @param day String to check
     * @return true if day is a day of the week and false if not
     * @author Irie Railton
     */
    public boolean validateDay(String day){
        day = day.toLowerCase();
        return day.equals("monday") || day.equals("tuesday") || day.equals("wednesday") || day.equals("thursday") || day.equals("friday") || day.equals("saturday") || day.equals("sunday");
    }

    public static void main(String[] args) {
        String phoneNum = "0123456789";
        SMSServiceSimulator simulator = new SMSServiceSimulator(phoneNum);
        simulator.listenForTexts();
    }
}
