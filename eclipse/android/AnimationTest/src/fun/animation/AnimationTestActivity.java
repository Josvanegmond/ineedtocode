package fun.animation;

import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;

public class AnimationTestActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static AssetManager assetManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AnimationTestActivity.assetManager = this.getAssets();
    }
    
    public static AssetManager getAssetManager()
    {
    	return AnimationTestActivity.assetManager;
    }
}