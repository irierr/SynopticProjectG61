package org.synoptic.sms;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Connection databaseConnection;

    static {
        Database.connectDatabase();
    }

    // (DG) - Return the Database Connection
    public static Connection getDatabaseConnection() {
        return databaseConnection;
    }

    // (DG) - Create a Database Connection. Returns true upon successful creation, terminates program upon failure.
    private static void connectDatabase() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            databaseConnection = DriverManager.getConnection("jdbc:mariadb://81.104.43.46:3306/ecoSwellDatabase", "appUser", "9iPTs#ko");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Gets all opening times for a given phone number
     * @param phoneNum user's phone number
     * @return List of opening times for each day as LocalTime times
     * @author Irie Railton
     */
    public static List<LocalTime> getOpeningTimes(String phoneNum){
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT open, close FROM OpeningTimes WHERE id = " + phoneNum + ";");
            ResultSet resultSet = statement.executeQuery();
            List<LocalTime> openingTimes = new ArrayList<>();

            while(resultSet.next()){
                openingTimes.add(LocalTime.parse(resultSet.getString("Open"), DateTimeFormatter.ofPattern("HH:mm")));
                openingTimes.add(LocalTime.parse(resultSet.getString("Close"), DateTimeFormatter.ofPattern("HH:mm")));
            }

            return openingTimes;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates opening times for a specific day given a users phone number.
     * @param phoneNum user's phone number
     * @param day day to update times
     * @param times new opening times as list of LocalTime times
     * @return true if update is successful or false if not
     * @author Irie Railton
     */
    public static boolean updateOpeningTimes(String phoneNum, int day, List<LocalTime> times){
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("UPDATE OpeningTimes SET open = " + times.get(0).toString() + ", close = " + times.get(1).toString() + " WHERE id =" + phoneNum + " AND day =" + day + ";");
            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates announcement for a business/ organisation.
     * @param phoneNum user's phone number
     * @param announcement new announcement as a String
     * @return true if update is successful or false if not
     * @author Irie Railton
     */
    public static boolean updateAnnouncement(String phoneNum, String announcement){
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("UPDATE DirectoryEntry SET announcement = " + announcement + " WHERE phone = " + phoneNum + ";");
            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}