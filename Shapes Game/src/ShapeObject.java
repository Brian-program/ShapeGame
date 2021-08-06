import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Arrays;


public class ShapeObject{

	private String objectName;
	private int sides = 3;
	private Polygon shape;
	
	private int[] pointX;
	private int[] pointY;
	private int radius = 50;
	private int xOffset = 200;
	private int yOffset = 0;
	
	/**
	 * @param objectName name of the object/shape name
	 * @param sides amount of sides the object/shape has
	 * constructor for new ShapeObject
	 */
	public ShapeObject(String objectName, int sides) {
		this.objectName = objectName;
		this.sides = sides;
		
		pointX = new int[sides];
		pointY = new int[sides];
		
		for(int i = 0; i < sides; i++) {
			pointX[i] = (int)((Math.cos(Math.toRadians(360*i/sides))*radius) + xOffset);
			pointY[i] = (int)((Math.sin(Math.toRadians(360*i/sides))*radius) + yOffset);
		}
		
		shape = new Polygon(pointX, pointY, sides);
	}

	/**
	 * @return name of object/shape
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @return number of sides of the object/shape
	 */
	public int getSides() {
		return sides;
	}
	
	/**
	 * @return the Polygon of the shape
	 */
	public Polygon getShape() {
		this.update();
		return this.shape;
	}
	
	/**
	 * @return the x of the shape
	 */
	public int getXOffset() {
		return this.xOffset;
	}
	
	/**
	 * @return the y of the shape
	 */
	public int getYOffset() {
		return this.yOffset;
	}
	
	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}
	
	public void update() {
		for(int i = 0; i < sides; i++) {
			pointX[i] = (int)((Math.cos(Math.toRadians(360*i/sides))*radius) + xOffset);
			pointY[i] = (int)((Math.sin(Math.toRadians(360*i/sides))*radius) + yOffset);
			shape = new Polygon(pointX, pointY, sides);
		}
	}
	
	
}
