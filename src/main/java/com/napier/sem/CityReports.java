package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CityReports {

    public Connection con = null;

    BufferedWriter writer;

    {
        try {
            writer = new BufferedWriter(new FileWriter(new File("./Documents/reports/City Reports.md")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

            String title = "\nDescending order of city populations in the world";
        this.produceReport(strSelect, "the world", title);

    }

    //Prints city populations in a given continent from largest to smallest
    void descendingCityContinentPop(String continent){

            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'" + continent + "'"
                            + "ORDER BY city.Population DESC";


        String title = "\nDescending order of city populations in " + continent;
        this.produceReport(strSelect, continent, title);
    }

    //Prints city populations in a given region from largest to smallest
    void descendingCityRegionPop(String region){

            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population  "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'" + region + "'"
                            + "ORDER BY city.Population DESC";


        String title = "\nDescending order of city populations in the " + region;
        this.produceReport(strSelect, region, title);
    }

    //Prints city populations in a given country from largest to smallest
    void descendingCityCountryPop(String country){

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode=" + "'"+ getCountryIDFromName(country) + "'"
                            + "ORDER BY Population DESC ";

        String title = "\nDescending order of city populations in " + country;
        this.produceReport(strSelect, country, title);
    }

    //Prints city populations in a given district from largest to smallest
    void descendingCityDistrictPop(String district){

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC";

        String title = "\nDescending order of city populations in the " + district;
        this.produceReport(strSelect, district, title);
    }

    //Prints top populated cities in a given limit
    void topPopulatedCityWorld(int n) {

            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;

        String title = "\n Top " + n + " populated cities in the world";
        this.produceReport(strSelect, "the world", title);
    }

    //Prints top populated cities in a given limit within a continent
    void topPopulatedCityContinent(int n, String continent) {



            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Continent=" + "'"+ continent+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;

        String title = "\nTop " + n + " populated cities in " + continent;
        this.produceReport(strSelect, continent, title);

    }

    //Prints top populated cities in a given limit within a region
    void topPopulatedCityRegion(int n, String region) {


            String strSelect =
                    "SELECT city.Name, city.CountryCode, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode=country.Code AND Region=" + "'"+ region+ "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;

        String title = "\nTop " + n + " populated cities in " + region;
        this.produceReport(strSelect, region, title);

    }

    //Prints top populated cities in a given limit within a country
    void topPopulatedCityCountry(int n, String country) {

            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode=" + "'"+ getCountryIDFromName(country) + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
        String title = "\nTop " + n + " populated cities in " + country;
            this.produceReport(strSelect, country, title);

    }

    //Prints top populated cities in a given limit within a district
    void topPopulatedCityDistrict(int n, String district) {


            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE District=" + "'" + district + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;

        String title = "\nTop " + n + " populated cities in " + district;
            this.produceReport(strSelect, district, title);


    }


    //Produces a Country Report by executing an SQL query and then printing the results/appropriate error message to console
    void produceReport(String strSelect, String type, String title) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSelect);
            StringBuilder sb = new StringBuilder();



            if (resultSet.first() == false) System.out.println("Couldn't find cities in " + type + " in world DB");
            resultSet.beforeFirst();

            sb.append("<br />" + title.toString() + "<br />");
            System.out.println(title.toString());

            while(resultSet.next()) {
                System.out.println(resultSet.getString("name") + ", " + getCountryNameFromID(resultSet.getString("CountryCode")) + ", " + resultSet.getString("district") + ", " + resultSet.getString("population") );
                sb.append(resultSet.getString("name") + ", " + getCountryNameFromID(resultSet.getString("CountryCode")) + ", " + resultSet.getString("district") + ", " + resultSet.getString("population")+ "<br />");
            }

            writer.write(sb.toString());



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
