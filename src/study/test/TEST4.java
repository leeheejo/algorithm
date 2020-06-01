package study.test;

import java.util.Arrays;

public class TEST4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] S = { -2, -3 };
//		solution(S);

		System.out.println(solution(S));
	}

	public static int solution(int[] S) {
		int max_sum = 0;
		int current_sum = 0;
		boolean positive = false;
		int n = S.length;
		for (int i = 0; i < n; ++i) {
			int item = S[i];
			if (item < 0) {
				if (max_sum >= current_sum) {
					max_sum = item;
					current_sum += item;
				}
			} else {
				positive = true;
				current_sum += item;
			}
		}
		if (current_sum > max_sum) {
			max_sum = current_sum;
		}
		if (positive) {
			return max_sum;
		}
		return -1;
	}
}
