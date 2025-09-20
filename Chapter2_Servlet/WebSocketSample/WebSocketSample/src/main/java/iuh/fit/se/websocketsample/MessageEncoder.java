package iuh.fit.se.websocketsample;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(Message object) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        Text.super.init(endpointConfig);
        System.out.println("Initializing message encoder");
    }

    @Override
    public void destroy() {
        Text.super.destroy();
        System.out.println("Destroyed message encoder");
    }
}
