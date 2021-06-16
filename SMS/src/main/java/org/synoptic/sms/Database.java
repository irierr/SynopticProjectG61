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

    public static List<LocalTime> getOpeningTimes(String phoneNum){
        try
        {

            PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT open, close FROM OpeningTimes WHERE id =" + phoneNum + ";");  //TODO add sql here
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

    public static boolean updateOpeningTimes(String phoneNum, int day, List<LocalTime> times){
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("");  //TODO add sql here
            statement.setString(1, String.valueOf(times.get(0)));
            statement.setString(2, String.valueOf(times.get(1)));

            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAnnouncement(String phoneNum, String announcement){
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("");  //TODO add sql here
            statement.setString(1, String.valueOf(announcement));

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