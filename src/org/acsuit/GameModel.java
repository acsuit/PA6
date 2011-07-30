package org.acsuit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;
import android.util.Log;

public class GameModel  {

	static HashMap<String, String> statemap = new HashMap<String, String>();
	static LinkedList<String> states = new LinkedList<String>();
	static LinkedList<String> capitals = new LinkedList<String>();
	static LinkedList<String> playList = new LinkedList<String>();
	Context context;
	public GameModel(Context context) {		
		this.context = context;
		InputStream is = context.getResources().openRawResource(R.raw.statecapitals);
		Scanner input = new Scanner(is);
		read(input);
		Collections.shuffle(states);
		for(int i=0; i< 3; i++){
			playList.add(states.get(i));
			playList.add(statemap.get(states.get(i)));
		}
		
		Log.d("PLAYLIST", playList.toString());
	}
	

	public static void read(Scanner input){
		while(input.hasNextLine()){ //reads each line of data
			String statecapital = input.nextLine();
			processLine(statecapital);
		}
	}
	public static void processLine(String text){
		String state = "", capital = "";
		String[] pair = text.split(" - ");
		state = pair[0];
		capital = pair[1];
		states.add(state);
		capitals.add(capital);
		statemap.put(state, capital);
	}
	




}


