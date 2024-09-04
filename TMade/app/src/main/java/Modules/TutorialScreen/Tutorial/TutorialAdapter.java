package Modules.TutorialScreen.Tutorial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;

import Modules.SymbolScreen.SymbolDetail.SymbolDetail;
import Modules.TutorialScreen.TutorialDetail.TutorialDetailScreen;
import Services.Tutorial.TutorialModel;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder> {

    private final Context tContext;
    private ArrayList<TutorialModel> tutorials;

    public TutorialAdapter(Context tContext) {
        this.tContext = tContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<TutorialModel> list) {
        this.tutorials = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_item, parent, false);
        return new TutorialAdapter.TutorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        TutorialModel tutorialModel = tutorials.get(position);
        if (tutorialModel == null) {
            return;
        }

        String iconName = tutorialModel.getTutorialImage();
        if (!iconName.isEmpty()) {
            Context context = holder.imageView.getContext();
            int id = context.getResources().getIdentifier(iconName, "drawable",
                    context.getPackageName());
            holder.imageView.setImageResource(id);
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        String tutorialName =  getStringByIdName(tContext, tutorialModel.getTutorialName());
        if (!tutorialName.isEmpty()) {
            holder.textView.setText(tutorialName);
        }

        holder.linearLayout.setBackgroundColor(Color.parseColor("#"+ tutorialModel.getColorString()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tContext, TutorialDetailScreen.class);
                intent.putExtra("TutorialModel", tutorialModel);
                tContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tutorials != null) {
            return tutorials.size();
        }
        return 0;
    }

    public static class TutorialViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final LinearLayout linearLayout;
        private final ImageView imageView;
        private final TextView textView;

        public TutorialViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.tutorial_card_view);
            linearLayout = itemView.findViewById(R.id.tutorial_item_linear_layout);
            imageView = itemView.findViewById(R.id.tutorial_image_view);
            textView = itemView.findViewById(R.id.tutorial_text_view);
        }
    }

    public static String getStringByIdName(Context context, String idName) {
        String resuls = "";
        Resources res = context.getResources();
        int resId = res.getIdentifier(idName, "string", context.getPackageName());
        if (resId > 0) {
            String resString = res.getString(resId);
            if (!resString.isEmpty()) {
                resuls = resString;
            }
        }
        return resuls;
    }
}
