package Eternity;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

public class semanticsParser {

    public semanticsParser(){
        engine  = new Eternity(0.000000001, true);
    }

    public semanticsParser(double precision, boolean rads){
        engine = new Eternity(precision, rads);
    }

    private static Eternity engine;

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

    public Function eCos = new Function("eCos", 1){
        @Override
        public double apply(double... args){
            return engine.eCos(args[0]);
        }
    };

    public Function eEulerExp = new Function("eEulerExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eEulerExp(args[0]);
        }
    };

    public Function eBaseTenExp = new Function("eBaseTenExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eBaseTenExp(args[0]);
        }
    };

    public Function eLog = new Function("eLog", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLog(args[0]);
        }
    };

    public Function eNaturalLog = new Function("eLn", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLn(args[0]);
        }
    };

    public Function eExpY = new Function("eExpY", 2) {
        @Override
        public double apply(double... args) {
            return engine.eExpY(args[0], args[1]);
        }
    };

    public String preFormatInput(String input){
        String formatted = input;
        formatted = formatted.replace("cos", "eCos");
        formatted = formatted.replace("ln", "eLn");
        formatted = formatted.replace("e^","eEulerExp");
        formatted = formatted.replace("log", "eLog");
        formatted = formatted.replace("10^", "eBaseTenExp");
        return formatted;
    }
}
