package Modules.TutorialScreen.TutorialDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.tmadecrochet.tmade.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Services.Tutorial.TutorialItem;

public class TutorialDetailAdapter extends RecyclerView.Adapter<TutorialDetailAdapter.TutorialDetailViewHolder> {

    private final Context tcontext;
    private final Lifecycle lifecycle;
    private List<TutorialItem> items;

    public TutorialDetailAdapter(Context tcontext, Lifecycle lifecycle) {
        this.tcontext = tcontext;
        this.lifecycle = lifecycle;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<TutorialItem> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TutorialDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_detail_item, parent, false);
        return new TutorialDetailAdapter.TutorialDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialDetailViewHolder holder, int position) {
        TutorialItem tutorialItem = items.get(position);
        if (tutorialItem == null) {
            return;
        }

        String tutorialTitleName =  tutorialItem.getItemName();
        if (!tutorialTitleName.isEmpty()) {
            holder.titleTextView.setText(tutorialTitleName);
        }

        String tutorialDesName =  tutorialItem.getItemDes();
        if (!tutorialDesName.isEmpty()) {
            holder.desTextView.setText(tutorialDesName);
        }

        String videoId =  tutorialItem.getItemUrl();
        if (!videoId.isEmpty()) {
            lifecycle.addObserver(holder.youTubePlayerView);
            holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (!items.isEmpty()) {
            return items.size();
        }
        return 0;
    }

    public static class TutorialDetailViewHolder extends RecyclerView.ViewHolder {
        private final YouTubePlayerView youTubePlayerView;
        private final TextView titleTextView;
        private final TextView desTextView;

        public TutorialDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            youTubePlayerView = itemView.findViewById(R.id.tutorial_detail_youtube_player_view);
            titleTextView = itemView.findViewById(R.id.tutorial_detail_title_text_view);
            desTextView = itemView.findViewById(R.id.tutorial_detail_des_text_view);
        }
    }
}
