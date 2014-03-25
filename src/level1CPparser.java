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


class level1CPparser {
	private String url = "";
	private char node_id = ' ';
	private int count = 0;
	public level1CPparser(String s, char id, int num){
		this.url = s;
		this.node_id = id;
		this.count = num;
	}
	public void parseWebLink() throws IOException{
		Document doc = Jsoup.connect(url).get();
		Elements info = doc.select("div[id*=MediaStoryList");
		Elements links = info.select("a[href]");
		boolean writeOrNot = true;
		try{
			/* define timeToken to compare either the target urls in the file */ 
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
			Date rightNow = new Date();
			String timeToken = sdf.format(rightNow);
			/* read the target#.txt where # is node id file to decide write or not */ 
			File file = new File("News/target" + node_id +".txt");
			if(file.exists()){
				FileInputStream fstream = new FileInputStream("News/target" + node_id +".txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;	
				int isSame = 1;
				while ((strLine = br.readLine()) != null)   {
					isSame = strLine.compareTo(timeToken + "count = " + count);
					if(isSame == 0){
						writeOrNot = false;
						System.out.println("The set of urls have already written in the file:target" + node_id + ".txt");
					}
				}
				//Close the input stream
				in.close();				
			}
			/* end the reading file */
			/* decide write or not */
			if(writeOrNot){
				/* write to file named target#.txt where # is node id */
				FileWriter outputToTxt = new FileWriter("News/target" + node_id +".txt", true);
				BufferedWriter writeToFile = new BufferedWriter(outputToTxt);
				writeToFile.write(timeToken + "count = " + count); 
				writeToFile.newLine();
				int urlIsSame = 1;
				for(int i = 0; i < links.size(); i++){
					String levelTwoUrl = links.get(i).attr("href");
					//String compareIsSame = links.get(i+1).attr("href");
					//System.out.println(line + "\n");					 
					if(i != 0){
						urlIsSame = levelTwoUrl.compareTo(links.get(i-1).attr("href"));
					}
					if(urlIsSame != 0){
						writeToFile.write(levelTwoUrl); 
						writeToFile.newLine();
					}					
				}
				writeToFile.close();
				System.out.println("The file : target" + node_id + ".txt is written!" );
			}			
		}catch (IOException e) {
			System.out.println("The IO Error msg is:" + e.getMessage());
		}
	}
}