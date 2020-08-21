package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N15683_200821 {

	static int N, M;
	static int[][] map, temp;
	static ArrayList<Camera> cameras;
	static int totalCamera = 0;
	static int answer = Integer.MAX_VALUE;

	public static class Camera {
		int x;
		int y;
		int type;
		int d = 0;

		public Camera(int x, int y, int type) {
			super();
			this.type = type;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Camera [x=" + x + ", y=" + y + ", type=" + type + ", d=" + d + "]";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		cameras = new ArrayList<Camera>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cameras.add(new Camera(i, j, map[i][j]));
					totalCamera++;
				}
			}
		}

		dfs(0);
		System.out.println(answer);
	}

	public static void dfs(int c) {
		if (c == totalCamera) {
			draw();
			print();
			int a = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] == 0)
						a++;
				}
			}
			
//			if(a == 6)
//				print();

			answer = Math.min(answer, a);
			return;
		}

		Camera camera = cameras.get(c);

		switch (camera.type) {
		case 1:
		case 3:
		case 4:
			for (int i = 0; i < 4; i++) {
				camera.d = i;
				dfs(c + 1);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				camera.d = i;
				dfs(c + 1);
			}
			break;
		case 5:
			dfs(c + 1);
			break;
		}

	}

	public static void draw() {
		temp = new int[N][M];
		cloneMap();
		for (int i = 0; i < cameras.size(); i++) {
			Camera c = cameras.get(i);
			switch (c.type) {
			case 1:
				switch (c.d) {
				case 0:
					left(c);
					break;
				case 1:
					right(c);
					break;
				case 2:
					up(c);
					break;
				case 3:
					down(c);
					break;
				}
				break;
			case 2:
				switch (c.d) {
				case 0:
					left(c);
					right(c);
					break;
				case 1:
					up(c);
					down(c);
					break;
				}
				break;
			case 3:
				switch (c.d) {
				case 0:
					right(c);
					up(c);
					break;
				case 1:
					left(c);
					up(c);
					break;
				case 2:
					left(c);
					down(c);
					break;
				case 3:
					right(c);
					down(c);
					break;
				}
				break;
			case 4:
				switch (c.d) {
				case 0:
					right(c);
					up(c);
					down(c);
					break;
				case 1:
					left(c);
					up(c);
					down(c);
					break;
				case 2:
					left(c);
					right(c);
					down(c);
					break;
				case 3:
					left(c);
					right(c);
					up(c);
					break;
				}
				break;
			case 5:
				left(c);
				right(c);
				up(c);
				down(c);
				break;
			}
		}

	}

	public static void left(Camera c) {
		for (int i = c.y - 1; i >= 0; i--) {
			if (map[c.x][i] == 6)
				break;
			if (map[c.x][i] == 0)
				temp[c.x][i] = 7;
		}

	}

	public static void right(Camera c) {
		for (int i = c.y + 1; i < M; i++) {
			if (map[c.x][i] == 6)
				break;
			if (map[c.x][i] == 0)
				temp[c.x][i] = 7;
		}
	}

	public static void down(Camera c) {
		for (int i = c.x + 1; i < N; i++) {
			if (map[i][c.y] == 6)
				break;
			if (map[i][c.y] == 0)
				temp[i][c.y] = 7;
		}
	}

	public static void up(Camera c) {
		for (int i = c.x - 1; i >= 0; i--) {
			if (map[i][c.y] == 6)
				break;
			if (map[i][c.y] == 0)
				temp[i][c.y] = 7;
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	public static void cloneMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}
}
