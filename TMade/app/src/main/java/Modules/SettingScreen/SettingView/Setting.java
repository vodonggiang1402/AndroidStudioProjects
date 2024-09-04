package Modules.SettingScreen.SettingView;

public class Setting {
    private int currentIndex;
    private int iconName;
    private String text;

    public Setting(int currentIndex, int iconName, String text) {
        this.currentIndex = currentIndex;
        this.iconName = iconName;
        this.text = text;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getIconName() {
        return iconName;
    }

    public void setIconName(int iconName) {
        this.iconName = iconName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
