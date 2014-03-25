import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class level2Thread extends Thread{
	char node_id = ' ';
	public level2Thread(char id){
		this.node_id = id;
	}
	public void run(){
		switch(node_id){
		case 'P':
			while(!Semaphore.semaphoreP)
				try{
					System.out.println(node_id + " sleeping!");
					sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		case 'F':
			while(!Semaphore.semaphoreF)
				try{
					System.out.println(node_id + " sleeping!");
					sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		case 'S':
			while(!Semaphore.semaphoreS)
				try{
					System.out.println(node_id + " sleeping!");
					sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		case 'L':
			while(!Semaphore.semaphoreL)
				try{
					System.out.println(node_id + " sleeping!");
					sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		case 'A':
			while(!Semaphore.semaphoreA)
				try{
					System.out.println(node_id + " sleeping!");
					sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		}
		System.out.println(node_id + " weak up!");
		
		
		/* The level-1 node is not writing now. */
		System.out.println("////////////////////////////////");
		System.out.println("node " + node_id + " parse START!");
		System.out.println("////////////////////////////////");
		try{
			level2CPparser node = new level2CPparser(node_id);
			FileInputStream fstream = new FileInputStream("News/target" + node_id + ".txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String urlTail;
			String url;
			while ((urlTail = br.readLine()) != null)   {
				if(!urlTail.startsWith("1")){
					int lastDot = urlTail.lastIndexOf("-");
					String urlTailEn = URLEncoder.encode(urlTail.substring(1), "UTF-8");
					url = "http://tw.news.yahoo.com" + "/" + urlTailEn;
					System.out.println("To parse: " + url);
					node.writeToFile(url, urlTail.substring(1, lastDot));
				}
			}
		}catch (IOException e) {
			System.out.println("The IO Error msg in level2Thread is:");
			e.printStackTrace();
		}
					
		System.out.println("////////////////////////////////");
		System.out.println("node " + node_id + " parse END!");
		System.out.println("////////////////////////////////");
		
		switch(node_id){
		case 'P':
			Semaphore.semaphoreP = false;
		case 'F':
			Semaphore.semaphoreF = false;
		case 'S':
			Semaphore.semaphoreS = false;
		case 'L':
			Semaphore.semaphoreL =  false;
		case 'A':
			Semaphore.semaphoreA =  false;
		}
	}/* run() end */
}
