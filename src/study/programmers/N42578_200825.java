package study.programmers;

import java.util.HashMap;

public class N42578_200825 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[][] clothes = { { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" },
				{ "green_turban", "headgear" } };

		System.out.println(solution(clothes));

	}

	public static int solution(String[][] clothes) {
		int answer = 1;
		int len = clothes.length;
		HashMap<String, Integer> type = new HashMap<String, Integer>();
		for (int i = 0; i < len; i++) {
			type.put(clothes[i][1], type.getOrDefault(clothes[i][1], 0) + 1);
		}
		for (String s : type.keySet()) {
			answer *= (type.get(s) + 1);
		}

		return answer - 1;
	}
}
