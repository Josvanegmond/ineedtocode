package fun.electricity;

import java.util.TimerTask;

public class PlayfieldTimerTask extends TimerTask
{
	PlayfieldSurface playfieldSurface;
	
	public PlayfieldTimerTask( PlayfieldSurface playfieldSurface )
	{
		this.playfieldSurface = playfieldSurface;
	}

	@Override
	public void run()
	{
	    playfieldSurface.draw();
	}
}
