package ltd.scau.dao;

import ltd.scau.mybatis.po.Image;

import java.util.List;

/**
 * @author Weijie Wu
 */
public interface ImageDao {

    Image insert(Image image);

    List<Image> findImageByUserId(Long id);

}
