package study.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HR_FrequencyQueries2 {

	static List freqQuery(List<Integer[]> queries) {
		List ans = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();

		for (int i = 0; i < queries.size(); i++) {
			int op = queries.get(i)[0];
			int num = queries.get(i)[1];

			if (op == 1) {
				// 맵에 저장
				// 인덱스에 발생 횟수 저장
				if (map.containsKey(num)) {
					int oldCount = map.get(num);
					int newCount = oldCount + 1;
					map.put(num, newCount);
					index.put(oldCount, index.get(oldCount) - 1);
					if (index.containsKey(newCount)) {
						index.put(newCount, index.get(newCount) + 1);
					} else {
						index.put(newCount, 1);
					}
				} else {
					map.put(num, 1);
					if (index.containsKey(1)) {
						index.put(1, index.get(1) + 1);
					} else {
						index.put(1, 1);
					}
				}
			} else if (op == 2) {
				if (map.containsKey(num)) {
					int oldCount = map.get(num);
					int newCount = Math.max(oldCount - 1, 0);
					map.put(num, newCount);
					index.put(oldCount, index.get(oldCount) - 1);

					if (index.containsKey(newCount)) {
						index.put(newCount, index.get(newCount) + 1);
					} else {
						index.put(newCount, 1);
					}
				}
			} else if (op == 3) {
				if (index.containsKey(num) && index.get(num) > 0) {
					ans.add(1);
				} else {
					ans.add(0);
				}
			}

		}

		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		List queries = new ArrayList<>();

		for (int i = 0; i < q; i++) {
			String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

			Integer[] queriesRowItems = new Integer[2];
			queriesRowItems[0] = Integer.parseInt(queriesRowTempItems[0]);
			queriesRowItems[1] = Integer.parseInt(queriesRowTempItems[1]);
			queries.add(queriesRowItems);
		}

		List ans = freqQuery(queries);

		for (int i = 0; i < ans.size(); i++) {
			bufferedWriter.write(String.valueOf(ans.get(i)));

			if (i != ans.size() - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}