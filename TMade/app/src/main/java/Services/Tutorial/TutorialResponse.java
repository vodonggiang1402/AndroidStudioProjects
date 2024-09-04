package Services.Tutorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TutorialResponse {
    @SerializedName("data")
    public ArrayList<TutorialModel> list;

    public TutorialResponse(ArrayList<TutorialModel> list) {
        this.list = list;
    }

    public ArrayList<TutorialModel> getList() {
        return list;
    }

    public void setList(ArrayList<TutorialModel> list) {
        this.list = list;
    }
}
