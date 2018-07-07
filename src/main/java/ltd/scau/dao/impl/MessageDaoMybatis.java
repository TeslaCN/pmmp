package ltd.scau.dao.impl;

import ltd.scau.dao.MessageDao;
import ltd.scau.mybatis.mapper.MessageMapper;
import ltd.scau.mybatis.po.Message;
import ltd.scau.mybatis.po.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Weijie Wu
 */
@Component
public class MessageDaoMybatis implements MessageDao {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> findAllMyMessage(Long id) {
        MessageExample example = new MessageExample();

        example.createCriteria().andFromUserIdEqualTo(id);
        example.or().andToUserIdEqualTo(id);

        return messageMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Message> findMessagesByUsersId(Long aId, Long bId) {
        MessageExample example = new MessageExample();

        example.createCriteria().andFromUserIdEqualTo(aId).andToUserIdEqualTo(bId);
        example.or().andToUserIdEqualTo(bId).andFromUserIdEqualTo(aId);

        return messageMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Message sendMessage(Message message) {
        messageMapper.insert(message);
        return message;
    }
}
