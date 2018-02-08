package Eternity;

public class Eternity {

    public static int eFactorial(int x){
        int runningTotal = 1;
        if (x==0){
            return runningTotal;
        }
        else{
            return eFactorial(x-1, runningTotal*x);
        }
    }

    private static int eFactorial(int x, int runningTotal){
        if (x==0){
            return runningTotal;
        }
        else{
            return eFactorial(x-1, runningTotal*x);
        }
    }

    /*
    //To be developed by Brandon Handfield
    public static double eExpEuler(double x){

    }

    //To be developed by Daniel Witkowski
    public static double eLog(double x){

    }

    //To be developed by Edip Tac
    public static double eBaseTenExp(double x){

    }

    //To be developed by Jonathan Bedard Schami
    public static double eCos(double x){

    }

    //To be developed by Julien Fagnan
    public static double eExpY(double x, double y){

    }
    */

    public static void main(String[] args) {
        System.out.println(eFactorial(10));
        System.out.println(eFactorial(3));
    }
}