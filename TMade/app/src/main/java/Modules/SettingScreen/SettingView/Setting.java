package Modules.SettingScreen.SettingView;

public class Setting {
    private int iconName;
    private String text;

    public Setting(int iconName, String text) {
        this.iconName = iconName;
        this.text = text;
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
