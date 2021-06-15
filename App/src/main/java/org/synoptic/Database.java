package org.synoptic;
import java.sql.*;

    public class Database {

        private static Connection databaseConnection = null;

        // (DG) - RUN THIS BEFORE EVERY SINGLE QUERY
        public static void BeforeQuery() {

            if(!Database.connectDatabase()) {
                System.out.println("Database connection failed for an unknown reason.");
            }

        }
        // (DG) - Return the Database Connection
        public static Connection getDatabaseConnection() {
            return databaseConnection;
        }

        // (DG) - Create a Database Connection. Returns true upon successful creation, terminates program upon failure.
        public static boolean connectDatabase() {

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                databaseConnection = DriverManager.getConnection("jdbc:mariadb://81.104.43.46:3306/ecoSwellDatabase", "appUser", "9iPTs#ko");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(1);
            }
            return true;

        }
    }
