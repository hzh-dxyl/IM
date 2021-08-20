package bean;

public class Friend {
    private int user_id;
    private int friend_id;
    private String friend_note;
    private int notice_rank;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public String getFriend_note() {
        return friend_note;
    }

    public void setFriend_note(String friend_note) {
        this.friend_note = friend_note;
    }

    public int getNotice_rank() {
        return notice_rank;
    }

    public void setNotice_rank(int notice_rank) {
        this.notice_rank = notice_rank;
    }
}
