package study.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HR_SherlockAndAnagram {

	static int sherlockAndAnagrams(String s) {
		int ans = 0;
		HashMap<Integer, Integer> pairCount = new HashMap<Integer, Integer>();
		pairCount.put(2, 1);
		for (int i = 2; i < 101; i++) {
			pairCount.put(i + 1, pairCount.get(i) + i);
		}

		HashMap<String, Integer> fullmap = new HashMap<String, Integer>();
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j < s.length() - i; j++) {
				String bits = s.substring(i, i + j + 1);
				char[] temp = bits.toCharArray();
				Arrays.sort(temp);
				if (fullmap.containsKey(new String(temp))) {
					fullmap.put(new String(temp), fullmap.get(new String(temp)) + 1);
				} else {
					fullmap.put(new String(temp), 1);
				}
			}
		}

		for (Integer i : fullmap.values()) {
			if (i > 1) {
				int c = pairCount.get(i);
				ans += c;
			}
		}
		return ans;
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
