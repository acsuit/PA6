package org.acsuit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;



public class MainMenu extends Activity implements android.view.View.OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View mStartButton = findViewById(R.id.startButton);
        mStartButton.setOnClickListener(this);
        View mAboutButton = findViewById(R.id.aboutButton);
        mAboutButton.setOnClickListener(this);
        
    }

public void onClick(View v) {


switch (v.getId())
{
case R.id.startButton:
	Intent x=new Intent(this, GameView.class);
	startActivity(x);
	break;
case R.id.aboutButton:
	Intent y=new Intent(this, About.class);
	startActivity(y);
	break;
}
}
@Override
protected void onResume()
{
super.onResume();
setVolumeControlStream(AudioManager.STREAM_MUSIC);
Music.create(this, R.raw.chinatown);
Music.setLooping(this, R.raw.chinatown);
Music.start(this);
}
@Override
protected void onPause()
{
super.onPause();
Music.stop(this);
}
@Override
protected void onDestroy()
{
super.onDestroy();
Music.stop(this);
}


}
