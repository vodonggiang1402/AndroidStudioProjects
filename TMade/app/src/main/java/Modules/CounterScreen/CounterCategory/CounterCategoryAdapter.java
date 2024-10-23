package Modules.CounterScreen.CounterCategory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Modules.CounterScreen.Counter.CounterAdapter;
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.UpdateView.UpdateViewAdapter;

public class CounterCategoryAdapter extends RecyclerView.Adapter<CounterCategoryAdapter.CounterCategoryViewHolder> {

    private final Context cContext;
    private List<CounterCategory> listCounterCategory;

    public CounterCategoryAdapter(Context cContext) {
        this.cContext = cContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CounterCategory> list) {
        this.listCounterCategory =  list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounterCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_category, parent, false);
        return new CounterCategoryAdapter.CounterCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterCategoryViewHolder holder, int position) {
        CounterCategory counterCategory = listCounterCategory.get(position);

        if (counterCategory == null) {
            return;
        }
        holder.nameCounterCategory.setText(counterCategory.getCounterCategoryName());
        holder.nameCounterCategory.setCompoundDrawablesWithIntrinsicBounds(counterCategory.getIconName(), 0, 0, 0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.cContext, RecyclerView.VERTICAL,false);
        holder.rcvCounterCategory.setLayoutManager(linearLayoutManager);
        CounterAdapter counterAdapter  = new CounterAdapter(this.cContext);
        counterAdapter.setData(counterCategory.getCounters());
        holder.rcvCounterCategory.setAdapter(counterAdapter);
    }

    @Override
    public int getItemCount() {
        if (listCounterCategory != null) {
            return listCounterCategory.size();
        }
        return 0;
    }

    public static class CounterCategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameCounterCategory;
        private final RecyclerView rcvCounterCategory;

        public CounterCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCounterCategory = itemView.findViewById(R.id.counter_category_title);
            rcvCounterCategory = itemView.findViewById(R.id.rcv_counter_category);
        }
    }
}
