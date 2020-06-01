package study.boj;

import java.util.Scanner;

//2차원 배열 dfs하는 것 잘 알아두자 무지헷갈리넹 
public class N15684_4 {
	static int N, M, H;
	static boolean[][] map;
	static boolean ok;
	static int ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		map = new boolean[H + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = true;
		}

		ans = 0;
		for (int i = 0; i <= 3; i++) {
			ans = i;
			dfs(1, 1, 0);
			if (ok)
				break;
		}

		System.out.println(ok ? ans : -1);

	}

	public static void dfs(int x, int y, int count) {
		if (ok)
			return;

		if (count == ans) {
			// true인지 false 인지 검사
			ok = check();
			return;
		}

		for (int i = x; i <= H; i++) { // 여기를 1로 해도 되지만 x로 해야 시간이 더 효율
			for (int j = 1; j < N; j++) {
				if (map[i][j] == true || map[i][j - 1] == true || map[i][j + 1] == true)
					continue;
				map[i][j] = true;
				dfs(i, j, count + 1);
				map[i][j] = false;
			}
		}
	}

	public static boolean check() {
		int temp = -1;
		for (int i = 1; i < N; i++) {
			temp = i;
			for (int j = 1; j <= H; j++) {
				if (map[j][temp] == true)
					temp++;
				else if (map[j][temp - 1] == true)
					temp--;
			}
			if (temp != i)
				return false;
		}
		return true;
	}
}
