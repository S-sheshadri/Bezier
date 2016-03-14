package bezierPlot;

import processing.core.*;
import java.util.*;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.geo.Location;


import de.fhpotsdam.unfolding.marker.MultiMarker;
import java.lang.Math;
public class Bezier2 extends PApplet {
	int cp=0;
	int x=0;
	int y=0;
	int i=0;
	 String ex;
     String ey;
    
	boolean newcurve=true;
	SimplePointMarker select=null;
	boolean bezierdone=false;
	List<SimplePointMarker> points = new ArrayList();
	LinkedList xres=new LinkedList();
	LinkedList yres;
	int lc=0;
	LinkedList multiple=new LinkedList();
	public void draw()
	{
		//axes
		stroke(0);
		line(width/2,0,width/2,height);
		line(0,height/2,width,height/2);
		for(SimplePointMarker m:points)
		{if(m==points.get(points.size()-1)||m==points.get(0))
			fill(255,0,0);
		else fill(255);
			ellipse(m.getLocation().x,m.getLocation().y,5,5);
		}
	/*	for(Object o: multiple){
		    Curve c=(Curve)o;
		    List<SimplePointMarker> pointsc=(List<SimplePointMarker>)c.points;
		{for(SimplePointMarker m:pointsc)
		{if(m==pointsc.get(pointsc.size()-1)||m==pointsc.get(0))
			fill(255,0,0);
		else fill(255);
			ellipse(m.getLocation().x,m.getLocation().y,5,5);
		}}}
		*///to see selected
		if(select!=null)
		{fill(0,0,255);
			ellipse(select.getLocation().x,select.getLocation().y,5,5);}
		//to plot the curve
       for(Object o: multiple){
	    Curve c=(Curve)o;
 	 
	    LinkedList x=c.xres;
	    LinkedList y=c.yres;
		for(int i=0;i<x.size()-1;i++)
	{stroke(1);
	line((float)x.get(i),(float)y.get(i),(float)x.get(i+1),(float)y.get(i+1));
	}}

		}
	
	public void setup()
	{
		background(255);
		size(displayWidth/2,displayHeight/2);
		smooth(4);
		}

	public void mouseClicked()
	{
		//if curve not drawn yet mouse click forms new points
		if(!bezierdone && newcurve){
			Location l=new Location(mouseX,mouseY);
			SimplePointMarker m=new SimplePointMarker(l);
			points.add(m);}
		else
		//if curve drawn mouse click selects points
		{for(SimplePointMarker m:points)
		{
			if(m.isInside(mouseX,mouseY,m.getLocation().x,m.getLocation().y))
			{
				newcurve=false;
				select=m;
				noFill();
				}
		}
		}
		}
	public void mouseReleased()
		{//to find new position for the point selected
		if(select!=null)
		{select.setLocation(mouseX,mouseY);
		select=null;
		equ();
		clear();
		setup();
		bezier();
		}
		}
	
	
	public void keyPressed()
	{//to deselect while rearranging
		if(key=='n')
		{
		newcurve=true;
		bezierdone=false;
			noFill();
			select=null;
			points.clear();
			
		}
		if(key=='\n')
			
		{
			  
			bezierdone=true;
			//newcurve=false;
			equ();
			bezier();
			System.out.println("EQUATION OF CURVE: "+multiple.size()+" "+"X:"+ex+"\nY: "+ey+"\n");
}
			
		
			
		
	}
	
	public void equ()
	{
		int n=points.size()-1;
        int i=0;
        ex="";
        ey="";
        for(;i<=n;i++)
	   {	ex=ex.concat("\n"+nCr(n,i)	+points.get(i).getLocation().x+"*(1-t)^"+(n-i)+"*(t)^"+i+"+");
        	ey=ey.concat("\n"+nCr(n,i)	+points.get(i).getLocation().y+"*(1-t)^"+(n-i)+"*(t)^"+i+"+");
        	   }
        ex=ex.substring(0,ex.length()-1);
        ey=ey.substring(0,ey.length()-1);
        for (int k = 0; k < 50; ++k) System.out.println();
        System.out.println("Equations:\n"+ex+"\n"+ey);}
	
	public void bezier()
	{
		float t=0;
		 float step=0.001f;
		 xres=new LinkedList();
		 yres=new LinkedList();
		 	 
				
		 while(t<=1)
		 {
		float x=0;
		float y=0;
		int n=points.size()-1;
		for(int i=0;i<=n;i++)
			{
			x+=(Math.pow(t,(n-i)))*(Math.pow((1-t),i))*nCr(n,i)*(int)points.get(i).getLocation().x;
		    y+=Math.pow(t,(n-i))*Math.pow((1-t),i)*nCr(n,i)*(int)points.get(i).getLocation().y;
			}
		xres.add(x);
		yres.add(y);
		t+=step;
		}
		Curve b=new Curve(xres,yres,points);	
		b.ex=ex;
		b.ey=ey;
		if(newcurve)
		multiple.add(lc++,b);
		else
		{
		if(multiple.size()!=0)
			multiple.removeLast();
		multiple.add(b);
		}
		}
	
	public int fact(int num)
	{
	int fact=1;
	for(i=1;i<=num;i++)
	fact=fact*i;
	return fact;
	}
	public int nCr(int n,int r)
	{if(n==r)return 1;
		return (fact(n)/(fact(n-r)*fact(r)));}
	
}
class Curve
{
	LinkedList xres;
	LinkedList yres;
	List<SimplePointMarker> points;
	String ex;
	String ey;
	Curve(LinkedList x,LinkedList y,List<SimplePointMarker> p)
	{
		points=new ArrayList(p);
		xres=new LinkedList(x);
		yres=new LinkedList(y)	;
	}}