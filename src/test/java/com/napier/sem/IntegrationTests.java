package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests
{
    static App a;
    static CountryReports CR;

    @BeforeAll
    static void init()
    {
        a = new App();
        a.connect("localhost:33060", 30000);
        CR = new CountryReports();
        CR.setConnection(a.con);
    }

    @Test
    void testCountryReportInvalidContinent()
    {
        CR.countryPopulationsForAContinent("Africccca");
    }

    @Test
    void testCountryReportInvalidRegion()
    {
        CR.countryPopulationsForARegion("Central Africaaaa");
    }

    @Test
    void testTopNCountryReportInvalidContinent()
    {
        CR.countryPopulationsForAContinent("Africccca");
    }

    @Test
    void testTopNCountryReportInvalidRegion()
    {
        CR.countryPopulationsForARegion("Central Africaaaa");
    }

    @Test
    void testCountryReportContinentNegativeN()
    {
        CR.topNCountryPopulationsForAContinent(a.validateN(-10), "Africa");
    }

    @Test
    void testCountryReportRegionNegativeN()
    {
        CR.topNCountryPopulationsForARegion(a.validateN(-10),"Central Africa");
    }



}