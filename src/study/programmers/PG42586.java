package study.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PG42586 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };

		int[] ans = solution(progresses, speeds);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	public static int[] solution(int[] progresses, int[] speeds) {

		Queue<Proccess> q = new LinkedList<Proccess>();
		for (int i = 0; i < progresses.length; i++) {
			q.add(new Proccess(progresses[i], speeds[i]));
		}

		ArrayList<Integer> ans = new ArrayList<Integer>();
		int time = 0;
		while (!q.isEmpty()) {
			time++;
			int count = 0;
			while (true) {
				if (q.peek().days <= time) {
					count++;
					q.poll();
					if (q.isEmpty())
						break;
				} else
					break;
			}
			if (count != 0)
				ans.add(count);
		}

		int[] answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}

	public static class Proccess {
		int proccess;
		int speed;
		int days;

		public Proccess(int proccess, int speed) {
			super();
			this.proccess = proccess;
			this.speed = speed;
			this.days = (100 - proccess) / speed;
			if ((100 - proccess) % speed > 0)
				this.days += 1;
		}

	}

}
