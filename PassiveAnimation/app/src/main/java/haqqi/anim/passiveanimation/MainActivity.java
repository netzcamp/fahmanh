package haqqi.anim.passiveanimation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.makeramen.roundedimageview.RoundedImageView;


public class MainActivity extends ActionBarActivity {

    ImageButton menu_btn, like_btn, close_btn, appr_btn;
    FrameLayout f_container;


    boolean drawn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f_container = (FrameLayout) findViewById(R.id.f_container);
        menu_btn = (ImageButton) findViewById(R.id.menu_btn);
        appr_btn = (ImageButton) findViewById(R.id.appr_btn);
        close_btn = (ImageButton) findViewById(R.id.close_btn);
        like_btn = (ImageButton) findViewById(R.id.like_btn);

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawn) {
                    like_btn.setVisibility(View.VISIBLE);
                    close_btn.setVisibility(View.VISIBLE);
                    appr_btn.setVisibility(View.VISIBLE);
                    like_btn.animate().y(like_btn.getTop() - like_btn.getWidth()).rotation(360).alpha(1).setInterpolator(new OvershootInterpolator(2));
                    close_btn.animate().x(close_btn.getLeft() - close_btn.getWidth()).rotation(360).alpha(1).setInterpolator(new OvershootInterpolator(2));
                    appr_btn.animate().x(appr_btn.getLeft() + appr_btn.getWidth()).rotation(360).alpha(1).setInterpolator(new OvershootInterpolator(2));
                } else {
                    like_btn.animate().y(like_btn.getTop()).rotation(0).alpha(0).setInterpolator(new OvershootInterpolator(2));
                    close_btn.animate().x(close_btn.getLeft()).rotation(0).alpha(0).setInterpolator(new OvershootInterpolator(2));
                    appr_btn.animate().x(appr_btn.getLeft()).rotation(0).alpha(0).setInterpolator(new OvershootInterpolator(2)).withEndAction(
                            new Runnable() {
                                @Override
                                public void run() {

                                    like_btn.setVisibility(View.GONE);
                                    close_btn.setVisibility(View.GONE);
                                    appr_btn.setVisibility(View.GONE);

                                }
                            }

                    );

                }

                drawn = !drawn;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logMsg(View view) {
        Log.d("log", view.getId() + "");
    }
}
