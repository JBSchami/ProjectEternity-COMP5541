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

    Operator eFactorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
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

    Function eCos = new Function("Cos", 1){
        @Override
        public double apply(double... args){
            return engine.eCos(args[0]);
        }
    };

    Function eEulerExp = new Function("Exp",1) {
        @Override
        public double apply(double... args) {
            return engine.eEulerExp(args[0]);
        }
    };

    Function eBaseTenExp = new Function("Ten",1) {
        @Override
        public double apply(double... args) {
            return engine.eBaseTenExp(args[0]);
        }
    };

    Function eLog = new Function("Log", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLog(args[0]);
        }
    };

    Function eNaturalLog = new Function("Ln", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLn(args[0]);
        }
    };
}
