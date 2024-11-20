package Services.Contact;

public class ContactModel {

    private final int iconName;
    private final String name;
    private final ContactType type;

    public ContactType getType() {
        return type;
    }

    public ContactModel(int iconName, String name, ContactType type) {
        this.iconName = iconName;
        this.name = name;
        this.type = type;
    }

    public int getIconName() {
        return iconName;
    }

    public String getName() {
        return name;
    }
}
