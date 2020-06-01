package study.boj;

import java.util.Scanner;

public class N14888_200508 {

	static int N;
	static int[] num;
	static int[] op = new int[4];
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	static int[] sel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		sel = new int[N - 1];

		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			op[i] = sc.nextInt();
		}

		dfs(0);

		System.out.println(max);
		System.out.println(min);
	}

	public static void dfs(int index) {
		if (index == N - 1) {
//			for (int i = 0; i < N - 1; i++) {
//				System.out.print(sel[i] + " ");
//			}
//			System.out.println();
			int a = calc();
			min = Math.min(a, min);
			max = Math.max(a, max);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] == 0)
				continue;
			op[i]--;
			sel[index] = i;
			dfs(index + 1);
			op[i]++;
		}
	}

	public static int calc() {
		int ans = 0;
		ans = num[0];
		for (int i = 0; i < N - 1; i++) {
			if (sel[i] == 0) {
				ans += num[i + 1];
			} else if (sel[i] == 1) {
				ans -= num[i + 1];
			} else if (sel[i] == 2) {
				ans *= num[i + 1];
			} else if (sel[i] == 3) {
				ans /= num[i + 1];
			}
		}
		return ans;
	}
}
