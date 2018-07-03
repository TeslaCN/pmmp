package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.Email;

public interface EmailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Email record);

    Email selectByPrimaryKey(Long id);

    List<Email> selectAll();

    int updateByPrimaryKey(Email record);
}