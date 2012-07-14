package edu.game.dungeonmaster.model;

import java.util.ArrayList;

public class Goal {
	
	private String name;
	
	private ArrayList<Task> taskList;
	
	public Goal()
	{
		this.taskList = new ArrayList<Task>();
		this.name = "goal_noname";
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Task addTask( Task task )
	{
		this.taskList.add( task );
		return task;
	}
	
	public ArrayList<Task> getTaskList()
	{
		return this.taskList;
	}

}
