package Modules.SymbolScreen.SymbolDetail.Step;

import android.content.Context;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Modules.SymbolScreen.Symbol.SymbolAdapter;
import Services.SymbolModel;
import Services.SymbolStep;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private final Context sContext;
    private List<SymbolStep> steps;

    public StepAdapter(Context context) {
        this.sContext = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SymbolStep> list) {
        this.steps = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_view, parent, false);
        return new StepAdapter.StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {

        SymbolStep symbolStep = steps.get(position);
        String symbolStepName = sContext.getResources().getString(R.string.step_text) + " " + String.valueOf(position + 1) + ": " + getStringByIdName(sContext, symbolStep.getContent());
        holder.textView.setText(symbolStepName);

        String iconName = symbolStep.getImageName();
        if (!iconName.isEmpty()) {
            Context context = holder.imageView.getContext();
            int id = context.getResources().getIdentifier(iconName, "drawable",
                    context.getPackageName());
            if (id > 0) {
                holder.imageView.setImageResource(id);
            } else {
                holder.imageView.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (steps != null) {
            return steps.size();
        }
        return 0;
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.step_title);
            imageView = itemView.findViewById(R.id.img_step);
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
