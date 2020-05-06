package study;

import java.util.ArrayList;
import java.util.Scanner;

public class N3190 {
	static int N, K, L; // 판의 크기, 사과의 수, 방향 회전 수
	static int[][] map;
	static Turn[] turn;
	static ArrayList<Point> snake;
	static int cd = 0; // 오른쪽 보고 시작
	static int ax[] = { 0, -1, 0, 1 }; // 우 상 좌 하 왼쪽 90도 순
	static int ay[] = { 1, 0, -1, 0 };

	static int time = 0;

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
		int x, y;

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
		snake = new ArrayList<Point>();

		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x - 1][y - 1] = 1; // 사과 1
		}

		L = sc.nextInt();
		turn = new Turn[L];
		for (int i = 0; i < L; i++) {
			int s = sc.nextInt();
			char d = sc.next().charAt(0);
			turn[i] = new Turn(s, d);
		}

		snake.add(new Point(0, 0));
		map[0][0] = 2;

		System.out.println(play());
	}

	public static int play() {
		int index = 0;
		Turn t = turn[index];
		while (true) {
			if (time == t.sec) {
				// 방향 전환
				if (t.dir == 'L') {
					cd = (cd + 1) % 4;
				} else if (t.dir == 'D') {
					cd = (cd + 3) % 4;
				}
				if (index + 1 != turn.length) {
					t = turn[index + 1];
					index++;
				}
			}
			time++;
			if (!move())
				return time;

		}

	}

	public static boolean move() {
		Point tail = snake.get(0);
		Point head = snake.get(snake.size() - 1);
		int nx = head.x + ax[cd];
		int ny = head.y + ay[cd];
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) // 벽에 부딛히거나
			return false;
		if (map[nx][ny] == 2) // 자기자신이면
			return false;

		// 헤드 이동
		if (map[nx][ny] != 1) { // 테일도 이동 -> 현재테일 칸 0
			map[tail.x][tail.y] = 0;
			snake.remove(0);
		}
		map[nx][ny] = 2;
		snake.add(new Point(nx, ny));

		return true;
	}

}
