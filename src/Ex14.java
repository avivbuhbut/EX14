import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Ex14 {

	/******************************* QEUSTION 1.a **************************/
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

	/******************************* QEUSTION 1.b **************************/
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

	/******************************* QEUSTION 2 **************************/
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

	public static void printArr(int[] a) { /************************************
											 * DELETE AFTER TESTING
											 *************************************/

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

		int number = 0;

		for (int i = 0; i < a.length; i++) { // finding the zero index

			if (a[i] == 1) {
				a[i] = ++number;
			} else {
				number = 0;
			}

		}

		return a;

	}

	/******************************* QEUSTION 3 **************************/

	/*
	 * General idea: 1.method howManyTimes that returns how many times a letter is
	 * accruing in the original string 2.compare the times the letter has accrued in
	 * the original string against every letter in the transform string - if its not
	 * there return false , if every letter is equal or more against all the letter
	 * in the transform string than its ok and i can return true
	 */

	public static boolean isTrans(String s, String t) {

		return isTrans(s, t, 0);
	}

	private static boolean sameOrder(String s, String t, int indexS, int indexT) {

		if (CheckOccurrensInTrans(t, indexS, s.charAt(indexS)) >= howManyTimesOccurreInString(s, indexS,
			s.charAt(indexS))) {

		

			if ((indexS == s.length() - 1 && indexT < t.length() - 1) && s.charAt(indexS) == t.charAt(indexT)) {
				return sameOrder(s, t, indexS, indexT + 1);
			} else if (((indexS == s.length() - 1 && indexT < t.length() - 1) && s.charAt(indexS) != t.charAt(indexT)))
				return sameOrder(s, t, indexS, indexT+1);

			if ((indexS >= s.length() || indexT >= t.length()) && t.length() == s.length())
				return false;

			if ((indexS == s.length() - 1 && indexT == t.length() - 1) && (s.charAt(indexS) == t.charAt(indexT))) {
				return true;
			} else if ((indexS == s.length() - 1 && indexT == t.length() - 1)
					&& (s.charAt(indexS) != t.charAt(indexT))) {
				return false;
			}

			if (s.charAt(indexS) == t.charAt(indexT)) {
				return sameOrder(s, t, indexS + 1, indexT + 1);
			} else {
				
				if (indexS > 0)
					if (t.charAt(indexT) == s.charAt(indexS - 1))
						return sameOrder(s, t, indexS, indexT + 1);
					else
						return false;

				if (indexT > 0)
					if (t.charAt(indexT - 1) == s.charAt(indexS))
						return sameOrder(s, t, indexS + 1, indexT);
					else
						return sameOrder(s, t, indexS, indexT + 1);

			}

		} 

	
		return false;
	}

	private static boolean isTrans(String s, String t, int i) {

		return sameOrder(s, t, 0, 0);

	}

	private static int CheckOccurrensInTrans(String t, int i, char letter) {

		if (i == t.length())
			return 0;

		
		if (t.charAt(i) == letter)
			return 1 + CheckOccurrensInTrans(t, i + 1, letter);

		return CheckOccurrensInTrans(t, i + 1, letter);

	}

	/*
	 * this method returns how many times a letter is accruing in the original
	 * string
	 */
	private static int howManyTimesOccurreInString(String s, int i, char letter) {

		if (i == s.length())
			return 0;

		
		if (s.charAt(i) == letter)
			return 1 + howManyTimesOccurreInString(s, i + 1, letter);
		;

		return howManyTimesOccurreInString(s, i + 1, letter);

	}
	/*
	
	public static void printPath(int [][] m) {
		printPathWeights(m,0,0,0);
	}
	
	private static void printPathWeights(int [][]m,int i, int j,int sum) {

		if(i<0||i<=m.length|| j>=m[0].length)
			return;
		
		if(m[i][j] == -1)
			return;
		
		if(i==m.length-1 && j==m[0].length-1)
			System.out.println(sum + m[m.length-1][m.length-1]);
		
		int temp = m[i][j];
		m[i][j] = -1;

		printPathWeights(m, i+1, j, sum+temp);
		printPathWeights(m, i, j+1, sum+temp);
		printPathWeights(m, i-1, j, sum+temp);
		printPathWeights(m, i, j-1, sum+temp);
		m[i][j]=temp;
		
	}
*/
	public static void main(String[] args) {

		int a[] = { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 };

		int[][] m = { 
				{ 8, 4, 2, 4, 3 },
				{ 6, 3, 8, 4, 5 },
				{ 1, 4, 9, 9, 7 },
				{ 2, 1, 7, 6, 5 },};

		String s = "abbcd";

		String trans = "a";
	//	System.out.println(isTrans(s, trans));

	printPath(m);

	}

}
