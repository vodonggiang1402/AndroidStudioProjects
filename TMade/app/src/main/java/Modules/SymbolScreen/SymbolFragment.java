package Modules.SymbolScreen;

import android.os.Bundle;

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

import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Services.SymbolModel;

public class SymbolFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_symbol, container, false);
        final FragmentActivity c = getActivity();
        RecyclerView rcvCategory = (RecyclerView) view.findViewById(R.id.rcv_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
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
        return  listSymbolCategory;
    }

}
