package edu.game.dungeonmaster.statics;

import java.util.ArrayList;

import edu.game.dungeonmaster.model.Goal;

public class GoalFactory {

	private static ArrayList<Goal> goalList = new ArrayList<Goal>();
	
	public static Goal addGoal()
	{
		Goal goal = new Goal();
		goalList.add( goal );
		return goal;
	}
	
	public static ArrayList<Goal> getGoalList()
	{
		return goalList;
	}
}
