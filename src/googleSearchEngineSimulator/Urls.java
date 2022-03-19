package googleSearchEngineSimulator;

/**
 * FileName: Urls.java
 * 
 * A Urls object contain url strings and PageRank object
 * PageRank object contain four scores and total score.
 * 
 * @author JianBin Wu
 *
 */
public class Urls {
	
	/** A url string */
	private String url;
	
	/** A pageRank object that has four scores */
	private PageRank pageRank;
	

	/**
	 * Construct a Urls object with url and pageRank object
	 * @param url website url
	 * @param pageRank pageRank object
	 */
	public Urls(String url , PageRank pageRank) {
		this.url = url;
		this.pageRank = pageRank;
	}
	
	/**
	 * Get score for the frequency and location of the PageRank object
	 * @return score for the frequency and location
	 */
	public int getFrequency()
	{
		return pageRank.getFrequency();
	}
	
	/**
	 * Get score for how long the web page has existed of the PageRank object
	 * @return score for how long the web page has existed
	 */
	public int getTimeExisted()
	{
		return pageRank.getTimeExisted();
	}
	
	/**
	 * Get score for number of other web pages of the PageRank object
	 * @return  score for number of other web pages
	 */
	public int getNumberWebLinks()
	{
		return pageRank.getNumberWebLinks();
	}
	
	/**
	 * Get score for AdsPriority of the PageRank object
	 * @return score for AdsPriority
	 */
	public int getAdsPriority()
	{
		return pageRank.getAdsPriority();
	}
	
	/**
	 * Get the total score of the PageRank object
	 * @return total score
	 */
	public int getTotalScore()
	{
		return pageRank.getTotalScore();
	}
	
	/**
	 * Get PageRank object
	 * @return PageRank object
	 */
	public PageRank getPageRank() {
		return pageRank;
	}
	
	/**
	 * Get the website url
	 * @return the website url
	 */
	public String getUrls()
	{
		return url;
	}
	
}
