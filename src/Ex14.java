import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class Ex14 {

	/* Q1 */
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

	public static int subStrMaxC(String s, char c, int k) {

		int encounterC = 0;
		int numOfWords = 0;
		int returnToIndex = 0;
		int secRun = 0;

		for (int i = 0; i < s.length();) {


				
			
			
			
			if (s.charAt(i) == c) {
				

				encounterC++;

				if (Math.abs(encounterC - k) == k) {
					encounterC = 0;
					i = returnToIndex;
					numOfWords += 2;

				}
				
				

				if (encounterC < 2)
					i++;

				if (encounterC == 2 && encounterC <= k && secRun==0) {
					numOfWords++;
					returnToIndex = i;
					i++;

				}

				if (encounterC > 2 && encounterC <= k && secRun ==0) { 
					numOfWords += 2;
					i++;
					
				}else if(encounterC > 2 && encounterC <= k && secRun ==1) {
					numOfWords++;
					i++;
				}
			

			} else
				i++;

		}
		return numOfWords;
	}

	public static void main(String[] args) {

		String s = "abcbcabcacab";

		System.out.println(subStrMaxC(s, 'c',2));

	}

}
