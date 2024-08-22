package Modules.SymbolScreen;

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
import android.widget.TextView;

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.Objects;

import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Services.SymbolModel;

public class SymbolFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_symbol, container, false);

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.symbol_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.setTitle(null);
        }

        final FragmentActivity c = getActivity();
        RecyclerView rcvCategory = (RecyclerView) view.findViewById(R.id.rcv_category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(layoutManager);

        SymbolCategoryAdapter symbolCategoryAdapter = new SymbolCategoryAdapter(this.getContext());

        rcvCategory.setItemAnimator(new DefaultItemAnimator());

        symbolCategoryAdapter.setData(getListSymbolCategory());
        rcvCategory.setAdapter(symbolCategoryAdapter);

        return view;
    }

    private ArrayList<SymbolCategory> getListSymbolCategory() {
        ArrayList<SymbolCategory> listSymbolCategory  = new ArrayList<>();
        ArrayList<SymbolModel> listSymbol = new ArrayList<>();
        listSymbol.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbol.add(new SymbolModel("2","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
        listSymbol.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbol.add(new SymbolModel("4","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
        listSymbol.add(new SymbolModel("5","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbol.add(new SymbolModel("6","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
        listSymbolCategory.add(new SymbolCategory("Mui don", listSymbol));

        ArrayList<SymbolModel> listSymbol1 = new ArrayList<>();
        listSymbol1.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbol1.add(new SymbolModel("2","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbol1.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
        listSymbolCategory.add(new SymbolCategory("Mui kep", listSymbol1));

//        ArrayList<SymbolModel> listSymbol2 = new ArrayList<>();
//        listSymbol2.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol2.add(new SymbolModel("2","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol2.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol2.add(new SymbolModel("4","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol2.add(new SymbolModel("5","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol2.add(new SymbolModel("6","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbolCategory.add(new SymbolCategory("Mui don", listSymbol2));
//
//
//        ArrayList<SymbolModel> listSymbol3 = new ArrayList<>();
//        listSymbol3.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol3.add(new SymbolModel("2","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol3.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol3.add(new SymbolModel("4","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol3.add(new SymbolModel("5","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol3.add(new SymbolModel("6","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbolCategory.add(new SymbolCategory("Mui don", listSymbol3));
//
//        ArrayList<SymbolModel> listSymbol4 = new ArrayList<>();
//        listSymbol4.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol4.add(new SymbolModel("2","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol4.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol4.add(new SymbolModel("4","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol4.add(new SymbolModel("5","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol4.add(new SymbolModel("6","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbolCategory.add(new SymbolCategory("Mui don", listSymbol4));
//
//        ArrayList<SymbolModel> listSymbol5 = new ArrayList<>();
//        listSymbol5.add(new SymbolModel("1","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol5.add(new SymbolModel("2","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol5.add(new SymbolModel("3","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol5.add(new SymbolModel("4","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbol5.add(new SymbolModel("5","mot", "abc", "ico_chain", "", "", true, new ArrayList<>(), ""));
//        listSymbol5.add(new SymbolModel("6","mot", "abc", "ico_slip_stich", "", "", true, new ArrayList<>(), ""));
//        listSymbolCategory.add(new SymbolCategory("Mui don", listSymbol5));
        return  listSymbolCategory;
    }

}
