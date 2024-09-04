package Modules.SettingScreen.SettingView;

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
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolDetail.Step.StepAdapter;
import Services.SymbolModel;

public class SettingApdater extends RecyclerView.Adapter<SettingApdater.SettingViewHolder> {
    private final Context sContext;
    private List<Setting> settings;

    public SettingApdater(Context sContext) {
        this.sContext = sContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Setting> list) {
        this.settings = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_view, parent, false);
        return new SettingApdater.SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        Setting setting = settings.get(position);
        if (setting == null) {
            return;
        }
        holder.textView.setText(setting.getText());
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(setting.getIconName(), 0, 0, 0);
    }

    @Override
    public int getItemCount() {
        if (!settings.isEmpty())
        {
            return settings.size();
        }
        return 0;
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.setting_text_view);
        }
    }

}
