package edu.game.dungeonmaster.tasks;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import edu.game.dungeonmaster.R;
import edu.game.dungeonmaster.model.Goal;
import edu.game.dungeonmaster.model.Task;
import edu.game.dungeonmaster.statics.ScreendataState;

public class ShowGoal implements OnClickListener
{
	private Activity topActivity;
	private Goal goal;
	
	private EditText goalName;
	
	public ShowGoal( Activity topActivity, Goal goal )
	{
		this.topActivity = topActivity;
		this.goal = goal;
		ScreendataState.currentGoal = goal;
		
		this.showGoalScreen();
        this.fillGoalTaskList();
	}
	
	public ShowGoal( Activity topActivity )
	{
		this.topActivity = topActivity;
        this.goal = ScreendataState.currentGoal;
        this.showGoalScreen();
        this.fillGoalTaskList();
	}
	
	
	private void showGoalScreen()
	{
		this.topActivity.setContentView( R.layout.creategoal );
		
		this.goalName = (EditText) this.topActivity.findViewById( R.id.goalNameEditText );
		this.goalName.setText( this.goal.getName() );
		
        Button createGoalCancel = (Button) this.topActivity.findViewById( R.id.createGoalOkButton );
        createGoalCancel.setOnClickListener( this );
        
        Button addWeekButton = (Button) this.topActivity.findViewById( R.id.addTaskButton );
        addWeekButton.setOnClickListener( this );
        
	}
    
	private void fillGoalTaskList()
	{
        //fill list with this goal's tasks
		ArrayList<Task> taskList = goal.getTaskList();
		Iterator<Task> iter = taskList.iterator();

		ArrayList<String> taskNameList = new ArrayList<String>();
		
		while( iter.hasNext() )
		{
			taskNameList.add( iter.next().getName() );
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( topActivity, android.R.layout.simple_list_item_2, android.R.id.text2, taskNameList );
		
		ListView listView = (ListView) topActivity.findViewById( R.id.taskListView );
		listView.setAdapter( adapter );
	}
	
	
	@Override
	public void onClick( View arg0 )
	{
		if( arg0 == this.topActivity.findViewById( R.id.createGoalOkButton ) )
		{
			this.goal.setName( this.goalName.getText().toString() );
			new Main( this.topActivity );
		}
		
		if( arg0 == this.topActivity.findViewById( R.id.addTaskButton ) )
		{
			this.goal.setName( this.goalName.getText().toString() );
			new AddTask( this.topActivity );
		}
	}

}
