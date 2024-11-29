package Modules.SymbolScreen.SymbolCategory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.List;

import Helper.SharedPrefHelper;
import Modules.CounterScreen.CounterCategory.CounterCategory;
import Modules.SymbolScreen.SelectSymbolItemListener;
import Modules.SymbolScreen.Symbol.SymbolAdapter;
import Modules.SymbolScreen.UpdateView.UpdateViewAdapter;
import Services.Counter.CounterModel;
import Services.Counter.CounterResponse;
import Services.Symbol.SymbolModel;
import Services.Symbol.SymbolResponse;

public class SymbolCategoryAdapter extends RecyclerView.Adapter<SymbolCategoryAdapter.SymbolCategoryViewHolder>  implements SelectSymbolItemListener {
    private final Context cContext;
    private final Activity cActivity;

    public SymbolCategoryAdapter(Context cContext, Activity activity) {
        this.cContext = cContext;
        this.cActivity = activity;
    }

    private List<SymbolCategory> listSymbolCategory;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SymbolCategory> list) {
        this.listSymbolCategory =  list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SymbolCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symbol_category, parent, false);
        return new SymbolCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymbolCategoryViewHolder holder, int position) {
        SymbolCategory symbolCategory = listSymbolCategory.get(position);
        if (symbolCategory == null) {
            return;
        }
        holder.nameSymbolCategory.setText(symbolCategory.getNameCategory());
        holder.nameSymbolCategory.setCompoundDrawablesWithIntrinsicBounds(symbolCategory.getIconName(), 0, 0, 0);

        ArrayList<SymbolModel> list = symbolCategory.getSymbols();
        if (!list.isEmpty()) {
            GridLayoutManager gridlayoutManager = new GridLayoutManager(this.cContext, 3);
            holder.rcvSymbolCategory.setLayoutManager(gridlayoutManager);
            SymbolAdapter symbolAdapter = new SymbolAdapter(cContext, this, cActivity);
            symbolAdapter.setData(symbolCategory.getSymbols());
            holder.rcvSymbolCategory.setAdapter(symbolAdapter);
        } else  {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.cContext, RecyclerView.VERTICAL,false);
            holder.rcvSymbolCategory.setLayoutManager(linearLayoutManager);
            UpdateViewAdapter updateViewAdapter  = new UpdateViewAdapter(this.cContext);
            holder.rcvSymbolCategory.setAdapter(updateViewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        if (listSymbolCategory != null) {
            return listSymbolCategory.size();
        }
        return 0;
    }

    @Override
    public void onUpdateItemClicked() {
        updateDataLocal();
    }

    private void updateDataLocal() {
        ArrayList<ArrayList<SymbolModel>> list = new ArrayList<ArrayList<SymbolModel>>();
        SymbolCategory list0 =  listSymbolCategory.get(0);
        list.add(list0.getSymbols());

        SymbolCategory list1 =  listSymbolCategory.get(1);
        list.add(list1.getSymbols());

        SymbolCategory list2 =  listSymbolCategory.get(2);
        list.add(list2.getSymbols());

        SymbolCategory list3 =  listSymbolCategory.get(3);
        list.add(list3.getSymbols());

        SymbolCategory list4 =  listSymbolCategory.get(4);
        list.add(list4.getSymbols());

        Gson gson = new Gson();
        SymbolResponse response = new SymbolResponse(list);
        String responseString = gson.toJson(response);
        SharedPrefHelper.saveSharedOBJECT(cContext,"symbol_response", responseString);
    }

    public static class SymbolCategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameSymbolCategory;
        private final RecyclerView rcvSymbolCategory;

        public SymbolCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSymbolCategory = itemView.findViewById(R.id.symbol_category_title);
            rcvSymbolCategory = itemView.findViewById(R.id.rcv_symbol_category);
        }
    }
}
