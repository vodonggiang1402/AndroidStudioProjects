package Services.Symbol;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SymbolModel implements Serializable {

    @SerializedName("symbol_id")
    @Expose
    private final String symbolId;

    @SerializedName("symbol_name")
    @Expose
    private final String symbolName;

    @SerializedName("symbol_des")
    @Expose
    private final String symbolDes;

    @SerializedName("icon_name")
    @Expose
    private final String iconName;

    @SerializedName("video_url")
    @Expose
    private final String videoUrl;

    @SerializedName("background_color")
    @Expose
    private final String backgroundColor;

    @SerializedName("is_ads")
    @Expose
    private final boolean isAds;

    @SerializedName("steps")
    @Expose
    private final ArrayList<SymbolStep> steps;

    @SerializedName("video_count")
    @Expose
    private final String videoCount;

    public SymbolModel(String symbolId, String symbolName, String symbolDes, String iconName, String videoUrl, String backgroundColor, boolean isAds, ArrayList<SymbolStep> steps, String videoCount) {
        this.symbolId = symbolId;
        this.symbolName = symbolName;
        this.symbolDes = symbolDes;
        this.iconName = iconName;
        this.videoUrl = videoUrl;
        this.backgroundColor = backgroundColor;
        this.isAds = isAds;
        this.steps = steps;
        this.videoCount = videoCount;
    }

    public String getSymbolId() {
        return symbolId;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public String getSymbolDes() {
        return symbolDes;
    }

    public String getIconName() {
        return iconName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isAds() {
        return isAds;
    }

    public ArrayList<SymbolStep> getSteps() {
        return steps;
    }

    public String getVideoCount() {
        return videoCount;
    }

    @NonNull
    @Override
    public String toString() {
        return "SymbolModel{" +
                "symbolId='" + symbolId + '\'' +
                ", symbolName='" + symbolName + '\'' +
                ", symbolDes='" + symbolDes + '\'' +
                ", iconName='" + iconName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", isAds=" + isAds +
                ", steps=" + steps +
                ", videoCount='" + videoCount + '\'' +
                '}';
    }
}
