import java.awt.Point;
import java.awt.Graphics;

public class Circle extends Shape implements EnclosesRegion
{
	private Point newPoint,endPoint;
	
	public Circle()
	{
		this(null);
	}
	
	public Circle(Point newPoint)
	{
		super(newPoint);
	}
	
	public void draw(Graphics g)
    {
		g.drawOval(newPoint.x, newPoint.y, endPoint.x, endPoint.y);
	}

	public String toString()
    {    
		return newPoint + super.toString();
    }
    
}