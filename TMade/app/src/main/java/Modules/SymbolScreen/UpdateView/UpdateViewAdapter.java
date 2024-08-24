package Modules.SymbolScreen.UpdateView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

public class UpdateViewAdapter extends RecyclerView.Adapter<UpdateViewAdapter.UpdateViewHolder> {
    private final Context uContext;

    public UpdateViewAdapter(Context uContext) {
        this.uContext = uContext;
    }

    @NonNull
    @Override
    public UpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_view, parent, false);
        return new UpdateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class UpdateViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;
        public UpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.symbol_item_linear_layout);
        }
    }
}
