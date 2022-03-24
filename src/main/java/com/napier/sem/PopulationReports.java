package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PopulationReports {
    public Connection con = null;

    void setConnection(Connection con) {
        this.con = con;
    }

    void worldPopulation() {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nPopulation report for the world:");
        this.produceReport(strSelect);
    }

    void continentPopulation(String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nPopulation report for the continent of " + continent + ":");
        this.produceReport(strSelect);
    }

    void regionPopulation(String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nPopulation report for the region of " + region + ":");
        this.produceReport(strSelect);
    }

    void countryPopulation(String country) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nPopulation report for the country of " + country + ":");
        this.produceReport(strSelect);
    }

    void districtPopulation(String district) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        System.out.println("\nPopulation report for the district of " + district + ":");
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
