package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N17406 {

	static int N, M, K;
	static int[][] origin, map;
	static boolean[] visited;
	static ArrayList<Turn> turn = new ArrayList<Turn>();
	static Turn[] sel;
	static int min = Integer.MAX_VALUE;

	public static class Turn {
		int r, c, s;

		public Turn(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[K];
		sel = new Turn[K];
		origin = new int[N + 1][M + 1];
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				origin[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			turn.add(new Turn(r, c, s));
		}

		dfs(0);

		System.out.println(min);

	}

	public static void dfs(int index) {
		if (index == K) {
			init();
			for (int i = 0; i < K; i++) {
				Turn t = sel[i];
				play(t.r, t.c, t.s);
			}
			min = Math.min(min, calc());
			return;
		}

		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			sel[index] = turn.get(i);
			dfs(index + 1);
			visited[i] = false;
		}
	}

	public static void play(int r, int c, int s) {

		int ax = r - s;
		int ay = c - s;
		int bx = r + s;
		int by = c + s;
		while (true) {
			turn(ax, ay, bx, by);
			ax++;
			ay++;
			bx--;
			by--;
			if (ax == bx && ay == by)
				break;
		}

	}

	public static void turn(int ax, int ay, int bx, int by) {
		int temp = map[ax][ay];
		for (int i = ax; i < bx; i++) {
			map[i][ay] = map[i + 1][ay];
		}

		for (int i = ay; i < by; i++) {
			map[bx][i] = map[bx][i + 1];
		}

		for (int i = bx; i > ax; i--) {
			map[i][by] = map[i - 1][by];
		}

		for (int i = by; i > ay; i--) {
			map[ax][i] = map[ax][i - 1];
		}

		map[ax][ay + 1] = temp;

	}

	public static int calc() {
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			int temp = 0;
			for (int j = 1; j < M + 1; j++) {
				temp += map[i][j];
			}
			ans = Math.min(ans, temp);
		}

		return ans;
	}

	public static void init() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
