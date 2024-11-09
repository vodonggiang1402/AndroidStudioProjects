package Modules.CounterScreen.Counter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tmadecrochet.tmade.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Helper.SharedPrefHelper;
import Modules.CounterScreen.CounterCategory.CounterCategory;
import Modules.CounterScreen.SelectICounterItemListener;
import Services.Counter.CounterModel;
import Services.Counter.CounterResponse;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.CounterViewHolder> {
    private final Context cContext;
    private List<CounterModel> counters;
    private final SelectICounterItemListener listener;

    public CounterAdapter(Context cContext, SelectICounterItemListener selectICounterItemListener) {
        this.cContext = cContext;
        this.listener = selectICounterItemListener;
    }

    public void setData(List<CounterModel> list) {
        this.counters = list;
        updateDataSetChanged();
    }

    public void addItemData(CounterModel counterModel) {
        this.counters.add(counterModel);
        updateDataSetChanged();
    }

    public void removeItemData(CounterModel counterModel) {
        this.counters.remove(counterModel);
        this.listener.onUpdateItemClicked();
        updateDataSetChanged();
    }

    public void updateItemCountData(CounterModel counterModel, boolean isMinus) {
        if (isMinus) {
            if (counterModel.getCount() > 1) {
                counterModel.setCount(counterModel.getCount() - 1);
            }
        } else {
            counterModel.setCount(counterModel.getCount() + 1);
        }
        this.listener.onUpdateItemClicked();
        updateDataSetChanged();
    }

    public void resetItemCountData(CounterModel counterModel) {
        counterModel.setCount(1);
        this.listener.onUpdateItemClicked();
        updateDataSetChanged();
    }

    public void updateItemNameData(CounterModel counterModel, String text) {
        counterModel.setCountName(text);
        updateDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDataSetChanged() {
        this.listener.onUpdateItemClicked();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_item, parent, false);
        return new CounterAdapter.CounterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterViewHolder holder, int position) {
        CounterModel counterModel = counters.get(position);
        if (counterModel == null) {
            return;
        }

        String counterName = getStringByIdName(cContext, counterModel.getCountName());
        if (!counterName.isEmpty()) {
            holder.titleTextView.setText(counterName);
        } else {
            holder.titleTextView.setText(counterModel.getCountName());
        }

        holder.counterTextView.setText(String.valueOf(counterModel.getCount()));

        GradientDrawable backgroundGradient = (GradientDrawable) holder.linearLayout.getBackground();
        backgroundGradient.setStroke(2, Color.parseColor("#" + counterModel.getColor()));

        if (counterModel.isGlobal()) {
            holder.moreBtn.setVisibility(View.GONE);
        } else {
            holder.moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(counterModel);
                }
            });
        }

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemCountData(counterModel, true);
            }
        });

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemCountData(counterModel, false);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (counters != null) {
            return counters.size();
        }
        return 0;
    }

    public static class CounterViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final LinearLayout linearLayout;
        private final TextView titleTextView;
        private final Button minusBtn;
        private final TextView counterTextView;
        private final Button plusBtn;
        private final Button moreBtn;

        public CounterViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.counter_card_view);
            linearLayout = itemView.findViewById(R.id.counter_item_view_layout);
            titleTextView = itemView.findViewById(R.id.counter_item_title);
            minusBtn = itemView.findViewById(R.id.counter_minus_button);
            counterTextView = itemView.findViewById(R.id.counter_item_text_view);
            plusBtn = itemView.findViewById(R.id.counter_plus_button);
            moreBtn = itemView.findViewById(R.id.counter_more_button);
        }
    }

    public static String getStringByIdName(Context context, String idName) {
        String resuls = "";
        Resources res = context.getResources();
        int resId = res.getIdentifier(idName, "string", context.getPackageName());
        if (resId > 0) {
            String resString = res.getString(resId);
            if (!resString.isEmpty()) {
                resuls = resString;
            }
        }
        return resuls;
    }

    private void showDialog(CounterModel counterModel)
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
                showDialogEditName(counterModel);
                dialog.dismiss();
            }
        });

        resetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetItemCountData(counterModel);
                dialog.dismiss();
            }
        });

        removeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemData(counterModel);
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

    private void showDialogEditName(CounterModel counterModel)
    {
        final Dialog dialog = new Dialog(cContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_counter_bottom_sheet_layout);

        EditText editText = dialog.findViewById(R.id.counter_add_edit_text);

        Button okBtn = dialog.findViewById(R.id.counter_ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = editText.getText().toString();
                if (!nameText.isEmpty()) {
                    updateItemNameData(counterModel, nameText);
                } else {
                    updateItemNameData(counterModel, "New counter");
                }

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
}

