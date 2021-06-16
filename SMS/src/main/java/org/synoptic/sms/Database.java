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
            PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT open, close FROM OpeningTimes WHERE id = ? ORDER BY day ASC;");
            statement.setString(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();

            List<LocalTime> openingTimes = new ArrayList<>();
            String[] times = new String[2];

            while(resultSet.next()){
                String[] columns = {"open", "close"};
                for (int i = 0; i < 2; i++) {
                    times[i] = resultSet.getString(columns[i]);
                    if(times[i].equalsIgnoreCase("closed")){
                        openingTimes.add(LocalTime.of(23, 59));
                    }
                    else{
                        if(times[i].length() < 5){
                            times[i] = "0" + times[i];
                        }
                        openingTimes.add(LocalTime.parse(times[i], DateTimeFormatter.ofPattern("HH:mm")));
                    }
                }
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
            PreparedStatement statement;
            if(times.isEmpty()){
                statement = getDatabaseConnection().prepareStatement("UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;");
                statement.setString(1, "CLOSED");
                statement.setString(2, "CLOSED");
                statement.setString(3, phoneNum);
                statement.setInt(4, day);
            }
            else{
                statement = getDatabaseConnection().prepareStatement("UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;");
                System.out.println(times.get(0).toString().length());
                statement.setString(1, times.get(0).toString());
                statement.setString(2, times.get(1).toString());
                statement.setString(3, phoneNum);
                statement.setInt(4, day);

            }
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
            PreparedStatement statement = getDatabaseConnection().prepareStatement("UPDATE DirectoryEntry SET announcement = ? WHERE phone = ?;");
            statement.setString(1, announcement);
            statement.setString(2, phoneNum);
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