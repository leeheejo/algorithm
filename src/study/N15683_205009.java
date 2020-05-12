package study;

import java.util.ArrayList;
import java.util.Scanner;

public class N15683_205009 {
	static int N, M;
	static int[][] map;
	static ArrayList<Cam> cam = new ArrayList<Cam>();
	static ArrayList<Cam> wall = new ArrayList<Cam>();
	static int ZERO = 0;
	static int tempZero = 0;
	static int min = Integer.MAX_VALUE;

	static class Cam {
		int x, y, t, d;

		public Cam(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Cam(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 6) {
					wall.add(new Cam(i, j));
				} else if (map[i][j] > 0 && map[i][j] < 6) {
					cam.add(new Cam(i, j, map[i][j]));
				} else if (map[i][j] == 0) {
					ZERO++;
				}
			}
		}

		dfs(0);
		System.out.println(min);
	}

	public static void dfs(int index) {
		if (index == cam.size()) {
			int a = check();
			min = Math.min(min, a);
			init();
			return;
		}

		switch (cam.get(index).t) {
		case 1:
			for (int i = 0; i < 4; i++) {
				cam.get(index).d = i;
				dfs(index + 1);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				cam.get(index).d = i;
				dfs(index + 1);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				cam.get(index).d = i;
				dfs(index + 1);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				cam.get(index).d = i;
				dfs(index + 1);
			}
			break;
		case 5:
			for (int i = 0; i < 1; i++) {
				cam.get(index).d = i;
				dfs(index + 1);
			}
			break;

		default:
			break;
		}
	}

	public static int check() {
		for (int i = 0; i < cam.size(); i++) {
			Cam c = cam.get(i);
			switch (c.t) {
			case 1:
				if (c.d == 0) {
					right(c);
				} else if (c.d == 1) {
					up(c);
				} else if (c.d == 2) {
					left(c);
				} else if (c.d == 3) {
					down(c);
				}
				break;
			case 2:
				if (c.d == 0) {
					right(c);
					left(c);
				} else if (c.d == 1) {
					up(c);
					down(c);
				}
				break;
			case 3:
				if (c.d == 0) {
					right(c);
					up(c);
				} else if (c.d == 1) {
					down(c);
					right(c);
				} else if (c.d == 2) {
					left(c);
					down(c);
				} else if (c.d == 3) {

					left(c);
					up(c);
				}
				break;
			case 4:
				if (c.d == 0) {
					right(c);
					left(c);
					up(c);
				} else if (c.d == 1) {
					up(c);
					down(c);
					right(c);
				} else if (c.d == 2) {
					left(c);
					right(c);
					down(c);
				} else if (c.d == 3) {
					down(c);
					left(c);
					up(c);
				}
				break;
			case 5:
				right(c);
				left(c);
				up(c);
				down(c);
				break;

			default:
				break;
			}

		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static void init() {
		map = new int[N][M];
		for (int i = 0; i < wall.size(); i++) {
			map[wall.get(i).x][wall.get(i).y] = 6;
		}

		for (int i = 0; i < cam.size(); i++) {
			map[cam.get(i).x][cam.get(i).y] = cam.get(i).t;
		}
	}

	public static void right(Cam c) {
		int x = c.x;
		int y = c.y;
		while (true) {
			y += 1;
			if (y >= M || map[x][y] == 6)
				break;
			if (map[x][y] == 0) {
				map[x][y] = 7;
			}
		}
	}

	public static void left(Cam c) {
		int x = c.x;
		int y = c.y;
		while (true) {
			y -= 1;
			if (y < 0 || map[x][y] == 6)
				break;
			if (map[x][y] == 0) {
				tempZero--;
				map[x][y] = 7;
			}
		}
	}

	public static void up(Cam c) {
		int x = c.x;
		int y = c.y;
		while (true) {
			x -= 1;
			if (x < 0 || map[x][y] == 6)
				break;

			if (map[x][y] == 0) {
				tempZero--;
				map[x][y] = 7;
			}
		}
	}

	public static void down(Cam c) {
		int x = c.x;
		int y = c.y;
		while (true) {
			x += 1;
			if (x >= N || map[x][y] == 6)
				break;
			if (map[x][y] == 0) {
				tempZero--;
				map[x][y] = 7;
			}
		}
	}

}
