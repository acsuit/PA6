package org.acsuit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//this is where the game happens
public class GameView extends Activity implements OnClickListener
{
private Button buttonA1;
private Button buttonA2;

private Button buttonB1;
private Button buttonB2;

private Button buttonC1;
private Button buttonC2;

private Button pick1;
private Button pick2;



private boolean isFirstCard = true;
private int turn=1;

private GameModel model;
Context context;

public void onCreate(Bundle savedInstanceState) {
	
super.onCreate(savedInstanceState);
setContentView(R.layout.concentration);
//View mStartButton = findViewById(R.id.startButton);
//mStartButton.setOnClickListener(this);
context = getApplicationContext();
model = new GameModel(context);
buttonA1= (Button) findViewById(R.id.button1);
buttonA1.setOnClickListener(this);
buttonA2= (Button) findViewById(R.id.button4);
buttonA2.setOnClickListener(this);

buttonB1= (Button) findViewById(R.id.button2);
buttonB1.setOnClickListener(this);
buttonB2= (Button) findViewById(R.id.button5);
buttonB2.setOnClickListener(this);

buttonC1= (Button) findViewById(R.id.button3);
buttonC1.setOnClickListener(this);
buttonC2= (Button) findViewById(R.id.button6);
buttonC2.setOnClickListener(this);


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


public void onClick(View v) {

switch (v.getId()){
	case R.id.button1:
		buttonA1.setText(GameModel.playList.get(0));
		picker(buttonA1);
		break;
	case R.id.button2:
		buttonB1.setText(GameModel.playList.get(1));
		picker(buttonB1);
		break;
	case R.id.button3:
		buttonC1.setText(GameModel.playList.get(2));
		picker(buttonC1);
		break;
	case R.id.button4:
		buttonA2.setText(GameModel.playList.get(3));
		picker(buttonA2);
		break;
	case R.id.button5:
		buttonB2.setText(GameModel.playList.get(4));
		picker(buttonB2);
		break;
	case R.id.button6:
		buttonC2.setText(GameModel.playList.get(5));
		picker(buttonC2);
		break;
		
	}
}

private void picker(Button pick){
	if(isFirstCard)
	{
		pick1=pick;
		isFirstCard = false;
	}
	else
	{
		pick2=pick;
		compareCard(pick1,pick2);
		turn++;
	}
}
private void compareCard(Button pick1, Button pick2) {
	int duration = Toast.LENGTH_SHORT;

//gets if the 2 cards match

	if(GameModel.statemap.get(pick1.getText())==pick2.getText() || 
		GameModel.statemap.get(pick2.getText())==pick1.getText()){

		
		pick1.setVisibility(Button.GONE);
		pick2.setVisibility(Button.GONE);
		Toast toast = Toast.makeText(context, ""+pick1.getText() + " and " + pick2.getText() + " match up!",duration);
		toast.show();
		isFirstCard = true;
	}
	else{
	
		Toast toast = Toast.makeText(context, ""+pick1.getText() + " and " + pick2.getText() + " don't match. ",duration);
		toast.show();
		pick1.setText("");
		pick2.setText("");
		isFirstCard = true;
}
}


}
