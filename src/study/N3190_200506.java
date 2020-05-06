package study;

import java.util.ArrayList;
import java.util.Scanner;

public class N3190_200506 {

	static int N, K, L;
	static int[][] map;
	static Turn[] turn;
	static ArrayList<Snake> snake = new ArrayList<Snake>();
	static boolean[][] visited;
	static int[] ax = { 0, -1, 0, 1 };
	static int[] ay = { 1, 0, -1, 0 };

	public static class Turn {
		int sec;
		char dir;

		public Turn(int sec, char dir) {
			super();
			this.sec = sec;
			this.dir = dir;
		}
	}

	public static class Snake {
		int x, y;

		public Snake(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];

		K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x-1][y-1] = 1;
		}

		L = sc.nextInt();
		turn = new Turn[L];
		for (int i = 0; i < L; i++) {
			int s = sc.nextInt();
			String d = sc.next();
			turn[i] = new Turn(s, d.charAt(0));
		}

		int time = 0;
		int nx = 0;
		int ny = 0;
		int nd = 0;
		snake.add(new Snake(nx, ny));
		Turn t = turn[0];
		int t_index = 0;
		while (true) {
			time++;
			nx = nx + ax[nd];
			ny = ny + ay[nd];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2)
				break;
			
			snake.add(new Snake(nx, ny));
			if (map[nx][ny] != 1) {
				Snake tail = snake.get(0);
				map[tail.x][tail.y] = 0;
				snake.remove(0);
			}
			map[nx][ny] = 2;
			
			if (t.sec == time) {
				if (t.dir == 'L') {
					nd = (nd + 1) % 4;
				} else if (t.dir == 'D') {
					nd = (nd + 3) % 4;
				}
				t_index++;
				if (t_index < L)
					t = turn[t_index];
			}
//			print();
		}

		System.out.println(time);
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
