package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N15686_200511 {

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static public int N, M;
	static public Point[] sel_chicken;
	static public ArrayList<Point> house;
	static public ArrayList<Point> chicken;
	static public boolean[] visited;
	static public int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		house = new ArrayList<Point>();
		chicken = new ArrayList<Point>();
		sel_chicken = new Point[M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int in = sc.nextInt();
				if (in == 1) {
					house.add(new Point(i, j));
				} else if (in == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}

		visited = new boolean[chicken.size()];
		dfs(0, 0);
		System.out.println(ans);

	}

	public static void dfs(int index, int p) {
		if (index == M) {
			int sum = 0;
			for (int i = 0; i < house.size(); i++) {
				int min = Integer.MAX_VALUE;
				Point h = house.get(i);
				for (int j = 0; j < sel_chicken.length; j++) {
					Point c = sel_chicken[j];
					int d = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
					min = Math.min(min, d);
				}
				sum += min;
			}

			ans = Math.min(sum, ans);
			return;
		}

		for (int i = p; i < chicken.size(); i++) {
			if (visited[i])
				continue;
			sel_chicken[index] = chicken.get(i);
			visited[i] = true;
			dfs(index + 1, i + 1);
			visited[i] = false;
		}
	}

}
