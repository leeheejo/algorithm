package study.boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N16236 {
	static class Fish implements Comparable<Fish> { // 물고기 위치저장..
		int y, x;

		public Fish(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if (this.y == o.y)
				return this.x < o.x ? -1 : 1;
			else
				return this.y < o.y ? -1 : 1;
		}

	}

	static class Dir { // 상어랑 물고기 사이의 거리 저장
		int y, x, val;

		public Dir(int y, int x, int val) {
			super();
			this.y = y;
			this.x = x;
			this.val = val;
		}
	}

	static int N;
	static int[][] map;
	static int[][] copy;

	static Fish shark;
	static ArrayList<Fish> fish;

	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int result;
	static int sharkSize;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		fish = new ArrayList<Fish>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) // 상어인경우!
					shark = new Fish(i, j);
				if (map[i][j] != 9 && map[i][j] != 0) // 물고기인경우!
					fish.add(new Fish(i, j));
			}
		}

		result = 0;
		sol(shark.y, shark.x);
		System.out.println(result);
	}

	public static void sol(int y, int x) {
		int sharkY = y;
		int sharkX = x;

		sharkSize = 2;
		int count = 0; // 상어가 물고기 먹은 횟수..

		while (true) {
			copy = new int[N][N];
			visited = new boolean[N][N];

			checking(sharkY, sharkX);

			int minDir = Integer.MAX_VALUE;
			// 먹을 수 있는 물고기 중 최소이동거리 저장한다..
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < sharkSize && map[i][j] > 0 && copy[i][j] > 0)
						if (minDir > copy[i][j])
							minDir = copy[i][j];
				}
			}

			// 먹을 물고기 저장
			ArrayList<Fish> list = new ArrayList<Fish>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < sharkSize && map[i][j] > 0 && copy[i][j] == minDir)
						list.add(new Fish(i, j));
				}
			}

			if (list.size() == 0) {
				break;
			} else {
				Collections.sort(list);
				int fy = list.get(0).y;
				int fx = list.get(0).x;

				result += copy[fy][fx];
				map[fy][fx] = 0;
				count++;

				if (count == sharkSize) {
					sharkSize++;
					count = 0;
				}

				map[sharkY][sharkX] = 0;
				sharkY = fy;
				sharkX = fx;
				continue;
			}
		}
	}

	// 상어로부터 거리를 구한다 (상어가 갈 수 있는 길)
	public static void checking(int y, int x) { // 카피 변수명 써도 안꼬이나?? 나중에 수정해보자..
		int count = 0;
		Queue<Dir> q = new LinkedList<Dir>();
		q.add(new Dir(y, x, count));
		copy[y][x] = count;
		visited[y][x] = true;
		while (!q.isEmpty()) {
			Dir d = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = d.y + dy[i];
				int nx = d.x + dx[i];
				int nval = d.val;
				if (nx < 0 || ny < 0 || ny >= N || nx >= N)
					continue;
				if (!visited[ny][nx] && map[ny][nx] <= sharkSize) {
					q.add(new Dir(ny, nx, nval + 1));
					copy[ny][nx] = nval + 1;
					visited[ny][nx] = true;
				}
			}
		}

	}

}
