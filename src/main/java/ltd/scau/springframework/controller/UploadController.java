package ltd.scau.springframework.controller;

import ltd.scau.dao.ImageDao;
import ltd.scau.dto.ResultDto;
import ltd.scau.mybatis.po.Image;
import ltd.scau.mybatis.po.User;
import ltd.scau.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Weijie Wu
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    private static final Log logger = LogFactory.getLog(UploadController.class);

    @Autowired
    private ImageDao imageDao;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultDto upload(@SessionAttribute(Constant.SESSION_USER) User me, MultipartFile file) {
        ResultDto dto = new ResultDto();

        String suffix = "";
        Pattern fileTypeMatcher = Pattern.compile(".*?(\\.\\w+$)");

        String fileName = file.getOriginalFilename();
        Matcher m = fileTypeMatcher.matcher(fileName);
        if (m.find()) {
            suffix = m.group(1);
        }

        String saveName = String.format("%s_%s%s", me.getId(), System.currentTimeMillis(), suffix);

        try {
            file.transferTo(new File(saveName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(String.format("Upload: %s, size = %d, save as: %s", file.getOriginalFilename(), file.getSize(), saveName));

        Image image = new Image();
        image.setUserId(me.getId());
        image.setUrl(saveName);
        imageDao.insert(image);

        dto.setCode(0);
        dto.setMessage(Constant.SUCCESS);

        return dto;
    }
}
