package haqqi.anim.simplescrollbasedanimation;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    ListView contentListView;
    GestureDetector mDetector;
    FrameLayout rootContainer;
    ImageButton floatButton;

    boolean isHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootContainer = (FrameLayout) findViewById(R.id.root_container);
        contentListView = (ListView) findViewById(R.id.content_list);
        floatButton = (ImageButton) findViewById(R.id.float_btn);

        String[] values = new String[]{
                "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Android Scroll",
                "Android Scrolling Event",
                "Simple Android Animation",
                "OnScroll Animation",
                "List View OnScroll Event",
                "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Android Scroll",
                "Android Scrolling Event",
                "Simple Android Animation",
                "OnScroll Animation",
                "List View OnScroll Event"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        contentListView.setAdapter(adapter);


        View v = this.findViewById(R.id.action_bar_container);
        mDetector = new GestureDetector(this, new ListGestureListener(v));

        contentListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return false;
            }
        });

        contentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        // TODO implement various app



    }

    public void fbClick(View view) {
//        if (isHidden) {
//            view.animate().yBy(-100).scaleY(1.2f).scaleX(1.2f).setInterpolator(new OvershootInterpolator(2));
//        } else {
//            view.animate().y(view.getTop()).scaleY(1f).scaleX(1f).setInterpolator(new OvershootInterpolator(2));
//        }
//        Log.d("fbClick", "floatingButton clicked " + view.getTop() + " : " + view.getY() + " : " + view.getTranslationY());
//        isHidden = !isHidden;
    }

    private class ListGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String LISTENER_TAG = "ListGestureListener";
        private float threshold = 3.0f;
        private View actionbarView;

        ListGestureListener(View v) {
            super();
            this.actionbarView = v;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(LISTENER_TAG, "onDown : " + e.toString());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > threshold) {
                // scrolling down
                floatButton.animate().y(rootContainer.getHeight()).scaleY(0.6f).scaleX(0.6f).rotation(0).setInterpolator(new OvershootInterpolator(0));
                actionbarView.animate().y(-actionbarView.getHeight()).alpha(0).setInterpolator(new OvershootInterpolator(2));
            }
            else if (distanceY < -threshold) {
                floatButton.animate().y(floatButton.getTop()).scaleY(1.2f).scaleX(1.2f).rotation(720).setInterpolator(new OvershootInterpolator(0));
                actionbarView.animate().y(0).alpha(1).setInterpolator(new OvershootInterpolator(2));

            }

            Log.d(LISTENER_TAG, "onScroll(" + distanceX + " ; " + distanceY + ") : " + e1.toString() + e2.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(LISTENER_TAG, "onFling(" + velocityX + " ; " + velocityY + ") : " + e1.toString() + e2.toString());
            return true;
        }


    }
}
