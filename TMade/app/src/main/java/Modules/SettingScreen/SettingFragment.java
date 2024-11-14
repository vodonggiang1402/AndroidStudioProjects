package Modules.SettingScreen;

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

import Helper.utils.LanguageUtils;
import Modules.SettingScreen.SettingView.Setting;
import Modules.SettingScreen.SettingView.SettingAdapter;

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

        SettingAdapter settingAdapter = new SettingAdapter(this.getContext());

        rcvSettingView.setItemAnimator(new DefaultItemAnimator());

        settingAdapter.setData(getListSymbolCategory());
        rcvSettingView.setAdapter(settingAdapter);

        return view;
    }

    private ArrayList<Setting> getListSymbolCategory() {
        ArrayList<Setting> settings  = new ArrayList<>();
        settings.add(new Setting(0, R.drawable.ico_setting_language, LanguageUtils.getLocaleStringResource(R.string.language_text, getContext())));
        settings.add(new Setting(1, R.drawable.ico_setting_create_contact, LanguageUtils.getLocaleStringResource(R.string.contact_text, getContext())));
        settings.add(new Setting(2, R.drawable.ico_setting_share, LanguageUtils.getLocaleStringResource(R.string.share_text, getContext())));
        settings.add(new Setting(3, R.drawable.ico_setting_rate, LanguageUtils.getLocaleStringResource(R.string.rate_text, getContext())));
        settings.add(new Setting(4, R.drawable.ico_app_version, LanguageUtils.getLocaleStringResource(R.string.app_version_text, getContext())));
        return  settings;
    }
}