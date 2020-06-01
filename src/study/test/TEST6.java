package study.test;

import java.util.HashMap;

public class TEST6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "example";

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		HashMap<Integer, Integer> occur = new HashMap<Integer, Integer>();
		for (int i = 0; i < s.length(); i++) {
			int cnt = map.getOrDefault(s.charAt(i), 0) + 1;
			map.put(s.charAt(i), cnt);
			occur.put(cnt - 1, occur.getOrDefault(cnt - 1, 0) - 1);
			occur.put(cnt, occur.getOrDefault(cnt, 0) + 1);
		}

		int ans = 0;
		for (int i = 0; i < occur.size(); i++) {
			if (occur.get(i) < 1)
				continue;
			ans += occur.get(i) - 1;
		}

		System.out.print(ans);
	}

}
