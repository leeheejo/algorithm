package study;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class HR_CountTriplets {

	// Complete the countTriplets function below.
	static long countTriplets(List<Long> arr, long r) {
		long ans = 0;

		
		Map<Long, Long> mapForStart = new HashMap<>();
		Map<Long, Long> mapForMiddle = new HashMap<>();
		for (long num : arr) {
			if (num % r == 0) {
				long prev = num / r;
				Long cntForEnd = mapForMiddle.get(prev); // num is the last number
				if (cntForEnd != null)
					ans += cntForEnd;

				Long cntForMiddle = mapForStart.get(prev); // num is the middle number
				if (cntForMiddle != null)
					mapForMiddle.put(num, mapForMiddle.getOrDefault(num, 0L) + cntForMiddle);
			}

			mapForStart.put(num, mapForStart.getOrDefault(num, 0L) + 1); // num is the first number
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(nr[0]);

		long r = Long.parseLong(nr[1]);

		List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Long::parseLong)
				.collect(toList());

		long ans = countTriplets(arr, r);

		System.out.println(ans);
	}
}
