package Modules.SettingScreen.Contact;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Data.Constant;
import Modules.SettingScreen.Language.LanguageAdapter;
import Services.Contact.ContactModel;
import Services.Contact.ContactType;
import Services.Language.Language;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private final Context cContext;
    private List<ContactModel> contactModelList;

    public ContactAdapter(Context context) {
        this.cContext = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ContactModel> list) {
        contactModelList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactAdapter.ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        ContactModel contactModel = contactModelList.get(position);
        holder.textView.setText(contactModel.getName());
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(contactModel.getIconName(), 0, 0, 0);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactModel.getType() == ContactType.PHONE) {
                    performPhoneCall();
                } else if (contactModel.getType() == ContactType.MAIL) {
                    performSendMail();
                } else {
                    performYoutube();
                }
            }
        });
    }

    private void performPhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + Constant.PhoneNumber.PHONE));
        try {
            cContext.startActivity(callIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(cContext, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
        }
    }

    private void performSendMail() {

    }

    private void performYoutube() {

    }

    @Override
    public int getItemCount() {
        if (contactModelList != null) {
            return contactModelList.size();
        }
        return 0;
    }

    public static class ContactHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;
        private final TextView textView;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.contact_linear_layout);
            textView = itemView.findViewById(R.id.contact_title);
        }
    }
}