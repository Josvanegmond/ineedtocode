package edu.game.dungeonmaster.tasks;

import edu.game.dungeonmaster.R;
import edu.game.dungeonmaster.R.id;
import edu.game.dungeonmaster.R.layout;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main implements OnClickListener {

	private Activity topActivity;
	
	public Main( Activity topActivity )
	{
		this.topActivity = topActivity;
		
		this.topActivity.setContentView( R.layout.main );
		
        Button createGoalButton = (Button) this.topActivity.findViewById( R.id.createGoalButton );
        createGoalButton.setOnClickListener( this );
        
        Button exitButton = (Button) this.topActivity.findViewById( R.id.exitButton );
        exitButton.setOnClickListener( this );
	}
	
	@Override
	public void onClick( View arg0 )
	{
		// TODO Auto-generated method stub
		if( arg0 == this.topActivity.findViewById( R.id.createGoalButton ) )
		{
			new CreateGoal( this.topActivity );
		}
		
		if( arg0 == this.topActivity.findViewById( R.id.exitButton ) )
		{
		}
	}

}
