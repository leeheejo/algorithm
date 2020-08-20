package study.programmers;

public class PG43165_2 {

	public static int answer = 0;
	public static int[] sel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		sel = new int[numbers.length];
		System.out.println(solution(numbers, target));
	}

	public static int solution(int[] numbers, int target) {
		dfs(0, numbers, target);
		return answer;
	}

	public static void dfs(int index, int[] numbers, int target) {
		if (index == numbers.length) {
			if (calc(numbers) == target)
				answer++;
			return;
		}

		for (int i = 0; i < 2; i++) {
			sel[index] = i;
			dfs(index + 1, numbers, target);
		}
	}

	public static int calc(int[] numbers) {
		int ans = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (sel[i] == 0) {
				ans += numbers[i];
			} else if (sel[i] == 1) {
				ans -= numbers[i];
			}
		}
		return ans;
	}
	

}
