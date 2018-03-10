package Eternity;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

    private final static semanticsParser parser = new semanticsParser(0.000000001);
    private final static ArrayList<Function> customFunctions = new ArrayList<>();

    public static void main(String[] args) {

        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);

        double X, P, Frac, I, L;
        X = 1;
        Frac = X;
        P = (1.0+X);
        I = 1.0;

        do{
            I++;
            Frac*=(X/I);
            L = P;
            P+=Frac;
        }while(L != P);

        System.out.println(P);

        System.out.println("Welcome to the calculator. Please your equation, or type 'q' to quit:");
        System.out.println("Note the following syntax:\nCos\nLog\nLn\nTen\nExp");
        Scanner s = new Scanner(System.in);
        while(true) {
            String input = s.next();
            if(input.equals("q"))
                break;
            else{
                try {
                    double result = new ExpressionBuilder(input)
                            .functions(customFunctions)
                            .operator(parser.eFactorial)
                            .build()
                            .evaluate();

                    System.out.println(result);
                } catch (java.lang.IllegalArgumentException error){
                    System.out.println(error.getMessage());
                }
            }


        }
        /*
        while (true) {
            System.out.println("\nSelect a function ('q' to quit):\n1. log(x)\n2. e^x\n3. 10^x\n4. cos(x)\n5. x^y");
            String choice = s.next();

            if (choice.equals("1")) {
                System.out.println("log(x) selected. Please enter x:");
                String input = s.next();
                double x = 0;
                try{
                   x = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                if(engine.eLog(x)==-1) {
                    continue;
                }
                System.out.println("log(" + x + ") = " + engine.eLog(x));
            }
            else if (choice.equals("2")) {
                System.out.println("e^x selected. Please enter x:");
                String input = s.next();
                double x = 0;
                try{
                    x = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                System.out.println("e^" + x + " = " + engine.eEulerExp(x));
            }
            else if (choice.equals("3")) {
                System.out.println("10^x selected. Please enter x:");
                String input = s.next();
                double x = 0;
                try{
                    x = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                System.out.println("10^" + x + " = " + engine.eBaseTenExp(x));
            }
            else if (choice.equals("4")) {
                System.out.println("cos(x) selected. Please enter x:");
                String input = s.next();
                double x = 0;
                try{
                    x = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                System.out.println("cos(" + x + ") = " + engine.eCos(x));
            }
            else if (choice.equals("5")) {
                System.out.println("x^y selected. Please enter x:");
                String input = s.next();
                double x = 0;
                try{
                    x = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                System.out.println("Please enter y:");
                input = s.next();
                double y = 0;
                try{
                    y = Double.parseDouble(input);
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }
                if(x%1.0 == 0 && y%1.0 == 0)
                    System.out.println(x + "^" + y + " = " + engine.eExpY(x, (long)y));
                else
                    System.out.println(x + "^" + y + " = " + engine.eExpY(x, y));
            }
            else if (choice.equalsIgnoreCase("q")){
                break;
            }
            else{
                System.out.println("Invalid input");
            }
        }*/
    }
}
