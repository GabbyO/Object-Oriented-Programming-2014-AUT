import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.colorchooser.*;
import java.io.*;

public class Drawing extends JPanel
{
	Point[] endPoints;
	Color lineColour;
	int numDraw;
	int numLines;
    private String fileName;
	
	public Drawing()
	{  
        super();
	    numLines = 0;
        numDraw = 0;
        endPoints = null;
	}
	
	public Drawing(File file)
	{  
        super();
        setBackground(Color.WHITE);
        
		try
      	{  
	      	FileInputStream fis = new FileInputStream(file);
            fileName = file.getName();
	      	ObjectInputStream ois = new ObjectInputStream(fis);
        	ArrayList<Point> list = new ArrayList<Point>();
        
        	while(fis.available() > 0)
        	{
                //Read a new Point 
	        	Point p  = (Point)ois.readObject();
	            list.add(p);
        	}
        
        	ois.close();
        
        	endPoints = list.toArray(new Point[list.size()]);
            numLines = endPoints.length - 1;
      	}
      	
      	catch (ClassNotFoundException event)
      	{
	      	JOptionPane.showMessageDialog(this, event.getMessage(),
            	"Can't Read This File", JOptionPane.ERROR_MESSAGE);
      	}
      	
      	catch (IOException event)
      	{  
	      	JOptionPane.showMessageDialog(this, event.getMessage(),
            	"Error Opening File", JOptionPane.ERROR_MESSAGE);
      	}
		
      	catch(NumberFormatException e)
    	{
    		System.out.println("Sorry this is not an int" + e.getMessage());
      	}
	}
	
	void setNumDraw(int numDraw)
	{
		this.numDraw = numDraw;
	}
	
	int getNumDraw()
	{
		return numDraw;	
	}
	
	int getNumLines()
	{
		return numLines;
	}
	
	void setColour(Color lineColour)
	{
		this.lineColour = lineColour;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        g.setColor(lineColour);
        
		for(int i = 0; i < numDraw; i++)
        {
            Point point1 = endPoints[i];
            Point point2 = endPoints[i+1];

            g.drawLine(point1.x, point1.y, point2.x, point2.y);
        }
	}
	
	public String toString()
	{
		return fileName;
	}
	
	public static void main(String[] args)
	{
        Drawing drawing = new Drawing(new File("square.drw"));
        drawing.setNumDraw(4);
        drawing.repaint();
        drawing.setPreferredSize(new Dimension(600,300));
        
		JFrame frame = new JFrame("Test Drawing");
      	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	frame.getContentPane().add(drawing);
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
