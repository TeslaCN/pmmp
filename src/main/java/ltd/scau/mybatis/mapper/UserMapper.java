package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}