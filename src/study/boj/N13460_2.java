package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 생각보다 쉽게 bfs로 풀면 된다..
// 1. 4방향으로 돌린다고 생각하고 각 방향으로 공이동시켜서 큐에 담는다 
// 2. 반복하다 조건 맞으면 나오기
// 주의할 것은 큐에서 나오는 조건, 큐에 담는 조건 ** 조건 주의 ** 
// 4차원 배열도 너무 겁먹지 말고 visited 의 경우에는 그 조합이 사용된 적이 있는가 여부 정도로 생각해도 될 듯 

public class N13460_2 {
	static int N, M;
	static char[][] map;
	static int answer = Integer.MAX_VALUE;
	static Ball start;
	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, -1, 1 };

	public static class Ball {
		int rx, ry, bx, by, count;

		public Ball(int rx, int ry, int bx, int by, int count) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.count = count;
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		int rx = 0;
		int ry = 0;
		int bx = 0;
		int by = 0;
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}

		start = new Ball(rx, ry, bx, by, 0);
		bfs();

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	public static void bfs() {
		boolean[][][][] visited = new boolean[10][10][10][10];
		Queue<Ball> q = new LinkedList<Ball>();
		q.add(start);
		visited[start.rx][start.ry][start.bx][start.by] = true;
		while (!q.isEmpty()) {
			Ball c = q.poll();
			if (c.count > 10)
				break;
			if (map[c.rx][c.ry] == 'O' && map[c.bx][c.by] != 'O') {
				answer = c.count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nrx = c.rx;
				int nry = c.ry;
				int nbx = c.bx;
				int nby = c.by;
				while (true) {
					if (map[nrx][nry] != '#' && map[nrx][nry] != 'O') {
						nrx += ax[i];
						nry += ay[i];
					} else {
						if (map[nrx][nry] == '#') { // 벽인경우는 계속 해야되니까 다시 한칸 빼주고 구멍인 경우는 걍 냅둠 
							nrx -= ax[i];
							nry -= ay[i];
						}
						break;
					}
				}

				while (true) {
					if (map[nbx][nby] != '#' && map[nbx][nby] != 'O') {
						nbx += ax[i];
						nby += ay[i];
					} else {
						if (map[nbx][nby] == '#') {
							nbx -= ax[i];
							nby -= ay[i];
						}
						break;
					}
				}

				if (nrx == nbx && nry == nby) { //공들이 같은 위치에 있는 경우에는  더 많이 이동한 애가 뒤에 있던애.. 하나 더빼준다 
					if (map[nrx][nry] != 'O') {
						int rd = Math.abs(c.rx - nrx) + Math.abs(c.ry - nry);
						int bd = Math.abs(c.bx - nbx) + Math.abs(c.by - nby);
						if (rd > bd) {
							nrx -= ax[i];
							nry -= ay[i];
						} else {
							nbx -= ax[i];
							nby -= ay[i];
						}
					}
				}

				if (nby < 0 || nbx < 0 || nry < 0 || nrx < 0 || nbx >= N || nrx >= N || nby >= M || nry >= M)
					continue;

				if (map[nbx][nby] == 'O' && map[nrx][nry] != 'O') //파란 공만 구멍에 들어가면 무시하고 다음꺼 넘어간다
					continue;

				if (visited[nrx][nry][nbx][nby] == false) {
					visited[nrx][nry][nbx][nby] = true;
					q.add(new Ball(nrx, nry, nbx, nby, c.count + 1));
				}
			}

		}
	}

}
