package Services.Contact;

public class ContactModel {

    private final int iconName;
    private final String name;

    public ContactModel(int iconName, String name) {
        this.iconName = iconName;
        this.name = name;
    }

    public int getIconName() {
        return iconName;
    }

    public String getName() {
        return name;
    }
}
