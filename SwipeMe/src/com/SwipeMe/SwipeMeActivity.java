package com.SwipeMe;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
// importing the onSwipeListener
import SwipeMe.OnSwipeListener;

public class SwipeMeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button test = (Button) findViewById(R.id.button1);
        
        //adding onTouch action to test button
        //but by sending it the on swipe listener
        test.setOnTouchListener(new OnSwipeListener() {
			
			@Override
			public void onSwipe(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				//checking the direction of the swipe
				switch(getSwipeTo())
				{
				case SWIPED_LEFT :
					//swiped to left
					Toast.makeText(getApplicationContext(), "Swiped Left", Toast.LENGTH_SHORT).show();
					break;
				case SWIPED_RIGHT :
					//swiped to right
					Toast.makeText(getApplicationContext(), "Swiped Right", Toast.LENGTH_SHORT).show();
					break;
				case SWIPED_UP :
					//swiped up
					Toast.makeText(getApplicationContext(), "Swiped Up", Toast.LENGTH_SHORT).show();
					break;
				case SWIPED_DOWN :
					//swiped down
					Toast.makeText(getApplicationContext(), "Swiped Down", Toast.LENGTH_SHORT).show();
					break;
				default:
					//clicked
					Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
    }
}