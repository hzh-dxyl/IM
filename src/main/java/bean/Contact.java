package bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Contact {
    @JSONField(name = "type")
    private int contact_type; //0为人，1为群
    @JSONField(name = "user_id")
    private int contact_id;
    @JSONField(name = "user_name")
    private String contact_name;
    @JSONField(name = "user_img")
    private String contact_img;
    @JSONField(name = "user_phone")
    private String contact_phone;
    @JSONField(name = "user_note")
    private String contact_note;

    public String getContact_note() {
        return contact_note;
    }

    public void setContact_note(String contact_note) {
        this.contact_note = contact_note;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public int getContact_type() {
        return contact_type;
    }

    public void setContact_type(int contact_type) {
        this.contact_type = contact_type;
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
