package com.napier.sem;

import java.sql.*;

public class App {
    private Connection con = null;


    public static void main(String[] args) {

        int n = 10;
        App a = new App();
        a.connect();

        CountryReports_Population b = new CountryReports_Population();
        b.setConnection(a.con);
        b.countryPopulationsForWorld();
        b.countryPopulationsForARegion("Central Africa");
        b.countryPopulationsForAContinent("Africa");

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
            

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException var4) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;

        for(int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");

            try {
                Thread.sleep(30000L);
                this.con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException var5) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(var5.getMessage());
            } catch (InterruptedException var6) {
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