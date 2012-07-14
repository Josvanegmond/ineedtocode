package edu.game.dungeonmaster.tasks;

import android.app.Activity;
import edu.game.dungeonmaster.statics.GoalFactory;

public class CreateGoal extends ShowGoal
{
	public CreateGoal( Activity topActivity )
	{
		super( topActivity, GoalFactory.addGoal() );
	}
}
