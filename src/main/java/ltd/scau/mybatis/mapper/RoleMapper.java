package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}