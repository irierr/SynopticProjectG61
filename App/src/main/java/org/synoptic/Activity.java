package org.synoptic;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Activity class for storing various activities and walking trails
 *
 * @author Harry Whitelam
 */
public class Activity {
    /**
     * Enum to specify the type of activity
     *
     * @author Harry Whitelam
     * @see java.lang.Enum
     */
    public enum Type {
        ATTRACTION,
        WALKING_TRAIL,
    }

    private Activity.Type type;
    private String name;
    private String address;
    private String description;
    private String phoneNumber;
    private String endAddress;

    /**
     * Default constructor for Activity
     *
     * @author Harry Whitelam
     */
    public Activity() {
        this.type = null;
    }

    /**
     * Parameterised constructor for Activity
     * An activity can either be a walking trail or an attraction. The only dissimilarity between these two is either a phone number or an end address
     * The constructor uses an if statement to check the enum type to correctly select the attributes to define
     *
     * @param phoneNumberEndAddressVar parameter to specify phone number or end address, depending on the type of the activity
     * @param nameVar parameter to specify name
     * @param addressVar parameter to specify address
     * @param descriptionVar parameter to specify description
     * @author Harry Whitelam
     */
    public Activity(Activity.Type typeVar, String nameVar, String addressVar, String descriptionVar, String phoneNumberEndAddressVar) {
        if (typeVar == Type.ATTRACTION) {
            this.type = Type.ATTRACTION;
            this.phoneNumber = phoneNumberEndAddressVar;
        }
        else {
            this.type = Type.WALKING_TRAIL;
            this.endAddress = phoneNumberEndAddressVar;
        }
        this.name = nameVar;
        this.address = addressVar;
        this.description = descriptionVar;
    }

    /**
     * Getters for all attributes of the Activity class
     *
     * @return the requested attribute
     * @author Harry Whitelam
     */
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getEndAddress() {
        return this.endAddress;
    }
    public String getDescription() {
        return this.description;
    }
    public Activity.Type getType() {
        return this.type;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Setters for all attributes of the Activity class
     *
     * @author Harry Whitelam
     */
    public void setName(String nameVar) {
        this.name = nameVar;
    }
    public void setAddress(String addressVar) {
        this.address = addressVar;
    }
    public void setEndAddress(String endAddressVar) {
        this.endAddress = endAddressVar;
    }
    public void setDescription(String descriptionVar) {
        this.description = descriptionVar;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setType(Activity.Type type) {
        this.type = type;
    }

    /**
     * Method override of toString, adapted for the Activity class
     *
     * @return string of all Activity attributes
     * @author Harry Whitelam
     */
    @Override
    public String toString() {
        if (this.type == Type.ATTRACTION) {
            return "Activity{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", description='" + description + '\'' +
                    ", type=" + type + '\'' +
                    '}';
        }
        else {
            return "Activity{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", endAddress=" + endAddress + '\'' +
                    ", description='" + description + '\'' +
                    ", type=" + type + '\'' +
                    '}';
        }
    }

}
