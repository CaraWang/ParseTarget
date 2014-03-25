import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class level2CPparser {
	//private String url = "";
	private char node_id = ' ';
	Document doc;
	public level2CPparser(char id) throws IOException{
		this.node_id = id;
	}
	public void writeToFile(String u, String fileName) throws IOException{
		String url = u;
		doc = Jsoup.connect(url).get();
		String title = this.getTitle();
		String publishedDate = this.getPublishedDate();
		String Provider = this.getProvider();
		String Description = this.getDescription();
		String content = this.parserNewsContent();
		try{
			System.out.println("Parse: " + title);
			File file = new File("News/node" + node_id +"/" + fileName + ".txt");
			if(!file.exists())
			{
			FileWriter outputToTxt = new FileWriter("News/node" + node_id +"/" + fileName + ".txt", true);
			BufferedWriter writeToFile = new BufferedWriter(outputToTxt);
			writeToFile.write("<url>" + url + "</url>"); writeToFile.newLine();
			writeToFile.write("<title>" + title + "</title>"); writeToFile.newLine();
			writeToFile.write("<publishDate>" + publishedDate + "</publishDate>"); writeToFile.newLine();
			writeToFile.write("<media>" + Provider + "</media>"); writeToFile.newLine();
			writeToFile.write("<abstract>" + Description + "</abstract>"); writeToFile.newLine();
			writeToFile.write("<content>" + content + "</content>"); writeToFile.newLine();
			writeToFile.close();
			}else{
				System.out.println("The file: " + title + " existed!");
			}
		}catch (IOException e) {
			e.printStackTrace();
			//System.out.println("The IO Error msg is:" + e.getMessage());
		}
	}
	private String parserNewsContent() throws IOException{
		Elements info = doc.select("div[id*=mediaarticlebody");
		Elements contentOrignal = info.select("p");
		String content = contentOrignal.text();
		return(content);
	}
	private String getTitle() throws IOException{
		Elements info = doc.select("meta[itemprop*=headline]");
		String title = info.get(0).attr("content");
		return(title);
	}
	private String getPublishedDate() throws IOException{
		Elements info = doc.select("meta[itemprop*=datePublished]");
		String date = info.get(0).attr("content");
		return(date);
	}
	private String getProvider() throws IOException{
		Elements info = doc.select("meta[itemprop*=provider]");
		String provider = info.get(0).attr("content");
		return(provider);
	}
	private String getDescription() throws IOException{
		Elements info = doc.select("meta[itemprop*=description]");
		String description = info.get(0).attr("content");
		return(description);
	}
}
