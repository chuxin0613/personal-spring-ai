package org.example.springai.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

/**
 * 会话消息 VO
 *
 * @author 初心
 */
@NoArgsConstructor
@Data
public class MessageVO {

    private String role;

    private String content;

    public MessageVO(Message message) {
        switch (message.getMessageType()) {
            case USER -> role = "user";
            case ASSISTANT -> role = "assistant";
            case SYSTEM -> role = "system";
            case TOOL -> role = "tool";
            default -> role = "unknown";
        }
        this.content = message.getText();
    }
}
