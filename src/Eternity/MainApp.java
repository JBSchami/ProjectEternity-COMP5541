package Eternity;

import java.util.Scanner;

public class MainApp {

    static int precision = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to the calculator. Please type in the number of the function you would like to compute, or type 'q' to quit:");
        Scanner s = new Scanner(System.in);

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
                if(Eternity.eLog(x)==-1) {
                    continue;
                }
                System.out.println("log(" + x + ") = " + Eternity.roundNumber(Eternity.eLog(x), precision));
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
                System.out.println("e^" + x + " = " + Eternity.exponentialCalc(x));
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
                System.out.println("10^" + x + " = " + Eternity.eBaseTenExp(x));
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
                System.out.println("cos(" + x + ") = " + Eternity.eCos(x, 0.00001));
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
                System.out.println(x + "^" + y + " = " + Eternity.eExpY(x, y));
            }
            else if (choice.equalsIgnoreCase("q")){
                break;
            }
            else{
                System.out.println("Invalid input");
            }

        }
    }

}
