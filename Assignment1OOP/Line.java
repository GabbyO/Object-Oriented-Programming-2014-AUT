import java.awt.Point;
import java.awt.Graphics;

public class Line extends Shape implements EnclosesRegion
{
	private Point newPoint,endPoint;
	
	public Line()
	{
		this(null);
	}
	
	public Line(Point newPoint)
	{
		super(newPoint);
	}
	
	public void draw(Graphics g)
    {
	    g.drawLine(newPoint.x, newPoint.y, endPoint.x, endPoint.y);
		repaint();
	}
	
	public String toString()
    {    
		return newPoint + super.toString();
    }
}