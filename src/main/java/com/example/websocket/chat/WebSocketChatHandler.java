//package com.example.websocket.chat;
//
//import com.example.websocket.dto.ChatMessageDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class WebSocketChatHandler extends TextWebSocketHandler {
//
//    private final ObjectMapper mapper;
//
//    // 현재 연결된 세션들
//    private final Set<WebSocketSession> sessions = new HashSet<>();
//
//    // chatRoomId: {session1, session2, ...}
//    private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();
//
//    // 소켓 연결 확인
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//
//        log.info("{} 열결됨", session.getId());
//        sessions.add(session);
//    }
//
//    // 소켓 통신 시 메세세지의 전송을 다루는 부분
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
//        String payload = message.getPayload();
//        log.info("payload: {}", payload);
//
//        //페이로드 -> chatMessageDto로 변환
//        try {
//            ChatMessageDto chatMessageDto = mapper.readValue(payload, ChatMessageDto.class);
//            log.info("chatMessageDto: {}", chatMessageDto.toString());
//
////            Long chatRoomId = chatMessageDto.getChatRoomId();
//
//            // 메모리 상에 채팅방에 대한 세션 없으면 만들어줌
//            if (!chatRoomSessionMap.containsKey(chatRoomId)) {
//                chatRoomSessionMap.put(chatRoomId, new HashSet<>());
//            }
//
//            Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);
//
//            // message에 담긴 타입을 확인한다.
//            // 이때 message에서 getType으로 가져온 내용이
//            // chatDTO의 열거형인 MessageType 안에 있는 ENTER과 동일한 값이라면
//            if (chatMessageDto.getMessageType().equals(ChatMessageDto.MessageType.ENTER)) {
//                // sessions에 넘어온 session을 담고,
//                chatRoomSession.add(session);
//            }
//
//            if (chatRoomSession.size() >= 3) {
//                removeClosedSession(chatRoomSession);
//            }
//
//            sendMessageToChatRoom(chatMessageDto, chatRoomSession);
//        } catch (Exception e) {
//            log.error("메세지를 ChatMessageDto로 변환하는데 실패했습니다.", e);
//        }
//    }
//
//    // 세션을 제거하는 메소드
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//
//        sessions.remove(session);
//        log.info("{} 연결 끊김", session.getId());
//    }
//
//    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
//        chatRoomSession.removeIf(session -> !sessions.contains(session));
//    }
//
//    private void sendMessageToChatRoom(ChatMessageDto chatMessageDto, Set<WebSocketSession> chatRoomSession) {
//        chatRoomSession.forEach(session -> sendMessage(session, chatMessageDto));
//    }
//
//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try {
//
//            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
//
//        } catch (Exception e) {
//            log.error("메세지를 전송하는데 실패했습니다.", e);
//        }
//    }
//
//}
