package study.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PG43164 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = { { "ICN", "BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, { "DOO", "COO" }, { "BOO", "DOO" },
				{ "DOO", "BOO" }, { "BOO", "ICN" }, { "COO", "BOO" } };
//		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };

//		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
//				{ "ATL", "SFO" } };

		solution(tickets);

	}

	public static boolean[] visited;
	public static List<String> list = new ArrayList<String>();

	public static String route = "";

	public static String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length];
//		Arrays.sort(tickets, new Comparator<String[]>() {
//			@Override
//			public int compare(String[] o1, String[] o2) {
//				return o1[1].compareTo(o2[1]);
//			}
//
//		});

		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i][0].equals("ICN")) {
				route = "ICN,";
				visited[i] = true;
				dfs(i, 1, tickets);
				visited[i] = false;
			}
		}

		Collections.sort(list);
		String[] answer = list.get(0).split(",");
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		return answer;
	}

	public static void dfs(int index, int cnt, String[][] tickets) {
		route += tickets[index][1] + ",";
		if (cnt == tickets.length) {
			list.add(route);
			return;
		}

		for (int i = 0; i < tickets.length; i++) {
			if (visited[i])
				continue;
			if (tickets[index][1].equals(tickets[i][0])) {
				visited[i] = true;
				dfs(i, cnt + 1, tickets);
				route = route.substring(0, route.length() - 4);
				visited[i] = false;
			}
		}

	}

}