package ltd.scau.dao;

import ltd.scau.mybatis.po.Message;

import java.util.List;

/**
 * @author Weijie Wu
 */
public interface MessageDao {

    List<Message> findAllMyMessage(Long id);

    List<Message> findMessagesByUsersId(Long aId, Long bId);

    Message sendMessage(Message message);

}
