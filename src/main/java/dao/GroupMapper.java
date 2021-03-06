package dao;

import bean.Group;
import bean.Member;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface GroupMapper {
    int createGroup(Group group);
    ArrayList<Member> getGroupMembers(Group group);
    Group selectGroup(Group group);
    String selectFriend_note(@Param("user_id")int user_id,@Param("friend_id") int friend_id);
    String selectPhone(int id);
    int updateMember(Member member);
    int addMember(Member member);
}
