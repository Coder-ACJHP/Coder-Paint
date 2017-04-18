package com.coder.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/*
 * 
 * Author Coder ACJHP
 * 
 */
public class HeartShape {

	private Path2D base;
    private Shape shape;
    private ArrayList<Shape> shapes;
    public HeartShape() {
    	super();
    }

    public Shape fillHeart(double x, double y, double HEART_RADIUS) {
        double r = HEART_RADIUS;
        double root2 = Math.sqrt(2);
        double cx = x;
        double cy = y;
        double dx = r / root2;
        double heights = 3 * dx + r;
        double top = cy - heights / 2;
        double bottom = cy + heights / 2;

        base = new Path2D.Double();
        Shape s = new Arc2D.Double(cx - dx - r, top, 2 * r, 2 * r, 45, 180, Arc2D.OPEN);
        base.append(s, false);
        s = new Line2D.Double(cx, bottom, cx - 2 * dx, bottom - 2 * dx);
        base.append(s, true);
        s = new Line2D.Double(cx, bottom, cx + 2 * dx, bottom - 2 * dx);
        base.append(s, true);
        s = new Arc2D.Double(cx + dx - r, top, 2 * r, 2 * r, -45, 180, Arc2D.OPEN);
        base.append(s, true);

        base.closePath();
        shape = base;

        return base;
    }

    public ArrayList<Shape> Heart(double x, double y, double HEART_RADIUS) {
		shapes = new ArrayList<>();
		double r = HEART_RADIUS;
		double root2 = Math.sqrt(2);
		double cx = x;
		double cy = y;
		double dx = r / root2;
		double heights = 3 * dx + r;
		double top = cy - heights / 2;
		double bottom = cy + heights / 2;
		Shape s = new Arc2D.Double(cx - dx - r, top, 2 * r, 2 * r, 45, 180, Arc2D.OPEN);
		shapes.add(s);
		s = new Arc2D.Double(cx + dx - r, top, 2 * r, 2 * r, -45, 180, Arc2D.OPEN);
		shapes.add(s);
		s = new Line2D.Double(cx, bottom, cx - 2 * dx, bottom - 2 * dx);
		shapes.add(s);
		s = new Line2D.Double(cx, bottom, cx + 2 * dx, bottom - 2 * dx);
		shapes.add(s);
		if (shapes != null) {
			return shapes;
		} else {
			return null;
		}
	}
    
    public void drawFilledHeart(Graphics2D g2d) {
            g2d.fill(shape);
            
        }
    
    public void drawHeart(Graphics2D g2d) {
    	shapes.forEach((ss) -> {
            g2d.draw(ss);
             });
    	}
 

    public void clear() {
    	shapes.clear();
    	base.reset();
    }
    /*
	 * What is the HEART_RADIUS? It's a control point for heart shape, it
	 * enlarge or reduce the shape. How to use it ? It's much easy, lets browse
	 * for example :
	 * 
	 * 
	 * private double x, y, HEART_RADIUS; 
	 * private Point startPoint;
	 * 
	 * At first let to create a point with mouse pressed to be able 
	 * know mouse location when it pressed.
	 * 
	 * @Override public void mousePressed(MouseEvent e) { 
	 * startPoint = e.getPoint(); 
	 * }
	 * 
	 * 
	 * At second let to create x and y points with mouse dragging.
	 * 
	 * @Override public void mouseDragged(MouseEvent e) { 
	 * x = Math.min(startpoint.x, e.getX());
     * y = Math.min(startpoint.y, e.getY());
	 * int width = Math.abs(startpoint.x - e.getX()); 
	 * int height = Math.abs(startpoint.y - e.getY());
	 *
	 * HEART_RADIUS = Math.min(width, height); 
	 * }
	 * 
	 * Thats it! If you have way better than this please share it. Happy coding
	 * :)
     */
}
