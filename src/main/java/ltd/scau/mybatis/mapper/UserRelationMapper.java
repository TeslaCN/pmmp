package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.UserRelation;

public interface UserRelationMapper {
    int insert(UserRelation record);

    List<UserRelation> selectAll();
}