package haqqi.anim.transitionsample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Scene mAScene;
    Scene mAnotherScene;
    ViewGroup mSceneRoot;
    ChangeBounds changeBounds;
    boolean toggle = true;
    TextView titles;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = (TextView) findViewById(R.id.titles);


// Create the scene root for the scenes in this app
        mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

// Create the scenes
        mAScene = Scene.getSceneForLayout(mSceneRoot, R.layout.a_scene, this);
        mAnotherScene =
                Scene.getSceneForLayout(mSceneRoot, R.layout.b_scene, this);

        changeBounds = new ChangeBounds();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            mAnotherScene.enter();
            if (toggle) {
                TransitionManager.go(mAnotherScene, changeBounds);
                titles.setText("Content Layout : b_scene");
            }
            else {
                TransitionManager.go(mAScene, changeBounds);
                titles.setText("Content Layout : a_scene");
            }

            toggle = !toggle;

            Log.d("TOGGLE", toggle +"");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
