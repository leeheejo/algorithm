package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14891_200508 {

	static int[][] wheel = new int[5][8];
	static boolean[] visited;
	static int K;
	static int[] ax = { 1, -1 };
	static int[] turn;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i < 5; i++) {
			String s = bf.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = s.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(bf.readLine());

		for (int i = 0; i < K; i++) {
			visited = new boolean[5];
			turn = new int[5];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int w = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			turn[w] = d;
			visited[w] = true;
			dfs(w, d);
			for (int k = 1; k < 5; k++) {
				if (turn[k] == 1) {
					move(k);
				} else if (turn[k] == -1) {
					move(k);
					move(k);
					move(k);
					move(k);
					move(k);
					move(k);
					move(k);
				}
			}
		}

		System.out.println(wheel[1][0] * 1 + wheel[2][0] * 2 + wheel[3][0] * 4 + wheel[4][0] * 8);

	}

	public static void dfs(int w, int dir) {

		for (int i = 0; i < 2; i++) {
			if (w + ax[i] < 1 || w + ax[i] > 4)
				continue;
			if (visited[w + ax[i]])
				continue;
			if (i == 0) {
				if (wheel[w][2] == wheel[w + ax[i]][6])
					continue;
			} else if (i == 1) {
				if (wheel[w][6] == wheel[w + ax[i]][2])
					continue;
			}
			visited[w + ax[i]] = true;
			turn[w + ax[i]] = dir * -1;
			dfs(w + ax[i], dir * -1);

		}

	}

	public static void move(int w) {
		int tmp = wheel[w][7];
		for (int i = 7; i >= 1; i--) {
			wheel[w][i] = wheel[w][i - 1];
		}
		wheel[w][0] = tmp;
	}

	public static void print() {
		for (int i = 1; i < 5; i++) {
			System.out.print(turn[i] + " ");
		}
		System.out.println();
	}

	public static void print2() {
		for (int i = 1; i < 5; i++) {
			for (int j = 0; j < 8; j++)
				System.out.print(wheel[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
}
