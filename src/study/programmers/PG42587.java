package study.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class PG42587 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;
		int ans = solution(priorities, location);
		System.out.println(ans);
	}

	public static int solution(int[] priorities, int location) {
		int answer = 0;
		Queue<P> q = new LinkedList<P>();
		for (int i = 0; i < priorities.length; i++) {
			q.add(new P(priorities[i], i));
		}

		while (true) {
			P p = q.poll();

			boolean flag = false;
			for (P tp : q) {
				if (tp.prior > p.prior) {
					flag = true;
					q.add(p);
					break;
				}
			}

			if (flag)
				continue;
			else {
				answer++;
				if (p.index == location)
					break;
			}

		}
		return answer;
	}

	public static class P {
		int prior;
		int index;

		public P(int prior, int index) {
			super();
			this.prior = prior;
			this.index = index;
		}
	}
}
