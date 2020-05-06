package study;

import java.util.Scanner;

public class N15684_2 {
	static int N, M, H; // N:세로 M:이미 놓인 가로선 H:가로 인풋순서를 왜 이따위로 했을까 너무 헷갈림 이런거 가로세로 잘 보자.
	static int[][] map;
	static int max;
	static boolean ok;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		map = new int[H + 2][N + 1]; // 좌표 개념 -> map으로 수용하기 위해 값을 각각 더해서 처리한다. 왜 H는H+2일까??

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = 1;
		}

		for (int i = 0; i <= 3; i++) {
			max = i;
			solve(1, 1, 0);
			if (ok)
				break;
		}

		System.out.println(ok ? max : -1);
	}

	public static void solve(int x, int y, int cnt) {
		if (ok)
			return;

		if (cnt == max) {
			if (check()) {
				ok = true;
			}
			return;
		}

		for (int i = (y < N ? x : x + 1); i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 1 || map[i][j - 1] == 1 || map[i][j + 1] == 1)
					continue;

				map[i][j] = 1;
				solve(i, j, cnt + 1);
				map[i][j] = 0;
			}
		}
	}

	public static boolean check() {
		for (int i = 1; i <= N; i++) {
			int tmp = i;
			for (int j = 1; j <= H + 1; j++) {
				if (map[j][tmp] == 1)
					tmp++;
				else if (map[j][tmp - 1] == 1)
					tmp--;
			}
			if (tmp != i)
				return false;
		}
		return true;
	}
}
