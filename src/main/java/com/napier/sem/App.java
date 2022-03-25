package com.napier.sem;

import javax.xml.transform.Result;
import java.sql.*;

public class App {
    public static void main(String[] args) {
        //Sets the user defined n
        int n = 10;

        //Intiliasing variables for query examples
        String loc = "world";
        String continent = "Asia";
        String region = "Middle East";
        String country = "USA";
        String district = "England";
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();


        //Feature 1 - World Reports (World Populations)
        a.descendingWorldPop();
        a.descendingContinentPop(continent);
        a.descendingRegionPop(region);


        //Feature 3 - City Reports (City Populations)
        a.descendingCityWorldPop();
        a.descendingCityContinentPop(continent);
        a.descendingCityRegionPop(region);
        a.descendingCityCountryPop(country);
        a.descendingCityDistrictPop(district);


        //Feature 4 - City Reports (Top N City Populations)
        a.topPopulatedCityWorld(n);
        a.topPopulatedCityContinent(n,continent);
        a.topPopulatedCityRegion(n,region);
        a.topPopulatedCityCountry(n,country);
        a.topPopulatedCityDistrict(n, district);


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
            System.out.println("Failed to get country details");
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

    //Prints country populations from largest to smallest
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
            // Return new country if valid.
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

    //Prints country populations in a given continent from largest to smallest
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
            // Return new country if valid.
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

    //Prints country populations in a given region from largest to smallest
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
            // Return new country if valid.
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

    //Prints city populations from largest to smallest
    void descendingCityWorldPop(){
        try {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            System.out.println("Descending order of country populations in the world");
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                city.name = rset.getString("Name");
                city.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(city.name + "," + city.population + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details in the world");
        }
    }

    //Prints city populations in a given continent from largest to smallest
    void descendingCityContinentPop(String continent){
        try {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Continent, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'" + continent + "'"
                            + "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city and country if valid.
            // Check one is returned
            System.out.println("Descending order of city populations in " + continent);
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                Country country = new Country();
                city.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                city.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(city.name + "," + country.continent + "," + city.population + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details in " + continent);
        }
    }

    //Prints city populations in a given region from largest to smallest
    void descendingCityRegionPop(String region){
        try {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Region, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'" + region + "'"
                            + "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city and country if valid.
            // Check one is returned
            System.out.println("Descending order of city populations in the " + region);
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                Country country = new Country();
                city.name = rset.getString("Name");
                country.region = rset.getString("Region");
                city.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(city.name + "," + country.region + "," + city.population + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details in " + region);
        }
    }

    //Prints city populations in a given country from largest to smallest
    void descendingCityCountryPop(String countryCode){
        try {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Code, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND city.CountryCode=" + "'" + countryCode + "'"
                            + "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country and city if valid.
            // Check one is returned
            System.out.println("Descending order of city populations in " + countryCode);
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                Country country = new Country();
                city.name = rset.getString("Name");
                country.code = rset.getString("Code");
                city.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(city.name + "," + country.code + "," + city.population + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details in " + countryCode);
        }
    }

    //Prints city populations in a given district from largest to smallest
    void descendingCityDistrictPop(String district){
        try {

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name,District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country and city if valid.
            // Check one is returned
            System.out.println("Descending order of city populations in the " + district);
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();

                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");


                //Outputs result of query

                System.out.println(city.name + "," + city.district + "," + city.population + "\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details in " + district);
        }
    }

    //Prints top populated cities in a given limit
    void topPopulatedCityWorld(int n) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT ID, Name, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("Top 10 populated cities in the world");
            while (rset.next()) {
                //object creation Not needed in this example
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println(city.id + "," + city.name + "," + city.population + "\n");
            }
        }

        // Execute SQL statement

            catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

    //Prints top populated cities in a given limit within a continent
    void topPopulatedCityContinent(int n, String continent) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();


            String strSelect =
                    "SELECT city.ID, city.Name, country.Continent, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'"+ continent+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("Top 10 populated cities in " + continent);
            while (rset.next()) {

                City city = new City();
                Country country = new Country();

                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println(city.id + "," + city.name + "," + country.continent + "," + city.population + "\n");
            }
        }

        // Execute SQL statement

        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

    //Prints top populated cities in a given limit within a region
    void topPopulatedCityRegion(int n, String region) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();


            String strSelect =
                    "SELECT city.ID, city.Name, country.Region, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'"+ region+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("Top 10 populated cities in " + region);
            while (rset.next()) {

                City city = new City();
                Country country = new Country();

                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                country.region = rset.getString("Region");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println(city.id + "," + city.name + "," + country.region + "," + city.population + "\n");
            }
        }

        // Execute SQL statement

        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

    //Prints top populated cities in a given limit within a country
    void topPopulatedCityCountry(int n, String country) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();


            String strSelect =
                    "SELECT ID, Name, CountryCode, Population "
                            + "FROM city "
                            + "WHERE CountryCode=" + "'"+ country+ "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("Top 10 populated cities in " + country);
            while (rset.next()) {

                City city = new City();

                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println(city.id + "," + city.name + "," + city.countryCode + "," + city.population + "\n");
            }
        }

        // Execute SQL statement

        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

    //Prints top populated cities in a given limit within a district
    void topPopulatedCityDistrict(int n, String district) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();


            String strSelect =
                    "SELECT ID, Name, District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("Top 10 populated cities in " + district);
            while (rset.next()) {

                City city = new City();

                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                //Outputs result of query
                System.out.println(city.id + "," + city.name + "," + city.district + "," + city.population + "\n");
            }
        }

        // Execute SQL statement

        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }
    }

}




