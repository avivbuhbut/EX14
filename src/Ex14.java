

/**
 * 
 * @author Aviv Buhbut , ID: 204445084
 *
 */

public class Ex14 {

	/******************************* QEUSTION 1.a **************************/
	/* Time Complexity O(N) */
	/* Space Complexity O(1) */
	
	
	/**
	 * 
	 * this method calculates how many words there are in the string that starts and ends with the letter 'c'
	 * 
	 * @param s - the string to check
	 * @param c - the letter 'c'
	 * @return - how many strings there are in the string s that starts and ends with the letter 'c'
	 */
	
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

	/******************************* QEUSTION 1.b **************************/
	/* Time Complexity O(N) */
	/* Space Complexity O(1) */
	
	
	/**
	 * this method calculates how many strings there are in the string that starts and ends with the letter 'c'
	 * with the minimum occurrence of k times between 2 c's
	 * 
	 * @param s - the string to check
	 * @param c - the letter c
	 * @param k - max times c can accrue in the word
	 * @return - how many strings there are in the string that starts and ends with the letter 'c'
	 */
	
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

	/******************************* QEUSTION 2 **************************/
	
	/* Time Complexity O(N) */
	/* Space Complexity O(1) */
	
	
	
	/**
	 * this method checks the distance between 2 zeros in the array and changes the array accordingly 
	 * 
	 * @param a  - the array to check
	 */

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

	

	}
	
	/**
	 *  changes the array in case of 1 zero in the start of the array
	 *  
	 * @param a - the array
	 * @return - the array after the changes
	 */

	private static int[] SingleZeroAtStart(int[] a) {

		if (a.length >= 1)
			for (int i = 0; i < a.length; i++) {

				if (a[i] == 1)
					a[i] = i;

			}

		return a;
	}
	
	/**
	 *  changes the array in case of 1 zero in the end of the array
	 *  
	 * @param a - the array
	 * @return - the array after the changes
	 */

	private static int[] SingleZeroAtLastIndex(int[] a) {
		int distance = 1;

		if (a.length >= 1)
			for (int i = a.length - 1; i > 0; i--) {

				if (a[i] == 1)
					a[i] = distance++;

			}

		return a;

	}
	
	/**
	 *  changes the array in case of 1 zero in the middle
	 *  
	 * @param a - the array
	 * @return - the array after the changes
	 */

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
	
	
	/**
	 *  changes the array in case of more than 1 zero
	 *  
	 * @param a - the array
	 * @return - the array after the changes
	 */

	private static int[] MoreThan1Zero(int[] a) {

		int counter = 1;

		int counterZeros = 0;

		for (int i = 0; i < a.length; i++) {

			if (a[i] == 0)
				counterZeros++;

			if (a[i] == 1 && counterZeros < 2) {
				a[i] = counter++;

			}

			if (a[i] == 1 && counterZeros == 2) {
				counter = 1;
				a[i] = counter++;
				counterZeros = 1;

			}

		}

		counterZeros = 0;
		counter = -1;

		for (int i = a.length - 1; i > 0; i--) {

			if (counter < a[i] && counter > 0)
				a[i] = counter++;

			if (a[i] == 0)
				counter = 1;

		}

		return a;
	}

	
	/******************************* QEUSTION 3 **************************/
	
	/**
	 * 
	 * @param s - the string to check
	 * @param t - the  string to check if it has been transform
	 * @return - true if the string has been transform
	 */
	public static boolean isTrans(String s, String t) {

		return isTrans(s, t, 0, 0);
	}

	private static boolean isTrans(String s, String t, int indexS, int indexT) {

		if (CheckOccurrensInTrans(t, indexS, s.charAt(indexS)) >= howManyTimesOccurreInString(s, indexS,
				s.charAt(indexS))) {

			if ((indexS == s.length() - 1 && indexT < t.length() - 1) && s.charAt(indexS) == t.charAt(indexT)) {
				return isTrans(s, t, indexS, indexT + 1);
			} else if (((indexS == s.length() - 1 && indexT < t.length() - 1) && s.charAt(indexS) != t.charAt(indexT)))
				return isTrans(s, t, indexS, indexT + 1);

			if ((indexS >= s.length() || indexT >= t.length()) && t.length() == s.length())
				return false;

			if ((indexS == s.length() - 1 && indexT == t.length() - 1) && (s.charAt(indexS) == t.charAt(indexT))) {
				return true;
			} else if ((indexS == s.length() - 1 && indexT == t.length() - 1)
					&& (s.charAt(indexS) != t.charAt(indexT))) {
				return false;
			}

			if (s.charAt(indexS) == t.charAt(indexT)) {
				return isTrans(s, t, indexS + 1, indexT + 1);
			} else {

				if (indexS > 0)
					if (t.charAt(indexT) == s.charAt(indexS - 1))
						return isTrans(s, t, indexS, indexT + 1);
					else
						return false;

				if (indexT > 0)
					if (t.charAt(indexT - 1) == s.charAt(indexS))
						return isTrans(s, t, indexS + 1, indexT);
					else
						return isTrans(s, t, indexS, indexT + 1);

			}

		}

		return false;
	}


/**
 * this method checks how many times a letter accruing in the transform string
 * 
 * @param t - the transform string
 * @param i - the index
 * @param letter - the letter to check
 * @return - the number of times a letter apper in the transform stirng
 */
	private static int CheckOccurrensInTrans(String t, int i, char letter) {

		if (i == t.length())
			return 0;

		if (t.charAt(i) == letter)
			return 1 + CheckOccurrensInTrans(t, i + 1, letter);

		return CheckOccurrensInTrans(t, i + 1, letter);

	}


	

	/**
	 * 
	 * this method checks how many times a letter accruing in a string 
	 * 
	 * @param s - the string to check
	 * @param i - the index 
	 * @param letter - the letter to check 
	 * @return - the number of times the letter accruing in the string
	 */
	private static int howManyTimesOccurreInString(String s, int i, char letter) {

		if (i == s.length())
			return 0;

		if (s.charAt(i) == letter)
			return 1 + howManyTimesOccurreInString(s, i + 1, letter);
		;

		return howManyTimesOccurreInString(s, i + 1, letter);

	}

	/******************************* QEUSTION 4 **************************/
	
	/**
	 * this method calculates the numbers of paths there are in the 2d array
	 * 
	 * 
	 * @param m - the 2d array
	 * @return - how many paths there are
	 */
	public static int countPath(int[][] m) {
		return countPath(m, 0, 0);
	}

	private static int countPath(int[][] m, int i, int j) {

		if (i >= m.length || j >= m[0].length)
			return 0;

		if (i == m.length - 1 && j == m[0].length - 1)
			return 1;

		return countPath(m, i + m[i][j] / 10, j + m[i][j] % 10) + countPath(m, i + m[i][j] % 10, j + m[i][j] / 10);

	}


}
