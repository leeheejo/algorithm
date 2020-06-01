package study.programmers;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

//https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html
public class PG42627 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 }, { 3, 7 }, { 1, 2 } };
		System.out.print(solution(jobs));
	}

	static class Job {
		int requestTime;
		int workingTime;

		Job(int requestTime, int workingTime) {
			this.requestTime = requestTime;
			this.workingTime = workingTime;
		}
	}

	public static int solution(int[][] jobs) {
		int answer = 0;
		LinkedList<Job> waiting = new LinkedList<Job>();
		PriorityQueue<Job> pq = new PriorityQueue<Job>(new Comparator<Job>() {

			@Override
			public int compare(Job j1, Job j2) {
				return j1.workingTime - j2.workingTime;
			}
		});

		for (int i = 0; i < jobs.length; i++) {
			waiting.add(new Job(jobs[i][0], jobs[i][1]));
		}

		Collections.sort(waiting, new Comparator<Job>() {

			@Override
			public int compare(Job j1, Job j2) {
				System.out.println(j1.requestTime + " " + j2.requestTime);
				return j1.requestTime - j2.requestTime;
			}
		});

		int cnt = 0;
		int time = 0;
		while (cnt < jobs.length) {
			while (!waiting.isEmpty() && waiting.peek().requestTime <= time) {
				pq.add(waiting.poll());
			}

			if (!pq.isEmpty()) {
				Job job = pq.poll();
				time += job.workingTime;
				answer += time - job.requestTime;
				cnt++;
			} else {
				time++;
			}
		}

		return answer / cnt;
	}

}
