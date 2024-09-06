package Services.Tutorial;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TutorialItem implements Serializable {
    @SerializedName("item_name")
    @Expose
    private final String itemName;

    @SerializedName("item_des")
    @Expose
    private final String itemDes;

    @SerializedName("item_image")
    @Expose
    private final String itemImage;

    @SerializedName("item_color")
    @Expose
    private final String itemColor;

    @SerializedName("item_url")
    @Expose
    private final String itemUrl;

    public TutorialItem(String itemName, String itemDes, String itemImage, String itemColor, String itemUrl) {
        this.itemName = itemName;
        this.itemDes = itemDes;
        this.itemImage = itemImage;
        this.itemColor = itemColor;
        this.itemUrl = itemUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDes() {
        return itemDes;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getItemColor() {
        return itemColor;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "TutorialItem{" +
                "itemName='" + itemName + '\'' +
                ", itemDes='" + itemDes + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemColor='" + itemColor + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                '}';
    }
}
