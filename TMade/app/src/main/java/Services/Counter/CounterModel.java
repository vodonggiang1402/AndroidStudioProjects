package Services.Counter;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CounterModel implements Serializable {

    @SerializedName("is_global")
    @Expose
    private boolean isGlobal;

    @SerializedName("count_name")
    @Expose
    private String countName;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("color")
    @Expose
    private String color;

    public CounterModel(boolean isGlobal, String countName, int count, String color) {
        this.isGlobal = isGlobal;
        this.countName = countName;
        this.count = count;
        this.color = color;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NonNull
    @Override
    public String toString() {
        return "CounterModel{" +
                "isGlobal=" + isGlobal +
                ", countName='" + countName + '\'' +
                ", count=" + count +
                ", color='" + color + '\'' +
                '}';
    }
}
