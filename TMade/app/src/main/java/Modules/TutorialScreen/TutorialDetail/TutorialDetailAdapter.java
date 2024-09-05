package Modules.TutorialScreen.TutorialDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.tmadecrochet.tmade.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modules.TutorialScreen.Tutorial.TutorialAdapter;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolStep;
import Services.Tutorial.TutorialItem;

public class TutorialDetailAdapter extends RecyclerView.Adapter<TutorialDetailAdapter.TutorialDetailViewHolder> {

    private final Context tcontext;
    private List<TutorialItem> items;

    public TutorialDetailAdapter(Context context) {
        this.tcontext = context;
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

        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = extractYTId(tutorialItem.getItemUrl());
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        String tutorialTitleName =  tutorialItem.getItemName();
        if (!tutorialTitleName.isEmpty()) {
            holder.titleTextView.setText(tutorialTitleName);
        }

        String tutorialDesName =  tutorialItem.getItemDes();
        if (!tutorialDesName.isEmpty()) {
            holder.desTextView.setText(tutorialDesName);
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

    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()){
            vId = matcher.group(1);
        }
        return vId;
    }
}
