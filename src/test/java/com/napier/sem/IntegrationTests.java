package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests
{
    static App a;
    static CountryReports countryReports;
    static CityReports cityReports;

    @BeforeAll
    static void init()
    {
        a = new App();
        a.connect("localhost:33060", 30000);
        countryReports = new CountryReports();
        countryReports.setConnection(a.con);

        cityReports = new CityReports();
        cityReports.setConnection(a.con);
    }

    @Test //Tests an invalid Continent input
    void testCountryReportInvalidContinent()
    {
        countryReports.countryPopulationsForAContinent("Africccca");
    }

    @Test //Tests an invalid region input
    void testCountryReportInvalidRegion()
    {
        countryReports.countryPopulationsForARegion("Central Africaaaa");
    }


    @Test //Tests a report with a negative N input with a valid Continent
    void testCountryReportContinentNegativeN()
    {
        countryReports.topNCountryPopulationsForAContinent(a.validateN(-10), "Africa");
    }

    @Test //Tests a report with a negative N with an invalid region
    void testCountryReportRegionNegativeN()
    {
        countryReports.topNCountryPopulationsForARegion(a.validateN(-5),"sfsfsf Africa");
    }

    @Test //Tests a country name to ID conversion
    void testGetCountryIDFromName()
    {
        assertEquals("AGO",cityReports.getCountryIDFromName("Angola"));
    }

    @Test //Tests a country ID to name conversion
    void testGetCountryNameFromID()
    {
        assertEquals("Angola",cityReports.getCountryNameFromID("AGO"));
    }

}