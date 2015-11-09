package de.hsh.Objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PrototypeWall extends Unmovable {
	private ArrayList<Point2D> lines = new ArrayList<Point2D>();
	
	
	public PrototypeWall() {
		lines = new ArrayList<Point2D>();
	}
	
	public void draw(Graphics g, Point2D p) { //Der Punkt p ist der Teil der prototypeWall, der sich noch ändert. Also die aktuelle Position des Player
		Graphics2D g2d = (Graphics2D) g;
		
		
		float dash[] = { 10.0f };
		g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		
		
		g2d.setColor(getColor());
		
		if(lines.size()>0) {
			g2d.drawLine((int)lines.get(lines.size()-1).getX(), (int)lines.get(lines.size()-1).getY(),(int)p.getX(), (int)p.getY());
			
			if(lines.size() >= 2) {
				for(int i = 0; i<lines.size()-1; i++) {
					g2d.drawLine((int)lines.get(i).getX(), (int)lines.get(i).getY(), (int)lines.get(i+1).getX(), (int)lines.get(i+1).getY());
				}
			}
		}
	}
	
	public Color getColor() {
		return Color.CYAN;
	}
	
	public boolean isDrawn() {
		return lines.size() > 0;
	}
	
	public void addEdge(Point2D edge) {
		lines.add(edge);
	}
	
	public void clear() {
		lines.clear();
	}
	
	public Point2D getEnd() {
		return lines.get(lines.size()-1);
	}
	
	public boolean intersects(Rectangle2D rect) {
		if(lines.size() >= 2) {
			for(int i = 0; i<lines.size()-2; i++) {
				Line2D line = new Line2D.Double(lines.get(i),lines.get(i+1));
				if(rect.intersectsLine(line)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
