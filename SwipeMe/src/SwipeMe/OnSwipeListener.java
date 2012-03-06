package SwipeMe;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



public abstract class OnSwipeListener implements OnTouchListener{

	public final static int SWIPED_LEFT = 1;
	public final static int SWIPED_RIGHT = 2;
	public final static int SWIPED_UP = 3;
	public final static int SWIPED_DOWN = 4;
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
			final float x = event.getX();
			final float y = event.getY();
	        
			firstTouchX = x;
			firstTouchY = y;
	        // Save the ID of this pointer
			activePointerID = event.getPointerId(0);

		}
		
		if(event.getAction() == event.ACTION_UP)
		{
			float dx = lastTouchX - firstTouchX;
			float dy = lastTouchY - firstTouchY;
			if(Math.abs(dx) > Math.abs(dy))
			{
				if(dx > 0)
				{
					SWIPE_ACTION = SWIPED_RIGHT;
				}
				else
				{
					SWIPE_ACTION = SWIPED_LEFT;
				}
			}
			else
			{
				if(dy > 0)
				{
					SWIPE_ACTION = SWIPED_UP;
				}
				else
				{
					SWIPE_ACTION = SWIPED_DOWN;
				}
			}
			onSwipe(v , event);
		}
		if(event.getAction() == event.ACTION_MOVE)
		{
			 final int pointerIndex = event.findPointerIndex(activePointerID);
		        final float x = event.getX(pointerIndex);
		        final float y = event.getY(pointerIndex);
		        
		        lastTouchX = x;
		        lastTouchY = y;

		}
		
		return true;
	}

	public  int getSwipeTo()
	{
		return SWIPE_ACTION;
	}
	
	abstract public void onSwipe(View v, MotionEvent event);

	
}
