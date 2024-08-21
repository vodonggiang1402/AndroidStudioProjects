package Modules.SymbolScreen.Symbol;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Services.SymbolModel;

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.SymbolViewHolder> {
    private final Context sContext;
    private List<SymbolModel> symbols;

    public SymbolAdapter(Context sContext) {
        this.sContext = sContext;
    }

    public void setData(List<SymbolModel> list) {
        this.symbols = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SymbolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symbol_item, parent, false);
        return new SymbolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymbolViewHolder holder, int position) {
        SymbolModel symbolModel = symbols.get(position);
        if (symbolModel == null) {
            return;
        }

//        Bitmap bm = null;
//        try {
//            bm = getBitmapFromAsset(symbolModel.getIconName());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        holder.imageView.setImageBitmap(bm);
//        holder.textView.setText(symbolModel.getSymbolName());
    }

    @Override
    public int getItemCount() {
        if (symbols != null) {
            return symbols.size();
        }
        return 0;
    }

    public static class SymbolViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        public SymbolViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_symbol);
            textView = itemView.findViewById(R.id.symbol_title);

        }
    }

    private Bitmap getBitmapFromAsset(String strName) throws IOException {
        AssetManager assetManager = sContext.getAssets();
        InputStream instr = assetManager.open(strName);
        return BitmapFactory.decodeStream(instr);
    }
}
