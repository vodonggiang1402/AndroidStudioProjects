package Modules.CounterScreen;

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

public class CounterFragment extends Fragment {

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
        RecyclerView rcvCounterCategory = (RecyclerView) view.findViewById(R.id.rcv_counter_category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCounterCategory.setLayoutManager(layoutManager);

        CounterCategoryAdapter counterCategoryAdapter = new CounterCategoryAdapter(this.getContext());

        rcvCounterCategory.setItemAnimator(new DefaultItemAnimator());

        counterCategoryAdapter.setData(getListCounterCategory(getContext()));
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
            boolean isGlobal = false;
            switch (i) {
                case 0:
                    isGlobal = true;
                    text = getString(R.string.main_counter_text);
                    iconName = R.drawable.ico_global_counter;
                    break;
                case 1:
                    text = getString(R.string.extra_counter_text);
                    iconName = R.drawable.ico_extra_counter;
                    break;
                default:
                    break;
            }
            listSymbolCategory.add(new CounterCategory(isGlobal, text, iconName, listCategory.get(i)));
        }

        return  listSymbolCategory;
    }
}