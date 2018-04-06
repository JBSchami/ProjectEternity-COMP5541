package Eternity.Logic;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

public class SemanticsParser {

    private static EternityEngine engine;

    /**
     * Default Constructor
     */
    public SemanticsParser(){
        engine  = new EternityEngine(0.000000001, true);
    }

    /**
     * Partial constructor, sets precision
     * @param precision desired precision
     */
    public SemanticsParser(double precision){
        engine = new EternityEngine(precision);
    }

    /**
     * Full constructor, sets precision and radian setting
     * @param precision the desired precision
     * @param rads true if radians, false if degree
     */
    public SemanticsParser(double precision, boolean rads){
        engine = new EternityEngine(precision, rads);
    }

    /**
     * Sets the angle type.
     * @param value True if radians, false if degree
     */
    public void setEngineAngle(boolean value){
        engine.setRads(value);
    }

    /**
     * Sets the engine precision
     * @param value
     */
    public void setEnginePrecision(double value) {engine.setPrecision(value);}

    /**
     * Returns the precision used for calculations
     * @return the precision of the engine.
     */
    public int getEnginePrecision(){
        double temp = engine.getPrecision();
        String value = Double.toString(temp);
        if(value.contains("E")){
            String decimals[] = value.split("E-");
            return Integer.parseInt(decimals[1]);
        }
        else{
            String decimals[] = value.split("\\.");
            return decimals[1].length();
        }
    }

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eFactorial
     */
    public Operator eFactorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
        @Override
        public double apply(double... args) {
            final int arg = (int) args[0];
            if((double)arg != args[0]){
                throw new IllegalArgumentException("Operand for factorial must be an integer");
            }
            if(arg < 0){
                throw new IllegalArgumentException("Operand for factorial cannot be less than 0");
            }
            return engine.eFactorial(arg);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eExpY
     */
    public Operator eExpY = new Operator("^", 2, false, Operator.PRECEDENCE_POWER+1) {
        @Override
        public double apply(double... args) {
            if(args[0]-(int)args[0] == 0 && args[1]-(int)args[1] ==0){
                return engine.eExpY(args[0], (long)args[1], false);
            }
            return engine.eExpY(args[0], args[1]);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eCos
     */
    public Function eCos = new Function("Cos", 1){
        @Override
        public double apply(double... args){
            return engine.eCos(args[0]);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eEulerExp
     */
    public Function eEulerExp = new Function("EulerExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eEulerExp(args[0]);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eBaseTenExp
     */
    public Function eBaseTenExp = new Function("BaseTenExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eBaseTenExp(args[0]);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eLog
     */
    public Function eLog = new Function("Log", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLog(args[0]);
        }
    };

    /**
     * Used to overwrite the Exp4J default behavior with our own function: eNaturalLog
     */
    public Function eNaturalLog = new Function("Ln", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLn(args[0]);
        }
    };

    /**
     * replaces the user friendly display text in the equation with our custom functions
     */
    public String preFormatInput(String input){
        String formatted = input;
        formatted = formatted.replace("cos", "Cos");
        formatted = formatted.replace("ln", "Ln");
        formatted = formatted.replace("e^","EulerExp");
        formatted = formatted.replace("log", "Log");
        formatted = formatted.replace("10^", "BaseTenExp");
        return formatted;
    }
}
