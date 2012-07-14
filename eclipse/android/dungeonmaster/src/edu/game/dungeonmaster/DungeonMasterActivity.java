package edu.game.dungeonmaster;

import edu.game.dungeonmaster.tasks.Main;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class DungeonMasterActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new Main( this );
    }
}