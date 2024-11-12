package Modules.SettingScreen.Language;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.List;

import Data.ItemClickListener;
import Helper.utils.LanguageUtils;
import Services.Language.Language;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageHolder> {
    private final Context sContext;
    private final List<Language> mLanguageList;
    private ItemClickListener<Language> mListener;
    private Language mCurrentLanguage;

    public LanguageAdapter(Context sContext, List<Language> mLanguageList) {
        this.sContext = sContext;
        this.mLanguageList = mLanguageList;
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
        holder.languageSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickItem(position, language);
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
        private final RadioButton languageRadioBtn;
        private final Button languageSaveButton;

        public LanguageHolder(@NonNull View itemView) {
            super(itemView);
            languageRadioBtn = itemView.findViewById(R.id.radio_item_language);
            languageSaveButton = itemView.findViewById(R.id.language_save_button);
        }
    }
}