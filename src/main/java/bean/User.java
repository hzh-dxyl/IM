package bean;

import java.util.ArrayList;

public class User {
    private int status; //登录返回的状态码，0为无效账号，1为密码错误，2为密码正确
    private int user_id;
    private String user_name;
    private String user_phone;
    private String user_pwd;
    private String user_gender;
    private String user_sign;
    private String user_img; //头像url
    private byte[] img_data; //头像文件字节
    private String img_hex;  //头像文件md5哈希码
    private ArrayList<Contact> contacts; //联系人信息

    public void cleanInfo(){
        setUser_img("");
        setUser_gender("");
        setUser_id(-1);
        setUser_name("");
        setUser_phone("");
        setUser_sign("");
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImg_hex() {
        return img_hex;
    }

    public void setImg_hex(String img_hex) {
        this.img_hex = img_hex;
    }

    public byte[] getImg_data() {
        return img_data;
    }

    public void setImg_data(byte[] img_data) {
        this.img_data = img_data;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_sign() {
        return user_sign;
    }

    public void setUser_sign(String user_sign) {
        this.user_sign = user_sign;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }
}
