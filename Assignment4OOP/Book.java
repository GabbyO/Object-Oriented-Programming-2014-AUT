import javax.swing.ImageIcon;

public class Book implements Comparable<Book>
{
	private String isbn;
	private String author;
   	private String title;
   	private ImageIcon coverImage;
	
   	public Book()
   	{  
	   this("unknown", "unknown", "unknown");
   	}
   	
   	public Book(String isbn, String author, String title)
   	{
   		this.title = title;
      	this.author = author;
        this.isbn = isbn;
      	if (isbn != null && isbn.length()>0)
        	coverImage = new ImageIcon(isbn);
      	else
        	coverImage = null;
   	}
	
    public String toString()
   	{  
	   	return title + " (by " + author + ")" + " ISBN: " + isbn;
   	}
   	
   	public void setCoverImage(ImageIcon icon)
	{  
		this.coverImage = icon;
	}
   
	public ImageIcon getCoverImage()
	{  
		return coverImage;
	}
	
	public String getAuthor()
	{  
		return author;
	}

	public String getTitle()
	{  
		return title;
	}
	
	public String getISBN()
	{  
		return isbn;
	}
	
	public int compareTo(Book book)
	{  
        return isbn.compareTo(book.getISBN());
    }
}
