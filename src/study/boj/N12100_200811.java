package study.boj;

import java.util.Scanner;

public class N12100_200811 {

	static int N;
	static int[][] map;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, map);
		System.out.println(ans);

	}

	public static int[][] up(int[][] m) {
		int[][] temp = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			int target = 0;
			for (int j = 0; j < N; j++) {
				if (m[j][i] == 0)
					continue;
				temp[target][i] = m[j][i];
				if (target > 0) {
					if (temp[target][i] == temp[target - 1][i] && !visited[target - 1][i]) {
						temp[target - 1][i] *= 2;
						temp[target][i] = 0;
						visited[target - 1][i] = true;
						target--;
					}
				}
				target++;
			}

		}
		return temp;
	}

	public static int[][] rotate(int[][] m) {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = m[N - j - 1][i];
			}
		}

		return temp;
	}

	public static void dfs(int cnt, int[][] m) {
		if (cnt == 5) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans = Math.max(ans, m[i][j]);
				}
			}
			return;
		}

//		틀린답
//		int[][] cp = new int[N][N];
//		cp = copy(m);
//
//		for (int i = 0; i < 4; i++) {
//			cp = up(cp);
//			dfs(cnt + 1, cp);
//			cp = rotate(m); //m은 계속 같은 값이게 된다... 
//		}
//
//		
		for (int i = 0; i < 4; i++) {
			int[][] cp = new int[N][N];
			cp = copy(m);
			dfs(cnt + 1, up(cp));
			m = rotate(m);
		}

	}

	public static int[][] copy(int[][] m) {

		int[][] cp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cp[i][j] = m[i][j];
			}
		}

		return cp;
	}

	public static void print(int[][] m) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}
