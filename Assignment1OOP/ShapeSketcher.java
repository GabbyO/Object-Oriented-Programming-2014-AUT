import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.*;

public class ShapeSketcher extends JPanel
{
	private Shape shape;
	private int x;
    private int y;
    private int width;
    private int height;
    protected JColorChooser colorChoose;
    protected Color selectedColor;
    
    Point newPoint;
	Point endPoint;
	
	boolean isCheckOval = false;
	boolean isCheckLine = false;
	boolean isCheckSqu = false;
	boolean isCheckRect = false;
	boolean isCheckCir = false;
	boolean isFilledShape = false;
    
    private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	
	JRadioButton line, oval, circle, rect, square; 
	JButton colour; 
	JCheckBox fillShape;
	
    private DrawPanel drawPanel;
    private ButtonListener buttonListener;
    
    public ShapeSketcher()
	{
        setPreferredSize(new Dimension(600,600));
        drawPanel = new DrawPanel();
        buttonListener = new ButtonListener();
        add(drawPanel);
        colorChoose = new JColorChooser(drawPanel.getForeground());
        
        
        line = new JRadioButton("Line");
	    oval = new JRadioButton("Oval");
	    circle = new JRadioButton("Circle");
	    rect = new JRadioButton("Rectangle");
	    square = new JRadioButton("Square");
	    fillShape = new JCheckBox("Fill Shape");
	    colour = new JButton("Colour");
	    add(new JLabel("Choose shape to draw"));
	    
		ButtonGroup group = new ButtonGroup();
	    group.add(line);
	    group.add(oval);
	    group.add(circle);
	    group.add(rect);
	    group.add(square);
	    
	    add(line);
	    add(oval);
	    add(circle);
	    add(rect);
	    add(square);
	    add(fillShape);
	    add(colour);

	    line.addActionListener(buttonListener);
	    oval.addActionListener(buttonListener);
	    circle.addActionListener(buttonListener);
	    rect.addActionListener(buttonListener);
	    square.addActionListener(buttonListener);
	    fillShape.addActionListener(buttonListener);
	    colour.addActionListener(buttonListener);
	    
	    drawPanel.addMouseListener(drawPanel);
	    drawPanel.addMouseMotionListener(drawPanel);
	}
    
    public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        public DrawPanel()
        {
            setPreferredSize(new Dimension(500,500));
            setBackground(Color.WHITE);
            newPoint = null;
            endPoint = null;
            addMouseListener(this);
       		addMouseMotionListener(this);
        }
        
        public void paintComponent(Graphics g)
        {
			super.paintComponent(g);
            if (newPoint != null) 
            {
	            if(getCheckLine(true) && line == line)
	            {
	                g.setColor(selectedColor);
	                g.drawLine(newPoint.x, newPoint.y, endPoint.x, endPoint.y);
                } 
                
                if(getCheckRect(true) && rect == rect )
	            {
	                g.setColor(selectedColor);
	                x = Math.min(newPoint.x, endPoint.x);
					y = Math.min(newPoint.y, endPoint.y);
					width = Math.abs(newPoint.x - endPoint.x);
					height = Math.abs(newPoint.y - endPoint.y);
					
	                
					if(isFilledShape == true)
	                {
		                g.fillRect(x, y, width, height);                
	                } else {
		                if(isFilledShape == false)
		                {
			                g.drawRect(x, y, width, height);
			                
		                }
	                }
                }
                
                if(getCheckOval(true) && oval == oval)
	            {
	                g.setColor(selectedColor);
	                x = Math.min(newPoint.x, endPoint.x);
					y = Math.min(newPoint.y, endPoint.y);
					width = Math.abs(newPoint.x - endPoint.x);
					height = Math.abs(newPoint.y - endPoint.y);
	                
					if(isFilledShape == true)
	                {
		                g.fillOval(x, y, width, height);                
	                } else {
		                if(isFilledShape == false)
		                {
			                g.drawOval(x, y, width, height);
			                
		                }
	                }
                }
                
                if(getCheckCircle(true) && circle == circle)
	            {
	                g.setColor(selectedColor);
	                x = Math.min(newPoint.x, endPoint.x);
					y = Math.min(newPoint.y, endPoint.y);
					width = Math.abs(newPoint.x - endPoint.x);
					height = Math.abs(newPoint.y - endPoint.y);
	                
					if(isFilledShape == true)
	                {
		                g.fillOval(x, y, width, height);                
	                } else {
		                if(isFilledShape == false)
		                {
			                g.drawOval(x, y, width, height);
			                
		                }
	                }
                }
                
                if(getCheckSquare(true) && square == square)
	            {
	                g.setColor(selectedColor);
	                x = Math.min(newPoint.x, endPoint.x);
					y = Math.min(newPoint.y, endPoint.y);
					width = Math.abs(newPoint.x - endPoint.x);
					height = Math.abs(newPoint.y - endPoint.y);
	                
					if(isFilledShape == true)
	                {
		                g.fillRect(x, y, width, height);                
	                } else {
		                if(isFilledShape == false)
		                {
			                g.drawRect(x, y, width, height);
			                
		                }
	                }
                }
            }
        }

        
        public void mouseDragged(MouseEvent e)
        {
	        endPoint = newPoint;
        	endPoint = e.getPoint();
        	repaint();
	    }
    	
    	public void mouseMoved(MouseEvent e)
    	{
	    	endPoint = e.getPoint();

	    }
    	
    	public void mouseClicked(MouseEvent e)
    	{
	    }
    	
    	public void mouseEntered(MouseEvent e){}
    	public void mouseExited(MouseEvent e){}
    	
    	public void mousePressed(MouseEvent e)
    	{
			newPoint = e.getPoint();
    	}	
    	public void mouseReleased(MouseEvent e)
    	{
	    	newPoint = endPoint;
	    }
	}
   
	public boolean getCheckLine(boolean getShape)
	{
		return isCheckLine;
	}
	
	public boolean getCheckOval(boolean getShape)
	{
		return isCheckOval;
	}
	
	public boolean getCheckRect(boolean getShape)
	{
		return isCheckRect;
	}
	
	public boolean getCheckSquare(boolean getShape)
	{
		return isCheckSqu;
	}
	
	public boolean getCheckCircle(boolean getShape)
	{
		return isCheckCir;
	}
	
	public boolean getFilledShape(boolean getShape)
	{
		return isFilledShape;
	}
	
	public class ButtonListener extends JPanel implements ActionListener	            
	{
		public ButtonListener()
		{
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
            if(source == line)
            { 
				isCheckLine = true;
				isCheckRect = false;
				isCheckSqu = false;
				isCheckOval = false;
				isCheckCir = false;
			}
			
			if(source == square)
            { 
				isCheckSqu = true;
				isCheckRect = false;
				isCheckLine = false;
				isCheckOval = false;
				isCheckCir = false;
			}
			
			if(source == oval)
            { 
				isCheckOval = true;
				isCheckRect = false;
				isCheckLine = false;
				isCheckSqu = false;
				isCheckCir = false;
			}
			
			if(source == rect)
            { 
				isCheckRect = true;
				isCheckOval = false;
				isCheckLine = false;
				isCheckSqu = false;
				isCheckCir = false;
			}
			
			if(source == circle)
            { 
				isCheckCir = true;
				isCheckOval = false;
				isCheckLine = false;
				isCheckSqu = false;
				isCheckLine = false;
			}
			
			if(source == colour)
			{
				selectedColor = JColorChooser.showDialog(frame, "Choose a color" , colorChoose.getColor());				
				
			}
			
			if(source == fillShape)
			{
				isFilledShape = true;
			} else {
				if(source != fillShape)
				{
					isFilledShape = false;
				}
			}
		}
	}
	
	public static void main(String[] args)
   	{   
	  JFrame frame = new JFrame("Shape Sketcher");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new ShapeSketcher());
      
      ShapeSketcher panel = new ShapeSketcher();
      frame.getContentPane().add(panel);
      
      frame.pack();
      
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      
      Dimension screenDimension = tk.getScreenSize();
      Dimension frameDimension = frame.getSize();
      
      frame.setLocation((screenDimension.width-frameDimension.width)/2,
         (screenDimension.height-frameDimension.height)/2);
      frame.setVisible(true);
   }
}