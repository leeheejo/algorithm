package study.boj;

import java.util.Scanner;

public class N14889_200818 {

	static int N;
	static int[][] map;
	static int[] nums;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		nums = new int[N / 2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dfs(0, 0);

		System.out.println(ans);
	}

	public static void dfs(int index, int start) {
		if (index == N / 2) {
			int a1 = 0;
			int a2 = 0;
			int[] re = new int[N / 2];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < N / 2; j++) {
					if (nums[j] == i)
						flag = false;
				}
				if (flag)
					re[idx++] = i;
			}

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					a1 += map[nums[i]][nums[j]];
				}
			}

			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					a2 += map[re[i]][re[j]];
				}
			}
			ans = Math.min(ans, Math.abs(a1 - a2));
			return;
		}

		for (int i = start; i < N; i++) {
			nums[index] = i;
			dfs(index + 1, i + 1);
		}
	}

}
