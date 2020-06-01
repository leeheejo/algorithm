package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

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

public class N2580_re {
	static int[][] map = new int[9][9];
	static ArrayList<Point> q = new ArrayList<Point>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				map[j][i] = sc.nextInt();
				if (map[j][i] == 0)
					q.add(new Point(j, i));
			}
		}
		run(0);
	}

	public static void run(int index) {
		if (index == q.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[j][i] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}

		Point tmp = q.get(index);
		for (int i = 1; i <= 9; i++) {
			if (!check(tmp.x, tmp.y, i))
				continue;

			map[tmp.x][tmp.y] = i;
			run(index + 1);
			map[tmp.x][tmp.y] = 0;
		}

	}

	public static boolean check(int x, int y, int index) {
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == index)
				return false;

			if (map[x][i] == index)
				return false;
		}
		for (int i = (y / 3) * 3; i < (y / 3) * 3 + 3; i++) {
			for (int j = (x / 3) * 3; j < (x / 3) * 3 + 3; j++) {
				if (map[j][i] == index)
					return false;
			}
		}
		return true;
	}

	static class Point {
		int x;
		int y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
