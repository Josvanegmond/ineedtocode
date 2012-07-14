package fun.electricity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Electricity
{
	private float width;
	private float positionX;
	private float positionY;
	
	private float startX;
	private float startY;
	
	private float steps;
	
	private Collection<Electricity> neighbours;
	
	public Electricity( float startX, float startY, float width, float steps, Collection<Electricity> neighbours )
	{
		this.positionX = startX;
		this.positionY = startY;
		
		this.startX = startX;
		this.startY = startY;
		
		this.width = width;
		this.steps = steps;
		
		this.neighbours = neighbours;
	}
	
	public void setX( float x )
	{
		this.startX = x;
		this.positionX = x;
	}
	
	public void setY( float y )
	{
		this.startY = y;
		this.positionY = y;
	}
	
	public void setWidth( float width )
	{
		this.width = width;
	}
	

	private void shootElectricity( Canvas canvas, float startX, float startY, float targetX, float targetY, float maxRange, Paint paint )
	{
		Random random = new Random();
		
		int maxHalfDeviation = 20;
		int maxDeviation = 40;
		
		float distanceX = targetX - startX;
		float distanceY = targetY - startY;
		
		if( Math.abs( distanceX ) < maxRange && Math.abs( distanceY ) < maxRange )
		{
			float stepX = distanceX / steps;
			float stepY = distanceY / steps;
			
			float nextX = startX;
			float nextY = startY;
			
			for( int i = 0; i < steps; i++ )
			{
				nextX += stepX;
				nextY += stepY;
				
				float deviationX = nextX + random.nextInt( maxDeviation ) - maxHalfDeviation;
				float deviationY = nextY + random.nextInt( maxDeviation ) - maxHalfDeviation;
				
				canvas.drawLine(startX, startY, deviationX, deviationY, paint);
				
				startX = deviationX;
				startY = deviationY;
			}
		}
	}

	
	
	
	
	public void draw( Canvas canvas )
	{
		if( width > 0 )
		{
			Random random = new Random();
			
			Paint paint = new Paint();
			paint.setStrokeWidth( 3 + width );
			paint.setColor( Color.WHITE );
			
			canvas.drawCircle( startX, startY, 6, paint);
			
			float maxRange = (int)( canvas.getWidth() / 1.3 );
			
			int number = random.nextInt( 7 );
			if( number == 1 )
			{
				shootElectricity( canvas, startX, startY, 0, 0, maxRange, paint);
			}
			else if( number == 2 )
			{
				shootElectricity( canvas, startX, startY, random.nextInt( canvas.getWidth() ), 0, maxRange, paint);
			}
			else if( number == 3 )
			{
				shootElectricity( canvas, startX, startY, 0, random.nextInt( canvas.getHeight() ), maxRange, paint);
			}
			else if( number == 4 )
			{
				shootElectricity( canvas, startX, startY, random.nextInt( canvas.getWidth() ), canvas.getHeight(), maxRange, paint );
			}
			else if( number == 5 )
			{
				shootElectricity( canvas, startX, startY, canvas.getWidth(), random.nextInt( canvas.getHeight() ), maxRange, paint );
			}
			
			else
			{
				Iterator<Electricity> iterator = this.neighbours.iterator();
				
				while( iterator.hasNext() )
				{
					Electricity neigh = iterator.next();
					shootElectricity( canvas, startX, startY, neigh.positionX, neigh.positionY, (int)( canvas.getHeight() ), paint );
				}
			}
		}
	}
}
