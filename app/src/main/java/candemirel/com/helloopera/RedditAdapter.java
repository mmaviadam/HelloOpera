package candemirel.com.helloopera;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import candemirel.com.helloopera.model.Child;
import candemirel.com.helloopera.model.Entry;

import static java.lang.String.format;

/**
 * Data Adapter for Reddit RecyclerView
 */
class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.RedditViewHolder> {

    private List<Child> mEntries;
    private int mRowLayout;
    private Context mContext;

    RedditAdapter(int rowLayout) {
        this.mRowLayout = rowLayout;
        mEntries = new ArrayList<>();
    }

    public void setEntries(List<Child> mEntries) {
        this.mEntries = mEntries;
    }

    @Override
    public RedditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        view.setBackgroundResource(R.drawable.list_item_background);
        return new RedditAdapter.RedditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RedditAdapter.RedditViewHolder holder, final int position) {
        Entry dat = mEntries.get(position).getData();
        holder.mTitle.setText(dat.getTitle());
        holder.mScore.setText(format("%d", dat.getScore()));
        holder.mSubReddit.setText(dat.getSubreddit());
        holder.mUrl = dat.getUrl();
        holder.mIndex.setText(format("%02d", position + 1));
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    boolean getHastItem() {
        return (this.getItemCount() == 0);
    }

    class RedditViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mLayoutRedditEntry;
        TextView mTitle;
        TextView mScore;
        TextView mSubReddit;
        TextView mIndex;
        String mUrl;

        RedditViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.txt_title);
            mSubReddit = (TextView) v.findViewById(R.id.txt_subreddit);
            mScore = (TextView) v.findViewById(R.id.txt_score);
            mIndex = (TextView) v.findViewById(R.id.txt_index);
            mLayoutRedditEntry = (RelativeLayout) v.findViewById(R.id.layout_redditentry);

            Typeface fntRobotoMed = Typeface.createFromAsset(
                    mContext.getAssets(), "fonts/Roboto-Medium.ttf");
            Typeface fntRobotoCondensedBold = Typeface.createFromAsset(
                    mContext.getAssets(), "fonts/RobotoCondensed-Bold.ttf");
            Typeface fntGothic = Typeface.createFromAsset(
                    mContext.getAssets(), "fonts/Gothic.ttf");

            mTitle.setTypeface(fntRobotoMed);
            mSubReddit.setTypeface(fntRobotoCondensedBold);
            mScore.setTypeface(fntRobotoCondensedBold);
            mIndex.setTypeface(fntGothic);
        }
    }
}
