package Eternity;

public class Testing {
    public static void main(String[] args) {
        printSeperator();
        System.out.println("TEST log(x) - START");
        printSeperator();
        System.out.println("log(100)  = 2           vs test output: " + Eternity.eLog(100, 0.000001));
        System.out.println("log(1235) = 3.091666958 vs test output: " + Eternity.eLog(1235, 0.000001));
        System.out.println("log(-40)  = -Infinity   vs test output: " + Eternity.eLog(-40, 0.000001));
        printSeperator();
        
        System.out.println(Eternity.eFactorial(22));
        printSeperator();
        
        System.out.println("TEST e^x - START");
        printSeperator();
        System.out.println("e^(-1)    = 0.367879441 vs test output: " + Eternity.eEulerExp(-1, 0.000001));
        System.out.println("e^(20)    = 485165195.4 vs test output: " + Eternity.eEulerExp(20, 0.1));
        System.out.println("e^(4.65)  = 104.5849856 vs test output: " + Eternity.eEulerExp(4.65, 0.000001));
        System.out.println("e^(-6.12) = 0.002198456 vs test output: " + Eternity.eEulerExp(-6.12, 0.000001));

        printSeperator();
        System.out.println("TEST 10^x - START");
        printSeperator();
        System.out.println("10^(5)      = 1000000     vs test output: " + Eternity.eBaseTenExp(5, 0.000001));
        System.out.println("10^(-5)     = 0.00001     vs test output: " + Eternity.eBaseTenExp(-5, 0.00000001));
        System.out.println("10^(7.1234) = 13286175.96 vs test output: " + Eternity.eBaseTenExp(7.1234, 0.000001));
        System.out.println("10^(-1.901) = 0.012473835 vs test output: " + Eternity.eBaseTenExp(-1.901, 0.000001));
        System.out.println("10^(0)      = 1           vs test output: " + Eternity.eBaseTenExp(0, 0.000001));

        printSeperator();
        System.out.println("TEST cos(x) - START");
        printSeperator();
        for(int i = 0; i <= 360; i+=15){
            System.out.println("Cos(" + i +") = " + Math.cos(Math.toRadians(i)) + " vs test output: " + Eternity.eCos((double)i, 0.00001));
        }

        printSeperator();
        System.out.println("TEST x^y - START");
        printSeperator();
        System.out.println("2^4      = 16          vs test output: " + Eternity.eExpY(2, 4));
        System.out.println("2.5^4.16 = 45.23040079 vs test output: " + Eternity.eExpY(2.5, (double)4.16, 0.000001));
        System.out.println("3.4^-2.06 = 0.080381004 vs test output: " + Eternity.eExpY(3.4, (double)-2.06, 0.000001));
    }

    private static void printSeperator(){
        System.out.println("=========================================================");
    }
}
