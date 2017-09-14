import java.util.NoSuchElementException;

public class BookArrayStack
{
	private Book[] bookArray;
	public int INITIAL_CAPACITY = 5;
   	private int numElements;
	
   	public BookArrayStack()
   	{
	   	bookArray = new Book[INITIAL_CAPACITY];
        numElements = 0;
   	}
   
   	public void push(Book book)
   	{
        if(numElements >= bookArray.length)
            expandCapacity();
   
        bookArray[numElements] = book;
        numElements ++;
   	}
   	
   	public Book pop() throws NoSuchElementException
   	{
	   	if(isEmpty())
    		throw new NoSuchElementException("The stack is empty");
        numElements --;
        Book temp = bookArray[numElements];
        bookArray[numElements] = null;
        return temp;	   	
   	}
   	
   	public Book peek() throws NoSuchElementException
   	{
	    if(isEmpty())
    		throw new NoSuchElementException("The stack is empty");
    	return null;
   	}
   	
   	public boolean isEmpty()
   	{
        if(numElements == 0)
            return true;
        else return false;
   	}
   	
   	public int size()
   	{
   		return numElements;
   	
   	}
   	
   	public String toString()
   	{
	   	return "";
   	}
   	
   	private void expandCapacity()
   	{   System.out.println("EXPANDING CAPACITY");
	   	Book[] newArray = new Book[bookArray.length*2];
        for(int i=0;i<bookArray.length;i++)
        {
            newArray[i] = bookArray[i];
        }
   	    bookArray = newArray;
   	}
   	
   	public static void main(String[] args)
   	{
	   	BookArrayStack stack = new BookArrayStack();
	   	Book book1 = new Book("325133123", "Manlord" , "Hello Java");
	   	Book book2 = new Book("341324111", "Terri" , "Welcome Java");
	   	Book book3 = new Book("341324111", "Joy" , "You're a Java!");
        
        
        stack.push(book1);
        stack.push(book2);
        stack.push(book3);
        
        System.out.println(stack.pop());
		System.out.println(stack.pop());
        System.out.println(stack.pop());
       
        
        
		try
		{  
			stack.peek();
		}
			catch(NoSuchElementException e)
		{   
			System.out.println(e);
		}
		
		try
		{  
			stack.pop();
		}
			catch(NoSuchElementException e)
		{   
			System.out.println(e);
		}
   	}
}
