package Modules.SettingScreen.SettingView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    private final Context sContext;
    private final Activity sActivity;
    private List<Setting> settings;
    private ReviewManager reviewManager;

    public SettingAdapter(Context sContext, Activity activity) {
        this.sContext = sContext;
        this.sActivity = activity;
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
        if (setting.getCurrentIndex() == 4) {
            String versionName = setting.getText() + " " + getVersion(sContext);
            holder.textView.setText(versionName);
        } else {
            holder.textView.setText(setting.getText());
        }
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
                        showRateApp();
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

    public String getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "1.0.0";
        }
    }

    public void showRateApp() {
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow(sActivity, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, continue regardless of the result.
                // show native rate app dialog on error
                showRateAppFallbackDialog();
            }
        });
    }

    private void showRateAppFallbackDialog() {
        new MaterialAlertDialogBuilder(sContext)
                .setTitle(R.string.rate_app_title)
                .setMessage(R.string.rate_app_message)
                .setPositiveButton(R.string.rate_btn_now, (dialog, which) -> {

                })
                .setNegativeButton(R.string.rate_btn_later,
                        (dialog, which) -> {
                        })
                .setNeutralButton(R.string.rate_btn_no,
                        (dialog, which) -> {
                        })
                .setOnDismissListener(dialog -> {
                })
                .show();
    }
}
