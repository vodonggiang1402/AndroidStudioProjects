package Modules.SettingScreen.SettingView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Modules.SettingScreen.Contact.ContactScreen;
import Modules.SettingScreen.Language.LanguageScreen;
import Modules.SymbolScreen.SymbolDetail.SymbolDetail;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private final Context sContext;
    private List<Setting> settings;

    public SettingAdapter(Context sContext) {
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
        return new SettingAdapter.SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        Setting setting = settings.get(position);
        if (setting == null) {
            return;
        }
        holder.textView.setText(setting.getText());
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(setting.getIconName(), 0, 0, 0);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (setting.getCurrentIndex()) {
                    case 0:
                        Log.i("Language", "Language");
                        Intent intent0 = new Intent(sContext, LanguageScreen.class);
                        sContext.startActivity(intent0);
                        break;
                    case 1:
                        Log.i("Contact", "Contact");
                        Intent intent1 = new Intent(sContext, ContactScreen.class);
                        sContext.startActivity(intent1);
                        break;
                    case 2:
                        Log.i("Share", "Share");
                        break;
                    case 3:
                        Log.i("Rate", "Rate");
                        break;
                    case 4:
                        Log.i("Version", "Version");
                        break;
                    default:
                        break;
                }
            }
        });
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
        private final LinearLayout linearLayout;
        private final TextView textView;
        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.setting_text_view);
            linearLayout = itemView.findViewById(R.id.setting_view_layout);
        }
    }

}
