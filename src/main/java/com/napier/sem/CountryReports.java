package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountryReports {

    public Connection con = null;

    //Sets database connection
    void setConnection(Connection con) {
        this.con = con;
    }

    //Countries In order of largest to smallest population in the world
    void countryPopulationsForWorld() {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the world:");
        this.produceReport(strSelect, "the world");
    }

    //Countries In order of largest to smallest population for a continent
    void countryPopulationsForAContinent(String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the continent of " + continent + ":");
        this.produceReport(strSelect, continent);
    }

    //Countries In order of largest to smallest population for a region
    void countryPopulationsForARegion(String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = '" + region + "' ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the region of " + region + ":");
        this.produceReport(strSelect, region);
    }


    //Top N populated countries in the world
    void topNCountryPopulationsForWorld(int n) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + n;
        System.out.println("\nTop " + n + " countries In order of largest to smallest population in the world:");
        this.produceReport(strSelect, "the world");
    }

    //Top N populated countries for a continent
    void topNCountryPopulationsForAContinent(int n, String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC LIMIT " + n;
        System.out.println("\nTop " + n + " Countries In order of largest to smallest population in the continent of " + continent + ":");
        this.produceReport(strSelect, continent);
    }

    //Top N populated countries for a region
    void topNCountryPopulationsForARegion(int n, String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = '" + region + "' ORDER BY Population DESC LIMIT " + n;
        System.out.println("\nTop " + n + " countries In order of largest to smallest population in the region of " + region + ":");
        this.produceReport(strSelect, region);
    }


    //Produces a Country Report by executing an SQL query and then printing the results/appropriate error message to console
    void produceReport(String strSelect, String type) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSelect);

            if (resultSet.getFetchSize() == 0) System.out.println("Couldn't find countries in " + type + " in world DB");

            while(resultSet.next())
                System.out.println(resultSet.getString("code") + ", " + resultSet.getString("name") + ", " + resultSet.getString("continent") + resultSet.getString("region") + ", " + resultSet.getInt("population") + ", " + resultSet.getInt("capital"));

        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }

}