package study;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HR_TwoStrings {

	// Complete the twoStrings function below.
	static String twoStrings(String s1, String s2) {
		String ans = "";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length(); i++) {
			map.put(s1.charAt(i), i);
		}

		boolean flag = false;
		for (int j = 0; j < s2.length(); j++) {
			if (map.containsKey(s2.charAt(j))) {
				flag = true;
				break;
			}
		}

		ans = flag ? "Yes" : "No";
		return ans;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			String s1 = scanner.nextLine();

			String s2 = scanner.nextLine();

			String result = twoStrings(s1, s2);

			System.out.println(result);
//			bufferedWriter.write(result);
//			bufferedWriter.newLine();
		}

//		bufferedWriter.close();

		scanner.close();
	}
}
