package study.programmers;

public class PG43163_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log" };
		System.out.println(solution(begin, target, words));

	}

	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static int solution(String begin, String target, String[] words) {
		visited = new boolean[words.length];
		dfs(begin, target, words, 0);
		return answer == Integer.MAX_VALUE ? 0 : answer;
	}

	public static void dfs(String now, String target, String[] words, int cnt) {
		if (now.equals(target)) {
			answer = Math.min(cnt, answer);
		}

		for (int i = 0; i < words.length; i++) {
			if (visited[i])
				continue;
			if (checkWord(now, words[i]) == 1) {
				visited[i] = true;
				dfs(words[i], target, words, cnt + 1);
				visited[i] = false;
			}
		}
	}

	public static int checkWord(String begin, String target) {
		int ans = 0;
		for (int i = 0; i < begin.length(); i++) {
			if (begin.charAt(i) != target.charAt(i)) {
				ans++;
			}
		}
		return ans;
	}
}
