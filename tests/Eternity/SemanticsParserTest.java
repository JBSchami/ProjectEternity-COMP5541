package Eternity;

import Eternity.Logic.SemanticsParser;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SemanticsParserTest {
    SemanticsParser parser = new SemanticsParser();

    @Test
    public void setEngineAngle() {
    }

    @Test
    public void preFormatInputLn() {
        assertEquals("Cos", parser.preFormatInput("cos"));
    }

    @Test
    public void preFormatEulerExp() {
        assertEquals("EulerExp", parser.preFormatInput("e^"));
    }

    @Test
    public void preFormatLog() {
        assertEquals("Log", parser.preFormatInput("log"));
    }

    @Test
    public void preFormatBaseTenExp() {
        assertEquals("BaseTenExp", parser.preFormatInput("10^"));
    }

    @Test
    public void preFormatEulerExpWithPreString() {
        assertEquals("7EulerExp3", parser.preFormatInput("7e^3"));
    }

    @Test
    public void setEngineAngle1() {
    }

    @Test
    public void setEnginePrecision() {
        parser.setEnginePrecision(0.000001);

    }

    @Test
    public void useFunctionEulerExp(){
        ArrayList<Function> customFunctions = new ArrayList<>();
        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);
        double result = new ExpressionBuilder("7EulerExp3")
                .functions(customFunctions)
                .operator(parser.eFactorial, parser.eExpY)
                .build()
                .evaluate();
        assertEquals(140.5987584623136741864997075820720252789153548698790510106,result,0.000000001);
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