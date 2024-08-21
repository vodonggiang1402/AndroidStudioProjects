package Modules.SymbolScreen.SymbolCategory;
import java.util.ArrayList;
import Services.SymbolModel;

public class SymbolCategory {
    private String nameCategory;
    private ArrayList<SymbolModel> symbols;

    public SymbolCategory(String nameCategory, ArrayList<SymbolModel> symbols) {
        this.nameCategory = nameCategory;
        this.symbols = symbols;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<SymbolModel> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<SymbolModel> symbols) {
        this.symbols = symbols;
    }
}
