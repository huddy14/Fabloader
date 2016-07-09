package jendrzyca.piotr.fabloader.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huddy on 7/9/16.
 */
public class RecyclerViewListener implements RecyclerView.OnItemTouchListener {

    public interface SongItemEventListener
    {
        void onTouch(int position);
    }
    private GestureDetector gestureDetector;
    private SongItemEventListener listener;

    public RecyclerViewListener(Context context, SongItemEventListener listener)
    {
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.listener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if(childView!= null && listener != null && gestureDetector.onTouchEvent(e))
            listener.onTouch(rv.getChildAdapterPosition(childView));
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
