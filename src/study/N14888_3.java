package study;

import java.util.Scanner;

public class N14888_3 {
	static int N;
	static int[] num;
	static int[] op = new int[4];
	static int[] op_order;
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		op_order = new int[N - 1];

		for (int i = 0; i < N; i++)
			num[i] = sc.nextInt();

		for (int i = 0; i < 4; i++)
			op[i] = sc.nextInt();

		dfs(0);
		System.out.println(MAX);
		System.out.println(MIN);

	}

	public static void dfs(int idx) {
		if (idx == N - 1) {
			int res = calc();
			MAX = Math.max(MAX, res);
			MIN = Math.min(MIN, res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] == 0)
				continue;

			--op[i];
			op_order[idx] = i;
			dfs(idx + 1);
			++op[i];
		}

	}

	public static int calc() {
		int ans = -1;
		ans = num[0];
		for (int i = 0; i < N - 1; i++) {
			switch (op_order[i]) {
			case 0:
				ans += num[i + 1];
				break;
			case 1:
				ans -= num[i + 1];
				break;
			case 2:
				ans *= num[i + 1];
				break;
			case 3:
				ans /= num[i + 1];
				break;
			default:
				break;
			}
		}

		return ans;
	}

}
