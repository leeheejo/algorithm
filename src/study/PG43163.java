package study;

public class PG43163 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

		System.out.println(solution(begin, target, words));
	}

	static boolean[] visited;
	static int count = Integer.MAX_VALUE;

	public static int solution(String begin, String target, String[] words) {
		visited = new boolean[words.length];
		for (int i = 0; i < words.length; i++) {
			int cnt = 0;
			for (int j = 0; j < words[i].length(); j++) {
				if (begin.charAt(j) != words[i].charAt(j)) {
					cnt++;
				}
				if (cnt > 1)
					break;
			}
			if (cnt == 1) {
				visited[i] = true;
				dfs(i, words, begin, target, 1);
				visited[i] = false;
			}
		}
		return count == Integer.MAX_VALUE ? 0 : count;
	}

	public static void dfs(int index, String[] words, String begin, String target, int res) {
		if (words[index].equals(target)) {
			count = Math.min(res, count);
			return;
		}
		for (int i = 0; i < words.length; i++) {
			if (visited[i])
				continue;
			int cnt = 0;
			for (int j = 0; j < words[i].length(); j++) {
				if (words[index].charAt(j) != words[i].charAt(j)) {
					cnt++;
				}
				if (cnt > 1)
					break;
			}
			if (cnt == 1) {
				visited[i] = true;
				dfs(i, words, begin, target, res + 1);
				visited[i] = false;
			}
		}

	}
}
