package Modules.CounterScreen.CounterCategory;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import Services.Counter.CounterModel;


public class CounterCategory {

    private boolean isGlobal;
    private String counterCategoryName;
    private final int iconName;
    private final int iconActionName;
    private ArrayList<CounterModel> counters;

    public CounterCategory(boolean isGlobal, String counterCategoryName, int iconName, int iconActionName, ArrayList<CounterModel> counters) {
        this.isGlobal = isGlobal;
        this.counterCategoryName = counterCategoryName;
        this.iconName = iconName;
        this.iconActionName = iconActionName;
        this.counters = counters;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public String getCounterCategoryName() {
        return counterCategoryName;
    }

    public void setCounterCategoryName(String counterCategoryName) {
        this.counterCategoryName = counterCategoryName;
    }

    public int getIconName() {
        return iconName;
    }

    public int getIconActionName() {
        return iconActionName;
    }

    public ArrayList<CounterModel> getCounters() {
        return counters;
    }

    public void setCounters(ArrayList<CounterModel> counters) {
        this.counters = counters;
    }

    @NonNull
    @Override
    public String toString() {
        return "CounterCategory{" +
                "isGlobal=" + isGlobal +
                ", counterCategoryName='" + counterCategoryName + '\'' +
                ", iconName=" + iconName +
                ", counters=" + counters +
                '}';
    }
}
