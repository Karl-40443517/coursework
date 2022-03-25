package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UnitTests
{
    static App a;

    @BeforeAll
    static void init()
    {
        a = new App();
    }

    @Test //Tests that validateN() returns a positive number when receiving a negative
    void testValidateNWhenNegetive()
    {
        assertEquals(a.validateN(-10), 10);
    }

    @Test //Tests that validateN() returns a positive number when receiving a positive
    void testValidateNWhenPositive()
    {
        assertEquals(a.validateN(10), 10);
    }
}