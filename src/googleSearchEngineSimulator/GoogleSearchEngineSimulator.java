package googleSearchEngineSimulator;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * FileName: GoogleSearchEngineSimulator.java
 * 
 * A Google Search Engine Simulator, allow user to search keyword
 * Generated a list of top 30 url results with rank score and sort it with heap sort.
 * Allow user to modify the list, rank scores and insert websites.
 * 
 * @author JianBin Wu
 *
 */
public class GoogleSearchEngineSimulator {
	

	/** ArrayList for sorted websites*/
	private static ArrayList<Urls> sortedWebsites = new ArrayList<Urls>();
	/** ArrayList for heapWebsites */
	private static ArrayList<Urls> heapWebsites = new ArrayList<Urls>(); 
	/** a temp ArrayList for heapWebsites, use to sync scores in heap priority queue with heapWebsites */
	private static ArrayList<Urls> heapTempWebsites;
	/** a heap sorter to sort the top 30 websites */
	private static HeapSort sorter = new HeapSort();
	/** rank score array for top 30 urls */
	private static int[] scoreArray = new int[30];
	/** heap priority queue for top 20 score*/
	private static int[] heapPriority = new int[30];
	
	//main method to run the program
	public static void main (String args[]) {
		//Promote the user to enter keywords
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Google Search Engine Simulator");
		System.out.println("-----------------------------------------------");
		System.out.println("Please enter the keyword.");
		String keyword = scanner.nextLine();
		
		//search keyword with crawler
		WebCrawler crawler = new WebCrawler (keyword);
		crawler.search();
		
		//print results from crawler
		ArrayList<Urls> websites = new ArrayList<Urls>();
		int counter = 1;
		for (String w : crawler.getUrls()) {
			PageRank a = new PageRank();
			websites.add(new Urls(w,a));
			System.out.println(counter + "." + w  + " RankScore: " + a.getTotalScore());
			counter++;
			if(counter == 31 ) {
				break;
			}
		}
		System.out.println("Total: " + (counter-1));
		System.out.println("-----------------------------------------------");
		
		//Promote the user to sort the websites
		System.out.println("Please press s to sort"); 
		String sortInput = scanner.nextLine().toLowerCase();
		while(!sortInput.equals("s"))
		{
			System.out.println("press s please");
			sortInput = scanner.nextLine().toLowerCase();
		}
		if(sortInput.equals("s")){
				//store scores into an array
				for(int i = 0 ; i < 30 ; i++) {
					scoreArray[i] = websites.get(i).getTotalScore(); 
				}
				
				//sort website with heapsort
				sorter.heapSort(scoreArray); 
				//invert sorted array so the websites in largest to least order
				reverseArray(scoreArray); 
				
				//sync the score with websites
				for(int i = 0; i < 30; i++) {
					for(int j = 0 ; j < websites.size(); j++) {
						//compare scores in array and ArrayList
						if(scoreArray[i] == websites.get(j).getTotalScore()) {
							sortedWebsites.add(new Urls(websites.get(j).getUrls(),websites.get(j).getPageRank()));
							websites.remove(j);
						}
					}
				}
				//print every websites from the list and their other four scores.
				for(int i = 0; i < 30; i++) {
					System.out.println(i+1 + "." + sortedWebsites.get(i).getUrls()  + " RankScore: " + sortedWebsites.get(i).getTotalScore() 
							+  "    [Frequency Score: " + sortedWebsites.get(i).getFrequency() 
							+ ", Existence Score: " + sortedWebsites.get(i).getTimeExisted() 
							+ ", WebLinks Score: " + sortedWebsites.get(i).getNumberWebLinks()
							+ ", AdsPriority Score: " + sortedWebsites.get(i).getAdsPriority() + "]");
				}
		}
		
		// Promote user to select options
		System.out.println("-----------------------------------------------"); 
		System.out.println("Please select the following option:"); 
		System.out.println("-----------------------------------------------");
		System.out.println("1.Heap Priority Queue for first 20 out of 30 website");
		System.out.println("you have to select 1 to do the options below");
		System.out.println("        ---------------------------------------");
		System.out.println("2.Insert a new web url link"); 
		System.out.println("3.View the first ranked web url link and remove it.");
		System.out.println("4.Increase a webpages' PageRank score by adding money");
		System.out.println("5.View a website's info");
		int selection = scanner.nextInt();
		
		//guide user to select 1
		while(selection != 1 )
		{
			System.out.println("Please enter 1! ");
			selection = scanner.nextInt();
		}
		
		while(selection == 1 || selection == 2 || selection == 3
				|| selection == 4 || selection == 5) {
			
			//heap priority queue if selected 1
			if(selection == 1) {
				System.out.println("-----------------------------------------------"); 
				System.out.println("Here are the first 20 sorted urls: "); 
				heapPriorityQueue();
				repeatPromote();
			}
			
			//Insert Website if selected 2
			if(selection == 2) {
				System.out.println("-----------------------------------------------"); 
				System.out.println("Please enter a url ");
				String url1 = scanner.next().toLowerCase();
				System.out.println("Please enter the frequency score for this url (Max:100)");
				int freqScore = scanner.nextInt();
				System.out.println("Please enter the existence score for this url (Max:100)");
				int existScore = scanner.nextInt();
				System.out.println("Please enter the web page links score for this url (Max:100)");
				int webScore = scanner.nextInt();
				System.out.println("Please enter the advertising score for this url (Max:100)");
				int adverScore = scanner.nextInt();
				insertWebsite(url1,freqScore,adverScore,existScore,webScore);
				repeatPromote();
				
				
			}
			
			//view and remove the first rank website if select 3
			if(selection == 3) {
				firstRankAndRemove();
				repeatPromote();
			}
			
			//increase a websites' rank score if select 4
			if(selection == 4) {
				System.out.println("-----------------------------------------------"); 
				System.out.println("Please enter the index of the website you modify ");
				int url = scanner.nextInt();
				System.out.println("Please enter a the amount of money you want to pay ");
				int money = scanner.nextInt();
				increaseRankScore(url,money);
				repeatPromote();
				
			}
			
			//get website info if select 5
			if(selection == 5 ) {
				System.out.println("-----------------------------------------------"); 
				System.out.println("Please enter the index of the url you want get info ");
				int url = scanner.nextInt();
				System.out.println("-----------------------------------------------"); 
				HeapScoreInfo(url);
				System.out.println("-----------------------------------------------");
				repeatPromote();
				
			}
			
			selection = scanner.nextInt();
		
		}
			//quit if select any other number
			if( selection != 1 && selection != 2 && selection != 3
					&& selection != 4 && selection != 5) {
					System.out.println("Thanks for using! Bye! ");
			}
		
	}	
		

		

	
	/**
	 * Promote user to select options
	 */
	public static void repeatPromote(){
		System.out.println("-----------------------------------------------"); 
		System.out.println("Please select the following option:"); 
		System.out.println("-----------------------------------------------"); 
		System.out.println("2.Insert a new web url link"); ;
		System.out.println("3.View the first ranked web url link and remove it");
		System.out.println("4.Increase a webpages' PageRank score");
		System.out.println("5.View a website's info");
		System.out.println("**Press any other number to quit**");
	}
	
	/**
	 * Heap Priority Queue with the first 20 websites
	 */
	public static void heapPriorityQueue()
	{
		for (int i = 0; i < 20; i++) {
			//insert sorted websites in to a heap.
			heapPriority[i] = scoreArray[i];
			heapWebsites.add(i, sortedWebsites.get(i));
			System.out.println(i+1 + "." + heapWebsites.get(i).getUrls()  + " RankScore: " + heapWebsites.get(i).getTotalScore());
		}
		
	}	
		

	/**
	 * Insert a website to the list with an url and 4 scores.
	 * 
	 * @param url website link.
	 * @param keyword frequency score for this url.
	 * @param ads advertising score for this url.
	 * @param page existence score for this url.
	 * @param web web page links score for this url.
	 */
	public static void insertWebsite(String url,int keyword , int ads, int page , int web)
	{
		//Check every score
		if(keyword > 100) {
			keyword = 100;
		}
		if(ads > 100) {
			ads = 100;
		}
		if(page > 100) {
			page = 100;
		}
		if(web > 100) {
			web = 100;
		}
		PageRank a = new PageRank(keyword, ads, page, web);
		Urls b = new Urls(url,a); //creat a new websites object with rank score.
		heapWebsites.add(b);
		sorter.maxHeapInsert(heapPriority, a.getTotalScore());
		syncLinkAndScore();
		
			
	}
	
	/**
	 * 	Increase a websites' rank score by adding money.
	 * 	Maximum score for Adspriority is 100.
	 */
	public static void increaseRankScore(int index, int money){
		heapWebsites.get(index-1).getPageRank().higherPriority(money);
		int a = heapWebsites.get(index-1).getTotalScore();
		sorter.heapIncreaseKey(heapPriority, index-1, a);
		syncLinkAndScore();
		
		
	}
		
	
	/**
	 * view the first rank url and remove it from the list.
	 */
	public static void firstRankAndRemove() {
		System.out.println("-----------------------------------------------"); 
		System.out.println("Here is the first ranked website ");
		System.out.println("1. " + heapWebsites.get(0).getUrls()  + " RankScore:" + sorter.heapMaximum(heapPriority));
		System.out.println("<This website have been removed from the list>");
		System.out.println("-----------------------------------------------");
		sorter.heapExtractMax(heapPriority);
		heapWebsites.remove(0);
		syncLinkAndScore();
	}
	
	
	
	/**
	 * Reverse an array.
	 * 
	 * @param A array to be invert
	 */
	public static void reverseArray (int A[])
	{
		int j = A.length - 1;
		for (int i = 0; i <= j; i++) {
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp; //swap the elements.
			j--;
		}
	}
	
	/**
	 * sync the heapWebsites list and heapPriority
	 * Print out the updated heap websites list
	 */
	public static void syncLinkAndScore() {
		heapTempWebsites = new ArrayList<Urls>();
		for(int i = 0 ;i < 30; i++) {
			for(int j = 0; j < heapWebsites.size() ;j++ ) {
				//compare scores in array and ArrayList
				if(heapPriority[i] == heapWebsites.get(j).getTotalScore()) {
					heapTempWebsites.add(new Urls(heapWebsites.get(j).getUrls(),heapWebsites.get(j).getPageRank()));
					heapWebsites.remove(j);
				}
			}
		}
		
		heapWebsites = heapTempWebsites;
		// print current list
		System.out.println("Current List:"); 
		for(int i = 0 ;i < heapWebsites.size(); i++) {
		System.out.println(i+1 + "." + heapWebsites.get(i).getUrls()  + " RankScore: " + heapWebsites.get(i).getTotalScore());
		}
	}
	
	
	/**
	 * Get other 4 scores info of a heap website
	 * @param i the website being get info
	 */
	public static void HeapScoreInfo(int i) {
		i--;
		System.out.println("Website: " + heapWebsites.get(i).getUrls() + " Total Score: " + heapWebsites.get(i).getTotalScore());
		System.out.println("Frequency Score: "+ heapWebsites.get(i).getFrequency());
		System.out.println("Existence Score: " + heapWebsites.get(i).getTimeExisted());
		System.out.println("WebLinks Score: " + heapWebsites.get(i).getNumberWebLinks());
		System.out.println("AdsPriority Score: " + heapWebsites.get(i).getAdsPriority());
	}
	
}//end




