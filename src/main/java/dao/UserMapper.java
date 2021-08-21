package dao;

import bean.Contact;
import bean.Friend;
import bean.User;

import java.util.ArrayList;

public interface UserMapper {
    User checkUser(User user);
    int createUser(User user);
    String checkHead(String hex);
    int checkPhone(User user);
    ArrayList<Contact> getContacts(User user);
    ArrayList<Contact> getContacts2(User user);
    ArrayList<Contact> getContacts3(User user);
    Contact getUserContact(Contact contact);
    Contact getGroupContact(Contact contact);
    User searchUser(User user);
    Friend selectFriend(int id);
    int updateFriend(Friend friend);
    int addFriend(Friend friend);
    int updateUser(User user);
}
