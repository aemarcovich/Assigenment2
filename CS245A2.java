/**
*Alejandro Marcovich
*/
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CS245A2 {
	// use hash map to set names
	// private final String file_name="tmdb_5000_credits.csv​";
	private graph1 head;
	public int vertices;
	public int[][] array;
	//private String input;

	public CS245A2() {
		head = new graph1();
	}
	// hashset
	// doc contains

	ArrayList<String> d = new ArrayList<String>();

/*	public void set_input(String line) {
		input = line;
	}*/
	/**
	 * changes for cases
	 * */
	public String caseCheck(String word) 
	{
		String name="";
		int temp;
		int temp2;
		int temp3;
		char ch;
		char ch2;
		char ch3;
		String first;
		String last;
		String mid;
		String [] hold=word.split(" ",3);
		if(hold.length==2) 
		{
			first=hold[0];
			last=hold[1];
			if (Character.isLowerCase(first.charAt(0))) 
			{
				temp=(int) first.charAt(0);
				temp=temp-32;
				ch=(char) temp;
				first=ch+first.substring(0+1);
			}
			if(Character.isLowerCase(last.charAt(0))) 
			{
				temp2=(int) last.charAt(0);
				temp2=temp2-32;
				ch2=(char) temp2;
				last=ch2+last.substring(0+1);
			}
			name= first+" "+last;
		}
		if(hold.length==3) 
		{
			first=hold[0];
			mid=hold[1];
			last=hold[2];
			if (Character.isLowerCase(first.charAt(0))) 
			{
				temp=(int) first.charAt(0);
				temp=temp-32;
				ch=(char) temp;
				first=ch+first.substring(0+1);
			}
			if(Character.isLowerCase(mid.charAt(0))) 
			{
				temp2=(int) mid.charAt(0);
				temp2=temp2-32;
				ch2=(char) temp2;
				mid=ch2+mid.substring(0+1);
			}
			if(Character.isLowerCase(last.charAt(0))) 
			{
				temp3=(int) last.charAt(0);
				temp3=temp3-32;
				ch3=(char) temp3;
				last=ch3+last.substring(0+1);
			}
			name= first+" "+mid+" "+last;
		}
		return name;
	}

	/**
	 * parses the cast list from the line.
	 */
	public String seperate(String line) {
		String gp = "";
		String pat = "(\\[.+?\\])";
		Pattern pt = Pattern.compile(pat);
		Matcher mat = pt.matcher(line);
		if (mat.find()) {
			gp = mat.group(0);
			gp = gp.replaceAll("\"\"", "\"");
		}
		/*
		 * String value; String[] cac= line.split(",",3); value=cac[2]; String[]
		 * cast=value.split("]",2); value=cast[0]+"]"; String[] ob=value.split("\"",2);
		 * value=ob[1];
		 */
		// JSONArray stringline = (JSONArray) parser.parse(line);
		/*
		 * CSVParser csvParser = new CSVParser(line, CSVFormat.DEFAULT); String new_line
		 * = csvRecord.get(2); return new_line;
		 */
		return gp;
	}

	/**
	 * Assemble hash map to act as graph.
	 */
	public void assemble(String line) {
		try {
			// use j-son to parse for cast list.
			JSONParser parser = new JSONParser();
			JSONArray acArray = (JSONArray) parser.parse(line);
			//turn each actor in the cast into j-son object.
			// find actor name
			for (int x = 0; x < acArray.size(); x++) {
				JSONObject ac = (JSONObject) acArray.get(x);
				String actor = (String) ac.get("name");
				actor=caseCheck(actor);
				for (int y = 0; y < acArray.size(); y++) {
					JSONObject ac2 = (JSONObject) acArray.get(y);
					String actor2 = (String) ac2.get("name");
					actor2=caseCheck(actor2);
					head.add(actor, actor2);
				}
			}
		}
		catch (Exception e)
		{
			//continue;
			//System.out.println("error with J-son");
		}
	}

/**
 * Function reads the file
 **/
	public void readFile()
	{
		//System.out.println("hi");
		// ArrayList<String> d=new ArrayList<String>();
		try
		{
			File movies = new File("tmdb_5000_credits.csv");
			Scanner scan = new Scanner(movies);
			String line = scan.nextLine();
			while(scan.hasNext())
			{
				line = scan.nextLine();
				int linesz = line.length();
				if (linesz == 0)
					continue;
				line = seperate(line);
				assemble(line);
				//System.out.println(line);
			}
			scan.close();
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("can't find file");
		}
	}
	/**
	 *Input asks user for names.
	 *loops until given valid name. 
	 */
	public void userInput() 
	{
		String name="";
		String name2="";
		Scanner act1=new Scanner(System.in);
		boolean check=false;
		while(check==false)
		{
			System.out.println("Actor 1:");
			name=act1.nextLine();
			name=caseCheck(name);
			if(head.checkIn(name)==true)
			{
				check=true;
			}
			else 
			{
				System.out.println("no such actor exists");
			}
		}
		check=false;
		while(check==false)
		{
			System.out.println("Actor 2:");
			name2=act1.nextLine();
			name2=caseCheck(name2);
			if(head.checkIn(name2)==true)
			{
				check=true;
			}
			else
			{
				System.out.println("no such actor exists");
				}
		}
		//Do a search between actors.
		ArrayList<String>new_path=head.bfs(name,name2);
		if(new_path == null)
		{
			System.out.println("No path between actors, go to bed!");
		} 
		else 
		{
			System.out.print("Path between "+name+" and "+name2+": "); 
			for(int x = 0; x <new_path.size(); x++)
			{ 
				//If not the last, we print it with the arrow
				if(x!=(new_path.size()-1))
				{
					System.out.print(new_path.get(x));
					System.out.println(" --> ");
				}
				else
				{
					System.out.print(new_path.get(x));
				}
			}
		}
		act1.close();
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		CS245A2 drive = new CS245A2();
		/*if (args.length > 0) {
			drive.set_input(args[0]);
			// drive.set_output(args[1]);
		}*/
		drive.readFile();
		drive.userInput();
		 /*try 
		 {
			 drive.userInput();
			 }
		 catch(Exception e)
		 {
			 System.out.println("can't find path");
			 }*/
	}
}
//pthread.exit(0) last line of your main