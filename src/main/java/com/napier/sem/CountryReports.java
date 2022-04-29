package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountryReports {

    public Connection con = null;
    public BufferedWriter writer;

    {
        try {
            writer = new BufferedWriter(new FileWriter(new File("./Documents/reports/" + "Country Reports.md")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Sets database connection
    void setConnection(Connection con) {
        this.con = con;
    }

    //Countries In order of largest to smallest population in the world
    void countryPopulationsForWorld() {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
        String title = "\nCountries In order of largest to smallest population in the world:";
        this.produceReport(strSelect, "the world", title);
    }

    //Countries In order of largest to smallest population for a continent
    void countryPopulationsForAContinent(String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";
        String title = "\nCountries In order of largest to smallest population in the continent of " + continent + ":";
        this.produceReport(strSelect, continent, title);
    }

    //Countries In order of largest to smallest population for a region
    void countryPopulationsForARegion(String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = '" + region + "' ORDER BY Population DESC";
        String title = "\nCountries In order of largest to smallest population in the region of " + region + ":";
        this.produceReport(strSelect, region, title);
    }


    //Top N populated countries in the world
    void topNCountryPopulationsForWorld(int n) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + n;
        String title = "\nTop " + n + " countries In order of largest to smallest population in the world:";
        this.produceReport(strSelect, "the world", title);
    }

    //Top N populated countries for a continent
    void topNCountryPopulationsForAContinent(int n, String continent) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC LIMIT " + n;
        String title = "\nTop " + n + " Countries In order of largest to smallest population in the continent of " + continent + ":";
        this.produceReport(strSelect, continent, title);
    }

    //Top N populated countries for a region
    void topNCountryPopulationsForARegion(int n, String region) {
        String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = '" + region + "' ORDER BY Population DESC LIMIT " + n;
        String title =  "\nTop " + n + " countries In order of largest to smallest population in the region of " + region + ":";
        this.produceReport(strSelect, region, title);
    }


    //Produces a Country Report by executing an SQL query and then printing the results/appropriate error message to console
    void produceReport(String strSelect, String type, String title) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSelect);
            StringBuilder sb = new StringBuilder();

            if (resultSet.first() == false) System.out.println("Couldn't find countries in " + type + " in world DB");
            resultSet.beforeFirst();


            sb.append("<br />" + title.toString() + "<br />");
            System.out.println(title.toString());

            while(resultSet.next()) {
                sb.append(resultSet.getString("code") + ", " + resultSet.getString("name") + ", " + resultSet.getString("continent") + resultSet.getString("region") + ", " + resultSet.getInt("population") + ", " + resultSet.getInt("capital") + "<br />");
                System.out.println(resultSet.getString("code") + ", " + resultSet.getString("name") + ", " + resultSet.getString("continent") + resultSet.getString("region") + ", " + resultSet.getInt("population") + ", " + resultSet.getInt("capital"));
            }

            writer.write(sb.toString());
            //writer.close();

        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }

}