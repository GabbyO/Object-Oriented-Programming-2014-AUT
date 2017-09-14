import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class BookGUI extends JPanel implements ActionListener
{
	JButton next, previous, add, remove;
   	BookPanel bookPanel;
   	JLabel bookTitle;
   	Book book;
   	SortedBookList.Node cycleNode;
   	SortedBookList addBook;
   	
	public BookGUI()
	{
		super(new BorderLayout());
        setPreferredSize(new Dimension(400,500));
        bookPanel = new BookPanel();
        bookPanel.setBackground(Color.WHITE);
        add(bookPanel,BorderLayout.CENTER);
        bookTitle = new JLabel("Books");
        next = new JButton("Next");
        previous = new JButton("Prev");
        add = new JButton("Add");
        remove = new JButton("Remove");
        
        JPanel buttonPanelOne = new JPanel();
       
        buttonPanelOne.add(previous);
        buttonPanelOne.add(next); 
        buttonPanelOne.add(add);
        buttonPanelOne.add(remove);
        add(buttonPanelOne,BorderLayout.SOUTH);
        add(bookTitle,BorderLayout.NORTH);
        next.addActionListener(this);
        previous.addActionListener(this);
		add.addActionListener(this);
        remove.addActionListener(this);
	}
	
	private class BookPanel extends JPanel
   	{
       	public void paintComponent(Graphics g)
       	{    
       		super.paintComponent(g);
	        if(cycleNode != null)
	        {
	        	Image i = cycleNode.bookElement.getCoverImage().getImage();
	            Image scaled = i.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
	            ImageIcon icon = new ImageIcon(scaled);
	            icon.paintIcon(this,g,0,0);
	        }
       }
	}
       
	public void actionPerformed(ActionEvent event)
   	{
        Object source = event.getSource();
        JFileChooser chooser = new JFileChooser(new File("."));
        if (source == next)
        {   
            if(cycleNode.next != null)
            {   cycleNode = cycleNode.next;
                bookTitle.setText(cycleNode.toString());
            }
            if(cycleNode.next == null)
                next.setEnabled(false);
            else
                next.setEnabled(true);
                
            bookPanel.repaint(); 
        }
        
        if (source == previous)
        {
	        if(cycleNode.previous != null)
            {   cycleNode = cycleNode.previous;
                bookTitle.setText(cycleNode.toString());
            }
            
			if(cycleNode.previous == null)
                previous.setEnabled(false);
            else
                previous.setEnabled(true);
                
            bookPanel.repaint(); 
        }
        
        if (source == remove)
        {
	        addBook.remove(book);
            bookPanel.repaint(); 
        }
        
        if (source == add)
        {
	    	int status = chooser.showOpenDialog(this);
        	if(status == JFileChooser.APPROVE_OPTION)
        	{  
        		File fileToOpen = chooser.getSelectedFile();
        		fileOpen(fileToOpen);
        	}
            addBook.add(book);    
            bookPanel.repaint(); 
        }
	}
	
	private void fileOpen(File file)
	{
		try
		{  
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = br.readLine();
			while (line != null)
			{  
			    line = br.readLine();
			}
			    br.close();
	  	}
      	
	  	catch (IOException e)
		{  
			JOptionPane.showMessageDialog(this, e.getMessage(),
		    	"Error Opening File", JOptionPane.ERROR_MESSAGE);
		}
	}  
   
   
   public static void main(String[] args)
   {  
	   BookGUI bookGUI = new BookGUI();
       JFrame frame = new JFrame("BookGUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().add(bookGUI);
       frame.pack();
       // position the frame in the middle of the screen
       Toolkit tk = Toolkit.getDefaultToolkit();
       Dimension screenDimension = tk.getScreenSize();
       Dimension frameDimension = frame.getSize();
       frame.setLocation((screenDimension.width-frameDimension.width)/2,
           (screenDimension.height-frameDimension.height)/2);
       frame.setVisible(true);
          
   }   


}
