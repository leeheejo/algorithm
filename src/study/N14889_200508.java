package study;

import java.util.Scanner;

public class N14889_200508 {

	static int N;
	static int[][] map;
	static boolean[] player;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		player = new boolean[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, 0);
		System.out.println(min);

	}

	public static void dfs(int index, int c) {
		if (index == N / 2) {
//			for (int i = 0; i < N; i++) {
//				System.out.print(player[i] + " ");
//			}
//			System.out.println();
			min = Math.min(min, calc());
			return;
		}

		for (int i = c; i < N; i++) {
			if (player[i] == true)
				continue;
			player[i] = true;
			dfs(index + 1, i);
			player[i] = false;
		}
	}

	public static int calc() {
		int team1 = 0;
		int team2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if (player[i] == player[j]) {
					if (player[i]) {
						team1 += map[i][j];
					} else {
						team2 += map[i][j];
					}
				}
			}
//			if (player[i]) {
//
//			} else {
//
//			}
		}

		return Math.abs(team1 - team2);
	}

}
