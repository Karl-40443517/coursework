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
    void testGetEmployee()
    {
        CR.topNCountryPopulationsForWorld(20);
    }
}