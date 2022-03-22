package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountryReports_Population {
    public Connection con = null;

    public CountryReports_Population() {
    }

    void setConnection(Connection con) {
        this.con = con;
    }

    void countryPopulationsForWorld() {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the world:");
        this.produceReport(strSelect);
    }

    void countryPopulationsForARegion(String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = '" + region + "' ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the region of " + region + ":");
        this.produceReport(strSelect);
    }

    void countryPopulationsForAContinent(String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";
        System.out.println("\nCountries In order of largest to smallest population in the continent of " + continent + ":");
        this.produceReport(strSelect);
    }

    void produceReport(String strSelect) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSelect);

            while(resultSet.next()) {
                System.out.println(resultSet.getString("code") + ", " + resultSet.getString("name") + ", " + resultSet.getString("continent") + resultSet.getString("region") + ", " + resultSet.getInt("population") + ", " + resultSet.getInt("capital"));
            }
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
            System.out.println("Couldn't find continent in DB");
        }

    }
}