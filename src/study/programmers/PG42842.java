package study.programmers;

public class PG42842 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int brown = 8;
		int yellow = 1;

		int[] answer = solution(brown, yellow);
		for (int i = 0; i < 2; i++)
			System.out.print(answer[i]+" ");
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		for (int y = 1; y <= 5000; y++) {
			int x = (brown + 4) / 2 - y;
			if (x > y)
				continue;
			if ((x - 2) * (y - 2) == yellow) {
				answer[0] = y;
				answer[1] = x;
				return answer;
			}
		}
		return answer;
	}
}
