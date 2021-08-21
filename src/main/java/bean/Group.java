package bean;

import java.util.ArrayList;

public class Group {
    private int group_id;
    private String group_name;
    private String group_img;
    private String group_notice; //群公告
    private ArrayList<Member> members;  //群成员

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_img() {
        return group_img;
    }

    public void setGroup_img(String group_img) {
        this.group_img = group_img;
    }

    public String getGroup_notice() {
        return group_notice;
    }

    public void setGroup_notice(String group_notice) {
        this.group_notice = group_notice;
    }
}
