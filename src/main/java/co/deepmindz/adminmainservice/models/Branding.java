package co.deepmindz.adminmainservice.models;

public class Branding {
    private String Name;

    public Branding(String name, String value) {
        this.Name = name;
        this.value = value;
    }

    public Branding() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
