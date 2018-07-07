package ltd.scau.springframework.controller;

import com.github.pagehelper.PageHelper;
import ltd.scau.dao.MessageDao;
import ltd.scau.dto.MessageListDto;
import ltd.scau.dto.ResultDto;
import ltd.scau.mybatis.po.Message;
import ltd.scau.mybatis.po.User;
import ltd.scau.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Weijie Wu
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @RequestMapping("/all")
    public MessageListDto allMessages(@SessionAttribute(Constant.SESSION_USER) User me, MessageListDto dto) {

        PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        dto.setMessages(messageDao.findAllMyMessage(me.getId()));

        return dto;
    }

    @RequestMapping(value = "/send/{id}", method = RequestMethod.POST)
    public ResultDto send(@SessionAttribute(Constant.SESSION_USER) User me, @PathVariable Long id, @Valid Message message) {
        ResultDto dto = new ResultDto();

        message.setFromUserId(me.getId());
        message.setToUserId(id);
        messageDao.sendMessage(message);

        if (message.getId() == null) {

            dto.setCode(1);
            dto.setMessage("ERROR");

        } else {

            dto.setCode(0);
            dto.setMessage(Constant.SUCCESS);
        }

        return dto;
    }

    @RequestMapping("/with/{id}")
    public MessageListDto messagesWith(@SessionAttribute(Constant.SESSION_USER) User me, @PathVariable Long id, MessageListDto dto) {

        dto.setMessages(messageDao.findMessagesByUsersId(me.getId(), id));

        return dto;
    }

}
