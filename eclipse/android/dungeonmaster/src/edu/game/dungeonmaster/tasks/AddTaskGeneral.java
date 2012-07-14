package edu.game.dungeonmaster.tasks;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import edu.game.dungeonmaster.R;
import edu.game.dungeonmaster.model.Goal;
import edu.game.dungeonmaster.model.Task;
import edu.game.dungeonmaster.statics.ScreendataState;

public class AddTaskGeneral implements OnFocusChangeListener {

	private EditText taskNameEditText;
	private Button addTaskOkButton;
	private Task task;
	private View view;
	
	public AddTaskGeneral( View view, Task task )
	{
		this.taskNameEditText = (EditText) view
				.findViewById(R.id.taskNameEditText);
		taskNameEditText.setText( task.getName() );
		taskNameEditText.setOnFocusChangeListener( this );

		this.view = view;
		this.task = task;
		
		view.setOnFocusChangeListener(this);
		
		//this.addTaskCancelButton = (Button) view.findViewById( R.id.addTaskCancelButton );
		//this.addTaskCancelButton.setOnClickListener( this );
	}
	
	
	@Override
	public void onFocusChange(View arg0, boolean arg1)
	{
		//if( arg0 == taskNameEditText )
		//{
			this.task.setName( taskNameEditText.getText().toString() ); 
		//}
	}
}
