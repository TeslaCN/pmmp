package ltd.scau.dto;

import ltd.scau.mybatis.po.User;

import java.util.Collections;
import java.util.List;

/**
 * @author Weijie Wu
 */
public class UserListDto extends ResultDto {

    protected int pageSize;

    protected int pageNo;

    protected List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserListDto{" +
                "users=" + users +
                '}';
    }
}
