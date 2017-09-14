import java.awt.Point;
import java.awt.Graphics;

public class Square extends Shape implements EnclosesRegion
{
	private Point newPoint,endPoint;
	
	public Square()
	{
		this(null);
	}
	
	public Square(Point newPoint)
	{
		super(newPoint);
	}
	
	public void draw(Graphics g)
    {
	    g.drawRect(newPoint.x, newPoint.y, endPoint.x, endPoint.y);
		repaint();		
	}
	
	public String toString()
    {    
		return newPoint + super.toString();
    }
}