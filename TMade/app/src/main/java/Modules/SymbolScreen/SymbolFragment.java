package Modules.SymbolScreen;

import android.content.Context;
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

import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.List;

import Modules.SymbolScreen.SymbolCategory.SymbolCategory;
import Modules.SymbolScreen.SymbolCategory.SymbolCategoryAdapter;
import Services.SymbolModel;
import Services.SymbolResponse;
import Helper.SharedPrefHelper;

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

        symbolCategoryAdapter.setData(getListSymbolCategory(getContext()));
        rcvCategory.setAdapter(symbolCategoryAdapter);

        return view;
    }

    private ArrayList<SymbolCategory> getListSymbolCategory(Context context) {
        ArrayList<SymbolCategory> listSymbolCategory  = new ArrayList<>();
        SymbolResponse data = (SymbolResponse) SharedPrefHelper.getSharedOBJECT(context,"symbol_response");
        Log.i("MainActivity","data" + data);

        ArrayList<ArrayList<SymbolModel>> listCategory =  data.list;
        for (int i=0; i<listCategory.size(); i++) {
            String text= "";
            int iconName = R.drawable.ico_symbol_header_1;
            switch (i) {
                case 0:
                    text = getString(R.string.basic_stitches);
                    iconName = R.drawable.ico_symbol_header_1;
                    break;
                case 1:
                    text = getString(R.string.puff_stitches);
                    iconName = R.drawable.ico_symbol_header_2;
                    break;
                case 2:
                    text = getString(R.string.increases_stitches);
                    iconName = R.drawable.ico_symbol_header_3;
                    break;
                case 3:
                    text = getString(R.string.decreases_stitches);
                    iconName = R.drawable.ico_symbol_header_4;
                    break;
                case 4:
                    text = getString(R.string.blo_flo_stitches);
                    iconName = R.drawable.ico_symbol_header_5;
                    break;
                default:
                    break;
            }
            ArrayList<SymbolModel> symbols = listCategory.get(i);
            listSymbolCategory.add(new SymbolCategory(text, iconName, symbols));
        }
        return  listSymbolCategory;
    }

}
