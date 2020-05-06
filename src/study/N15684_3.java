package study;

import java.util.Scanner;

public class N15684_3 {
	static int N, M, H;
	static boolean[][] map;
	static int answer;
	static boolean ok = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		map = new boolean[H + 2][N + 1];
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = true;
		}

		for (int i = 0; i <= 3; i++) {
			answer = i;
			play(1, 1, 0);
			if (ok)
				break;
		}

		System.out.println(ok ? answer : -1);
	}

	public static void play(int x, int y, int count) {

		if (ok)
			return;

		if (count == answer) {
			if (check())
				ok = true;
			return;
		}

		for (int i = (y < N ? x : x + 1); i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == true || map[i][j - 1] == true || map[i][j + 1] == true)
					continue;

				map[i][j] = true;
				play(i, j, count + 1);
				map[i][j] = false;
			}
		}

	}

	public static boolean check() {
		for (int i = 1; i <= N; i++) {
			int tmp = i;
			for (int j = 1; j <= H; j++) {
				if (map[j][tmp] == true)
					tmp++;
				else if (map[j][tmp - 1] == true)
					tmp--;
			}
			if (tmp != i)
				return false;
		}
		return true;
	}

}
