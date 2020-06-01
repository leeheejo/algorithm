package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class N14891 {
	static int[][] map = new int[5][9];
	static Queue<Turn> q;
	static boolean[] visited;
	static int K;

	public static class Turn {
		int index, dir;

		public Turn(int index, int dir) {
			super();
			this.index = index;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 4; i++) {
			String s = bf.readLine();
			for (int j = 1; j <= 8; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
			}
		}

		K = Integer.parseInt(bf.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			q = new LinkedList<Turn>();
			visited = new boolean[5];
			int saw = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			q.add(new Turn(saw, dir));
			visited[saw] = true;
			dfs(saw, dir);
			turn();
		}

		System.out.println(map[1][1] * 1 + map[2][1] * 2 + map[3][1] * 4 + map[4][1] * 8);
	}

	public static void dfs(int s, int d) {
		int[] a = { -1, 1 };
		for (int i = 0; i < 2; i++) {
			if (s + a[i] < 1 || s + a[i] > 4)
				continue;
			if (visited[s + a[i]] == true)
				continue;
			if (i == 0) { // 앞쪽 바퀴
				if (map[s + a[i]][3] != map[s][7]) {
					visited[s + a[i]] = true;
					q.add(new Turn(s + a[i], d * -1));
					dfs(s + a[i], d * -1);
				}
			} else if (i == 1) { // 뒤쪽 바퀴
				if (map[s + a[i]][7] != map[s][3]) {
					visited[s + a[i]] = true;
					q.add(new Turn(s + a[i], d * -1));
					dfs(s + a[i], d * -1);
				}
			}
		}
	}

	public static void turn() {
		while (!q.isEmpty()) {
			Turn t = q.poll();
			int s = t.index;
			int d = t.dir;
			if (d == 1) {
				move(s);
			} else {
				move(s);
				move(s);
				move(s);
				move(s);
				move(s);
				move(s);
				move(s);
			}
		}
	}

	public static void move(int index) { // 시계방향 회전/ 7번하면 반시계 방향
		int tmp = map[index][8];
		for (int i = 8; i > 1; i--) {
			map[index][i] = map[index][i - 1];
		}
		map[index][1] = tmp;
	}

}