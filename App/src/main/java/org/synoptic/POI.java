package sample;

import java.util.HashMap;
import java.time.LocalTime;

public class POI {
    private enum Type {
        SHOP,
        RESTAURANT,
        ATTRACTION,
        WALKING_TRAIL
    }

    private String name;
    private String address;
    private String description;
    private HashMap<Integer, LocalTime[]> openingHours;
    private Type type;
    private String endAddress;


    public POI() {
        this.type = null;
    }

    public POI(int typeVar, String nameVar, String addressVar, String descriptionVar, HashMap<Integer, LocalTime[]> openingHoursVar) {
        switch(typeVar) {
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
    };
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
        switch(typeVar) {
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

    public String toString() {
        if (this.type == Type.WALKING_TRAIL) {
            return ("Name: " + this.name + "\n" +
                    "Address: " + this.address + "\n" +
                    "End address: " + this.endAddress + "\n" +
                    "Description: " + this.description + "\n" +
                    "Type: " + this.type + "\n");
        }
        else {
            return ("Name: " + this.name + "\n" +
                    "Address: " + this.address + "\n" +
                    "Description: " + this.description + "\n" +
                    "Opening hours: " + printOpeningHours() + "\n" +
                    "Type: " + this.type + "\n");
        }
    }
}
