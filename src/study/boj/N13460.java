package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N13460 {

	public static class INFO {
		int ry, rx, by, bx, count;

		public INFO(int ry, int rx, int by, int bx, int count) {
			super();
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}

		public INFO() {
			super();
		}

	}

	static INFO start = new INFO();
	static char[][] map;
	static int N, M;
	static int[] ay = { -1, 1, 0, 0 };
	static int[] ax = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					start.ry = i;
					start.rx = j;
				}
				if (map[i][j] == 'B') {
					start.by = i;
					start.bx = j;
				}
			}
		}

		start.count = 0;

		int ret = bfs();
		System.out.println(ret);
	}

	public static int bfs() {
		int[][][][] visited = new int[10][10][10][10];
		Queue<INFO> q = new LinkedList<INFO>();
		q.add(start);
		visited[start.ry][start.rx][start.by][start.bx] = 1;

		int ret = -1;
		while (!q.isEmpty()) {
			INFO cur = q.poll();
			if (cur.count > 10)
				break;
			if (map[cur.ry][cur.rx] == 'O' && map[cur.by][cur.bx] != 'O') {
				ret = cur.count;
				break;
			}

			for (int dir = 0; dir < 4; dir++) {
				int next_ry = cur.ry;
				int next_rx = cur.rx;
				int next_by = cur.by;
				int next_bx = cur.bx;

				while (true) {
					if (map[next_ry][next_rx] != 'O' && map[next_ry][next_rx] != '#') {
						next_ry += ay[dir];
						next_rx += ax[dir];
					} else {
						if (map[next_ry][next_rx] == '#') {
							next_ry -= ay[dir];
							next_rx -= ax[dir];
						}
						break;
					}
				}

				while (true) {
					if (map[next_by][next_bx] != 'O' && map[next_by][next_bx] != '#') {
						next_by += ay[dir];
						next_bx += ax[dir];
					} else {
						if (map[next_by][next_bx] == '#') {
							next_by -= ay[dir];
							next_bx -= ax[dir];
						}
						break;
					}
				}

				if (next_ry == next_by && next_bx == next_rx) {
					if (map[next_ry][next_rx] != 'O') {
						int red_dist = Math.abs(next_ry - cur.ry) + Math.abs(next_rx - cur.rx);
						int blue_dist = Math.abs(next_by - cur.by) + Math.abs(next_bx - cur.bx);
						if (red_dist > blue_dist) {
							next_ry -= ay[dir];
							next_rx -= ax[dir];
						} else {
							next_by -= ay[dir];
							next_bx -= ax[dir];
						}
					}
				}

				if (next_by < 0 || next_bx < 0 || next_ry < 0 || next_rx < 0 || next_by >= N || next_ry >= N
						|| next_bx >= M || next_by >= M)
					continue;
				
				if (map[next_by][next_bx] == 'O' && map[next_ry][next_rx] != 'O')
					continue;

				if (visited[next_ry][next_rx][next_by][next_bx] == 0) {

					visited[next_ry][next_rx][next_by][next_bx] = 1;
					INFO next = new INFO(next_ry, next_rx, next_by, next_bx, cur.count + 1);
					q.add(next);
//					visited[next_ry][next_rx][next_by][next_bx] = 0;
				}
			}

		}

		return ret;
	}

}
