package Services.Counter;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import Services.Symbol.SymbolModel;

public class CounterResponse {

    @SerializedName("data")
    public ArrayList<ArrayList<CounterModel>> list;

    public ArrayList<ArrayList<CounterModel>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<CounterModel>> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public String toString() {
        return "CounterResponse{" +
                "list=" + list +
                '}';
    }
}
