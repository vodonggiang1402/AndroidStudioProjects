package com.tmadecrochet.tmade.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SymbolStep implements Serializable {

    @SerializedName("step_name")
    @Expose
    private String stepName;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("image_name")
    @Expose
    private String imageName;

    public  SymbolStep () {

    }

    public SymbolStep(String stepName, String content, String imageName) {
        this.stepName = stepName;
        this.content = content;
        this.imageName = imageName;
    }

    public String getStepName() {
        return stepName;
    }

    public String getContent() {
        return content;
    }

    public String getImageName() {
        return imageName;
    }

    @NonNull
    @Override
    public String toString() {
        return "SymbolStep{" +
                "stepName='" + stepName + '\'' +
                ", content='" + content + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
