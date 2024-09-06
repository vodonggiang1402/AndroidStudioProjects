package Modules.CounterScreen.CounterCategory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CounterCategoryViewHolder extends RecyclerView.ViewHolder {
        public CounterCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
