import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Shape extends JPanel implements EnclosesRegion
{
	Point startPoint;
    Point controlPoint;
    Color colour;
	protected JColorChooser colorChoose;
	public int x1, x2, y1, y2, width, height;

    public Shape()
    {		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.width = width;
		this.height = height;
    }
    
    public Shape(Point startPoint)
    {
		this.startPoint = startPoint;
		this.controlPoint = controlPoint;
		setFilled(false);
		setColour(Color.BLACK);
		setControlPoint(this.controlPoint);
		repaint();
    }
    
    public void setFilled(boolean filled)
    {
		if(filled == true)
		{
			filled = true;
		}
    }
    
    public int getArea()
    {
	    return height * width;
    }
    
    public Color getColour()
    {
	    colour = colorChoose.getColor();
	    return colour;
    }

    public void setColour(Color colour)
    {
	    this.colour = colour;
    }
    
    public void setControlPoint(Point controlPoint)
    {
	    controlPoint = controlPoint;
    }
    
    public void draw(Graphics g)
    {
	    super.paintComponent(g);
    }
    
    public String toString()
    {
	    Point point = new Point(x1, y1);
	 	String pointString = point.toString(); 
	    return pointString;
    }
}