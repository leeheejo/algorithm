package study.programmers;

import java.util.HashMap;

public class N42576_200825 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };

		String answer = solution(participant, completion);
		System.out.println(answer);

	}

	public static String solution(String[] participant, String[] completion) {
		String answer = "";

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int participantCount = participant.length;
		for (int i = 0; i < participantCount; i++) {
			map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
		}

		int completionCount = completion.length;
		for (int i = 0; i < completionCount; i++) {
			if (map.containsKey(completion[i]))
				map.put(completion[i], map.get(completion[i]) - 1);
		}

		for (String s : map.keySet()) {
			if (map.get(s) != 0) {
				answer = s;
			}
		}
		return answer;
	}
}
