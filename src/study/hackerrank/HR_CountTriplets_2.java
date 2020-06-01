package study.hackerrank;

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

public class HR_CountTriplets_2 {

	// Complete the countTriplets function below.
	static long countTriplets(List<Long> arr, long r) {
		long ans = 0;

		Map<Long, Long> mapForStart = new HashMap<Long, Long>();
		Map<Long, Long> mapForMiddle = new HashMap<Long, Long>();

		for (Long l : arr) {
			if (l % r == 0) {
				long prev = l / r;
				if (mapForMiddle.containsKey(prev)) {
					ans += mapForMiddle.get(prev);
				}

				if (mapForStart.containsKey(prev)) {
					mapForMiddle.put(l, mapForMiddle.getOrDefault(l, 0L) + mapForStart.get(prev));
				}
			}

			mapForStart.put(l, mapForStart.getOrDefault(l, 0L) + 1);
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
