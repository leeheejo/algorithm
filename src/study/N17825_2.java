package study;

import java.util.Arrays;
import java.util.Scanner;

public class N17825_2 {
	static Node start;
	static int[] dice = new int[10];
	static int[] selections = new int[10];
	static Node[] markers = new Node[4];
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++)
			dice[i] = sc.nextInt();

		init();
		permutation(0);
		System.out.println(ans);
	}

	public static class Node {
		int value;
		boolean isEmpty, isEnd;
		Node shortcut, next;

		public Node(int value) {
			super();
			this.value = value;
			this.isEmpty = false;
			this.isEnd = false;
			this.shortcut = null;
			this.next = null;
		}

		public Node addNext(int value) {
			Node temp = new Node(value);
			this.next = temp;
			return temp;
		}

		public static Node getTarget(int value) {
			Node cur = start;
			while (true) {
				if (cur == null)
					return null;
				if (cur.value == value) {
					return cur;
				}
				cur = cur.next;
			}
		}

	}

	public static void init() {
		start = new Node(0);
		Node end = null;
		Node center = new Node(25);

		Node temp = start;
		for (int i = 2; i <= 40; i += 2) {
			temp = temp.addNext(i);
		}

		end = temp.addNext(0);
		end.next = end;
		end.isEnd = true;

		// 13 16 18 25
		temp = Node.getTarget(10);
		temp = temp.shortcut = new Node(13); //왜 여기다가 add next하면 안될까..? 기존next 가 사라지니까! 
		temp = temp.addNext(16);
		temp = temp.addNext(19);
		temp.next = center;

		// 20 22 24 25
		temp = Node.getTarget(20);
		temp = temp.shortcut = new Node(22);
		temp = temp.addNext(24);
		temp.next = center;

		// 30 28 27 26 25
		temp = Node.getTarget(30);
		temp = temp.shortcut = new Node(28);
		temp = temp.addNext(27);
		temp = temp.addNext(26);
		temp.next = center;

		// 25 30 35 40
		temp = center.addNext(30);
		temp = temp.addNext(35);
		temp.next = Node.getTarget(40);
	}

	public static void permutation(int index) {
		if (index == 10) {
			Arrays.fill(markers, start);
			int a = play();
			ans = Math.max(a, ans);
			recover();
			return;
		}

		for (int i = 0; i < 4; i++) {
			selections[index] = i;
			permutation(index + 1);
		}

	}

	public static int play() {
		int a = 0;
		
		for (int i = 0; i < 10; i++) {
			int d = dice[i];
			Node cur = markers[selections[i]];
			cur.isEmpty = true;

			for (int j = 0; j < d; j++) {
				if (j == 0 && cur.shortcut != null) {
					cur = cur.shortcut;
				} else {
					cur = cur.next;
				}
			}

			if (!cur.isEmpty && !cur.isEnd) // 도착칸에 말이 있는 경우
				return 0;

			markers[selections[i]] = cur;
			a += cur.value;
			cur.isEmpty = false;

		}

		return a;
	}

	public static void recover() {
		for (int i = 0; i < 4; i++) {
			markers[i].isEmpty = true;
		}
	}
}
