package fun.animation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends SurfaceView implements Callback, OnTouchListener
{
	private SurfaceHolder surfaceView;
	private Bitmap cubemap;
	
	private boolean allowDrawing;
	
	private ArrayList<Cube> cubes;
	
	public DrawView( Context context, AttributeSet attrs )
	{
		super( context, attrs );
		getHolder().addCallback( this );

		Random random = new Random();
		this.cubes = new ArrayList<Cube>();
		for( int i = 0; i < 5; i++ )
		{
			this.cubes.add( new Cube( random.nextInt(300), random.nextInt(800) ) );
		}
		
		this.allowDrawing = false;
		
	    this.setOnTouchListener(this);
	    
	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(
	    	new TimerTask()
	    	{
				@Override
				public void run()
				{
					Canvas canvas = surfaceView.lockCanvas();
					
					if( allowDrawing == true )
					{
						draw( canvas );
					
						Iterator<Cube> iter = cubes.iterator();
						while( iter.hasNext() )
						{
							Cube cube = iter.next();
							cube.updateState();
							cube.draw( canvas, cubemap );
						}
	
						surfaceView.unlockCanvasAndPost( canvas );
					}
				}
	    	}, 1000, 40
	    );
	}
	
	public void draw( Canvas canvas )
	{
		canvas.drawARGB(255, 0, 30, 120 );
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated( SurfaceHolder surfaceView )
	{	
		this.allowDrawing = true;
		this.surfaceView = surfaceView;
		
		try
		{
			this.cubemap = BitmapFactory.decodeStream( AnimationTestActivity.getAssetManager().open("cube.png") );
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("drawview", "exception in bitmap loading");
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		this.allowDrawing = false;	
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			float x = event.getX();
			float y = event.getY();

			Iterator<Cube> iter = cubes.iterator();
			while( iter.hasNext() )
			{
				Cube cube = iter.next();
				
				if( x > cube.getX() && x < cube.getX() + 60 &&
					y > cube.getY() && y < cube.getY() + 60 )
				{
					cube.setState( cube.getState() + 1 );
				}
			}
		}
		
		return true;
	}
	
}
