package Eternity;

import org.junit.Test;

import static org.junit.Assert.*;

public class SemanticsParserTest {
    SemanticsParser parser = new SemanticsParser();

    @Test
    public void setEngineAngle() {
    }

    @Test
    public void preFormatInputLn() {
        assertEquals("eCos", parser.preFormatInput("cos"));
    }

    @Test
    public void preFormatEulerExp() {
        assertEquals("eEulerExp", parser.preFormatInput("e^"));
    }

    @Test
    public void preFormatLog() {
        assertEquals("eLog", parser.preFormatInput("log"));
    }

    @Test
    public void preFormatBaseTenExp() {
        assertEquals("eBaseTenExp", parser.preFormatInput("10^"));
    }

    @Test
    public void preFormatGeneralExp() {
        assertEquals("eExpY(8,98)", parser.preFormatInput("9+8^(98)"));
    }


    @Test
    public void setEngineAngle1() {
    }

    @Test
    public void setEnginePrecision() {
    }

    @Test
    public void getEnginePrecision() {
        SemanticsParser nineDecimals = new SemanticsParser(0.000000001);
        assertEquals(9,nineDecimals.getEnginePrecision());

        SemanticsParser fourDecimals = new SemanticsParser(0.0001);
        assertEquals(4,nineDecimals.getEnginePrecision());

    }

    @Test
    public void preFormatInput() {
    }
}