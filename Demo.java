/*Applet now coming
 * ntn being drawn though*/
package bezierPlot;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import processing.core.*;
import java.io.*;
import java.util.*;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.geo.Location;


import java.util.*;
import java.awt.*;
public class Demo extends PApplet
{
	public LinkedList multiple=new LinkedList(),xres=new LinkedList(),yres=new LinkedList();
	int lc=0;
	//to call setup()
	
	 LinkedList xp,yp;
    
    /*methods to get shit done */
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
		int n=xp.size()-1;
		for(int i=0;i<=n;i++)
			{
			x+=(Math.pow(t,(n-i)))*(Math.pow((1-t),i))*nCr(n,i)*(float)xp.get(i);
		    y+=Math.pow(t,(n-i))*Math.pow((1-t),i)*nCr(n,i)*(float)yp.get(i);
			}
		xres.add(x);
		yres.add(y);
		t+=step;
		}
		Curve b=new Curve(xres,yres,new LinkedList());	
		multiple.add(lc++,b);
		}
		
   public int fact(int num)
		{int i;
			int fact=1;
			for(i=1;i<=num;i++)
			fact=fact*i;
			return fact;
			}
	public int nCr(int n,int r)
			{if(n==r)return 1;
				return (fact(n)/(fact(n-r)*fact(r)));}
	public  void setup()
	{
		   try
			{ 
		       Scanner in=new Scanner(new File("/home/s-sheshadri/workspace/UCSDUnfoldingMaps/data/bezierPlot/Letters/E.txt"));
		       
		       while(in.hasNext())
		       {xp=new LinkedList();
		       yp=new LinkedList();
		       String s=in.nextLine();
		       System.out.println(s+"f");
		       String sa[]=s.split("%+");
		       for(int i=0;i<sa.length;i++)
		               { xp.add(Float.parseFloat(sa[i].split(",")[0].substring(1,sa[i].split(",")[0].length()-1)));
		               yp.add(Float.parseFloat(sa[i].split(",")[1].substring(1,sa[i].split(",")[1].length()-1)));}
		       bezier();   
			      }
		    
		       	 }       
		       

		 
		catch(IOException e)
		{
			System.out.println("EXCEPTION");
		}

		background(255);
		size(displayWidth,displayHeight);
		//to plot the curve
	
	}
	public void draw()
	{fill(0);
	//axes
	//line(0,displayHeight/2,displayWidth,displayHeight/2);
	/*stroke(150,150,150);
	float t=displayWidth/18;
	for(float i=0;i<19;i++)
	{
		//axes in red
		if(abs(i*t-displayWidth/2)<=20)
		{
			stroke(255,0,0);
			line(i*t,0,i*t,displayHeight);
			stroke(150);}
		else
		
		line(i*t,0,i*t,displayHeight);
		
	}
	t=displayHeight/16;
	for(int i=0;i<=16;i++)
	{
		line(0,i*t,displayWidth,i*t);
		
	}
	//x axes in red 
	stroke(3);
	stroke(255,0,0);
	line(0,displayHeight/2,displayWidth,displayHeight/2);
*/
	//line(displayWidth/2,0,displayWidth/2,displayHeight);
	//curve
	for(Object o: multiple)
	{
		    Curve c=(Curve)o;
		    	 	 
		    LinkedList x=c.xres;
		    LinkedList y=c.yres;
			for(int i=0;i<x.size()-1;i++)
		{stroke(178,34,34);
		fill(178,34,34);
		ellipse((float)x.get(i),(float)y.get(i),4,4);
		}}
	
	}
	}		
	
