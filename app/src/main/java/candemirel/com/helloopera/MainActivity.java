package candemirel.com.helloopera;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.List;

import candemirel.com.helloopera.model.Child;
import candemirel.com.helloopera.model.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MainActivity for app
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RedditAdapter mRedditAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private RedditApi mApiService;
    private FloatingActionButton mReloadButton;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.entries_recycler_view);
        mRedditAdapter = new RedditAdapter(R.layout.list_item_reddit);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mRedditAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mReloadButton = (FloatingActionButton) findViewById(R.id.fab_reload_button);

        mApiService = RedditAPIFactory.createAPI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        Typeface fntRockoFLF = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/RockoFLF.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(fntRockoFLF);
        collapsingToolbar.setExpandedTitleTypeface(fntRockoFLF);

        setReloadButton();
        getRedditPosts();
    }

    private void setReloadButton() {
        final Animation anim_reloading = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_reloading);
        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReloadButton.setClickable(false);
                mReloadButton.startAnimation(anim_reloading);
                getRedditPosts();
            }
        });
    }

    private void finalizeGetting() {
        mLinearLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
        playReloadedAnim();
        mReloadButton.setClickable(true);
    }

    private void getRedditPosts() {
        Call<ResponseBody> call = mApiService.getRedditItems(RedditAPIFactory.POST_COUNT);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();
                if (statusCode != 200) {
                    Log.e(TAG, "Http status: " + statusCode);
                    ShowToast(R.string.error_try_again);
                } else {
                    List<Child> entries = response.body().getData().getChildren();
                    boolean isFirst = mRedditAdapter.getHastItem();
                    mRedditAdapter.setEntries(entries);
                    mRedditAdapter.notifyDataSetChanged();
                    if (!isFirst)
                        ShowToast(R.string.entries_reloaded);
                }
                finalizeGetting();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
                ShowToast(R.string.error_cannot_connect_server);
                finalizeGetting();
            }
        });
    }

    private void playReloadedAnim() {
        final Animation anim_reloaded;
        anim_reloaded = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_reloaded);

        anim_reloaded.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mReloadButton.clearAnimation();
            }
        });
        mReloadButton.startAnimation(anim_reloaded);
    }

    public void ShowToast(int message) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

}
