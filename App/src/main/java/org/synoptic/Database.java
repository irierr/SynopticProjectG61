package org.synoptic;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to control the database connection.
 */
public class Database {

    private static Connection databaseConnection;

    /**
     * Static body to initialise the database
     */
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
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Gets a list of all of the {@link DirectoryEntry Directory Entries}.
     *
     * @return A list of entries
     * @throws SQLException When there is an exception, so it won't return an empty list.
     */
    public static List<DirectoryEntry> getAllDirectoryEntrys() throws SQLException
    {
        PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT * FROM DirectoryEntry;");
        ResultSet resultSet = statement.executeQuery();

        List<DirectoryEntry> DirectoryEntrys = new ArrayList<>();

        while (resultSet.next())
        {
            List<LocalTime> times = getOpeningTimes(resultSet.getString("phone"));

            HashMap<Integer, LocalTime[]> map = new HashMap<>();

            for (int i = 0; i < times.size(); i += 2)//0, 2, 4, 6, 8, 10, 12
            {
                map.put(Math.floorDiv(i, 2) + 1, new LocalTime[] {times.get(i), times.get(i + 1)});
            }

            DirectoryEntrys.add(new DirectoryEntry(resultSet.getString("phone"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("description"), map, resultSet.getString("announcement")));
        }

        return DirectoryEntrys;
    }

    /**
     * Gets all activities
     *
     * @return A list of activities
     * @throws SQLException When there is an exception so an empty list isn't returned.
     */
    public static List<Activity> getAllActivities() throws SQLException{

        PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT * FROM Activities;");
        ResultSet resultSet = statement.executeQuery();

        List<Activity> Activities = new ArrayList<>();

        while (resultSet.next()){
            Activities.add(new Activity(resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("endAddress"), resultSet.getString("description"), resultSet.getString("phone"), Activity.Type.valueOf(resultSet.getString("type"))));
        }

        return Activities;
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
            PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT open, close FROM OpeningTimes WHERE id = ? ORDER BY day;");
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
                statement.setString(1, "'" + times.get(0).toString() + "'");
                statement.setString(2, "'" + times.get(1).toString() + "'");
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

    /*
        CREATE TABLE DirectoryEntry (
        phone VARCHAR(20) NOT NULL PRIMARY KEY,
        name VARCHAR(200) NOT NULL,
        address VARCHAR(200) NOT NULL,
        description VARCHAR(200) NOT NULL,
        announcement VARCHAR(200)
        );

        CREATE TABLE OpeningTimes
        (
            id VARCHAR(20) NOT NULL,
            open VARCHAR(5),
            close VARCHAR(5),
            day INT NOT NULL,
            CONSTRAINT FOREIGN KEY(id) REFERENCES DirectoryEntry(phone) ON DELETE CASCADE ON UPDATE CASCADE
        );

        create table Activities
        (
            type VARCHAR(20) NOT NULL,
            name VARCHAR(200) NOT NULL,
            address VARCHAR(200) NOT NULL,
            endAddress VARCHAR(200),
            description VARCHAR(200) NOT NULL,
            phone VARCHAR(20),
            CONSTRAINT FOREIGN KEY(id) REFERENCES DirectoryEntry(phone) ON DELETE CASCADE ON UPDATE CASCADE
        );
    */
}
