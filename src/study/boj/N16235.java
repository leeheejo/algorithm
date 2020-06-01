package study.boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class N16235 {
	public static class Tree implements Comparable<Tree> {
		int x, y, z;
		boolean alive;

		public Tree(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.alive = true;
		}

		@Override
		public String toString() {
			return "Tree [x=" + x + ", y=" + y + ", z=" + z + ", alive=" + alive + "]";
		}

		@Override
		public int compareTo(Tree o) { // 이놈.. 같을때 0리턴안하면 에러나는듯..
			// TODO Auto-generated method stub
			if (this.z < o.z)
				return -1;
			else if (this.z > o.z)
				return 1;
			else
				return 0;
		}
	}

	static int N, M, K;
	static int[][] s2d2;
	static int[][] map;
	static ArrayList<Tree> tree;
	static int[] ax = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] ay = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		s2d2 = new int[N][N];
		map = new int[N][N];
		tree = new ArrayList<Tree>();
		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], 5);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s2d2[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			tree.add(new Tree(x - 1, y - 1, z));
		}

		int cnt = 0;
		while (cnt < K) {
			spring();
			summer();
			fall();
			winter();
			cnt++;
		}

		System.out.println(tree.size());
	}

	public static void spring() {
		Collections.sort(tree);
		for (int i = 0; i < tree.size(); i++) {
			Tree t = tree.get(i);
			if (map[t.x][t.y] >= t.z) {
				map[t.x][t.y] -= t.z;
				t.z++;
			} else {
				t.alive = false;
			}
		}
	}

	public static void summer() { // 리스트에서 뭐 지울 때는 꼭하나 더 생성해서 인덱스 안꼬이게하자!!
		ArrayList<Tree> tmp = new ArrayList<Tree>();
		for (int i = 0; i < tree.size(); i++) {
			Tree t = tree.get(i);
			if (!t.alive) {
				map[t.x][t.y] += t.z / 2;
			} else {
				tmp.add(t);
			}
		}
		tree.clear();
		tree = tmp;
	}

	public static void fall() {
		ArrayList<Tree> tmp = new ArrayList<Tree>();
		for (int i = 0; i < tree.size(); i++) {
			Tree t = tree.get(i);
			tmp.add(t);

			if (t.z % 5 != 0)
				continue;

			for (int j = 0; j < 8; j++) {
				int nx = t.x + ax[j];
				int ny = t.y + ay[j];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				tmp.add(new Tree(nx, ny, 1));
			}

		}

		tree.clear();
		tree = tmp;
	}

	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += s2d2[i][j];
			}
		}
	}
}
