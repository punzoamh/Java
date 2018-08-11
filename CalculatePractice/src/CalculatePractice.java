import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;




class myStack<E>
{
	private Vector<E> stack;
	private int top = -1; // index of the top item of the stack


	public myStack() 
	{
		stack = new Vector<E>();
	}

	public void push(E x) 
	{

		stack.add(x);
		top++;
		for(int i=0;i<stack.size();i++)
		{
			System.out.println(stack.get(i));
		}
	}

	public E pop() {
		if (top == -1) {
			return null; // stack is empty
		}
		E temp = stack.get(top);
		stack.remove(top);
		top--;
		return temp;
	}


	public boolean isEmpty(){
		return top == -1;
	}

	public int size() 
	{
		return top+1;
	}

	public E peek() 
	{
		E temp=stack.get(top);
		return temp;
	}
	public void print()
	{
		for(int i=0;i<stack.size();i++)
		{
			System.out.println(stack.elementAt(i));
		}
		
	}




}

public class CalculatePractice
{

	private String fileName;
	private String fileName2;
	protected static Stack<Float> value=new Stack<>();

	//protected static myStack<Integer> value2=new myStack<>();
	protected Stack<Character> operator=new Stack<>();
	protected static Stack<Integer> priority=new Stack<>();
	//protected Stack<Integer> priority=new Stack<>();
	private static Vector Answer=new Vector();

	CalculatePractice()
	{

	}
	CalculatePractice(String fileName)
	{
		this.fileName=fileName;

	}
	public void setFileName (String fileName){fileName=this.fileName;}
	public String getFileName(){return fileName;}
	//public void setFileName2 (String fileName2){fileName2=this.fileName2;}
	//public String getFileName2(){return fileName2;}
	public Scanner infile = null;

	public boolean myOperator(char op)
	{
		return op=='+'||op=='-'||op=='*'||op=='/'||op=='^'
				||op=='('||op==')'||op=='%';
	}
	public static int myPriority(char op)
	{
		if(op=='+'||op=='-'){return 1;}
		else if (op=='*'||op=='/'||op=='%'){return 2;}
		else if (op=='^'){return 5;}
		else if(op=='('){return 6;}
		else {return -1;}

	}
	public static int myPriority2(char op)
	{
		if(op=='+'||op=='-'){return 1;}
		else if (op=='*'||op=='/'||op=='%'){return 2;}
		else if (op=='^'){return 4;}
		else if(op=='('){return 0;}
		else {return -1;}

	}

	public void readFile() 
	{





		String inFile=getFileName();

		try
		{
			infile = new Scanner(new FileReader(inFile));
		} 
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
				{
					//System.out.println("Here's an operator");
					//System.out.println(operator);
					myPush(operator,op);
					//System.out.println(operator);
				}
				else
				{
					//System.out.println("Here's a number");
					char num=token.charAt(0);
					String num1=Character.toString(num);
					value.push(Float.parseFloat(num1));
					//System.out.println(value);
					//System.out.println("Here's a number");

				}
				
				System.out.println("hello flag"+value);
				System.out.println("Flag2"+operator);
				System.out.println("Flag3"+priority);
			}
			
			/**while(operator.size()>0)
			{
				char oldOp=operator.pop();
				popAndProcess(value,oldOp,operator);
				//System.out.println("Operator's here");
				//System.out.println(operator);
			}*/
			//System.out.println("The Answer");
			
			if(value.peek()%1==.0)
			{
				//int x=value.peek().intValue();
				Answer.add(value.pop().intValue());


			}
			else
			{
				//float y=value.peek();
				Answer.add(value.pop());

			}

		}

		infile.close();
	}
	public void writeResults(String fileName)
	{

		try
		{

			PrintWriter writer=  new PrintWriter(fileName);
			for(int i=0;i<Answer.size();i++)
			{
				writer.println(Answer.elementAt(i));
			}


			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Was not able to writ to file");
			// TODO Auto-generated catch block
		}

	}
	public static void myPush(Stack<Character> operator, char op)
	{
		if (operator.isEmpty())
		{
			operator.push(op);
			priority.push(myPriority2(op));
		}
		
		else 
		{
			
			char oldOp = operator.peek();
			int x= priority.peek();
			
			if (myPriority2(op) >= x) { operator.push(oldOp); priority.push(myPriority2(op)); System.out.println(operator+"\n");System.out.println(priority); }
			else { popAndProcess(value, oldOp,operator); }
			operator.push(op);
		}
		
		
		/**if (op == '(') { operator.push(op); }
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
		{ 
			operator.push(op); 
		}
		else
		{  
			char oldOp = operator.pop();
			if (priority(op) > priority(oldOp)) { operator.push(oldOp); }
			else { popAndProcess(value, oldOp,operator); }
			operator.push(op);
		}*/
	}

	public static void popAndProcess(Stack<Float> value, char op,Stack<Character>operator)
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
			else{	z=x/y;}
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
			if(value.size()==0){System.out.println("No Value Found");}
			else if(operator.size()==0){System.out.println("No Operator");}
			else{System.out.println("Error of unknown source");}
			e.printStackTrace(); // prints error(s)
			System.exit(0); // Exits entire program
		}

		/**if(value.size()==0){System.out.println("Error");}
		float y=value.pop();
		if(value.size()==0){System.out.println("Error");}
		float x=value.pop();
		float z=0;
		if(operator.size()==0){System.out.println("No Operator");}*/


	}

}
