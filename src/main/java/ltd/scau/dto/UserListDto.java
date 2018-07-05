package ltd.scau.dto;

import ltd.scau.mybatis.po.User;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

/**
 * @author Weijie Wu
 */
public class UserListDto extends ResultDto {

    @Min(1)
    protected int pageSize;

    @Min(1)
    protected int pageNo;

    protected List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "UserListDto{" +
                "users=" + users +
                '}';
    }
}
