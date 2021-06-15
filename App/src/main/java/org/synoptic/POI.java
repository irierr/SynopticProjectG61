package org.synoptic;

import java.util.HashMap;
import java.time.LocalTime;

public class POI {
    public enum Type {
        SHOP,
        RESTAURANT,
        ATTRACTION,
        WALKING_TRAIL
    }

    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private HashMap<Integer, LocalTime[]> openingHours;
    private POI.Type type;
    private String endAddress;


    public POI() {
        this.type = null;
    }

    public POI(POI.Type typeVar, String phoneNumberVar, String nameVar, String addressVar, String descriptionVar, HashMap<Integer, LocalTime[]> openingHoursVar) {
        this.type = typeVar;
        this.name = nameVar;
        this.phoneNumber = phoneNumberVar;
        this.address = addressVar;
        this.description = descriptionVar;
        this.openingHours = openingHoursVar;
    }
    public POI(String phoneNumberVar, String nameVar, String addressVar, String descriptionVar, String endAddressVar) {
        this.type = Type.WALKING_TRAIL;
        this.name = nameVar;
        this.phoneNumber = phoneNumberVar;
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
    public POI.Type getType() {
        return this.type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(POI.Type type) {
        this.type = type;
    }

    public String printOpeningHours() {
        StringBuilder hoursString = new StringBuilder();
        for (int i : this.openingHours.keySet()) {
            switch(i) {
                case(1):
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
            LocalTime[] hours = openingHours.get(i);
            hoursString.append(hours[0]).append(" - ").append(hours[1]).append("\n");
        }
        return hoursString.toString();
    }

    @Override
    public String toString() {
        if (type == Type.WALKING_TRAIL)
        {
            return "POI{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", description='" + description + '\'' +
                    ", type=" + type +
                    ", endAddress='" + endAddress + '\'' +
                    '}';
        }
        else
        {
            return "POI{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", description='" + description + '\'' +
                    ", openingHours=" + openingHours +
                    ", type=" + type +
                    '}';
        }
    }
}
