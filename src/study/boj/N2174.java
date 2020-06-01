package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class N2174 {
	static int A, B;
	static int N, M;
	static int[][] map = new int[101][101];
	static int[] ax = { 0, 1, 0, -1 };
	static int[] ay = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Robot[] robots = new Robot[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = B - Integer.parseInt(st.nextToken()) + 1;
			String direction = st.nextToken();
			robots[i] = new Robot(x, y, direction);
		}

		Queue<Command> commands = new LinkedList<Command>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int robotNum = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int repeat = Integer.parseInt(st.nextToken());
			Command c = new Command(robotNum, command, repeat);
			commands.add(c);
		}

		while (!commands.isEmpty()) {
			Command c = commands.poll();
			for (int i = 1; i <= c.repeat; i++) {
				if (c.command.equals("L")) {
					if (robots[c.robot].direction.equals("N")) {
						robots[c.robot].direction = "W";
					} else if (robots[c.robot].direction.equals("E")) {
						robots[c.robot].direction = "N";
					} else if (robots[c.robot].direction.equals("S")) {
						robots[c.robot].direction = "E";
					} else if (robots[c.robot].direction.equals("W")) {
						robots[c.robot].direction = "S";
					}
				} else if (c.command.equals("R")) {
					if (robots[c.robot].direction.equals("N")) {
						robots[c.robot].direction = "E";
					} else if (robots[c.robot].direction.equals("E")) {
						robots[c.robot].direction = "S";
					} else if (robots[c.robot].direction.equals("S")) {
						robots[c.robot].direction = "W";
					} else if (robots[c.robot].direction.equals("W")) {
						robots[c.robot].direction = "N";
					}

				} else if (c.command.equals("F")) {
					int nextX = 0;
					int nextY = 0;
					int index = -1;
					if (robots[c.robot].direction.equals("N")) {
						index = 0;
					} else if (robots[c.robot].direction.equals("E")) {
						index = 1;
					} else if (robots[c.robot].direction.equals("S")) {
						index = 2;
					} else if (robots[c.robot].direction.equals("W")) {
						index = 3;
					}
					nextX = robots[c.robot].x + ax[index];
					nextY = robots[c.robot].y + ay[index];

					if (nextX <= 0 || nextY <= 0 || nextX > A || nextY > B) {
						System.out.println("Robot " + c.robot + " crashes into the wall");
						System.exit(0);
					}

					for (int j = 1; j <= N; j++) {
						if (j == c.robot)
							continue;
						if (nextX == robots[j].x && nextY == robots[j].y) {
							System.out.println("Robot " + c.robot + " crashes into robot " + j);
							System.exit(0);

						}
					}
					robots[c.robot].x = robots[c.robot].x + ax[index];
					robots[c.robot].y = robots[c.robot].y + ay[index];
				}
			}
		}

		System.out.println("OK");
	}

	static class Robot {
		int x;
		int y;
		String direction;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}

		public Robot(int x, int y, String direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

	}

	static class Command {
		int robot;
		String command;
		int repeat;

		public int getRobot() {
			return robot;
		}

		public void setRobot(int robot) {
			this.robot = robot;
		}

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		public int getRepeat() {
			return repeat;
		}

		public void setRepeat(int repeat) {
			this.repeat = repeat;
		}

		public Command(int robot, String command, int repeat) {
			super();
			this.robot = robot;
			this.command = command;
			this.repeat = repeat;
		}

	}
}
