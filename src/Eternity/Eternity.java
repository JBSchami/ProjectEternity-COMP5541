package Eternity;

public class Eternity {
    /**
     * Factorial is required for many of the other functions
     * being developed. This function is a starting point
     * Future implementations will overcome system variable
     * limitations.
     *
     * Used for the initial call
     *
     * @author Jonathan Bedard Schami
     * @param x value of which to find factorial
     * @return factorial value of x
     */
    public static int eFactorial(int x){
        int runningTotal = 1;
        if (x==0){
            return runningTotal;
        }
        else{
            return eFactorial(x-1, runningTotal*x);
        }
    }

    /**
     * Factorial is required for many of the other functions
     * being developed. This function is a starting point
     * Future implementations will overcome system variable
     * limitations.
     *
     * Used for the tail recursion calls
     *
     * @author Jonathan Bedard Schami
     * @param x value of which to find factorial
     * @param runningTotal the accumulated value
     * @return factorial value of x
     */
    private static int eFactorial(int x, int runningTotal){
        if (x==0){
            return runningTotal;
        }
        else{
            return eFactorial(x-1, runningTotal*x);
        }
    }

    /**
     * Most functions require natural number power function
     * @author Julien Fagnan
     * @param x base number
     * @param y exponent number
     * @return the value of base x to the power of y
     */
	public static double eExpY(double x, long y) {
		double z;
		if (y == 0) {
			return 1;
		} else if (y % 2 == 0) {
			z = eExpY(x, y/2);
			return z * z;
		} else {
			z = eExpY(x, (y-1)/2);
			return x * z * z;
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
    */

    /**
     * Cos(x) = sum(from n = 0 to n = infinity) of :
     *  (((-1)^n)/(2n!))*(x^2n)
     *
     *  Currently limited due to system limitations.
     *
     * @author Jonathan Bedard Schami
     * @param x the angle in radians which we want to obtain the cosine of
     * @param precision the precision to which we want to calculate the value
     * @return the value to the precision determined.
     */
    public static double eCos(double x, double precision){
        double temp;
        double retVal = 0;
        double delta;
        int n = 0;
        do{
            temp = retVal;
            retVal = temp + (eExpY((-1), n)*eExpY(x, 2*n))/eFactorial(2*n);
            n++;
            if (retVal-temp < 0)
                delta = (-1)*(retVal-temp);
            else
                delta = retVal-temp;
        }while((delta) > precision);
        return retVal;
    }

    /*
    //To be developed by Julien Fagnan
    public static double eExpY(double x, double y){
		return eExpEuler(y * eLog(x));
    }
    */

    //Developped by Julien Fagnan
	//Ramanujan-Sato formulas
    /*
	public static final double ePI(){
	double e = 0, res = 0;
	for (int i = 0; i<5; i++) {
		e += eFactorial(4*i) * (26390 * i + 1103) / (eExpY(eFactorial(i),i) * eExpY(396,4*i));
	}
	res = 9801 / (e * eExpY(8, (long)0.5));
	return res;
	}
    */

    public static void main(String[] args) {
        
    }
}
