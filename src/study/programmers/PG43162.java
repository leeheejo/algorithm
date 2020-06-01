package study.programmers;

public class PG43162 {

	static int[][] map;
	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int[][] computers = new int[][] { { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 1, 1, 0 }, { 1, 1, 0, 1 } };
		map = new int[n][n];

		System.out.println(solution(n, computers));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static boolean[] visited;

	public static int solution(int n, int[][] computers) {
		visited = new boolean[n];
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			dfs(i, computers);
			answer++;
		}

		return answer;
	}

	public static void dfs(int index, int[][] computers) {
		for (int i = 0; i < computers.length; i++) {
			if (computers[index][i] == 1 && visited[i] == false) {
				visited[i] = true;
				dfs(i, computers);
			}
		}
	}

}
