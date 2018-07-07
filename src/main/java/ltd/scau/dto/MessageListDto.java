package ltd.scau.dto;

import ltd.scau.mybatis.po.Message;

import java.util.List;

/**
 * @author Weijie Wu
 */
public class MessageListDto extends ListDto {

    protected List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessageListDto{" +
                "messages=" + messages +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
