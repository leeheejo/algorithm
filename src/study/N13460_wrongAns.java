package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N13460_wrongAns {
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[10][10];
		visited = new boolean[10][10][10][10];

		int rx = 0, ry = 0, bx = 0, by = 0;
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}
		visited[rx][ry][bx][by] = true;
		dfs(0, rx, ry, bx, by);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	public static void dfs(int index, int rx, int ry, int bx, int by) {
		if (index > 10) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nrx = rx;
			int nry = ry;
			int nbx = bx;
			int nby = by;

			while (true) {
				nrx += ax[i];
				nry += ay[i];
				if (map[nrx][nry] == '#' || map[nrx][nry] == 'O')
					break;
			}
			if (map[nrx][nry] == '#') {
				nrx -= ax[i];
				nry -= ay[i];
			}

			while (true) {
				nbx += ax[i];
				nby += ay[i];

				if (map[nbx][nby] == '#' || map[nbx][nby] == 'O')
					break;
			}
			if (map[nbx][nby] == '#') {
				nbx -= ax[i];
				nby -= ay[i];
			}

			if (map[nbx][nby] != 'O') {
				if (nrx == nbx && nry == nby) {
					int rd = Math.abs(nrx - rx) + Math.abs(nry - ry);
					int bd = Math.abs(nbx - bx) + Math.abs(nby - by);
					if (rd > bd) {
						nrx -= ax[i];
						nry -= ay[i];
					} else {
						nbx -= ax[i];
						nby -= ay[i];
					}
				}
			}
			if (nrx < 0 || nbx < 0 || nrx >= N || nbx >= N || nby < 0 || nry < 0 || nry >= M || nby >= M)
				continue;

			if (visited[nrx][nry][nbx][nby])
				continue;

			if (map[nbx][nby] == 'O')
				continue;

			if (map[nrx][nry] == 'O' && map[nbx][nby] != 'O') {
				ans = Math.min(index + 1, ans);
				return;
			}

			visited[nrx][nry][nbx][nby] = true;
			dfs(index + 1, nrx, nry, nbx, nby);
			visited[nrx][nry][nbx][nby] = false;
		}
	}

}
