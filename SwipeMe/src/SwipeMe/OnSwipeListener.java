package SwipeMe;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



public abstract class OnSwipeListener implements OnTouchListener{

	public final static int SWIPED_LEFT = 1;
	public final static int SWIPED_RIGHT = 2;
	public final static int SWIPED_UP = 3;
	public final static int SWIPED_DOWN = 4;
	public final static int CLICKED = 5;
	private int SWIPE_ACTION = 0;
	private float firstTouchX = 0;
	private float firstTouchY = 0;
	private float lastTouchX =0 ;
	private float lastTouchY=0;
	int activePointerID;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == event.ACTION_DOWN)
		{
			//calculate the first touch position
			final float x = event.getX();
			final float y = event.getY();
	        
			firstTouchX = x;
			firstTouchY = y;
	        // Save the ID of the active finger
			activePointerID = event.getPointerId(0);

		}
		
		if(event.getAction() == event.ACTION_UP)
		{
			//calculate the desplacment in the x axis
			float dX = lastTouchX - firstTouchX;
			//calculate the desplacment in the y axis
			float dY = lastTouchY - firstTouchY;
			
			final int pointerIndex = event.findPointerIndex(activePointerID);
			if(firstTouchX == event.getX(activePointerID) && firstTouchY == event.getY(activePointerID))
			{
				//clicked
				SWIPE_ACTION = CLICKED;
			}
			else
			{
				//Check if the movement is horizontally or vertically
				if(Math.abs(dX) > Math.abs(dY))
				{
					// Swiped horizontally
					if(dX > 0)
					{
						//Swiped Right
						SWIPE_ACTION = SWIPED_RIGHT;
					}
					else
					{
						//Swiped Left
						SWIPE_ACTION = SWIPED_LEFT;
					}
				}
				else
				{
					// Swiped vertically
					if(dY > 0)
					{
						// Swiped Down
						SWIPE_ACTION = SWIPED_DOWN;
					}
					else
					{
						// Swiped Up
						SWIPE_ACTION = SWIPED_UP;
					}
				}
			}
			
			//call on Swipe which will be overridden
			onSwipe(v , event);
		}
		if(event.getAction() == event.ACTION_MOVE)
		{
			 final int pointerIndex = event.findPointerIndex(activePointerID);
			 //while moving calculate the last touch
		        final float x = event.getX(pointerIndex);
		        final float y = event.getY(pointerIndex);
		        
		        lastTouchX = x;
		        lastTouchY = y;

		}
		
		return true;
	}

	/*
	 * Returns the direction which the view is swiped to
	 */
	public  int getSwipeTo()
	{
		
		return SWIPE_ACTION;
	}
	
	/*
	 * Will be called after the user leave the View object
	 */
	abstract public void onSwipe(View v, MotionEvent event);

	
}
