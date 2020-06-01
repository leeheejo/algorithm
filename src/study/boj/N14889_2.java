package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14889_2 {

	static int N;
	static int[][] map;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		bfs(0, 0);
		System.out.println(answer);

	}
 
	public static void bfs(int index, int c) { //�ð��ʰ� ... int c �̳� �� �����غ���
		if (index == N / 2) {
			int a = 0;
			int b = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i] == true && visited[j] == true) {
						a += map[j][i];
					} else if (visited[i] == false && visited[j] == false) {
						b += map[j][i];
					}
				}
			}
			answer = Math.min(Math.abs(a - b), answer);
			return;
		}

		for (int i = c; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			bfs(index + 1, i);
			visited[i] = false;
		}
	}
}
