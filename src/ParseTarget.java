import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Semaphore{
	public static boolean semaphoreP;
	public static boolean semaphoreF;
	public static boolean semaphoreS;
	public static boolean semaphoreL;
	public static boolean semaphoreA;
}

public class ParseTarget {	
	public static void main(String[] args) throws Exception{
		/* LEVEL-1 parser start */
		
		
		level1Thread level1node1_P = new level1Thread("politics", 'P');
		level1node1_P.start();
		level1Thread level1node2_F = new level1Thread("finance", 'F');
		level1node2_F.start();
		level1Thread level1node3_S = new level1Thread("society", 'S');
		level1node3_S.start();
		level1Thread level1node4_L = new level1Thread("local", 'L');
		level1node4_L.start();
		level1Thread level1node5_A = new level1Thread("art-edu", 'A');
		level1node5_A.start();
		
		/* LEVEL-1 parser end */
		
		/* LEVEL-2 parser start */
		
		/* node1_P parse start */
		level2Thread level2node1_P = new level2Thread('P');
		level2node1_P.start();
		/* node1_P parse end */		
		/* node2_F parse start */
		level2Thread level2node2_F = new level2Thread('F');
		level2node2_F.start();
		// node2_F parse end 
		// node3_S parse start 
		level2Thread level2node3_S = new level2Thread('S');
		level2node3_S.start();
		// node3_S parse end 
		// node4_L parse start 
		level2Thread level2node4_L = new level2Thread('L');
		level2node4_L.start();
		// node4_L parse end 
		// node5_A parse start 
		level2Thread level2node5_A = new level2Thread('A');
		level2node5_A.start();
		/* node5_A parse end */
		/* LEVEL-2 parser end */
	}
}
