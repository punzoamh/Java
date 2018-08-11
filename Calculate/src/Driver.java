/**
 * CS 202
 * @author Antonio Punzo
 * 12/15/2014
 * Final Project
 * Stack Calculator
 * @version 1, 12/15/2014
 */
import java.util.Scanner;


public class Driver
{
	public static void main( String [ ] args )
	 {
		Scanner input = new Scanner(System.in);	
		String name="Antonio Punzo",section="CS 202",school="Hood College",year="Fall 2014";
		System.out.println(name+"\n"+section+"\t"+school+"\t"+year);
		System.out.println("All file names should be in the format ./src/FileName.txt");
		System.out.println("Please enter the file name for data to be read from");
		
		String fileName=input.nextLine();
		System.out.println("Please enter the file name for data to be written to");
	
		String fileName2=input.nextLine();
		Calculate newCalc=new Calculate(fileName);
		newCalc.readFile();
		newCalc.writeResults(fileName2);
		
	 }
}
