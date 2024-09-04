package Modules.SymbolScreen.SymbolCategory;
import java.util.ArrayList;
import Services.Symbol.SymbolModel;

public class SymbolCategory {
    private String nameCategory;
    private final int iconName;
    private ArrayList<SymbolModel> symbols;

    public SymbolCategory(String nameCategory, int iconName, ArrayList<SymbolModel> symbols) {
        this.nameCategory = nameCategory;
        this.iconName = iconName;
        this.symbols = symbols;
    }

    public int getIconName() {
        return iconName;
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
