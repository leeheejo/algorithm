package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N4013 {
	public static int T, K; // T= 테스트케이스, K=회전수
	public static int[][] arr = new int[5][8]; // 자석정보

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			K = sc.nextInt();
			P[] testCase = new P[K + 1];

			for (int i = 1; i <= 4; i++) {
				for (int j = 0; j < 8; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for (int i = 1; i <= K; i++) {
				int s = sc.nextInt();
				int d = sc.nextInt();
				testCase[i] = new P(s, d);
			}

			// 바퀴돌리기
			for (int i = 1; i <= K; i++) {
				P p = testCase[i];
				run(p);
			}
			int ans = arr[1][0] + (arr[2][0] * 2) + (arr[3][0] * 4) + (arr[4][0] * 8);
			System.out.println("#" + (t + 1) + " " + ans);
		}

	}

	static class P {
		int saw; // 바퀴
		int dir; // 방향

		public P(int saw, int dir) {
			super();
			this.saw = saw;
			this.dir = dir;
		}
	}

	public static void run(P p) {
		boolean[] visited = new boolean[5];
		int[] finDir = new int[5]; // 최종적으로 돌릴 방향

		finDir[p.saw] = p.dir;
		visited[p.saw] = true;

		Queue<P> queue = new LinkedList();

		if (p.saw == 1) { // 1번
			if (arr[p.saw][2] != arr[p.saw + 1][6]) { // 다음 바퀴와 극이 다른경우
				P next = new P(p.saw + 1, p.dir * -1);
				queue.add(next);
			}
		} else if (p.saw == 4) {// 4번
			if (arr[p.saw][6] != arr[p.saw - 1][2]) { // 전 바퀴와 극이 다른경우
				queue.add(new P(p.saw - 1, p.dir * -1));
			}
		} else { // 2번 3번
			if (arr[p.saw][2] != arr[p.saw + 1][6]) { // 다음 바퀴와 극이 다른경우
				queue.add(new P(p.saw + 1, p.dir * -1));
			}
			if (arr[p.saw][6] != arr[p.saw - 1][2]) { // 전 바퀴와 극이 다른경우
				queue.add(new P(p.saw - 1, p.dir * -1));
			}
		}

		while (!queue.isEmpty()) {
			P temp = queue.poll();
			finDir[temp.saw] = temp.dir;
			visited[temp.saw] = true;

			if (temp.saw == 1) { // 1번
				if (visited[temp.saw + 1] == false && arr[temp.saw][2] != arr[temp.saw + 1][6]) { // 다음 바퀴와 극이 다른경우
					queue.add(new P(temp.saw + 1, temp.dir * -1));
				}
			} else if (temp.saw == 4) {// 4번
				if (visited[temp.saw - 1] == false && arr[temp.saw][6] != arr[temp.saw - 1][2]) { // 전 바퀴와 극이 다른경우
					queue.add(new P(temp.saw - 1, temp.dir * -1));
				}
			} else { // 2번 3번
				if (visited[temp.saw + 1] == false && arr[temp.saw][2] != arr[temp.saw + 1][6]) { // 다음 바퀴와 극이 다른경우
					queue.add(new P(temp.saw + 1, temp.dir * -1));
				}
				if (visited[temp.saw - 1] == false && arr[temp.saw][6] != arr[temp.saw - 1][2]) { // 전 바퀴와 극이 다른경우
					queue.add(new P(temp.saw - 1, temp.dir * -1));
				}
			}

		}

		for (int i = 1; i <= 4; i++) {
			move(i, finDir[i]);
		}
	}

	public static void move(int saw, int dir) {
		if (dir == 1) {
			int tmp = arr[saw][7];
			for (int i = 7; i >= 1; i--) {
				arr[saw][i] = arr[saw][i - 1];
			}
			arr[saw][0] = tmp;
		} else if (dir == -1) {
			int tmp = arr[saw][0];
			for (int i = 0; i <= 6; i++) {
				arr[saw][i] = arr[saw][i + 1];
			}
			arr[saw][7] = tmp;
		}

	}

}
