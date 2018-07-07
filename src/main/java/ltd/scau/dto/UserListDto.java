package ltd.scau.dto;

import ltd.scau.mybatis.po.User;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

/**
 * @author Weijie Wu
 */
public class UserListDto extends ListDto {

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
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
