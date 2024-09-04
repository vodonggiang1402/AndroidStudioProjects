package Services.Tutorial;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TutorialModel implements Serializable {

    @SerializedName("tutorial_name")
    @Expose
    private final String tutorialName;

    @SerializedName("tutorial_des")
    @Expose
    private final String tutorialDes;

    @SerializedName("tutorial_image")
    @Expose
    private final String tutorialImage;

    @SerializedName("color")
    @Expose
    private final String colorString;

    @SerializedName("list")
    @Expose
    private final ArrayList<TutorialItem> list;

    public TutorialModel(String tutorialName, String tutorialDes, String tutorialImage, String colorString, ArrayList<TutorialItem> list) {
        this.tutorialName = tutorialName;
        this.tutorialDes = tutorialDes;
        this.tutorialImage = tutorialImage;
        this.colorString = colorString;
        this.list = list;
    }

    public String getTutorialName() {
        return tutorialName;
    }

    public String getTutorialDes() {
        return tutorialDes;
    }

    public String getTutorialImage() {
        return tutorialImage;
    }

    public String getColorString() {
        return colorString;
    }

    public ArrayList<TutorialItem> getList() {
        return list;
    }

    @NonNull
    @Override
    public String toString() {
        return "TutorialModel{" +
                "tutorialName='" + tutorialName + '\'' +
                ", tutorialDes='" + tutorialDes + '\'' +
                ", tutorialImage='" + tutorialImage + '\'' +
                ", colorString='" + colorString + '\'' +
                ", list=" + list +
                '}';
    }
}
