package org.synoptic;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
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
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(1);
        }
    }

    public static boolean registerShop(String name, String address, String phoneNumber, String description)
    {
        try
        {
            PreparedStatement statement = getDatabaseConnection().prepareStatement("INSERT INTO DirectoryEntry (name, address, phone, description) VALUES (?, ?, ?, ?);");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phoneNumber);
            statement.setString(4, description);

            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static List<DirectoryEntry> getAllDirectoryEntrys() throws SQLException
    {
        PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT * FROM DirectoryEntry;");
        ResultSet resultSet = statement.executeQuery();

        List<DirectoryEntry> DirectoryEntrys = new ArrayList<>();

        while (resultSet.next())
        {
            DirectoryEntrys.add(new DirectoryEntry(resultSet.getString("phone"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("description"), new HashMap<Integer, LocalTime[]>(), resultSet.getString("announcement")));
        }

        return DirectoryEntrys;
    }

    public static List<Activity> getAllActivities() throws SQLException{

        PreparedStatement statement = getDatabaseConnection().prepareStatement("SELECT * FROM Activities;");
        ResultSet resultSet = statement.executeQuery();

        List<Activity> Activities = new ArrayList<>();

        while (resultSet.next()){
            Activities.add(new Activity(resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("endAddress"), resultSet.getString("description"), resultSet.getString("phone"), Activity.Type.valueOf(resultSet.getString("type"))));
        }

        return Activities;
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
