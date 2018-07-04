package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.UserRelation;
import ltd.scau.mybatis.po.UserRelationExample;
import org.apache.ibatis.annotations.Param;

public interface UserRelationMapper {
    long countByExample(UserRelationExample example);

    int deleteByExample(UserRelationExample example);

    int insert(UserRelation record);

    int insertSelective(UserRelation record);

    List<UserRelation> selectByExample(UserRelationExample example);

    int updateByExampleSelective(@Param("record") UserRelation record, @Param("example") UserRelationExample example);

    int updateByExample(@Param("record") UserRelation record, @Param("example") UserRelationExample example);
}