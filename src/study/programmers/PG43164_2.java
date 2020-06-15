package study.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PG43164_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = { { "ICN", "BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, { "DOO", "COO" }, { "BOO", "DOO" },
				{ "DOO", "BOO" }, { "BOO", "ICN" }, { "COO", "BOO" } };
		String[] ans = solution(tickets);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	static boolean[] visited;
	static String route = "";
	public static List<String> list = new ArrayList<String>();

	public static String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length];
		Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				return o1[1].compareTo(o2[1]);
			}
		});

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
		return answer;
	}

	public static void dfs(int index, int count, String[][] tickets) {
		route += tickets[index][1] + ",";
		if (count == visited.length) {
			list.add(route);
			return;
		}
		String nxt = tickets[index][1];
		for (int i = 0; i < tickets.length; i++) {
			if (visited[i])
				continue;
			if (tickets[i][0].equals(nxt)) {
				visited[i] = true;
				dfs(i, count + 1, tickets);
				route = route.substring(0, route.length() - 4);
				visited[i] = false;
			}

		}

	}
}
