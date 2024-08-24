package Modules.SymbolScreen.Symbol;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import Services.SymbolModel;

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.SymbolViewHolder> {
    private final Context sContext;
    private static List<SymbolModel> symbols;

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
        String iconName = symbolModel.getIconName();
        if (!iconName.isEmpty()) {
            Context context = holder.imageView.getContext();
            int id = context.getResources().getIdentifier(iconName, "drawable",
                    context.getPackageName());
            holder.imageView.setImageResource(id);
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        String symbolName =  getStringByIdName(sContext, symbolModel.getSymbolName());
        if (!symbolName.isEmpty()) {
            holder.textView.setText(symbolName);
        }
        holder.linearLayout.setBackgroundColor(Color.parseColor("#"+ symbolModel.getBackgroundColor()));
    }

    @Override
    public int getItemCount() {
        if (symbols != null) {
            return symbols.size();
        }
        return 0;
    }

    public static class SymbolViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;
        private final ImageView imageView;
        private final TextView textView;
        public SymbolViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.symbol_item_linear_layout);
            imageView = itemView.findViewById(R.id.img_symbol);
            textView = itemView.findViewById(R.id.symbol_title);

        }

        public void setClickMethod(int position){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "abc", Toast.LENGTH_LONG).show();
                }
            });

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
