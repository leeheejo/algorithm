package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class N2667_DFS {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int N;
	static int[][] arr = new int[25][25];
	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int k = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) {
					count = 0;
					DFS(i, j);
					k++;
					pq.add(count);
				}
			}
		}
		System.out.println(k);
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}

	}

	public static void DFS(int x, int y) {
		arr[x][y] = 0; // visited 검사를 arr로 한다
		count++;

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
				continue;

			if (arr[nextX][nextY] == 0)
				continue;

			DFS(nextX, nextY);

		}

	}

}