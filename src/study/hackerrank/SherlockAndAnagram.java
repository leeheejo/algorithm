package study.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndAnagram {

	// Complete the sherlockAndAnagrams function below.
	static int sherlockAndAnagrams(String s) {

		HashMap<Integer, Integer> pairCount = new HashMap<Integer, Integer>();
		pairCount.put(2, 1);

		for (int i = 2; i < 102; i++) {
			pairCount.put(i + 1, pairCount.get(i) + i);
		}

		int count = 0;
		HashMap<Integer, ArrayList<String>> fullMap = new HashMap<Integer, ArrayList<String>>();

		for (int j = 0; j < s.length(); j++) {

			ArrayList<String> aList = new ArrayList<String>();
			for (int i = 0; i < s.length() - j; i++) {
				String bits = s.substring(i, i + j + 1);
				char[] temp = bits.toCharArray();
				Arrays.sort(temp);
				String sorted = new String(temp);
				aList.add(sorted);
			}
			fullMap.put(aList.get(0).length(), aList);

		}

		for (int i = 1; i < s.length(); i++) {
			ArrayList<String> aList = fullMap.get(i);

			HashMap<String, Integer> freq = new HashMap<String, Integer>();

			for (int j = 0; j < aList.size(); j++) {
				if (freq.get(aList.get(j)) == null) {
					freq.put(aList.get(j), 1);
				} else {
					int increment = freq.get(aList.get(j)) + 1;
					freq.put(aList.get(j), increment);
				}

			}

			for (Integer r : freq.values()) {
				if (r > 1) {

					int c = pairCount.get(r);
					count = count + c;

				}
			}
		}
		return count;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			String s = scanner.nextLine();

			int result = sherlockAndAnagrams(s);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}