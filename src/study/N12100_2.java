package study;

import java.util.Scanner;

import study.N12100.BOARD;

public class N12100_2 {
	static int N;
	static int[][] in;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		in = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				in[i][j] = sc.nextInt();
			}
		}
		Board start = new Board();
		start.copy(in);
		dfs(start, 0);
		System.out.println(ans);

	}

	public static class Board {
		int[][] map = new int[20][20];

		public Board(int[][] map) {
			super();
			this.map = map;
		}

		public Board() {
			super();
		}

		void copy(int[][] c) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.map[i][j] = c[i][j];
				}
			}
		}

		int getMax() {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, this.map[i][j]);
				}
			}
			return max;
		}

		void rotate() {
			int[][] temp = new int[20][20];
			// 90도 회전
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = this.map[N - j - 1][i];
				}
			}
			// 맵 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.map[i][j] = temp[i][j];
				}
			}

		}

		void up() {
			int[][] temp = new int[20][20];
			for (int y = 0; y < N; y++) {
				int target = -1;
				boolean flag = false;
				for (int x = 0; x < N; x++) {
					if (map[x][y] == 0)
						continue;

					temp[++target][y] = this.map[x][y];
					if (target != 0) {
						if (temp[target][y] == temp[target - 1][y] && flag == false) {
							flag = true;
							temp[target - 1][y] *= 2;
							temp[target][y] = 0;
							target--;
						} else {
							flag = false;
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.map[i][j] = temp[i][j];
				}
			}
		}
	}

	public static void dfs(Board b, int cnt) {
		if (cnt == 5) {
			ans = Math.max(ans, b.getMax());
			return;
		}
		Board cur = b;
		for (int i = 0; i < 4; i++) {
			Board next = new Board();
			next.copy(cur.map);
			next.up();
			dfs(next, cnt + 1);
			cur.rotate();
		}
	}

	public static void print(Board b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(b.map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
}
