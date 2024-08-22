package Modules.SymbolScreen.SymbolCategory;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import Modules.SymbolScreen.Symbol.SymbolAdapter;

public class SymbolCategoryAdapter extends RecyclerView.Adapter<SymbolCategoryAdapter.SymbolCategoryViewHolder> {
    private final Context cContext;
    private List<SymbolCategory> listSymbolCategory;

    public SymbolCategoryAdapter(Context cContext) {
        this.cContext = cContext;
    }

    public void setData(List<SymbolCategory> list) {
        this.listSymbolCategory =  list;
    }

    @NonNull
    @Override
    public SymbolCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symbol_category, parent, false);
        return new SymbolCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymbolCategoryViewHolder holder, int position) {
        SymbolCategory symbolCategory = listSymbolCategory.get(position);
        if (symbolCategory == null) {
            return;
        }
        holder.nameSymbolCategory.setText(symbolCategory.getNameCategory());
        holder.imageView.setImageResource(symbolCategory.getIconName());
        GridLayoutManager gridlayoutManager = new GridLayoutManager(this.cContext, 3);
        holder.rcvSymbolCategory.setLayoutManager(gridlayoutManager);

        SymbolAdapter symbolAdapter = new SymbolAdapter(this.cContext);
        symbolAdapter.setData(symbolCategory.getSymbols());
        holder.rcvSymbolCategory.setAdapter(symbolAdapter);
    }

    @Override
    public int getItemCount() {
        if (listSymbolCategory != null) {
            return listSymbolCategory.size();
        }
        return 0;
    }

    public static class SymbolCategoryViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView nameSymbolCategory;
        private final RecyclerView rcvSymbolCategory;

        public SymbolCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_symbol_header);
            nameSymbolCategory = itemView.findViewById(R.id.symbol_category_title);
            rcvSymbolCategory = itemView.findViewById(R.id.rcv_symbol_category);
        }
    }

    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = cContext.getAssets();
        InputStream instr = assetManager.open(strName);
        return BitmapFactory.decodeStream(instr);
    }
}
