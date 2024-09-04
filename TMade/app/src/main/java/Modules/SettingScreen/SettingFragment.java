package Modules.SettingScreen;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;

import Helper.SharedPrefHelper;
import Modules.SettingScreen.SettingView.Setting;
import Modules.SettingScreen.SettingView.SettingApdater;
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Services.SymbolModel;
import Services.SymbolResponse;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.setting_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.setTitle(null);
        }

        final FragmentActivity c = getActivity();
        RecyclerView rcvSettingView = (RecyclerView) view.findViewById(R.id.rcv_setting_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvSettingView.setLayoutManager(layoutManager);

        SettingApdater settingApdater = new SettingApdater(this.getContext());

        rcvSettingView.setItemAnimator(new DefaultItemAnimator());

        settingApdater.setData(getListSymbolCategory());
        rcvSettingView.setAdapter(settingApdater);

        return view;
    }

    private ArrayList<Setting> getListSymbolCategory() {
        ArrayList<Setting> settings  = new ArrayList<>();
        settings.add(new Setting(R.drawable.ico_setting_language, this.getString(R.string.language_text)));
        settings.add(new Setting(R.drawable.ico_setting_create_contact, this.getString(R.string.contact_text)));
        settings.add(new Setting(R.drawable.ico_setting_share, this.getString(R.string.share_text)));
        settings.add(new Setting(R.drawable.ico_setting_rate, this.getString(R.string.rate_text)));
        settings.add(new Setting(R.drawable.ico_app_version, this.getString(R.string.app_version_text)));
        return  settings;
    }
}