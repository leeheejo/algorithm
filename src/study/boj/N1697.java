package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N1697 {

	static int N, K;
	static int[] arr = new int[100001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		arr[N] = 0;
		bfs(N, 0);

		System.out.print(arr[K]);
	}

	public static class Point {
		int x;
		int count;

		public Point(int x, int count) {
			super();
			this.x = x;
			this.count = count;
		}
	}

	// 재귀를 사용해서 풀어보려고 했나
	// 최단시간을 구하는데는 적합하지 않음. now == k 일때 함수를 종료해야하는데 재귀로 하면 최단을 구할 수 없음.
	public static void bfs(int now, int count) {

		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(now, count));

		while (!q.isEmpty()) {
			Point tmp = q.poll();

			if (tmp.x == K)
				break;

			for (int i = 0; i < 3; i++) {
				int next = 0;
				if (i == 0)
					next = tmp.x - 1;
				if (i == 1)
					next = tmp.x + 1;
				if (i == 2)
					next = tmp.x * 2;

				if (next < 0 || next >= arr.length || arr[next] != 0)
					continue;

				arr[next] = tmp.count + 1;
				q.add(new Point(next, tmp.count + 1));
			}

		}

	}

}
