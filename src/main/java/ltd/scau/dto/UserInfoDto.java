package ltd.scau.dto;

import ltd.scau.mybatis.po.User;

/**
 * @author Weijie Wu
 */
public class UserInfoDto extends ResultDto {

    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.setPassword(null);
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "user=" + user +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
