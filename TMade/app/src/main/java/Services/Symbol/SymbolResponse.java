package Services.Symbol;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SymbolResponse {
    @SerializedName("data")
    public ArrayList<ArrayList<SymbolModel>> list;

    public SymbolResponse(ArrayList<ArrayList<SymbolModel>> list) {
        this.list = list;
    }

    public ArrayList<ArrayList<SymbolModel>> getList() {
        return list;
    }
}
