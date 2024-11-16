package Modules.SettingScreen.Contact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;

import Modules.SettingScreen.Language.LanguageAdapter;
import Services.Contact.ContactModel;
import Services.Language.Language;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private List<ContactModel> contactModelList;

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
    }

    @Override
    public int getItemCount() {
        if (contactModelList != null) {
            return contactModelList.size();
        }
        return 0;
    }

    public static class ContactHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contact_title);
        }
    }
}