import java.util.Stack;
import java.util.Vector;
public static void myPush(Stack<Character> operator, char op)
{
	
	if(value.pop()%1!=.0)
	{
	
		char op1 = operator.pop();
		char op2 = operator.pop();
	   //operator.push(op);
        if (priority(op2) > priority(op1)) { operator.push(op1); 
       System.out.println(operator.toString());}
        else { popAndProcess(value2, op1);System.out.println(operator); System.out.println(value2);}
	
	
	
}

public class Stack <E> //class Stack<E>
{
		private Vector<E> stack;
	    private int top = -1; // index of the top item of the stack

	    public Stack() 
	    {
	        stack = new Vector<E>();
	    }

	    public void push(E obj) 
	    {
	    	
	        stack.add(obj);
	        top++;
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
	
}
