package study;

public class PG43165 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		dfs(0, numbers.length, 0, target, numbers);

		System.out.println(count);
	}

	static int count = 0;

	public static void dfs(int index, int size, int ans, int target, int[] numbers) {
		if (index == size) {

			if (ans == target) {
				count++;
			}

			return;
		}

		for (int i = 0; i < 2; i++) {
			if (i == 0)
				dfs(index + 1, size, ans - numbers[index], target, numbers);
			else
				dfs(index + 1, size, ans + numbers[index], target, numbers);
		}

	}

	public static int solution(int[] numbers, int target) {
		dfs(0, numbers.length, 0, target, numbers);
		return count;
	}
}
