package org.synoptic;

import java.util.HashMap;
import java.time.LocalTime;

/**
 * DirectoryEntry class for storing various locations
 *
 * @author Harry Whitelam, Darcey Gardiner
 */
public class DirectoryEntry {
    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private HashMap<Integer, LocalTime[]> openingHours;
    private String announcement;

    /**
     * Parameterised constructor for DirectoryEntry, used for shops, restaurants or attractions
     *
     * @param phoneNumberVar parameter to specify phone number
     * @param nameVar parameter to specify name
     * @param addressVar parameter to specify address
     * @param descriptionVar parameter to specify description
     * @param openingHoursVar parameter to specify opening hours
     * @param announcementVar parameter to specify announcement
     * @author Harry Whitelam, Darcey Gardiner
     */
    public DirectoryEntry(String phoneNumberVar, String nameVar, String addressVar, String descriptionVar, HashMap<Integer, LocalTime[]> openingHoursVar, String announcementVar) {
        this.name = nameVar;
        this.phoneNumber = phoneNumberVar;
        this.address = addressVar;
        this.description = descriptionVar;
        this.openingHours = openingHoursVar;
        this.announcement = announcementVar;
    }

    /**
     * Getters for all attributes of the DirectoryEntry class
     *
     * @return the requested attribute
     * @author Harry Whitelam, Darcey Gardiner
     */
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getDescription() {
        return this.description;
    }
    public HashMap<Integer, LocalTime[]> getOpeningHours() {
        return this.openingHours;
    }
    public String getAnnouncement() {
        return this.announcement;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setters for all attributes of the DirectoryEntry class
     *
     * @author Harry Whitelam, Darcey Gardiner
     */
    public void setName(String nameVar) {
        this.name = nameVar;
    }
    public void setAddress(String addressVar) {
        this.address = addressVar;
    }
    public void setDescription(String descriptionVar) {
        this.description = descriptionVar;
    }
    public void setOpeningHours(HashMap<Integer, LocalTime[]> openingHoursVar) {
        this.openingHours = openingHoursVar;
    }
    public void setAnnouncement(String announcementVar) {
        this.announcement = announcementVar;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method to convert an integer which represents a day into a string
     *
     * @param dayNumber an integer that indicates a specific day (1 -> Monday etc.)
     * @return String of day
     * @author Brandon Gous
     */
    public String dayNumberToString(int dayNumber)
    {
        switch (dayNumber)
        {
            case 1 -> {
                return "Monday";
            }
            case 2 -> {
                return "Tuesday";
            }
            case 3 -> {
                return "Wednesday";
            }
            case 4 -> {
                return "Thursday";
            }
            case 5 -> {
                return "Friday";
            }
            case 6 -> {
                return "Saturday";
            }
            case 7 -> {
                return "Sunday";
            }
            default -> throw new IllegalArgumentException("Day number must be between 1 and 7");
        }
    }

    /**
     * Method to print the opening hours hashmap as a legible string
     *
     * @return string of the opening hours in a readable format
     * @author Harry Whitelam
     */
    public String printOpeningHours(){
        StringBuilder hoursString = new StringBuilder();
        for (int i : this.openingHours.keySet()) {
            switch (i) {
                case (1) -> hoursString.append("Monday: ");
                case (2) -> hoursString.append("Tuesday: ");
                case (3) -> hoursString.append("Wednesday: ");
                case (4) -> hoursString.append("Thursday: ");
                case (5) -> hoursString.append("Friday: ");
                case (6) -> hoursString.append("Saturday: ");
                case (7) -> hoursString.append("Sunday: ");
            }
            LocalTime[] hours = openingHours.get(i);
            hoursString.append(hours[0]).append(" - ").append(hours[1]).append("\n");
        }
        return hoursString.toString();
    }

    /**
     * Method override of toString, adapted for the DirectoryEntry class
     *
     * @return string of all DirectoryEntry attributes
     * @author Harry Whitelam, Darcey Gardiner
     */
    @Override
    public String toString() {
        return "DirectoryEntry{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", description='" + description + '\'' +
            ", openingHours=" + openingHours +
            ", announcement=" + announcement +
            '}';
    }
}
