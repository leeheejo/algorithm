package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N1697_re {
	static int N, K;
	static int[] visited = new int[100001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		visited[N] = 1;
		bfs();
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);

		while (!q.isEmpty()) {
			int tmp = q.poll();
			if (tmp == K) {
				System.out.println(visited[tmp] - 1);
				System.exit(0);
			}

			for (int i = 0; i < 3; i++) {
				int next = 0;
				switch (i) {
				case 0:
					next = tmp - 1;
					break;
				case 1:
					next = tmp + 1;
					break;
				case 2:
					next = tmp * 2;
					break;
				default:
					break;
				}
				if (next < 0 || next > 100000) // 범위 조건 처리 주의! 런타임에러
					continue;

				if (visited[next] > 0)
					continue;

				visited[next] = visited[tmp] + 1;
				q.add(next);
			}
		}

	}

}
