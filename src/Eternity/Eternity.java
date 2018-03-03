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
	
    public static double eFactorial(long x){
    	double runningTotal = 1;
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
	
    private static double eFactorial(double x, double runningTotal){
        if (x==0){
            return runningTotal;
        }
        else{
            return eFactorial(x-1, runningTotal*x);
        }
    }


    /**
     * Perform x^y for natural numbers.
     * @author Julien Fagnan
     * @param x Base
     * @param y Exponent
     * @return x^y
     */
	public static double eExpY(double x, long y) {
		double z;
		boolean neg = false;
		//x^-y = 1/x^y
		if (y<0) {
			neg = true;
			y = -y;
		}
		
		if (y == 0) {
			z = 1;
		} else if (y % 2 == 0) {
			z = eExpY(x, y/2);
			z *= z;
		} else {
			z = eExpY(x, (y-1)/2);
			z *= x * z;
		}
		
		if (neg) {
			return 1/z;
		} else {
			return z;
		}
	}

    /**
     * Performs x^y for real numbers.
     * @author Julien Fagnan
     * @param x Base
     * @param y Exponent
     * @param precision Level of precision
     * @return x^y
     */
    public static double eExpY(double x, double y, double precision){
        //converting ln to log
        double lnInLog = eLog(x, precision)/(0.43429448190325182765112891891661);
        double values = y * lnInLog;

        return eEulerExp(values, precision);
    }

    /**
     * This is the function that performs e^x
     * @author Brandon Handfield
     * @param x The value for which we want to calculate the value
     * @return the value of e^x
     */
    /*
    public static double eEulerExp(double x){

        //initialize the return value of answer to 1, seeing as e^0 is equal to 1
        double answer = 1;
        int seriesCounter = 99999999;
        //here the for loop starts at setting i to n-1, we are doing -1 because by already setting answer=1 we have already
        //accounted for the first term of the Taylor Series that approximates the answer
        for (int i = seriesCounter -1; i > 0; i--){

            answer = 1 + x*answer/i;
        }

        return answer;

    }
	*/
    /**
     * Perform e^x to a given level of precision.
     * @author Julien Fagnan
     * @param x Exponent
     * @param precision Level of precision
     * @return e^x
     */
    public static double eEulerExp(double x, double precision) {
		double e = 0, res = 0;
		long i=0;
		do {
			res += (e=eExpY(x, i) / eFactorial(i));
			i++;
		} while ((precision < e || -precision > e));
		return res;
	}
    
    /**
     * Function to calculate exponents with base 10 i.e. 10^x
     * @author Edip Tac
     * @param x Exponent
     * @param precision Level of precision
     * @return 10^x
     */
    public static double eBaseTenExp(double x, double precision){
    	return eExpY(10.0,x, precision);
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
     * @param precision Level of precision
     * @return The result of the log_10(x)
     */
    public static double eLog(double x, double precision) {
        if(x<=0) {
            //System.out.println("Invalid input. Cannot compute logarithm of number less than or equal to 0.");
            return Double.longBitsToDouble(0xfff0000000000000L);
        }
        double factor = 10;
        int counter = 1;
        while (factor < x) {
            counter++;
            factor *= 10;
        }
        double seriesInput = x / factor;
        double partialAnswer = naturalLog(seriesInput, precision) / 2.3025850929940456840179914546844;
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
     * @param precision Level of precision
     * @return result of power series
     */
    public static double naturalLog(double num, double precision) {
        double x = num - 1;
        double sum = 0;
        double e=0;
        long i = 1;
        do {
            sum += (e = eExpY((-1), (i - 1)) * eExpY(x, i) / i);
            i++;
        } while ((precision < e || -precision > e));
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
	res = 9801 / (e * eExpY(8, 0.5, 0.0000000001));
	return res;
	}
}
