package org.synoptic;

import java.util.HashMap;
import java.time.LocalTime;

public class POI {
    private enum Type {     // Used to identify different types of location
        SHOP,
        RESTAURANT,
        ATTRACTION,
        WALKING_TRAIL
    }

    private String name;                // attributes for identification
    private String address;
    private String description;
    private HashMap<Integer, LocalTime[]> openingHours;     // hashmap of opening hours
    private Type type;
    private String endAddress;      // used only for walking trails, to find a start and end point


    // default constructor
    public POI() {
        this.type = null;
    }

    // the parameterised constructor for shops, restaurants and attractions
    public POI(int typeVar, String nameVar, String addressVar, String descriptionVar, HashMap<Integer, LocalTime[]> openingHoursVar) {
        switch (typeVar) {
            case (0):
                this.type = Type.SHOP;
                break;
            case (1):
                this.type = Type.RESTAURANT;
                break;
            case (2):
                this.type = Type.ATTRACTION;
                break;
        }
        this.name = nameVar;
        this.address = addressVar;
        this.description = descriptionVar;
        this.openingHours = openingHoursVar;
    }
    // the parameterised constructor for walking trails
    public POI(String nameVar, String addressVar, String descriptionVar, String endAddressVar) {
        this.type = Type.WALKING_TRAIL;
        this.name = nameVar;
        this.address = addressVar;
        this.description = descriptionVar;
        this.endAddress = endAddressVar;
    }


    //getters
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
    public String getEndAddress() {
        return this.endAddress;
    }
    public Type getType() {
        return this.type;
    }

    //setters
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
    public void setEndAddress(String endAddressVar) {
        this.endAddress = endAddressVar;
    }
    public void setType(int typeVar) {
        switch (typeVar) {
            case (0):
                this.type = Type.SHOP;
                break;
            case (1):
                this.type = Type.RESTAURANT;
                break;
            case (2):
                this.type = Type.ATTRACTION;
                break;
            case (3):
                this.type = Type.WALKING_TRAIL;
                break;
        }
    }

    // converts openingHours hashmap into easy-to-read string
    public String printOpeningHours() {
        StringBuilder hoursString = new StringBuilder();
        for (int i : this.openingHours.keySet()) {      // iterates through the opening hours hashmap
            switch(i) {
                case(1):        // checks for which day it is
                    hoursString.append("Monday: ");
                    break;
                case(2):
                    hoursString.append("Tuesday: ");
                    break;
                case(3):
                    hoursString.append("Wednesday: ");
                    break;
                case(4):
                    hoursString.append("Thursday: ");
                    break;
                case(5):
                    hoursString.append("Friday: ");
                    break;
                case(6):
                    hoursString.append("Saturday: ");
                    break;
                case(7):
                    hoursString.append("Sunday: ");
                    break;
            }
            LocalTime[] hours = openingHours.get(i);        // gets the opening hours
            hoursString.append(hours[0]).append(" - ").append(hours[1]).append("\n");       // appends the details to a string and adds newline characters
        }
        return hoursString.toString();      // returns the complete string
    }

    // toString to print details about the POI
    public String toString() {
        if (this.type == Type.WALKING_TRAIL) {      // prints walking trail details, or...
            return ("Name: " + this.name + "\n" +
                    "Address: " + this.address + "\n" +
                    "End address: " + this.endAddress + "\n" +
                    "Description: " + this.description + "\n" +
                    "Type: " + this.type + "\n");
        }
        else {      // ...prints details for other types
            return ("Name: " + this.name + "\n" +
                    "Address: " + this.address + "\n" +
                    "Description: " + this.description + "\n" +
                    "Opening hours: " + printOpeningHours() + "\n" +
                    "Type: " + this.type + "\n");
        }
    }
}
