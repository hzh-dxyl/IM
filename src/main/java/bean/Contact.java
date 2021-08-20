package bean;

public class Contact {
    private int type; //0为人，1为群
    private int contact_id;
    private String contact_name;
    private String contact_img;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_img() {
        return contact_img;
    }

    public void setContact_img(String contact_img) {
        this.contact_img = contact_img;
    }
}
