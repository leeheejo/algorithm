package study.boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N16236_2 {

	static class Fish implements Comparable<Fish> {
		int x, y, v;

		public Fish(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.x == o.x)
				return this.y < o.y ? -1 : 1;
			else
				return this.x < o.x ? -1 : 1;
		}

	}

	static class Dis {
		int x, y, v;

		public Dis(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}

	}

	static int N;
	static int[][] map;
	static int[][] dis;
	static boolean[][] visited;
	static Fish shark;
	static ArrayList<Fish> fish;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int ans = 0;
	static int cnt = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		fish = new ArrayList<Fish>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9)
					shark = new Fish(i, j, 2);
			}
		}

		eat();

		System.out.println(ans);
	}

	public static void findDis() {
		dis = new int[N][N];
		visited = new boolean[N][N];

		Queue<Dis> q = new LinkedList<Dis>();
		q.add(new Dis(shark.x, shark.y, 0));
		dis[shark.x][shark.y] = 0;
		visited[shark.x][shark.y] = true;
		while (!q.isEmpty()) {
			Dis d = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = d.x + ax[i];
				int ny = d.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (map[nx][ny] > shark.v)
					continue;
				if (visited[nx][ny])
					continue;

				q.add(new Dis(nx, ny, d.v + 1));
				dis[nx][ny] = d.v + 1;
				visited[nx][ny] = true;
			}

		}

	}

	public static void eat() {

		while (true) {

			findDis();

			// 먹을 수 있는 물고기 중 단거리를 찾는다..
			int MinDir = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dis[i][j] == 0)
						continue;

					if (map[i][j] > 0 && map[i][j] < shark.v)
						MinDir = Math.min(MinDir, dis[i][j]);
				}
			}

			// 먹을 애들을 리스트에 넣는다..
			ArrayList<Fish> eatlist = new ArrayList<Fish>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dis[i][j] == MinDir && map[i][j] > 0 && map[i][j] < shark.v) {
						eatlist.add(new Fish(i, j, map[i][j]));
					}
				}
			}

			Collections.sort(eatlist);

			if (eatlist.size() == 0)
				return;

			// 정렬한 것 중가장 위에 있는 애를 먹는다.
			Fish s = eatlist.get(0);
			map[s.x][s.y] = 0;
			ans += dis[s.x][s.y];
			map[shark.x][shark.y] = 0; //상어 처음 위치를 바꿔줘야 함..ㅠ.ㅠ 이것때매 개고생!! 
			shark.x = s.x;
			shark.y = s.y;

			cnt++;
			if (cnt == shark.v) {
				shark.v++;
				cnt = 0;
			}
//
//			System.out.println();
//			print();
//			System.out.println();
		}
	}

	static void print() {
		System.out.println(shark.v + " " + ans);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
