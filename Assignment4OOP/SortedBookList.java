public class SortedBookList
{
	private Node firstNode; // reference to first node in SortedBookList
  	private Node lastNode; // reference to last node in SortedBookList
  	public Node cycleNode;
  	private int numElements;
	Book book;
   	public SortedBookList()
   	{
   	    firstNode = null; // initially no books in the list
        lastNode = null; // initially no books in the list
   	}
   	
   	public boolean add(Book book)
   	{ 
    	Node newNode = new Node(book);
        
        if (firstNode == null)
        {   
	        // no nodes in the list so add newNode at start
	    	firstNode = newNode;
	        lastNode = newNode;
	        cycleNode = newNode;
	        return true;
		}
		else
		{
	    if (newNode.compareTo(firstNode)<0)
	    {
			//find the end of the list and add newNode at end
		    newNode.previous = firstNode;
		    firstNode.next = newNode;
		    firstNode = newNode;
		    return true;
		}
		else
		{
	    if (newNode.compareTo(lastNode)>0)
	    {
			//find the end of the list and add newNode at end
	        newNode.previous = lastNode;
	        lastNode.next = newNode;
	        lastNode = newNode;
	        return true;
		}  
		}	
	    cycleNode = firstNode;
	        
	    while(newNode.compareTo(cycleNode)>0)
	    {
			cycleNode = cycleNode.next;
		}
			
		newNode.next = cycleNode;
		newNode.previous = cycleNode.previous;
		cycleNode.previous.next = newNode;
		cycleNode.previous = newNode;
	}
	return false;
   	}
   	
   	public boolean remove(Book book)
   	{ 
        if(book != null)
        {
	        return true;
        } else {
			return false;
		}
   	}
   	
   	
   	public Book get(int index)
   	{
		Book book = new Book();
		Book[] bookArray = new Book[index];

		return book;   	
    }
   	
   	public int size()
   	{
        return numElements;
   	}
   	
   	public String getISBN()
   	{
	   	return book.getISBN();
   	} 	
   	
   
	public String toString()
	{  
		String output = "";
	    Node currentNode = lastNode;
	    while (currentNode != null)
	    {  
		    output += currentNode.bookElement.toString() + "\n";
		    currentNode = currentNode.previous;
	    }
	    return output;      
	}

   	// an inner class that represents a node in the list
   	protected class Node
   	{
      	public Book bookElement; // reference to the Book in list
	    public Node next; // link to next BookNode in list
	    public Node previous; // link to prev BookNode in list
      
     	public Node(Book bookElement)
      	{
      		this.bookElement = bookElement;
         	next = null;
         	previous = null;
      	}
   	}
   	
	public static void main(String[] args)
	{  
		SortedBookList list = new SortedBookList();
       	list.add(new Book("Java Software Solutions", "Lewis/Loftus",
        	"JavaSoftwareSolutions"));
       	list.add(new Book("Core Java", "Horstmann/Cornell",
        	"CoreJava"));
       	System.out.println("Some Java books:");
       	System.out.println(list); 
   }
   
}
