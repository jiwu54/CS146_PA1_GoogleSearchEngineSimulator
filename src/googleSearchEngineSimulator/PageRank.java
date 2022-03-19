package googleSearchEngineSimulator;

import java.util.Random;

/**
 * 
 * FileName: PageRank.java
 * 
 * Rank with numerical score for each url page by the following:
 * the frequency and location of keywords within the web page,
 * how long the web page has existed,
 * the number of other web pages that link to the page in question,
 * how much the webpage owner has paid to Google for advertisement purpose.
 * 
 * The score of the urls are generate by a random number from 1 - 100.
 * 
 * @author JianBin Wu
 *
 */
public class PageRank {
	
	/** a random score */
	private Random randomScore;
	
	/** the score for the frequency and location of keywords within the web page*/
	private int keywordFrequency;
	
	/** the score for how long the web page has existed */
	private int pageExisted;
	
	/** the score for the number of other web pages that link to the page in question */
	private int webLinks;
	
	/** the score for how much the webpage owner has paid to Google for advertisement purpose. */
	private int adsPriority;
	
	/** the sum of all the other four score */
	private int totalScore;
	
	
	/**
	 * Construct a RankPage object with 4 random score factors.
	 */
	public PageRank() {
		this.keywordFrequency =  numberOfFrequency();
		this.adsPriority = adsPriority();
		this.pageExisted = timeExisted();
		this.webLinks = numberWebLinks();
		rank();
	}
	
	/**
	 * Construct a RankPage object with 4 score factors (not random).
	 */
	public PageRank(int keyword , int ads, int pageE , int web ) {
		this.keywordFrequency =  keyword;
		this.adsPriority = ads;
		this.pageExisted = pageE;
		this.webLinks = web;
		rank();
	}
	
	/**
	 * a random score for the frequency and location of keywords within the web page.
	 * @return the score for the frequency and location of keywords within the web page.
	 */
	private int numberOfFrequency()
	{
		randomScore = new Random();
		keywordFrequency = randomScore.nextInt(100) + 1;
		return keywordFrequency;
	}
	
	/**
	 * a random score for how long the web page has existed.
	 * @return the score for how long the web page has existed.
	 */
	private int timeExisted()
	{
		randomScore = new Random();
		pageExisted = randomScore.nextInt(100) + 1;
		return pageExisted;
	}
	
	/**
	 * a random score for the number of other web pages that link to the page in question.
	 * @return the score for the number of other web pages that link to the page in question.
	 */
	private int numberWebLinks()
	{
		randomScore = new Random();
		webLinks = randomScore.nextInt(100) + 1;
		return webLinks;
	}
	
	/**
	 * a random score for how much the webpage owner has paid to Google for advertisement purpose.
	 * @return the score for how much the webpage owner has paid to Google for advertisement purpose.
	 */
	private int adsPriority()
	{
		randomScore = new Random();
		adsPriority = randomScore.nextInt(100) + 1;
		return adsPriority;
	}
	
	/**
	 * Get score for the frequency and location
	 * @return score for the frequency and location
	 */
	public int getFrequency()
	{
		return keywordFrequency;
	}
	
	/**
	 * Get score for how long the web page has existed
	 * @return score for how long the web page has existed
	 */
	public int getTimeExisted()
	{
		return pageExisted;
	}
	
	/**
	 * Get score for number of other web pages
	 * @return  score for number of other web pages
	 */
	public int getNumberWebLinks()
	{
		return webLinks;
	}
	
	/**
	 * Get score for AdsPriority
	 * @return score for AdsPriority
	 */
	public int getAdsPriority()
	{
		return adsPriority;
	}
	
	/**
	 * Get the total rank score
	 * @return total score
	 */
	public int getTotalScore()
	{
		return totalScore;
	}
	
	/**
	 * sum up of all four scores.
	 * @return the total score of the page.
	 */
	public int rank() {
		
		totalScore = keywordFrequency + pageExisted + webLinks + adsPriority;
		return totalScore;
	}
	
	
	/**
	 * the amount of money that Webpage owners paid to increase webpage’s exposure for advertisement purpose.
	 * the highest priority score is 100.
	 * 
	 * @param paid the amount of money being paid.
	 */
	public void higherPriority(int money)
	{
		adsPriority = adsPriority + money;
		if (adsPriority > 100) {
			adsPriority = 100;
		}
		totalScore = keywordFrequency + pageExisted + webLinks + adsPriority;
	}
	
	
	
}
