package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class N2667 {
	static int[][] arr = new int[25][25];
	static int[][] visited = new int[25][25];

	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 1, -1 };

	static int K;
	static int count = 0;
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		// Buffered reader �ʱ�ȭ�ϴ� �� �ǳ� ��Գ�
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(bf.readLine());

		for (int i = 0; i < K; i++) {
			String s = bf.readLine();
			for (int j = 0; j < s.length(); j++) {
				// ��Ʈ�� �Է� �޾Ƽ�(���� ����) int�� �Է��� ��
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				if (arr[i][j] == 1 && visited[i][j] == 0) { // ���� �ְ� �湮���� ���� ���
					count = 0;
					ans++;
					run(new Point(i, j));
					pq.add(count);
				}
			}
		}

		System.out.println(ans);
		while (!pq.isEmpty())
			System.out.println(pq.poll());

	}

	public static void run(Point p) {

		Queue<Point> q = new LinkedList<Point>();
		q.add(p);
		while (!q.isEmpty()) {
			Point temp = q.poll();
			count++;
			visited[temp.x][temp.y] = count;
			for (int i = 0; i < 4; i++) {

				int x = temp.x + ax[i];
				int y = temp.y + ay[i];

				if (x < 0 || x >= K || y < 0 || y >= K)
					continue;

				if (arr[x][y] == 1 && visited[x][y] == 0) {
					visited[x][y] = count;
					q.add(new Point(x, y));
				}

			}
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Point() {
			super();
		}

	}

}
