package org.example.springai.controller;

import lombok.RequiredArgsConstructor;
import org.example.springai.entity.vo.MessageVO;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会话记录 Controller
 *
 * @author 初心
 */
@RestController
@RequestMapping("ai/history")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final JdbcChatMemoryRepository chatMemoryRepository;

    @GetMapping("/")
    public List<String> getChatIdList() {
        return chatMemoryRepository.findConversationIds();
    }

    @GetMapping("/{chatId}")
    public List<MessageVO> getChatDetailsByChatId(@PathVariable String chatId) {
        List<Message> messageList = chatMemoryRepository.findByConversationId(chatId);
        return messageList.stream().map(MessageVO::new).toList();
    }

    @DeleteMapping("/delete/{chatId}")
    public void deleteChatHistory(@PathVariable String chatId) {
        chatMemoryRepository.deleteByConversationId(chatId);
    }
}
