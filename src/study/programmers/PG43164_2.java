package study.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PG43164_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
		String[] ans = solution(tickets);
//		for (int i = 0; i < ans.length; i++) {
//			System.out.println(ans[i]);
//		}
	}

	public static String[] solution(String[][] tickets) {
		String[] answer = {};
		Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				System.out.println(o1[1].toString() + " " + o2[1].toString());
				return o2[];
			}
		});
		return answer;
	}
}
