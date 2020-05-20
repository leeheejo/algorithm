package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PG42577 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };

		int[] a = solution(genres, plays);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

	}

	public static class Music implements Comparable<Music> {
		int index;
		String genres;
		int plays;
		int totalPlay;

		public Music(int index, String genres, int plays) {
			super();
			this.index = index;
			this.genres = genres;
			this.plays = plays;
			this.totalPlay = map.get(genres);
		}

		@Override
		public int compareTo(Music o) {
			// TODO Auto-generated method stub
			if (this.totalPlay == o.totalPlay) {
				if (this.plays == o.plays) {
					return this.index < o.index ? -1 : 1;
				} else
					return this.plays > o.plays ? -1 : 1;

			} else
				return this.totalPlay > o.totalPlay ? -1 : 1;
		}

	}

	static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		
		Music[] arr = new Music[genres.length];
		for (int i = 0; i < genres.length; i++) {
			String gen = genres[i];
			int play = plays[i];
			map.put(gen, map.getOrDefault(gen, 0) + play);
		}

		for (int i = 0; i < genres.length; i++) {
			arr[i] = new Music(i, genres[i], plays[i]);
		}

		Arrays.sort(arr);
		ArrayList<Integer> al = new ArrayList<Integer>();
		String gen = arr[0].genres;
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (cnt == 2 && gen.equals(arr[i].genres))
				continue;
			if (!gen.equals(arr[i].genres)) {
				cnt = 0;
				gen = arr[i].genres;
			}
			if (gen.equals(arr[i].genres)) {
				al.add(arr[i].index);
				cnt++;
			}
		}

		int size = al.size();
		answer = new int[size];

		for (int i = 0; i < size; i++) {
			answer[i] = al.get(i);
		}

		return answer;
	}

}
