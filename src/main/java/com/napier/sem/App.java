package com.napier.sem;

import java.sql.*;

public class App {

    public Connection con = null;

    public static void main(String[] args) {

        int n = 5;
        App a = new App();

        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        CountryReports CR = new CountryReports();
        CR.setConnection(a.con);

        CR.countryPopulationsForWorld();
        CR.countryPopulationsForARegion("Central Africa");
        CR.countryPopulationsForAContinent("Africa");

        CR.topNCountryPopulationsForWorld(n);
        CR.topNCountryPopulationsForARegion(n,"Central Africa");
        CR.topNCountryPopulationsForAContinent(n, "Africa");

        a.disconnect();
    }

    void topNPopulatedLocations(int n, String loc) {
        try {
            Statement stmt = this.con.createStatement();
            String strSelect = "";
            if (loc == "world") {
                strSelect = "SELECT ID, Name, Population FROM country ORDER BY Population LIMIT " + n;
            }

            stmt.executeQuery(strSelect);
        } catch (Exception var6) {
            System.out.println(var6.getMessage());
            System.out.println("Failed to get City details");
        }

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
}