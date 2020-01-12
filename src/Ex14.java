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
s
				if (encounterC == 3) { // changind the i to the c in the middle of the word
					numOfWords++;
					i = middleCIndex;
					encounterC = 0;
	
				}else if (encounterC == 2) { // cause after ill finish the word ill need to start again to the next word
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

	public static void main(String[] args) {

		String s = "abcbcabcacabcc";

		System.out.println(subStrC(s, 'c'));

	}

}
