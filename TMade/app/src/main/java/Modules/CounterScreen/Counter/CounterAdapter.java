package Modules.CounterScreen.Counter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

import java.util.List;

import Modules.SymbolScreen.Symbol.SymbolAdapter;
import Modules.SymbolScreen.SymbolDetail.SymbolDetail;
import Services.Counter.CounterModel;
import Services.Symbol.SymbolModel;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.CounterViewHolder> {
    private final Context cContext;
    private List<CounterModel> counters;

    public CounterAdapter(Context cContext) {
        this.cContext = cContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CounterModel> list) {
        this.counters = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_item, parent, false);
        return new CounterAdapter.CounterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterViewHolder holder, int position) {
        CounterModel counterModel = counters.get(position);
        if (counterModel == null) {
            return;
        }

        String counterName =  getStringByIdName(cContext, counterModel.getCountName());
        if (!counterName.isEmpty()) {
            holder.titleTextView.setText(counterName);
        }

        holder.counterTextView.setText(String.valueOf(counterModel.getCount()));

        GradientDrawable backgroundGradient = (GradientDrawable)holder.linearLayout.getBackground();
        backgroundGradient.setStroke(2, Color.parseColor("#"+ counterModel.getColor()));

        holder.imageViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterModel.setCount(counterModel.getCount() - 1);
            }
        });

        holder.imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterModel.setCount(counterModel.getCount() + 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (counters != null) {
            return counters.size();
        }
        return 0;
    }

    public static class CounterViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final LinearLayout linearLayout;
        private final TextView titleTextView;
        private final ImageView imageViewMinus;
        private final TextView counterTextView;
        private final ImageView imageViewPlus;

        public CounterViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.counter_card_view);
            linearLayout = itemView.findViewById(R.id.counter_item_view_layout);
            titleTextView = itemView.findViewById(R.id.counter_item_title);
            imageViewMinus = itemView.findViewById(R.id.img_counter_minus);
            counterTextView = itemView.findViewById(R.id.counter_item_text_view);
            imageViewPlus = itemView.findViewById(R.id.img_counter_plus);
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
