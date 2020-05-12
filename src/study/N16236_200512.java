package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N16236_200512 {

	public static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int size;
		int dis;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		public Fish(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if (this.dis == o.dis) {
				if (this.x == o.x) {
					if (this.y == o.y)
						return 0;
					else
						return this.y < o.y ? -1 : 1;
				} else
					return this.x < o.x ? -1 : 1;
			} else
				return this.dis < o.dis ? -1 : 1;
		}
	}

	static int N;
	static int[][] map, temp;
	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };
	static Fish shark;
	static int eatCount = 0;

	static ArrayList<Fish> eat;
	static int time = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					shark = new Fish(i, j, 2);
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			bfs();
			if (eat.size() == 0) {
				System.out.println(time);
				break;
			}
			eat();
		}

	}

	public static void bfs() {
		temp = new int[N][N];
		eat = new ArrayList<Fish>();
		Queue<Fish> q = new LinkedList<Fish>();
		shark.dis = 1;
		temp[shark.x][shark.y] = shark.dis;
		q.add(shark);

		while (!q.isEmpty()) {
			Fish f = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = f.x + ax[i];
				int ny = f.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (map[nx][ny] > shark.size)
					continue;
				if (temp[nx][ny] > 0)
					continue;
				Fish nf = new Fish(nx, ny);
				nf.dis = f.dis + 1;
				temp[nx][ny] = nf.dis;
				q.add(nf);

				if (map[nx][ny] > 0 && map[nx][ny] < shark.size) {
					nf.size = map[nx][ny];
					eat.add(nf);
				}
			}
		}

	}

	public static void eat() {
		Collections.sort(eat);
		Fish f = eat.get(0);
		eatCount++;
		time += f.dis - 1;
		map[f.x][f.y] = 0;
		shark.x = f.x;
		shark.y = f.y;
		if (eatCount == shark.size) {
			eatCount = 0;
			shark.size++;
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(temp[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
