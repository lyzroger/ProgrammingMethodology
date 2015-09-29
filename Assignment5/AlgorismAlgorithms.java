/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;

public class AlgorismAlgorithms extends ConsoleProgram {
	public void run() {
		/* Sit in a loop, reading numbers and adding them. */
		while (true) {
			String n1 = readLine("Enter first number:  ");
			String n2 = readLine("Enter second number: ");
			println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
			println();
		}
	}
	
	/**
	 * Given two string representations of nonnegative integers, adds the
	 * numbers represented by those strings and returns the result.
	 * 
	 * @param n1 The first number.
	 * @param n2 The second number.
	 * @return A String representation of n1 + n2
	 */
	private String addNumericStrings(String n1, String n2) {
		int n1_length = n1.length();
		int n2_length = n2.length();
		int carry = 0;
		int value = 0;
		char digit;
		int n1_value;
		int n2_value;
		int diff; //difference of n1 & n2 length.
		String sum_revs = " "; //it is reversed as the correct sum value.
		String n1_new = n1;
		String n2_new = n2;
		//if n1 & n2 have different length, then make them have the same length.
		if(n1_length > n2_length) {
			diff = n1_length - n2_length;
			n2_new = sameLength(n2, diff);
		} else {
			diff = n2_length - n1_length;
			n1_new = sameLength(n1, diff);
		}
		
		int length_new = n1_new.length();
		
		for(int i = length_new - 1; i >= 0; i--) {
			n1_value = n1_new.charAt(i) - '0';
			n2_value = n2_new.charAt(i) - '0';
			value = n1_value + n2_value + carry;
			if(value > 9) {
				value = value - 10;
				digit = (char)(value + '0');
				sum_revs += digit;
				carry = 1;
				if(i == 0) {
					sum_revs += '1';
				}
			} else {
				digit = (char)(value + '0');
				sum_revs += digit;
				carry = 0;
			}
		}
		carry = 0;
		return reverseString(sum_revs);
	}
	
	private String sameLength(String s, int diff) {
		for(int i = 0; i < diff; i++) {
			s = '0' + s;
		}
		return s;
	}
	
	private String reverseString(String s_revs) {
		String result = " ";
		for(int i = 0; i < s_revs.length(); i++) {
			result = s_revs.charAt(i) + result;
		}
		return result;
	}
}
