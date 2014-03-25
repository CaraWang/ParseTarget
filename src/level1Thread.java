import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class level1Thread  extends Thread{
	String targetCatalogue = "";
	char node_id = ' ';
	public level1Thread(String tc, char id){
		this.targetCatalogue = tc;
		this.node_id = id;
	}
	public void run() {
		String url = "http://tw.news.yahoo.com/" + targetCatalogue + "/archive/";
		System.out.println("Level 1 parser" + node_id + ": " + targetCatalogue + " start!");
		try{
			for(int i = 1; i <= 40; i ++){
				if(i == 1){
					level1CPparser parser = new level1CPparser(url, node_id, i);
					parser.parseWebLink();
				}
				System.out.println("Level 1 parser" + node_id + " : " + targetCatalogue + " count:" + i);
				level1CPparser parser = new level1CPparser(url + i + ".html", node_id, i);
				parser.parseWebLink();
				
			}
			System.out.println("Level 1 parser" + node_id + ": " + targetCatalogue + " end!");
		}catch (IOException e) {
			System.out.println("The IO Error msg is:" + e.getMessage());
		}
		switch(node_id){
		case 'P':
			System.out.println("!!!!!Weak up " + node_id + "!!!!!");
			Semaphore.semaphoreP = true;
		case 'F':
			System.out.println("!!!!!Weak up " + node_id + "!!!!!");
			Semaphore.semaphoreF = true;
		case 'S':
			System.out.println("!!!!!Weak up " + node_id + "!!!!!");
			Semaphore.semaphoreS = true;
		case 'L':
			System.out.println("!!!!!Weak up " + node_id + "!!!!!");
			Semaphore.semaphoreL = true;
		case 'A':
			System.out.println("!!!!!Weak up " + node_id + "!!!!!");
			Semaphore.semaphoreA = true;
		}
	}
}
