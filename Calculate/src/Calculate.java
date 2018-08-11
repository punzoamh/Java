/**
 * CS 202
 * @author Antonio Punzo
 * 12/15/2014
 * Final Project
 * Stack Calculator
 * @version 1, 12/15/2014
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;



/**
 * myStack creates a generic stack using a vector
 * @author Tony
 *
 * @param <E> Any element that will be handled by generic stack myStack
 */
class myStack<E>
{
	private Vector<E> stack;
	private int top = -1; // index of the top item of the stack

	/**
	 * This method creates a new vector called stack to be used for the generic stack
	 */
	public myStack() 
	{
		stack = new Vector<E>();
	}
	/**
	 * This method is responsible for pushing an element on to the top of the stack
	 * The index counter is incremented each time an item is pushed onto the top of the stack
	 * @param x object to be added to the stack
	 */
	public void push(E x) 
	{

		stack.add(x);
		top++;
		
	}
	/**
	 * This method is responsible for popping an element off the top of the stack
	 * @return null if stack is empty otherwise it returns the element that is at the top of the stack
	 */
	public E pop() {
		if (top == -1) {
			return null; // stack is empty
		}
		E temp = stack.get(top);
		stack.remove(top);
		top--;
		return temp;
	}

	/**
	 * This method test whether the stack is empty
	 * @return true if top is -1
	 */
	public boolean isEmpty(){
		return top == -1;
	}
	/**
	 * This method gives the size of the stack
	 * @return index +1 to account for vector starting at 0 for 1st element
	 */
	public int size() 
	{
		return top+1;
	}
	/**
	 * This method shows what element is currently on the top of the stack
	 * @return element currently at top of stack
	 */
	public E peek() 
	{
		E temp=stack.get(top);
		return temp;
	}
	
	



}
/**
 * This application will accept a file name to read input of equations
 * That input is tokenized and the equations are processed through the use of stacks
 * The answers for each equation are then printed to the desired file
 * @author Tony
 *
 */
public class Calculate
{
	/**
	 * inFileName- file name where data will be read from
	 */
	private String inFileName;
	/**
	 * Stack to handle number input that is read
	 */
	protected static myStack<Float> value=new myStack<>();
	/**
	 * Stack to handle any character/operator that is read
	 */
	protected myStack<Character> operator=new myStack<>();
	/**
	 * Vector to hold Answers for equations
	 */
	private static Vector Answer=new Vector();
	/**
	 * Scanner responsible for reading in the input from the file
	 */
	public Scanner infile = null;

	
	//constructors
	/**
	 * Default Constructor
	 */
	Calculate()
	{

	}
	/**
	 * Constructor that accept a file name for data input
	 * @param inFileName file name to be read from
	 */
	Calculate(String inFileName)
	{ this.inFileName=inFileName; }
	/**
	 * 
	 * @param inFileName file name to be read from
	 */
	public void setInFileName (String inFileName){inFileName=this.inFileName;}
	/**
	 * 
	 * @return inFileName file name to be read from
	 */
	public String getInFileName(){return inFileName;}
	
	/**
	 * This method tests if character read is an acceptable operator
	 * @param op character being read in 
	 * @return true if character matches an acceptable operator
	 */
	public boolean myOperator(char op)
	{
		return op=='+'||op=='-'||op=='*'||op=='/'||op=='^'
				||op=='('||op==')'||op=='%';
	}
	/**
	 * This method establishes the priority of the different operators
	 * @param op character being read in
	 * @return priority values of the various operators
	 */
	public static int priority(char op)
	{
		if(op=='+'||op=='-'){return 1;}
		else if (op=='*'||op=='/'||op=='%'){return 2;}
		else if (op=='^'){return 5;}
		else {return -1;}

	}

	/**
	 * This method is responsible for reading the data from the file
	 * Data read in is tokenized and processed
	 * If character is an operator it is placed on operator stack
	 * If character/string is a number it is placed on the value stack
	 * Once answers are calculated the method determines which answers should be floats and which should be integers
	 * Answers are stored in vector called Answers
	 */
	public void readFile() 
	{
		String inFile=getInFileName();
		try
		{ infile = new Scanner(new FileReader(inFile)); } 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
			e.printStackTrace(); // prints error(s)
			System.exit(0); // Exits entire program
		}
		while (infile.hasNextLine())
		{
			String line=infile.nextLine();
			StringTokenizer tokenizer=new StringTokenizer(line);
			while (tokenizer.hasMoreTokens())
			{
				String token=tokenizer.nextToken();
				char op=token.charAt(0);
				if(myOperator(op))
				{ myPush(operator,op); }
				else
				{
					try
					{
						String num=token;
						value.push(Float.parseFloat(num));
					}
					catch(NumberFormatException e)
					{
						System.out.println("Incorrect Input Format");
						e.printStackTrace(); // prints error(s)
						System.exit(0); // Exits entire program
						
					}
				}
			}
			while(operator.size()>0)
			{
				char oldOp=operator.pop();
				popAndProcess(value,oldOp,operator);
			}
			if(value.peek()%1==.0)
			{ Answer.add(value.pop().intValue()); }
			else
			{ Answer.add(value.pop()); }
		}

		infile.close();
	}
	/**
	 * This method accepts a file name for the answers to be written to
	 * Will write the answers to the file from the vector Answer
	 * @param outFileName file name for answers to be written to
	 */
	public void writeResults(String outFileName)
	{

		try
		{
			PrintWriter writer=  new PrintWriter(outFileName);
			for(int i=0;i<Answer.size();i++)
			{writer.println(Answer.elementAt(i));}
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Was not able to write to file");
			// TODO Auto-generated catch block
		}

	}
	/**
	 * This method is responsible for determining action to be performed on operator depending on priority
	 * It will push an operator on the operator stack or process the operators and values depending on priority
	 * @param operator stack which holds operators
	 * @param op character which represents the various operators
	 */
	public static void myPush(myStack<Character> operator, char op)
	{
		if (op == '(') { operator.push(op); }
		else if (op == ')')
		{  
			boolean flag = false;
			while (!flag)
			{  
				if (operator.size() == 0) { System.out.println("No matching ("); }
				char oldOp = operator.pop();
				if (oldOp == '(') { flag = true; }
				else { popAndProcess(value, oldOp,operator); }
			}
		}
		else if (operator.size() == 0) 
		{ operator.push(op); }
		else
		{  
			char oldOp = operator.pop();
			if (priority(op) > priority(oldOp)) { operator.push(oldOp); }
			else { popAndProcess(value, oldOp,operator); }
			operator.push(op);
		}
	}
	
	/**
	 * This method will do the actual calculations
	 * When an equation is ready to be processed it will pop the values off the stack and store them as floats
	 * Uses a switch statement to determine what calculation should be performed depending on operator
	 * @param value stack holding the numerical values
	 * @param op character which represents the various operators
	 * @param operator stack which holds operators
	 */
	public static void popAndProcess(myStack<Float> value, char op,myStack<Character>operator)
	{
		try
		{
			float y=value.pop();
			float x=value.pop();
			float z=0;
			switch (op)
			{
			case '+': z=x+y;
			break;
			case '-': z=x-y;
			break;
			case '*': z=x*y;
			break;
			case '/': if(y==0){System.out.println("Error No Division by zero");}
			else{ z=x/y;}
			break;
			case '^': z=(int) Math.pow(x, y);
			break;
			case '%': z=x%y;
			case '(': break;
			case ')': break;
			default: System.out.println("Error Incorrect Opperator");
			}

			value.push(z);
		} 
		catch (NullPointerException e) 
		{
			if(value.size()==0){System.out.println("No Value Found or Incorrect Input Format");}
			else if(operator.size()==0){System.out.println("No Operator or Incorrect Input Format");}
			else{System.out.println("Error of unknown source");}
			e.printStackTrace(); // prints error(s)
			System.exit(0); // Exits entire program
			
		}
		
	}

}

