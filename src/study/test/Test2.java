package study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test2 {

	static int[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Integer> arr = new ArrayList<>();
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			arr.add(n);
			sum += n;
		}

		System.out.println(ans(arr));

	}

	public static int ans(List<Integer> arr) {
		int ans = 0;
		int[][] map = new int[arr.size()][2];
		map[0][1] = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			map[i][0] = map[i - 1][0]; // 홀수
			map[i][1] = map[i - 1][1]; // 짝수
			int n = arr.get(i);
			if (i % 2 == 0) {
				map[i][1] += arr.get(i);
			} else {
				map[i][0] += arr.get(i);
			}
		}

		int total_odd = map[arr.size() - 1][1];
		int total_even = map[arr.size() - 1][0];
		for (int i = 0; i < arr.size(); i++) {

			int before_i_odd = i == 0 ? 0 : map[i - 1][1];
			int before_i_even = i == 0 ? 0 : map[i - 1][0];
			int i_odd = map[i][1];
			int i_even = map[i][0];

			if (before_i_even + (total_odd - i_odd) == before_i_odd + (total_even - i_even)) {
				ans++;
			}
		}

		return ans;
	}

	public static void print(List<Integer> arr, int[][] map) {
		System.out.println("홀수  짝");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(map[i][0] + " " + map[i][1]);
		}
	}
}
