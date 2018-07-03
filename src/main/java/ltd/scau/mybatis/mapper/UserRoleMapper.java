package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
}