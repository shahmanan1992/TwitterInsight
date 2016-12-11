package twittersearch;

import toneAnalyzer.ToneAnalyzer;

public class Twitter {

	public static void main(String[] args) {
		twitterSearch ts=new twitterSearch();
		ToneAnalyzer ta=new ToneAnalyzer();
		ts.tweetSearch("trump");
		//ta.toneAnalyze("Did you know?Donald Trump is actually a tumble weed with pork chop attached. #hungry");

	}

}
