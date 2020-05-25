package study;

import java.util.LinkedList;
import java.util.Queue;

public class PG42583 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] truck_weights = { 7, 4, 5, 6 };
		int bridge_length = 2;
		int weight = 10;

		System.out.println(solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;
		Queue<Truck> bridge = new LinkedList<Truck>();
		int lastEnterTruckIndex = 0;
		int currentBridgeWeight = 0;

		while (lastEnterTruckIndex < truck_weights.length) {
			time++;
			if (!bridge.isEmpty()) {
				Truck frontTruck = bridge.peek();
				if (time - frontTruck.enterTime == bridge_length) {
					currentBridgeWeight -= bridge.poll().weight;
				}

			}

			int compareTruck = truck_weights[lastEnterTruckIndex];
			if (currentBridgeWeight + compareTruck <= weight) {
				bridge.add(new Truck(compareTruck, time));
				lastEnterTruckIndex++;
				currentBridgeWeight += compareTruck;
			}

		}

		time += bridge_length;
		return time;
	}

	public static class Truck {
		int weight;
		int enterTime;

		public Truck(int weight, int enterTime) {
			super();
			this.weight = weight;
			this.enterTime = enterTime;
		}
	}

}
