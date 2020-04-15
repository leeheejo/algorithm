package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//반ㄹ
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0 
//0 0 0 0 0 0 0 0 0

public class N2580 {
	static int[][] map = new int[10][10];
	static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 9; i++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			for (int j = 1; j <= 9; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] == 0)
					points.add(new Point(j, i));
			}
		}
		System.out.println();

		run(0);

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void run(int index) {
		if (index == points.size()) {
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <= 9; j++) {
					System.out.print(map[j][i] + " ");
				}
				System.out.println();
			}

			System.exit(0); //1개 찍으면 종료해버리려고 
//			return;
		}

		Point p = points.get(index);
		for (int i = 1; i < 10; i++) {
			if (!check(p.x, p.y, i))
				continue;
			map[p.x][p.y] = i;
			run(index + 1);
			map[p.x][p.y] = 0;

		}
	}

	public static boolean check(int x, int y, int v) {
		for (int i = 1; i < 10; i++) {
			if (map[i][y] == v || map[x][i] == v)
				return false;
		}

		for (int ty = ((y - 1) / 3) * 3 + 1; ty < ((y - 1) / 3) * 3 + 4; ty++) {
			for (int tx = ((x - 1) / 3) * 3 + 1; tx < ((x - 1) / 3) * 3 + 4; tx++) {
				if (map[tx][ty] == v)
					return false;
			}

		}

		return true;
	}
}
