package Modules.SettingScreen.Contact;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.List;

import Helper.utils.LanguageUtils;
import Modules.SettingScreen.Language.ItemClickListener;
import Modules.SettingScreen.Language.LanguageAdapter;
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Services.Contact.ContactModel;
import Services.Language.Language;

public class ContactScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ico_back);
        this.setTitle(null);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TextView textView = (TextView)findViewById(R.id.contact_toolbar_title);
        textView.setText(LanguageUtils.getLocaleStringResource(R.string.contact_screen_header_title, getApplicationContext()));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        RecyclerView rcvContact = (RecyclerView) findViewById(R.id.rcv_contact);

        TextView contactDescription = (TextView)findViewById(R.id.contact_description);
        contactDescription.setText(LanguageUtils.getLocaleStringResource(R.string.contact_screen_description_text, getApplicationContext()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rcvContact.setLayoutManager(layoutManager);

        ContactAdapter contactAdapter = new ContactAdapter();
        contactAdapter.setData(getListContact(getApplicationContext()));
        rcvContact.setItemAnimator(new DefaultItemAnimator());
        rcvContact.setAdapter(contactAdapter);
    }

    private ArrayList<ContactModel> getListContact(Context context) {
        ArrayList<ContactModel> result = new ArrayList<>();
        result.add(new ContactModel(R.drawable.ico_phone, "+84357798368"));
        result.add(new ContactModel(R.drawable.ico_setting_create_contact, "tmadeapp@gmail.com"));
        result.add(new ContactModel(R.drawable.ico_youtube, "tmade-0705"));
        return result;
    }
}