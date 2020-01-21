import java.util.function.IntConsumer;

import com.sun.javafx.runtime.VersionInfo;

import javafx.beans.binding.BooleanBinding;

public class Ex14 {

	/*******************************QEUSTION 1.a**************************/
	/* Complexity O(N) */
	public static int subStrC(String s, char c) {

		int encounterC = 0;
		int numOfWords = 0;
		int middleCIndex = 0;

		for (int i = 0; i < s.length();) {

			if (s.charAt(i) == c) {

				encounterC++;

				if (encounterC == 3) { // changind the i to the c in the middle of the word
					numOfWords++;
					i = middleCIndex;
					encounterC = 0;

				} else if (encounterC == 2) { // cause after ill finish the word ill need to start again to the next
												// word
					// from
					// the middle c in the current word
					middleCIndex = i;

					i++;
				} else
					i++;

			} else
				i++;

		}

		return numOfWords;
	}

	/*******************************QEUSTION 1.b**************************/
	/* Complexity O(N) */
	public static int subStrMaxC(String s, char c, int k) {

		int encounterC = 0;
		int numOfWords = 0;
		int returnToIndex = 0;
		int counterCsInWords = 0;
		int maxCsInWord = k;
		int secRound = 0;

		for (int i = 0; i < s.length();) {

			if (s.charAt(i) == c) {

				encounterC++;

				if (encounterC < 2)
					i++;
				if (encounterC == 2) {
					returnToIndex = i;
					numOfWords++;
					counterCsInWords++;
					i++;

				}

				if (encounterC > 2 && counterCsInWords <= maxCsInWord && secRound == 0) {
					counterCsInWords++;
					numOfWords += 2;
					i++;

				}

				else if (encounterC > 2 && counterCsInWords <= maxCsInWord && secRound == 1) {

					if (counterCsInWords == maxCsInWord)
						i++;

				}

			} else {
				i++;

				if (i == s.length() && secRound != 1) {
					i = returnToIndex;
					counterCsInWords = maxCsInWord - 1;
					encounterC = 0;
					secRound = 1;

				}

			}

		}

		return numOfWords;
	}

	
	
	/*******************************QEUSTION 2**************************/
	public static void zeroDistance(int[] a) {
		int counterZeros = 0;
		int startIndex = 0;
		int lastIndex = a.length - 1;

		int flagSingle0AtStartIndex = 0;
		int flagSingle0AtLastIndex = 0;
		int flagSingle0MidArr = 0;
		int flagMoreThan1Zero = 0;

		for (int i = 0; i < a.length; i++) {

			if (a[i] == 0)
				counterZeros++;
		}
		
		
			if (counterZeros == 1 && a[startIndex] == 0) {
				flagSingle0AtStartIndex = 1;
			}

			else if (counterZeros == 1 && a[lastIndex] == 0) {
				flagSingle0AtLastIndex = 1;
			}

			else if (counterZeros == 1) {
				flagSingle0MidArr = 1;

			} else {
				flagMoreThan1Zero = 1;
			}

		
		if (flagSingle0AtStartIndex == 1)
			a = SingleZeroAtStart(a);

		if (flagSingle0AtLastIndex == 1)
			a = SingleZeroAtLastIndex(a);

		if (flagSingle0MidArr == 1)
			a = SingleZeroInMiddle(a);

		if (flagMoreThan1Zero == 1)
			a = MoreThan1Zero(a);

		printArr(a);

	}

	public static void printArr(int[] a) { /************************************DELETE AFTER TESTING*************************************/
	
		for (int i = 1; i < a.length; i++) {
			if (i < a.length - 1)
				System.out.print(a[i] + ",");
			else
				System.out.print(a[i]);
		}
	}

	private static int[] SingleZeroAtStart(int[] a) {

		if (a.length >= 1)
			for (int i = 0; i < a.length; i++) {

				if (a[i] == 1)
					a[i] = i;

			}

		return a;
	}

	private static int[] SingleZeroAtLastIndex(int[] a) {
		int distance = 1;

		if (a.length >= 1)
			for (int i = a.length - 1; i > 0; i--) {

				if (a[i] == 1)
					a[i] = distance++;

			}

		return a;

	}

	private static int[] SingleZeroInMiddle(int[] a) {

		int indexOfZero = 0;

		for (int i = 0; i < a.length; i++) { // finding the zero index

			if (a[i] == 0) {
				indexOfZero = i;
			}

		}

		int distanceUp = 1;
		for (int i = indexOfZero; i < a.length - 1; i++) { // changing all above values

			a[i + 1] = distanceUp++;

		}

		int distanceDown = 1;
		for (int i = indexOfZero; i > 1; i--) { // changing all below values

			a[i - 1] = distanceDown++;

		}

		return a;
	}

	private static int[] MoreThan1Zero(int[] a) {

		int indexFirstZero = 0;
		int indexNextZero = 0;
		int counterZeros = 0;

		for (int i = 0; i < a.length; i++) { // finding the zero index

			if (a[i] == 0 ) {
				indexFirstZero = i;
				counterZeros++;
			} else {
				indexNextZero = i;
				counterZeros++;

			}

		}

		int diffBetwenZeros = ((indexFirstZero + indexNextZero) / 2);

		int distanceDown = 2;
		System.out.println(indexFirstZero);
//System.out.println(indexFirstZero + diffBetwenZeros);

/*
		for (int i = indexFirstZero + diffBetwenZeros; i!=indexFirstZero; i--) { // changing values below

			
			a[i] = distanceDown++;

		}*/
		
		/*
		int distanceUp = 1;
		System.out.println(Math.abs(diffBetwenZeros- indexNextZero));
		for (int i = Math.abs(diffBetwenZeros- indexNextZero); i!= indexNextZero; i++) { // changing values below

			a[i] = distanceUp++;

		}*/

	

	return a;

	}

	
	/*******************************QEUSTION 3**************************/
	
	/*General idea:
	 * 1.method howManyTimes  that returns how many times a letter is accruing in the original string
	 * 2.compare the times the letter has accrued in the original string against every letter in the transform string - 
	 * if its not there return false , if every letter is equal or more against all the letter in the transform string
	 * than its ok and i can return true
	 * */
	
	
	

	 
	public static boolean isTrans (String s, String t) {
		
		
		return isTrans(s,t,0);
	}
	
	public static boolean isTrans (String s, String t, int i) {
		
		int occurrenceOfLeterInString =  howManyTimesOccurreInString(s,i, s.charAt(i));

		if(i==s.length() )
			return true;
		
		if(CheckOccurrensInTrans(t,i) <= occurrenceOfLeterInString)
			return isTrans (s,t,i+1);
		else
			return false;
		
	}
	
	private static int CheckOccurrensInTrans(String t, int i) {
		int letterOccurreInTrans = 0;
		
		if(i==t.length())
			return letterOccurreInTrans;
		
		if(s.charAt(i)  == letter)
			letterOccurre++;
		
		
	}
	
	/*this method returns how many times a letter is accruing in the original string*/
	private static int howManyTimesOccurreInString(String s, int i, int letter) {
		int letterOccurre = 0;
		
		if(i==s.length())
			return letterOccurre;
		
		if(s.charAt(i)  == letter)
			letterOccurre++;
		
		howManyTimesOccurreInString(s,i+1);
		
		
	}
	
	public static void main(String[] args) {

		String s = "abc";

		// System.out.println(subStrMaxC(s, 'c', 0));

		int a[] = { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 };

		zeroDistance(a);

	}

}
