package study;

import java.util.Scanner;

public class N15684_200509 {

	static int N, M, H;
	static boolean[][] map;
	static boolean flag = false;
	static int ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		map = new boolean[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = true;
		}

		for (int i = 0; i <= 3; i++) {
			ans = i;
			dfs(i, 0, 1);
			if (flag)
				break;
		}

		System.out.println(flag ? ans : -1);
	}

	// c1부터시작해야 한다.
	public static void dfs(int count, int index, int c) {
		if (flag)
			return;
		if (count == index) {
			flag = check();
			return;
		}

		for (int i = c; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j])
					continue;
				if ((j - 1 > 0 && map[i][j - 1] == true) || (j + 1 <= N && map[i][j + 1] == true))
					continue;
				map[i][j] = true;
				dfs(count, index + 1, i);
				map[i][j] = false;
			}
		}
	}

	public static boolean check() {
		for (int i = 1; i < N; i++) {
			int target = i;
			for (int j = 1; j < H + 1; j++) {
				if (map[j][target]) {
					target++;
					continue;
				}
				if (map[j][target - 1]) {
					target--;
					continue;
				}
			}
			if (target != i)
				return false;

		}

		return true;
	}

}
