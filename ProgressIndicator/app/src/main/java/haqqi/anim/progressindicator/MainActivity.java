package haqqi.anim.progressindicator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


public class MainActivity extends ActionBarActivity {

    boolean isProgressing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}
