package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CityReports {

    public Connection con = null;

    //Sets database connection
    void setConnection(Connection con) {
        this.con = con;
    }

    //Prints city populations from largest to smallest
    void descendingCityWorldPop(){

            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";

            System.out.println("\nDescending order of country populations in the world");
        this.produceReport(strSelect, "the world");

    }

    //Prints city populations in a given continent from largest to smallest
    void descendingCityContinentPop(String continent){

            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'" + continent + "'"
                            + "ORDER BY city.Population DESC";


            System.out.println("\nDescending order of city populations in " + continent);
        this.produceReport(strSelect, continent);
    }

    //Prints city populations in a given region from largest to smallest
    void descendingCityRegionPop(String region){

            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population  "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'" + region + "'"
                            + "ORDER BY city.Population DESC";


            System.out.println("\nDescending order of city populations in the " + region);
        this.produceReport(strSelect, region);
    }

    //Prints city populations in a given country from largest to smallest
    void descendingCityCountryPop(String country){

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode=" + "'"+ getCountryIDFromName(country) + "'"
                            + "ORDER BY Population DESC ";

            System.out.println("\nDescending order of city populations in " + country);
        this.produceReport(strSelect, country);
    }

    //Prints city populations in a given district from largest to smallest
    void descendingCityDistrictPop(String district){

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC";

            System.out.println("\nDescending order of city populations in the " + district);
        this.produceReport(strSelect, district);
    }

    //Prints top populated cities in a given limit
    void topPopulatedCityWorld(int n) {

            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;

            System.out.println("\n Top " + n + " populated cities in the world");
        this.produceReport(strSelect, "the world");
    }

    //Prints top populated cities in a given limit within a continent
    void topPopulatedCityContinent(int n, String continent) {



            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'"+ continent+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;

            System.out.println("\nTop " + n + " populated cities in " + continent);
        this.produceReport(strSelect, continent);

    }

    //Prints top populated cities in a given limit within a region
    void topPopulatedCityRegion(int n, String region) {


            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'"+ region+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;

            System.out.println("\nTop " + n + " populated cities in " + region);
        this.produceReport(strSelect, region);

    }

    //Prints top populated cities in a given limit within a country
    void topPopulatedCityCountry(int n, String country) {

            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode=" + "'"+ getCountryIDFromName(country) + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
            System.out.println("\nTop " + n + " populated cities in " + country);
            this.produceReport(strSelect, country);

    }

    //Prints top populated cities in a given limit within a district
    void topPopulatedCityDistrict(int n, String district) {


            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;

            System.out.println("\nTop " + n + " populated cities in " + district);
            this.produceReport(strSelect, district);


    }


    //Produces a Country Report by executing an SQL query and then printing the results/appropriate error message to console
    void produceReport(String strSelect, String type) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSelect);

            if (resultSet.first() == false) System.out.println("Couldn't find cities in " + type + " in world DB");
            resultSet.beforeFirst();

            while(resultSet.next()) System.out.println(resultSet.getString("name") + ", " + getCountryNameFromID(resultSet.getString("CountryCode")) + ", " + resultSet.getString("district") + ", " + resultSet.getString("population"));





        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }

    String getCountryNameFromID(String id){

        try {
            Statement stmt = this.con.createStatement();
            String strSelect =
                    "SELECT Name "
                            + "FROM country "
                            + "WHERE Code=" + "'" + id + "'";

            ResultSet resultSet = stmt.executeQuery(strSelect);
            while(resultSet.next()) return resultSet.getString("name");

        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }
        return "";
    }

    String getCountryIDFromName(String name) {

        try {
            Statement stmt = this.con.createStatement();
            String strSelect =
                    "SELECT Code "
                            + "FROM country "
                            + "WHERE Name=" + "'" + name + "'";

            ResultSet resultSet = stmt.executeQuery(strSelect);
            while (resultSet.next()) return resultSet.getString("code");

        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }
        return "";
    }

}
