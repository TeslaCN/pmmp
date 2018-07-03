package ltd.scau.mybatis.mapper;

import java.util.List;
import ltd.scau.mybatis.po.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Image record);

    Image selectByPrimaryKey(Long id);

    List<Image> selectAll();

    int updateByPrimaryKey(Image record);
}