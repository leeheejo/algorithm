package study.programmers;

public class PG43162_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } };

		System.out.println(solution(n, computers));
	}

	public static void dfs(int index, int[][] computers) {
		for (int i = 0; i < computers.length; i++) {
			if (index == i)
				continue;
			if (computers[index][i] == 1) {
				if (visited[i])
					continue;
				else {
					visited[i] = true;
					dfs(i, computers);
				}
			}
		}
	}

	static boolean[] visited;

	public static int solution(int n, int[][] computers) {
		visited = new boolean[n];
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			answer++;
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				if (computers[i][j] == 1) {
					if (visited[j])
						continue;
					else {
						visited[j] = true;
						dfs(j, computers);
					}
				}

			}
		}
		return answer;
	}
}
