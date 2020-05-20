package study;

public class PG42841 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] baseball = { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } };
//		solution(baseball);
		dfs(0, baseball);
		System.out.println(cnt);
//		check(324, baseball);

	}

	static int[] ans = new int[3];
	static int cnt = 0;

	public static void dfs(int index, int[][] baseball) {
		if (index == 3) {
			int in = Integer.parseInt(ans[0] + "" + ans[1] + "" + ans[2]);

			if (check(in, baseball))
				cnt++;
//			for (int i = 0; i < 3; i++)
//				System.out.print(ans[i]);
//			System.out.println();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			boolean flag = true;
			for (int j = index; j >= 0; j--) {
				if (ans[j] == i) {
					flag = false;
				}
			}
			if (!flag)
				continue;
			ans[index] = i;
			dfs(index + 1, baseball);
		}
	}

	public static boolean check(int num, int[][] baseball) {

		for (int i = 0; i < baseball.length; i++) {
			if (checkStrike(num, baseball[i][0]) != baseball[i][1])
				return false;
			if (checkBall(num, baseball[i][0]) != baseball[i][2]) {
				return false;
			}
//			System.out.println(">>" + checkStrike(num, baseball[i][0]) + " " + checkBall(num, baseball[i][0]));
		}

		return true;
	}

	public static int checkStrike(int num, int input) {
		int count = 0;
		String sNum = num + "";
		String sInput = input + "";
		for (int i = 0; i < 3; i++) {
			if (sNum.charAt(i) == sInput.charAt(i))
				count++;

		}

		return count;
	}

	public static int checkBall(int num, int input) {
		int count = 0;
		String sNum = num + "";
		String sInput = input + "";
		for (int i = 0; i < 3; i++) {
			if (sNum.contains(sInput.subSequence(i, i + 1)) && sNum.charAt(i) != sInput.charAt(i))
				count++;
		}

		return count;
	}

	public static int solution(int[][] baseball) {
		dfs(0, baseball);
		return cnt;
	}
}
