package study.boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N14502_200507 {
	static int[][] map;
	static int[][] copy;
	static int N, M;
	static ArrayList<Virus> virus = new ArrayList<Virus>();
	static int ZERO = 0;
	static int ans = Integer.MIN_VALUE;
	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };

	public static class Virus {
		int x, y;

		public Virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2)
					virus.add(new Virus(i, j));
				if (map[i][j] == 0)
					ZERO++;
			}
		}
		copy(copy, map);
		dfs(0, 0);

		System.out.println(ans);
	}

	public static void copy(int[][] dest, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dest[i][j] = origin[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void dfs(int index, int c) {
		if (index == 3) {
			ans = Math.max(ans, bfs());
			return;
		}
		for (int i = c; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 1 || copy[i][j] == 2)
					continue;

				copy[i][j] = 1;
				dfs(index + 1, i);
				copy[i][j] = 0;

			}
		}
	}

	public static int bfs() {
		int[][] temp = new int[N][M];
		int count = ZERO - 3;
		copy(temp, copy);
		Queue<Virus> q = new LinkedList<Virus>();
		for (int i = 0; i < virus.size(); i++) {
			q.add(virus.get(i));
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = v.x + ax[i];
				int ny = v.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (temp[nx][ny] != 0)
					continue;
				temp[nx][ny] = 2;
				count--;
				q.add(new Virus(nx, ny));
			}
		}

		return count;
	}

}
