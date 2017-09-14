import java.awt.Point;
import java.awt.Graphics;

public class Oval extends Shape implements EnclosesRegion
{
	private Point newPoint,endPoint;
	
	public Oval()
	{
		this(null);
	}
	
	public Oval(Point newPoint)
	{
		super(newPoint);
	}
	
	public void draw(Graphics g)
    {
		g.drawOval(newPoint.x, newPoint.y, endPoint.x, endPoint.y);
		repaint();
	}
	
	public String toString()
    {    
		return newPoint + super.toString();
    }
}