package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N15683 {
	public static class Cam {
		int x, y;
		int type;
		int dir;

		public Cam(int x, int y, int type, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Cam [type=" + type + ", dir=" + dir + "]";
		}
	}

	public static class Wall {
		int x, y;

		public Wall(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static ArrayList<Cam> cam = new ArrayList<Cam>();
	static ArrayList<Wall> wall = new ArrayList<Wall>();
	static int[][] origin, map;
	static int N, M;
	static int test = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		origin = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				origin[i][j] = sc.nextInt();
				if (origin[i][j] > 0 && origin[i][j] < 6)
					cam.add(new Cam(i, j, origin[i][j], 0));
				if (origin[i][j] == 6)
					wall.add(new Wall(i, j));
			}
		}

		dfs(0);
		System.out.println(min);

	}

	public static void dfs(int index) {
		if (index == cam.size()) {
			// map clear
			init();
			min = Math.min(watch(), min);
//			System.out.println(min);
//			print();
			return;
		}

		Cam tmp = cam.get(index);
		if (tmp.type == 1) {
			for (int d = 0; d < 4; d++) {
				tmp.dir = d;
				dfs(index + 1);
			}
		} else if (tmp.type == 2) {
			for (int d = 0; d < 2; d++) {
				tmp.dir = d;
				dfs(index + 1);
			}

		} else if (tmp.type == 3) {
			for (int d = 0; d < 4; d++) {
				tmp.dir = d;
				dfs(index + 1);
			}

		} else if (tmp.type == 4) {
			for (int d = 0; d < 4; d++) {
				tmp.dir = d;
				dfs(index + 1);
			}
		} else if (tmp.type == 5) {
			for (int d = 0; d < 1; d++) {
				tmp.dir = d;
				dfs(index + 1);
			}
		}

	}

	public static int watch() {
		int ans = 0;
		for (int i = 0; i < cam.size(); i++) {
			Cam tmp = cam.get(i);
			if (tmp.type == 1) {
				if (tmp.dir == 0) {
					up(tmp);
				} else if (tmp.dir == 1) {
					down(tmp);
				} else if (tmp.dir == 2) {
					right(tmp);
				} else if (tmp.dir == 3) {
					left(tmp);
				}

			} else if (tmp.type == 2) {
				if (tmp.dir == 0) {
					up(tmp);
					down(tmp);
				} else if (tmp.dir == 1) {
					left(tmp);
					right(tmp);
				}
			} else if (tmp.type == 3) {
				if (tmp.dir == 0) {
					up(tmp);
					right(tmp);
				} else if (tmp.dir == 1) {
					right(tmp);
					down(tmp);
				} else if (tmp.dir == 2) {
					up(tmp);
					left(tmp);
				} else if (tmp.dir == 3) {
					down(tmp);
					left(tmp);
				}
			} else if (tmp.type == 4) {
				if (tmp.dir == 0) {
					left(tmp);
					right(tmp);
					up(tmp);
				} else if (tmp.dir == 1) {
					left(tmp);
					right(tmp);
					down(tmp);
				} else if (tmp.dir == 2) {
					up(tmp);
					down(tmp);
					left(tmp);
				} else if (tmp.dir == 3) {
					up(tmp);
					down(tmp);
					right(tmp);
				}
			} else if (tmp.type == 5) {
				up(tmp);
				down(tmp);
				left(tmp);
				right(tmp);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					ans++;
			}
		}
		return ans;
	}

	public static void up(Cam c) {
		int x = c.x;
		int y = c.y;

		while (true) {
			x -= 1;
			if (x < 0 || map[x][y] == 6)
				return;
			map[x][y] = -1;
		}
	}

	public static void down(Cam c) {
		int x = c.x;
		int y = c.y;

		while (true) {
			x += 1;
			if (x >= N || map[x][y] == 6)
				return;
			map[x][y] = -1;
		}
	}

	public static void left(Cam c) {
		int x = c.x;
		int y = c.y;

		while (true) {
			y -= 1;
			if (y < 0 || map[x][y] == 6)
				return;
			map[x][y] = -1;
		}
	}

	public static void right(Cam c) {
		int x = c.x;
		int y = c.y;

		while (true) {
			y += 1;
			if (y >= M || map[x][y] == 6)
				return;
			map[x][y] = -1;
		}
	}

	public static void init() {
		map = new int[N][M];
		for (int i = 0; i < wall.size(); i++) {
			Wall w = wall.get(i);
			map[w.x][w.y] = 6;
		}
		for (int i = 0; i < cam.size(); i++) {
			Cam c = cam.get(i);
			map[c.x][c.y] = c.type;
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
