package edu.game.dungeonmaster.tasks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import edu.game.dungeonmaster.R;
import edu.game.dungeonmaster.model.Goal;
import edu.game.dungeonmaster.model.Task;
import edu.game.dungeonmaster.statics.ScreendataState;

@SuppressLint("ParserError")
public class AddTask implements OnClickListener
{
	private Activity topActivity;
	
	private Task task;
	
	private Button addTaskOkButton;
	private Button addTaskCancelButton;

	
	public AddTask(Activity topActivity)
	{
		this.topActivity = topActivity;

		this.topActivity.setContentView(R.layout.addtask);
		
		this.task = new Task("task_noname");
		
		this.addTaskOkButton = (Button) topActivity.findViewById( R.id.addTaskOkButton );		
		this.addTaskOkButton.setOnClickListener(this);
		
		this.addTaskCancelButton = (Button) topActivity.findViewById( R.id.addTaskCancelButton );
		this.addTaskCancelButton.setOnClickListener(this);
		
		ViewPager taskPager = (ViewPager) topActivity
				.findViewById(R.id.taskpager);

		taskPager.setAdapter(
				new PagerAdapter()
			{
				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					return 3;
				}
	
				public Object instantiateItem(View collection, int position)
				{
					LayoutInflater inflater = (LayoutInflater) collection
							.getContext().getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
	
					View view = null;
					switch (position) {
					case 0:
	
						view = inflater.inflate(R.layout.addtaskgeneral, null);
						new AddTaskGeneral( view, AddTask.this.task );
						
						break;
					case 1:
						view = inflater.inflate(R.layout.addtaskdeadline, null);
						break;
					case 2:
						view = inflater.inflate(R.layout.addtaskoptions, null);
						break;
					}
	
					((ViewPager) collection).addView(view, 0);
	
					return view;
				}
	
				@Override
				public CharSequence getPageTitle(int position) {
					String name = "unknown";
	
					switch (position) {
					case 0:
						name = "General settings";
						break;
					case 1:
						name = "Deadline setup";
						break;
					case 2:
						name = "Options";
						break;
					}
	
					return name;
				}
	
				@Override
				public void destroyItem(View arg0, int arg1, Object arg2){
					((ViewPager) arg0).removeView((View) arg2);
				}
	
				@Override
				public boolean isViewFromObject(View arg0, Object arg1) {
					return arg0 == ((View) arg1);
				}
			}
		);
	}//end of constructor
	

	@Override
	public void onClick(View v)
	{
		if (v == addTaskOkButton)
		{
			Goal goal = ScreendataState.currentGoal;
			goal.addTask( this.task );

			new ShowGoal(this.topActivity);
		}

		if (v == addTaskCancelButton)
		{
			new ShowGoal(this.topActivity);
		}
	}

}
