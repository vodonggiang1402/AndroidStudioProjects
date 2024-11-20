package Modules.SettingScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.tmadecrochet.tmade.R;

import java.util.ArrayList;

import Helper.utils.LanguageUtils;
import Modules.SettingScreen.Contact.ContactScreen;
import Modules.SettingScreen.Language.LanguageScreen;
import Modules.SettingScreen.SettingView.Setting;
import Modules.SettingScreen.SettingView.SettingAdapter;
import Modules.SettingScreen.SettingView.SettingItemClickListener;

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

        TextView textView = (TextView)view.findViewById(R.id.setting_toolbar_title);
        textView.setText(LanguageUtils.getLocaleStringResource(R.string.tab_setting_title, getContext()));

        final FragmentActivity c = getActivity();
        RecyclerView rcvSettingView = (RecyclerView) view.findViewById(R.id.rcv_setting_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvSettingView.setLayoutManager(layoutManager);

        SettingAdapter settingAdapter = new SettingAdapter(this.getContext());

        rcvSettingView.setItemAnimator(new DefaultItemAnimator());

        settingAdapter.setData(getListSymbolCategory());
        rcvSettingView.setAdapter(settingAdapter);

        settingAdapter.setListener(new SettingItemClickListener<Setting>() {
            @Override
            public void onClickItem(Setting item) {
                switch (item.getCurrentIndex()) {
                    case 2:
                        Log.i("Share", "Share");
                        break;
                    case 3:
                        Log.i("Rate", "Rate");
                        reviewApp(getContext(), getActivity());
                        break;
                    default:
                        break;
                }
            }
        });

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

    private static void reviewApp(Context context, Activity activity) {
        ReviewManager manager = ReviewManagerFactory.create(context);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, log or handle the error code.
                @ReviewErrorCode int reviewErrorCode = ((ReviewException) task.getException()).getErrorCode();
            }
        });
    }
}