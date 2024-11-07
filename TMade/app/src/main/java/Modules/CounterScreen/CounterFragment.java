package Modules.CounterScreen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.Objects;

import Helper.SharedPrefHelper;
import Modules.CounterScreen.CounterCategory.CounterCategory;
import Modules.CounterScreen.CounterCategory.CounterCategoryAdapter;
import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Services.Counter.CounterModel;
import Services.Counter.CounterResponse;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolResponse;
import Services.Tutorial.TutorialModel;
import Services.Tutorial.TutorialResponse;

public class CounterFragment extends Fragment implements SelectItemListener {
    CounterCategoryAdapter counterCategoryAdapter;
    RecyclerView rcvCounterCategory;
    LinearLayoutManager layoutManager;
    ArrayList<CounterCategory> currentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_counter, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.counter_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.setTitle(null);
        }

        final FragmentActivity c = getActivity();
        rcvCounterCategory = (RecyclerView) view.findViewById(R.id.rcv_counter_category);

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCounterCategory.setLayoutManager(layoutManager);

        counterCategoryAdapter = new CounterCategoryAdapter(this.getContext(), this);

        rcvCounterCategory.setItemAnimator(new DefaultItemAnimator());

        currentList = getListCounterCategory(getContext());

        counterCategoryAdapter.setData(currentList);
        rcvCounterCategory.setAdapter(counterCategoryAdapter);

        return view;
    }

    private ArrayList<CounterCategory> getListCounterCategory(Context context) {
        ArrayList<CounterCategory> listSymbolCategory  = new ArrayList<>();
        CounterResponse data = (CounterResponse) SharedPrefHelper.getSharedOBJECT(context,"counter_response", CounterResponse.class);

        ArrayList<ArrayList<CounterModel>> listCategory =  data.list;
        for (int i=0; i<listCategory.size(); i++) {
            String text= "";
            int iconName = R.drawable.ico_extra_counter;
            int iconActionName = R.drawable.ico_refresh;
            boolean isGlobal = false;
            switch (i) {
                case 0:
                    isGlobal = true;
                    text = getString(R.string.main_counter_text);
                    iconName = R.drawable.ico_global_counter;
                    iconActionName = R.drawable.ico_refresh;
                    break;
                case 1:
                    text = getString(R.string.extra_counter_text);
                    iconName = R.drawable.ico_extra_counter;
                    iconActionName = R.drawable.ico_more;
                    break;
                default:
                    break;
            }
            listSymbolCategory.add(new CounterCategory(isGlobal, text, iconName, iconActionName, listCategory.get(i)));
        }

        return  listSymbolCategory;
    }


    private void showDialogAddCounterTitle(Context context, CounterCategory category, int position)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_counter_bottom_sheet_layout);

        EditText editText = dialog.findViewById(R.id.counter_add_edit_text);
        Button okBtn = dialog.findViewById(R.id.counter_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CounterModel counterModel = new CounterModel(false, editText.getText().toString(), 1, "F76A89");
                ArrayList<CounterModel> extraList = category.getCounters();
                extraList.add(counterModel);
                counterCategoryAdapter.notifyItemChanged(position);
                dialog.dismiss();
            }
        });

        Button cancelBtn = dialog.findViewById(R.id.counter_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT) );
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onItemClicked(Context context, CounterCategory category, int position) {
        showDialogAddCounterTitle(context, category, position);
    }
}