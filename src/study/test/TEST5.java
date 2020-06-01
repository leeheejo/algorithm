package study.test;

import java.util.ArrayList;

public class TEST5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "Forget  CVs..Save time . x x";
		ArrayList<String> list = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (sb.length() == 0 && s.charAt(i) == ' ')
				continue;
			if (s.charAt(i) != '.' && s.charAt(i) != '!' && s.charAt(i) != '?') {
				sb.append(s.charAt(i));
			} else {
				list.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			String[] tmp = list.get(i).split(" ");
			int cnt = 0;
			for (int k = 0; k < tmp.length; k++) {
				if (tmp[k].equals(""))
					continue;
				cnt++;
			}
			ans = ans > cnt ? ans : cnt;
		}

		System.out.println(ans);

	}

}
