package org.synoptic.sms;
import java.time.LocalTime;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        while(true){
            input = in.nextLine();
            if(input.equalsIgnoreCase("start")){
                System.out.println("""
                        Please input one the following:\s
                        opening times\s
                        announcement\s
                        """);
            } else if(input.equalsIgnoreCase("opening times")){
                LocalTime openingTimes;
                System.out.println("Please input your opening time for Monday to Sunday in the format HH:MM - HH:MM in individual texts for each day");
                for (int i = 1; i < 7; i++) {
                    input = in.nextLine();

                    if(input.isEmpty()){
                        System.out.println("Please re-enter your opening times for ");
                    }
                }
            }

        }
    }
}
