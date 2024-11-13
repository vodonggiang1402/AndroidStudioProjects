package Modules.SettingScreen.Language;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Helper.utils.LanguageUtils;
import Services.Language.Language;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageHolder> {
    private List<Language> mLanguageList;
    private ItemClickListener<Language> mListener;
    private Language mCurrentLanguage = LanguageUtils.getCurrentLanguage();

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Language> list) {
        mLanguageList = list;
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListener<Language> listener) {
        mListener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCurrentLanguage(Language language) {
        mCurrentLanguage = language;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LanguageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item, parent, false);
        return new LanguageHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageHolder holder, int position) {
        Language language = mLanguageList.get(position);
        holder.languageRadioBtn.setText(language.getName());
        holder.languageRadioBtn.setChecked(mCurrentLanguage.getId() == position);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentLanguage(language);
                mListener.onClickItem(language);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mLanguageList != null) {
            return mLanguageList.size();
        }
        return 0;
    }

    public static class LanguageHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final RadioButton languageRadioBtn;
        public LanguageHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.frame_item_language);
            languageRadioBtn = itemView.findViewById(R.id.radio_item_language);
        }
    }
}