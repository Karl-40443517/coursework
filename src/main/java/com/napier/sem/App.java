package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class App {

    public Connection con = null;

    public static void main(String[] args) {

        int n = 5;
        App a = new App();

        //attempts to connect application to the world database
        if(args.length < 1)
            a.connect("localhost:33060", 30000);
        else
            a.connect(args[0], Integer.parseInt(args[1]));

        //Creates CountryReports objects and connects it to the world database
        CountryReports countryReports = new CountryReports();
        countryReports.setConnection(a.con);
        new File("./Documents/reports/Country Report.md").mkdir();

        //Countries In order of largest to smallest population for the following locations: world, a continent , a region
        //https://github.com/Karl-40443517/coursework/issues/5
        countryReports.countryPopulationsForWorld();
        countryReports.countryPopulationsForAContinent("Africa");
        countryReports.countryPopulationsForARegion("Central Africa");

        //Top N populated countries for the following locations: world, a continent , a region
        //https://github.com/Karl-40443517/coursework/issues/4
        countryReports.topNCountryPopulationsForWorld(a.validateN(n));
        countryReports.topNCountryPopulationsForAContinent(a.validateN(n), "Africa");
        countryReports.topNCountryPopulationsForARegion(a.validateN(n),"Central Africa");



        //Creates CityReports objects and connects it to the world database
        CityReports cityReports = new CityReports();
        cityReports.setConnection(a.con);

        //https://github.com/Karl-40443517/coursework/issues/3
        //Cities In order of largest to smallest population for the following locations: world, a continent , a region, a country, a district
        cityReports.descendingCityWorldPop();
        cityReports.descendingCityContinentPop("Africa");
        cityReports.descendingCityRegionPop("Central Africa");
        cityReports.descendingCityCountryPop("Angola");
        cityReports.descendingCityDistrictPop("Benguela");

        //https://github.com/Karl-40443517/coursework/issues/6
        //Top N populated cities for the following locations: world, a continent , a region, a country, a district
        cityReports.topPopulatedCityWorld(a.validateN(n));
        cityReports.topPopulatedCityContinent(a.validateN(n),"Africa");
        cityReports.topPopulatedCityRegion(a.validateN(n),"Central Africa");
        cityReports.topPopulatedCityCountry(a.validateN(n), "Angola");
        cityReports.topPopulatedCityDistrict(a.validateN(n),"Benguela");


        //disconnects application from world database
        a.disconnect();
    }


    public void connect(String location, int delay) {
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
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
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


    public void disconnect() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (Exception var2) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public int validateN(int n) {
        if (n < 0) n *= -1;
        return n;
    }


}