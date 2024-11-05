package Modules.CounterScreen.CounterCategory;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmadecrochet.tmade.R;

import java.util.List;
import java.util.Objects;

import Modules.CounterScreen.Counter.CounterAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CounterCategoryAdapter extends RecyclerView.Adapter<CounterCategoryAdapter.CounterCategoryViewHolder> {

    private final Context cContext;
    private List<CounterCategory> listCounterCategory;

    public CounterCategoryAdapter(Context cContext) {
        this.cContext = cContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CounterCategory> list) {
        this.listCounterCategory =  list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounterCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_category, parent, false);
        return new CounterCategoryAdapter.CounterCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterCategoryViewHolder holder, int position) {
        CounterCategory counterCategory = listCounterCategory.get(position);

        if (counterCategory == null) {
            return;
        }

        holder.nameCounterCategory.setText(counterCategory.getCounterCategoryName());
        holder.nameCounterCategory.setCompoundDrawablesWithIntrinsicBounds(counterCategory.getIconName(), 0, 0, 0);
        holder.buttonCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, counterCategory.getIconActionName(), 0);
        holder.buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterCategory.isGlobal()) {
                    showDialog();
                } else {
                    showDialogAddCounterTitle();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.cContext, RecyclerView.VERTICAL,false);
        holder.rcvCounterCategory.setLayoutManager(linearLayoutManager);
        CounterAdapter counterAdapter  = new CounterAdapter(this.cContext);
        counterAdapter.setData(counterCategory.getCounters());
        holder.rcvCounterCategory.setAdapter(counterAdapter);
    }

    @Override
    public int getItemCount() {
        if (listCounterCategory != null) {
            return listCounterCategory.size();
        }
        return 0;
    }

    public static class CounterCategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameCounterCategory;
        private final RecyclerView rcvCounterCategory;
        private final Button buttonCategory;

        public CounterCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCounterCategory = itemView.findViewById(R.id.counter_category_title);
            buttonCategory = itemView.findViewById(R.id.counter_refresh_button);
            rcvCounterCategory = itemView.findViewById(R.id.rcv_counter_category);
        }
    }

    private void showDialogAddCounterTitle()
    {
        final Dialog dialog = new Dialog(cContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_counter_bottom_sheet_layout);

        Button okBtn = dialog.findViewById(R.id.counter_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void showDialog()
    {
        final Dialog dialog = new Dialog(cContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout editLayout = dialog.findViewById(R.id.layout_edit);
        LinearLayout resetLayout = dialog.findViewById(R.id.layout_reset);
        LinearLayout removeLayout = dialog.findViewById(R.id.layout_remove);
        LinearLayout cancelLayout = dialog.findViewById(R.id.layout_cancel);

        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        resetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        removeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancelLayout.setOnClickListener(new View.OnClickListener() {
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
}
