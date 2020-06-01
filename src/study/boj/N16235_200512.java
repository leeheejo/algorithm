package study.boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class N16235_200512 {

	public static class Tree implements Comparable<Tree> {
		int x, y;
		int age;
		boolean alive;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
			this.alive = true;
		}

		@Override
		public int compareTo(Tree o) {
			// TODO Auto-generated method stub
			if (this.age < o.age)
				return -1;
			else if (this.age > o.age)
				return 1;
			return 0;
		}

	}

	static int N, M, K;
	static int[][] map;
	static int[][] a;
	static ArrayList<Tree> tree = new ArrayList<Tree>();
	static int[] ax = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] ay = { -1, 0, 1, -1, +1, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new int[N + 1][N + 1];
		a = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], 5);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				a[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int age = sc.nextInt();
			tree.add(new Tree(x, y, age));
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(tree.size());
	}

	public static void spring() {
		Collections.sort(tree);
		for (int i = 0; i < tree.size(); i++) {
			Tree t = tree.get(i);
			int nut = map[t.x][t.y];
			if (nut - t.age >= 0) {
				map[t.x][t.y] -= t.age;
				t.age++;
			} else {
				t.alive = false;
			}
		}
	}

	public static void summer() {
		ArrayList<Tree> temp = new ArrayList<Tree>();
		for (int i = 0; i < tree.size(); i++) {
			Tree t = tree.get(i);
			if (tree.get(i).alive) {
				temp.add(tree.get(i));
				continue;
			}
			map[t.x][t.y] += t.age / 2;
		}

		tree.clear();
		for (int i = 0; i < temp.size(); i++) {
			tree.add(temp.get(i));
		}
	}

	public static void fall() {
		int cur = tree.size();
		for (int i = 0; i < cur; i++) {
			Tree t = tree.get(i);
			if (t.age % 5 == 0) {
				for (int j = 0; j < 8; j++) {
					int nx = t.x + ax[j];
					int ny = t.y + ay[j];
					if (nx < 1 || ny < 1 || nx > N || ny > N)
						continue;
					tree.add(new Tree(nx, ny, 1));
				}
			}
		}
	}

	public static void winter() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += a[i][j];
			}
		}

	}

}
