package study.boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N3190_200812 {

	static int N, K, L;
	static int[][] map;
	static int[] ax = { -1, 0, 1, 0 };
	static int[] ay = { 0, 1, 0, -1 };

	public static class Turn {
		int sec;
		char dir;

		public Turn(int sec, char dir) {
			super();
			this.sec = sec;
			this.dir = dir;
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x - 1][y - 1] = 1;
		}

		L = sc.nextInt();
		Queue<Turn> q = new LinkedList<Turn>();
		for (int i = 0; i < L; i++) {
			int s = sc.nextInt();
			String d = sc.next();
			q.add(new Turn(s, d.charAt(0)));
		}

		ArrayList<Point> snake = new ArrayList<>();
		snake.add(new Point(0, 0));
		int dir = 1;
		map[0][0] = 2;

		int time = 0;

		Turn t = q.poll();
		while (true) {
			time++;
			Point head = snake.get(snake.size() - 1);
			int nx = head.x + ax[dir];
			int ny = head.y + ay[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2)
				break;

			snake.add(new Point(nx, ny));
			if (map[nx][ny] != 1) {
				Point tail = snake.get(0);
				map[tail.x][tail.y] = 0;
				snake.remove(0);
			}
			map[nx][ny] = 2;

			if (t.sec == time) {
				if (t.dir == 'D') {
					dir = (dir + 1) % 4;
				} else if (t.dir == 'L') {
					dir = (dir + 3) % 4;
				}
				if (!q.isEmpty())
					t = q.poll();
			}
//			print();
		}

		System.out.println(time);
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
}
