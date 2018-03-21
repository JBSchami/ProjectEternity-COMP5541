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

}