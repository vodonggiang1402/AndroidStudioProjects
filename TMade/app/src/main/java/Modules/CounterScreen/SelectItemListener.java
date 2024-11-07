package Modules.CounterScreen;
import android.content.Context;

import Modules.CounterScreen.CounterCategory.CounterCategory;

public interface SelectItemListener {
    void onItemClicked(Context context, CounterCategory category, int position);
}
