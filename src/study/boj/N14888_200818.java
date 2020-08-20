package study.boj;

import java.util.Scanner;

public class N14888_200818 {

	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int[] nums;
	static int[] op = new int[4];
	static int[] order;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		order = new int[N - 1];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			op[i] = sc.nextInt();
		}

		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int count) {
		if (count == N - 1) {
			int c = calc();
			max = Math.max(max, c);
			min = Math.min(min, c);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				order[count] = i;
				op[i]--;
				dfs(count + 1);
				op[i]++;
			}
		}

	}

	public static int calc() {
		int res = nums[0];
		for (int i = 0; i < N - 1; i++) {
			if (order[i] == 0) {
				res += nums[i + 1];
			} else if (order[i] == 1) {
				res -= nums[i + 1];
			} else if (order[i] == 2) {
				res *= nums[i + 1];
			} else if (order[i] == 3) {
				res /= nums[i + 1];
			}
		}

		return res;
	}

}
