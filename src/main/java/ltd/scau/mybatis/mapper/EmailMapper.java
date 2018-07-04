package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.Email;
import ltd.scau.mybatis.po.EmailExample;
import org.apache.ibatis.annotations.Param;

public interface EmailMapper {
    long countByExample(EmailExample example);

    int deleteByExample(EmailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Email record);

    int insertSelective(Email record);

    List<Email> selectByExampleWithBLOBs(EmailExample example);

    List<Email> selectByExample(EmailExample example);

    Email selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByExampleWithBLOBs(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByExample(@Param("record") Email record, @Param("example") EmailExample example);

    int updateByPrimaryKeySelective(Email record);

    int updateByPrimaryKeyWithBLOBs(Email record);

    int updateByPrimaryKey(Email record);
}