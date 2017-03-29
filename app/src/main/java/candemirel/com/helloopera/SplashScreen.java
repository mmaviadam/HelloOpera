package candemirel.com.helloopera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Splach Screen Activity for app
 */
public class SplashScreen extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
