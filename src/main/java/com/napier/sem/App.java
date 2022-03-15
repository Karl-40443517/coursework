package com.napier.sem;

import javax.xml.transform.Result;
import java.sql.*;

public class App {
    public static void main(String[] args) {
        //Sets the user defined n
        int n = 10;
        String loc = "world";
        String continent = "Asia";
        String region = "Middle East";
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();

        // Calls example method
        //a.exampleMethod(n);
        a.descendingWorldPop();
        a.descendingContinentPop(continent);
        a.descendingRegionPop(region);


        // Disconnect from database
        a.disconnect();
    }

    //Prints the country that has the ID that matches n
    void exampleMethod(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println("(Example Query) Country with an ID of " + ID + ":");
                System.out.println(city.name + ", " + city.countryCode + ", " + city.district + ", " + city.population + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

    //Prints the top N populated countries from specified location
    void topNPopulatedLocations(int n, String loc) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            String strSelect = "";

            //if location set to world
            if (loc == "world") {
                strSelect =
                        "SELECT ID, Name, Population "
                                + "FROM country "
                                + "ORDER BY Population "
                                + "LIMIT " + n;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }

    }

    //Connection to MySQL database.
    private Connection con = null;

    //Connect to the MySQL database.
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    //Disconnect from the MySQL database.
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    void descendingWorldPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            System.out.println("Descending order of country populations by World");
            while (rset.next()) {
                //object creation Not needed in this example
                Country country = new Country();
                country.name = rset.getString("Name");
                country.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(country.name + "," + country.population + "\n");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
        }
    }

    void descendingContinentPop(String continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name,Continent, Population "
                            + "FROM country "
                            + "WHERE Continent = " + "'" + continent + "'"
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            System.out.println("Descending order of country populations in " + continent);
            while (rset.next()) {
                //object creation Not needed in this example
                Country country = new Country();
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(country.name + "," + country.continent + "," + country.population + "\n");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get " + continent + " details");
        }
    }

    void descendingRegionPop(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name,Region, Population "
                            + "FROM country "
                            + "WHERE Region = " + "'" + region + "'"
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            System.out.println("Descending order of country populations in " + region);
            while (rset.next()) {
                //object creation Not needed in this example
                Country country = new Country();
                country.name = rset.getString("Name");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(country.name + "," + country.region + "," + country.population + "\n");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get " + region + " details");
        }
    }
}


