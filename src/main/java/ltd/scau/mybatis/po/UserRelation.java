package ltd.scau.mybatis.po;

import java.util.Date;

public class UserRelation {

    public static final Integer NONE = 0;

    public static final Integer WAIT_FOR_ACCEPT = 1;

    public static final Integer FRIEND = 2;

    private Long userAId;

    private Long userBId;

    private Integer relation;

    private Date updateTime;

    public Long getUserAId() {
        return userAId;
    }

    public void setUserAId(Long userAId) {
        this.userAId = userAId;
    }

    public Long getUserBId() {
        return userBId;
    }

    public void setUserBId(Long userBId) {
        this.userBId = userBId;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}