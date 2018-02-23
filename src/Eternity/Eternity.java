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

    /**
     * eExpY works for non-integers
     * @author Julien Fagnan
     * @param x base number
     * @param y exponent number
     * @return the value of base x to the power of y
     */
    public static double eExpY(double x, double y){
        //converting ln to log
        double lnInLog = eLog(x)/(0.43429448190325182765112891891661);
        double values = y * lnInLog;
        return exponentialCalc(values);
    }

    /**
     * This is the function that performs e^x
     * @author Brandon Handfield
     * @param x The value for which we want to calculate the value
     * @return the value of e^x
     */
    public static double exponentialCalc(double x){

	    double answer = 0;
	    double numerator = 1;
	    double denominator = 1;
	    double term = 0;
	    
	    int i = 1;

	    if (x == 0)
	      answer = 0;//Ignore first term = 1, to be added at end
	    else{
	      do{
	        numerator = numerator * x;
	        denominator = denominator * i;
	        i++;
	        term = numerator/denominator;
	        answer = answer + term;
	      } while (term > 0.000001);
	    }
	    //Add 1 because first term always 1 and not computed in answer
	    return (answer+1);
    }

    /**
     * Function to calculate exponents with base 10 i.e. 10^x
     * @author Edip Tac
     * @param x Exponent
     * @return 10^x
     */
    public static double eBaseTenExp(double x){
    	return eExpY(10.0,x);
    }

    /**
     * Cos(x) = sum(from n = 0 to n = infinity) of :
     *  (((-1)^n)/(2n!))*(x^2n)
     *
     * @author Jonathan Bedard Schami
     * @param x the angle in degree which we want to obtain the cosine of
     * @param precision the precision to which we want to calculate the value
     * @return the value to the precision determined.
     */
    public static double eCos(double x, double precision){
        boolean isLeftSideQuadrant = false;
        x = x%360;
        x = (x*ePI())/180; //Convert degree to radians
        double piVal = ePI();
        //from 90 to 180 cos(x) = -cos(pi-x)
        if(x > piVal/2 && x <= piVal){
            x = piVal-x;
            isLeftSideQuadrant = true;
        }
        //from 180 to 270 cos(x) = -cos((-1)*(pi-x))
        if(x > piVal && x <= 1.5*piVal){
            isLeftSideQuadrant = true;
            x = (-1)*(piVal-x);
        }

        if(x > 1.5*piVal && x <= 2*piVal){
            x = 2*piVal - x;
        }

        double temp;
        double retVal = 0;
        double delta;
        int n = 0;
        do{
            temp = retVal;
            //Cosine taylor series expansion
            retVal = temp + (eExpY((-1), n)*eExpY(x, 2*n))/eFactorial(2*n);
            n++;
            //When retVal gets extremely small, it is zero


            //Keep expanding the taylor series until desired precision is met
            if (retVal-temp < 0)
                delta = (-1)*(retVal-temp);
            else
                delta = retVal-temp;

        }while((delta) > precision);

        if(retVal < precision)
            retVal = 0;

        if(isLeftSideQuadrant && retVal != 0){
            retVal = retVal*(-1);
        }
        return retVal;
    }

    /**
     * Logarithm Base 10 implementation.
     * @author Daniel Witkowski
     * @param x The value for which we want to obtain the log base 10 value
     * @return The result of the log_10(x)
     */
    public static double eLog(double x) {
        if(x<=0) {
            System.out.println("Invalid input. Cannot compute logarithm of number less than or equal to 0.");
            return -1;
        }
        double factor = 10;
        int counter = 1;
        while (factor < x) {
            counter++;
            factor *= 10;
        }
        double seriesInput = x / factor;
        double partialAnswer = naturalLog(seriesInput) / 2.3025850929940456840179914546844;
        double answer = partialAnswer + counter;
        answer = roundNumber(answer, 5);
        return answer;
    }

    /**
     * currently calculates the natural logarithm of a number between 0 and 2 (exclusive)
     * using a power series significant inaccuracy remains for very small numbers
     * method is required for logx function
     * @author Daniel Witkowski
     * @param num input must be between 0 and 2, exclusive
     * @return result of power series
     */
    public static double naturalLog(double num) {
        double x = num - 1;
        double sum = 0;
        for (int i = 1; i <= 1000000; i++) {
            sum += eExpY((-1), (i - 1)) * eExpY(x, i) / i;
        }
        return sum;
    }

    /**
     * Rounds a floating point number to the specified number of decimal places
     * @author Edip Tac
     * @param unroundedNumber the number before rounding
     * @param decimalPlaces the number of decimal places to round to
     * @return the rounded number
     */
    public static double roundNumber(double unroundedNumber, int decimalPlaces){
        unroundedNumber *= eExpY(10,decimalPlaces);
        if(unroundedNumber<0)
            unroundedNumber -= 0.5;
        else
            unroundedNumber += 0.5;
        double roundedNumber = (int) unroundedNumber;
        roundedNumber /= eExpY(10,decimalPlaces);
        return roundedNumber;
    }
    
    /**
     * Some functions require the value of Pi
     * @author Julien Fagnan
     * @return A value of pi based on the Ramanujan-Sato formulas
     */
	public static final double ePI(){
	double e = 0, res = 0;
	for (int i = 0; i<5; i++) {
		e += eFactorial(4*i) * (26390 * i + 1103) / (eExpY(eFactorial(i), 4) * eExpY(396,4*i));
	}
	res = 9801 / (e * eExpY(8, 0.5));
	return res;
	}
}
