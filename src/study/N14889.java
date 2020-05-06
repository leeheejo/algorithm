package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14889 {

	static int N;
	static int[][] map = new int[21][21];
	static int ans = Integer.MAX_VALUE;
	static boolean[] visited = new boolean[21];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		run(0, 1);
		System.out.println(ans);
	}

	public static void run(int index, int c) {// 시간초과 나와서 수정한 부분
		if (index == N / 2) {
			int a = 0;
			int b = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i] == true && visited[j] == true) {
						a += map[j][i];
					}
					if (visited[i] == false && visited[j] == false) {
						b += map[j][i];
					}
				}

			}
			ans = Math.min(ans, Math.abs(a - b));
			return;
		}

		for (int i = c; i <= N; i++) { // 시간 초과 나와서 수정한 부분 인덱스로 이전까지탐색한부분 넘겨줘서 그 다음부터 탐색할 수 있게 처리함
			if (visited[i])
				continue;

			visited[i] = true;
			run(index + 1, i);
			visited[i] = false;

		}

	}
}
