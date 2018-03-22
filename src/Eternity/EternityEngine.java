package Eternity;

public class EternityEngine {
    static private double precision;
    static private boolean rads;
    static private long decimal;

    /**
     * Default constructor sets a base precision of 9 decimal places
     */
    public EternityEngine(){
        EternityEngine.precision = 0.000000001;
        EternityEngine.rads = true;
        EternityEngine.decimal = 0;
        setDecimal();
    }

    public EternityEngine(double precision){
        EternityEngine.precision = precision;
        EternityEngine.decimal = 0;
        setDecimal();
    }
    /**
     * Parametered constructo allows for precision to be set at initialization.
     */
    public EternityEngine(double precision, boolean rads){
        EternityEngine.precision = precision;
        EternityEngine.rads = rads;
        EternityEngine.decimal = 0;
        setDecimal();
    }

    /**
     * Getter for radians setting
     * @return a boolean, true if rads, false otherwise
     */
    public boolean isRads() {
        return rads;
    }

    /**
     * Setter for radians
     * @param rads true if rads, false if degrees
     */
    public void setRads(boolean rads) {
        EternityEngine.rads = rads;
    }

    /**
     * Get the current precisoin of the calculator
     * @return the current precision setting for the calculator
     */
    public double getPrecision() {
        return precision;
    }

    /**
     * setter for precision
     * @param precision the desired precision of the calculator
     */
    public void setPrecision(double precision) {
        EternityEngine.precision = precision;
        EternityEngine.decimal = 0;
        setDecimal();
    }

    /**
     * Setter for decimal spaces used in for truncating number
     */
    private void setDecimal(){
        double temp = precision;
        while(temp < 1){
            temp*=10;
            decimal++;
        }
    }

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
    public double eFactorial(long x){
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
    private double eFactorial(double x, double runningTotal){
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
	public double eExpY(double x, long y) {
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
     * @return x^y
     */
    public double eExpY(double x, double y){
        //converting ln to log
        //double lnInLog = eLog(x)/(0.43429448190325182765112891891661);
        double values = y * eLn(x);

        return eEulerExp(values);
    }

    /**
     * Perform e^x to a given level of precision.
     * @author Julien Fagnan
     * @param x Exponent
     * @return e^x
     */
    public double eEulerExp(double x) {
		double e = 0, res = 0;
		long i=0;
		do {
		    //double temp = res;
			res += (e=eExpY(x, i) / eFactorial(i));
			i++;
			//e = res-temp;
			if(e < 0){e = -e;}
		} while (precision < e);

		//res = roundNumber(res);
		return res;
	}
    
    /**
     * Function to calculate exponents with base 10 i.e. 10^x
     * @author Edip Tac
     * @param x Exponent
     * @return 10^x
     */
    public double eBaseTenExp(double x){
        if(x%1 == 0) //if x is integer
    	    return eExpY(10.0,(long)x);
        else
            return eExpY(10.0,x);
    }

    /**
     * Cos(x) = sum(from n = 0 to n = infinity) of :
     *  (((-1)^n)/(2n!))*(x^2n)
     *
     * @author Jonathan Bedard Schami
     * @param x the angle in degree which we want to obtain the cosine of
     * @return the value to the precision determined.
     */
    public double eCos(double x){
        boolean isLeftSideQuadrant = false;
        double piVal = ePI();

        //Pre processing of x, is it rads or degrees?
        if(EternityEngine.rads){
            x = x%(2*piVal);
        }
        else {
            x = x % 360;
            x = (x * piVal) / 180; //Convert degree to radians
        }
        //Pre processing of x, simplify the angle
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
        //retVal = roundNumber(retVal);
        return retVal;
    }

    /**
     * Logarithm Base 10 implementation.
     * @author Daniel Witkowski
     * @param x The value for which we want to obtain the log base 10 value
     * @return The result of the log_10(x)
     */
    public double eLog (double x) throws IllegalArgumentException {
        if(x==0) {
            return Double.NEGATIVE_INFINITY;
        }
        else if(x<0){
            throw new IllegalArgumentException("Logarithm does not accept values smaller than 0");
        }
        else {
            double answer;
            answer = eLn(x) / eLn(10);
            //answer = roundNumber(answer);
            return answer;
        }
    }

    /**
     * currently calculates the natural logarithm of a number between 0 and 2 (exclusive)
     * using a power series significant inaccuracy remains for very small numbers
     * method is required for logx function
     * @author Daniel Witkowski
     * @param x number for which to calculate the natural log of
     * @return result of power series
     */
    public double eLn(double x) throws IllegalArgumentException {
    	//Error handling for x < 0;
		if (x==0) {
			return Double.NEGATIVE_INFINITY;
		} else if (x<0){
			throw new IllegalArgumentException("Natural Logarithm does not accept values smaller than 0");
		} else {
			double e = 0, res = 0;
			long i=0;
			x = (x-1)/(x+1);
			do {
				res +=  (e = 2 * eExpY(x, 2*i + 1) / (2*i+1));
				i++;
			} while ((precision < e || -precision > e));
			if (x<=2) {
				return res;
			} else {
				return -res;
			}
		}
    }

    /**
     * Rounds a floating point number to the specified number of decimal places
     * @author Edip Tac
     * @param unroundedNumber the number before rounding
     * @return the rounded number
     */
    private double roundNumber(double unroundedNumber){
        unroundedNumber *= eExpY(10,decimal);
        if(unroundedNumber<0)
            unroundedNumber -= 0.5;
        else
            unroundedNumber += 0.5;
        double roundedNumber = (int) unroundedNumber;
        roundedNumber /= eExpY(10,decimal);
        return roundedNumber;
    }
    
    /**
     * Some functions require the value of Pi
     * @author Julien Fagnan
     * @return A value of pi based on the Ramanujan-Sato formulas
     */
	public final double ePI(){
        double e = 0, res = 0;

        double temp = getPrecision();
        //Temporarily increase precision for pi calculation
        setPrecision(0.0000000001);
        for (int i = 0; i<5; i++) {
            e += eFactorial(4*i) * (26390 * i + 1103) / (eExpY(eFactorial(i), 4) * eExpY(396,4*i));
        }
        res = 9801 / (e * eExpY(8, 0.5));

        //Set precision back to engine value
        setPrecision(temp);
        return res;
	}
}
