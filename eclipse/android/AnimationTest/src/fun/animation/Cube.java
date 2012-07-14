package fun.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Cube
{	
	private int cubestate;
	
	private static final int CUBE_START = 0;
	private static final int CUBE_RUN = 1;
	private static final int CUBE_END = 2;
	
	private int x;
	private int y;
	
	private int counter;
	
	public Cube( int x, int y )
	{
		cubestate = CUBE_END;
		
		this.counter = 0;
		
		this.x = x;
		this.y = y;
	}
	
	public int getCounter()
	{
		return this.counter;
	}
	
	public void setX( int x )
	{
		this.x = x;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public void setY( int y )
	{
		this.y = y;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setState( int state )
	{
		this.cubestate = state;
		this.cubestate %= 3;
	}
	
	public int getState()
	{
		return this.cubestate;
	}
	

	public void draw( Canvas canvas, Bitmap cubemap )
	{
		int x = ( getCounter() * 60 ) % 300;
		int y = (int)( getCounter() / 5 * 60 ) % 360;
		
		Bitmap cubepart = Bitmap.createBitmap( cubemap, x, y, 60, 60 );
		Paint paint = new Paint();
		canvas.drawBitmap( cubepart, getX(), getY(), paint );
	}

	

	public void updateState()
	{
		counter++;
		
		if( cubestate == CUBE_START )
		{
			counter = 0;
			cubestate = CUBE_RUN;
		}
		
		if( cubestate == CUBE_RUN )
		{
			if( counter > 16 )
			{
				counter = 11;
			}
		}
		
		if( cubestate == CUBE_END )
		{
			if( counter > 30 )
			{
				counter = 30;
			}
		}
	}
	
}
