package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N3954_2 {
	public static class Pair {
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}

	}

	static Stack<Integer> stack;
	static int mem_size, code_size, input_size, pointer, input_index;
	static String text, program;
	static Pair[] pairs;
	static int[] mem;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			stack = new Stack<Integer>();
			pairs = new Pair[4096];
			mem = new int[100001];
			input_index = 0;
			pointer = 0;
			String[] temp = br.readLine().split(" ");
			mem_size = Integer.parseInt(temp[0]);
			code_size = Integer.parseInt(temp[1]);
			input_size = Integer.parseInt(temp[2]);

			program = br.readLine();
			text = br.readLine();

			findPair();
			play();

		}

	}

	public static void findPair() {
		for (int i = 0; i < code_size; i++) {
			if (program.charAt(i) == '[') {
				stack.push(i);
				pairs[i] = new Pair(i, 0);
			} else if (program.charAt(i) == ']') {
				int temp = stack.pop();
				pairs[temp].right = i;
				pairs[i] = new Pair(temp, i);
			}
		}
	}

	public static void play() {
		int idx = 0;
		int LoopCount = 0;
		int loopIdx = 0;
		while (true) {
			char command = program.charAt(idx);
			switch (command) {
			case '-':
				mem[pointer] = (mem[pointer] - 1) % 256;
				break;
			case '+':
				mem[pointer] = (mem[pointer] + 1) % 256;
				break;
			case '<':
				if (pointer == 0)
					pointer = mem_size - 1;
				else
					pointer--;
				break;
			case '>':
				if (pointer == mem_size - 1)
					pointer = 0;
				else
					pointer++;
				break;
			case '[':
				if (mem[pointer] == 0) {
					idx = pairs[idx--].right;
					LoopCount++;
				}
				break;
			case ']':
				if (mem[pointer] != 0) {
					idx = pairs[idx--].left;
					LoopCount++;
				}
				break;
			case '.':

				break;
			case ',':
				if (input_index == input_size) {
					mem[pointer] = 255;
				} else {
					mem[pointer] = text.charAt(input_index++);
				}
				break;

			}

			idx++;
			LoopCount++;

			if (idx == code_size) {
				System.out.println("Terminates");
				break;
			}
			if (idx > loopIdx) {
				loopIdx = idx;
			}

			if (LoopCount >= 50000000) {
				System.out.println("Loops " + pairs[loopIdx].left + " " + pairs[loopIdx].right);
				break;
			}

		}

	}

}
