package Modules.CounterScreen.Counter;

import android.annotation.SuppressLint;
import android.content.Context;
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

    }

    @Override
    public int getItemCount() {
        if (counters != null) {
            return counters.size();
        }
        return 0;
    }

    public static class CounterViewHolder extends RecyclerView.ViewHolder {
        public CounterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
