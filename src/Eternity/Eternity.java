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
     * Function to reverse the contents of an array. 
     * This function does not change the array passed as param.
     * 
     * @author Edip Tac
     * @param arr The array whose contents have to be reversed
     * @return A reversed form of the array.
     */
	static int [] reverseArr( int [] arr){
		int [] tempArr = new int [arr.length];
		for(int j=0; j < tempArr.length; j++){
			tempArr[j] = arr[j];
		}
		for(int i = 0; i < tempArr.length / 2; i++)
		{
		    int temp = tempArr[i];
		    tempArr[i] = tempArr[tempArr.length - i - 1];
		    tempArr[tempArr.length - i - 1] = temp;
		}
		return tempArr;
	}
	
	/**
     * Function to multiply 2 arrays containing integers and returns an
     * array of integers containing the final answer. This function works by doing
     * multiplication followed by addition, repeatedly.
     * Note: Provide arrays as is, the function auto-reverses the content
     * 
     * @author Edip Tac
     * @param multiplier The multiplier array
     * @param multiplicand The multiplicand array
     * @return The final answer to the multiplication, in proper order (not reversed)
     */
	 public static int [] eMult(int [] multiplier, int [] multiplicand){
		//create copies of the arrays passed since this method
		 //performs calculations on reversed arrays
		int [] multiplierArr = reverseArr(multiplier);
		int [] multiplicandArr = reverseArr(multiplicand);
		int carry = 0; //tracks carries when multiplying
		int carryAdd = 0; //tracks carries when adding after multiplying
		int index=0; //since long multiplication requires padding with 0 this index tracks the padding
		
		//create a new array of ints and fill with 0
		//so that we can shrink to the correct size after
		//the multiplication is complete
		int [] ans = new int [(multiplierArr.length + multiplicandArr.length)];
		for(int i=0; i<ans.length; i++){
			ans[i] = 0;
		}
		//since the largest number of digits that multiplying a multiplicand can
		//produce is 1 more then the original number of digits, the temp array is
		//adjusted accordingly
		int [] temp = new int[multiplicandArr.length+1];
		
		
		/*
		 * This is the actual multiplication step. Here the each digit of
		 * the multiplier proceeds to multiply each digits of the multiplicand
		 * and stores the answer in the temp array
		 */
		for (int i = 0; i < multiplierArr.length; i++) {
			for (int j = 0; j < multiplicandArr.length; j++) {
				int product = (multiplierArr[i] * multiplicandArr[j]) + carry;
				temp[j] = product % 10;
				carry = product / 10;
			}
			//This was added since the last digit can still have a carry that
			//needs to be added
			if(carry > 0){
				temp[temp.length - 1] = carry;
			}
			//We don't want the carry to leak into the next
			//digits that are multiplying so reset it
			carry = 0;
			
			/*
			 * This step adds the temp into the final answer. It also checks
			 * whether each digit contains a carry and handles it accordingly.
			 */
			for (int j = 0; j < temp.length; j++) {
				ans[(j + index)] += (temp[j] + carryAdd);
				carryAdd = ans[(j + index)] / 10;

				if (ans[(j + index)] > 9) {
					ans[(j + index)] = ans[(j + index)] % 10;
					
				}

			}
			//this resets the temp since some digits can be left over
			//from the previous multiplication. To prevent this we reset 
			//it to 0 each time we are done using it.
			for(int j =0; j < temp.length; j++){
				temp[j] = 0;
			}
			carryAdd = 0; //resetting carryAdd once its done.
			
			///increasing the shift, in order to imitate padding the
			//answer with 0 everytime we move to next digit of multiplier
			index++; 
		}
		
		//we create an array that doesn't have the excessive paddings since the actual size of the
		//digits can be smaller than the size of the array.
		int [] shrunk_ans;
		int nbOfZeroesPadded = 0;
		
		//this is done to detect where the number actually begins
		for(int i= ans.length-1; i >= 0; i--){
			if(ans[i] == 0){
				nbOfZeroesPadded++;
			}
			else{
				break;
			}
		}
		int nbOfNonzeroDigits = ans.length - nbOfZeroesPadded;
		
		shrunk_ans = new int [nbOfNonzeroDigits];
		for (int i = 0; i < shrunk_ans.length; i++) {
			shrunk_ans[i] = ans[i];
		}
		
		int [] finalAns = reverseArr(shrunk_ans);
		return finalAns;
	}
	

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

    public static void multiplyTester(){
		int [] multiplier = {1,2,3,4,5};
		int [] multiplicand = {1,2,3,4,5};

		int [] answer = eMult(multiplier, multiplicand);

		System.out.println("Multiplication:");
		for(int i=0; i < multiplicand.length; i++){
			System.out.print(multiplicand[i]);
		}

		System.out.print(" x ");
		for(int i=0; i < multiplier.length; i++){
			System.out.print(multiplier[i]);
		}

		System.out.print("\nAnswer: ");
		for(int i=0; i < answer.length; i++){
			System.out.print(answer[i]);
		}
	}

    public static void main(String[] args) {
    	multiplyTester();
    }


}
