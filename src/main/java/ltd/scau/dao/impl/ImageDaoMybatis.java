package ltd.scau.dao.impl;

import ltd.scau.dao.ImageDao;
import ltd.scau.mybatis.mapper.ImageMapper;
import ltd.scau.mybatis.po.Image;
import ltd.scau.mybatis.po.ImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Weijie Wu
 */
@Component
public class ImageDaoMybatis implements ImageDao {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Image insert(Image image) {
        imageMapper.insert(image);
        return image;
    }

    @Override
    public List<Image> findImageByUserId(Long id) {
        ImageExample example = new ImageExample();

        example.createCriteria().andUserIdEqualTo(id);

        return imageMapper.selectByExample(example);
    }
}
