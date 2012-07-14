package fun.electricity;

import java.util.ArrayList;
import java.util.Timer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;


public class PlayfieldSurface extends SurfaceView implements Callback, OnTouchListener
{
	private ArrayList< Electricity > electricityMap;
	
	public PlayfieldSurface( Context context, AttributeSet attrs )
	{
		super(context, attrs);
	    getHolder().addCallback(this);
	    
	    this.setOnTouchListener(this);
	    
	    this.electricityMap = new ArrayList< Electricity >();

	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate( new PlayfieldTimerTask( this ), 800, 20 );
	}
	
	public void draw()
	{
	    Canvas canvas = this.getHolder().lockCanvas();
	    
		canvas.drawARGB(255, 0, 30, 120 );
		
		int size = electricityMap.size();
		for( int pointerNumber = 0; pointerNumber < size; pointerNumber++ )
		{
			Electricity electricity = electricityMap.get( pointerNumber );
			
			if( electricity != null )
			{
				electricity.draw( canvas );
			}
		}
			
	    this.getHolder().unlockCanvasAndPost(canvas);
	}
	
	public void update( MotionEvent event )
	{
		int size = electricityMap.size();
		for( int pointerNumber = 0; pointerNumber < size; pointerNumber++ )
		{
			if( electricityMap.size() > pointerNumber )
			{
				Electricity electricity = electricityMap.get( pointerNumber );
				
				electricity.setX( event.getX( pointerNumber ) );
				electricity.setY( event.getY( pointerNumber ) );
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		draw();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public boolean onTouch( View v, MotionEvent event )
	{
		int action = event.getActionMasked() & MotionEvent.ACTION_MASK;
		
		if( action == MotionEvent.ACTION_POINTER_DOWN || action == MotionEvent.ACTION_DOWN )
		{
			Electricity electricity = new Electricity( event.getX(), event.getY(), 3, 10, electricityMap );
			electricityMap.add( event.getActionIndex(), electricity );
		}
		
		if( action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_UP )
		{
			electricityMap.remove( event.getActionIndex() );
		}
		
		if( action == MotionEvent.ACTION_MOVE )
		{
			update( event );
		}
		
		return true;
	}
}