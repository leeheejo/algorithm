package study.programmers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class N42579_200825 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		solution(genres, plays);
	}

	public static class Song implements Comparable<Song> {
		int index;
		int genreCount;
		String genre;
		int plays;

		public Song(int index, int genreCount, String genre, int plays) {
			super();
			this.index = index;
			this.genreCount = genreCount;
			this.genre = genre;
			this.plays = plays;
		}

		@Override
		public int compareTo(Song o) {
			// TODO Auto-generated method stub
			if (this.genreCount == o.genreCount) {
				if (this.plays == o.plays) {
					return this.index < o.index ? -1 : 1;
				} else
					return this.plays < o.plays ? 1 : -1;

			} else
				return this.genreCount < o.genreCount ? 1 : -1;
		}

		@Override
		public String toString() {
			return "Song [index=" + index + ", genreCount=" + genreCount + ", genre=" + genre + ", plays=" + plays
					+ "]";
		}

	}

	public static int[] solution(String[] genres, int[] plays) {

		int len = genres.length;

		HashMap<String, Integer> genreCount = new HashMap<String, Integer>();
		for (int i = 0; i < len; i++) {
			genreCount.put(genres[i], genreCount.getOrDefault(genres[i], 0) + plays[i]);
		}

		ArrayList<Song> songs = new ArrayList<Song>();
		for (int i = 0; i < len; i++) {
			songs.add(new Song(i, genreCount.get(genres[i]), genres[i], plays[i]));
		}

		Collections.sort(songs);

		ArrayList<Integer> ans = new ArrayList<Integer>();
		String flagString = songs.get(0).genre;
		int flagInteger = 0;
		for (int i = 0; i < len; i++) {

			if (flagString.equals(songs.get(i).genre)) {
				if (flagInteger == 2)
					continue;
			} else {
				flagInteger = 0;
				flagString = songs.get(i).genre;
			}
			flagInteger++;

			ans.add(songs.get(i).index);
		}

		int[] answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++)
			answer[i] = ans.get(i);

		return answer;
	}
}
