package iuh.fit.se.websocketsample;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(EndpointConfig endpointConfig) {
        Text.super.init(endpointConfig);
        System.out.println("Initializing message decoder");
    }

    @Override
    public Message decode(String jsonMessage) throws DecodeException {
        try {
            return objectMapper.readValue(jsonMessage, Message.class);
        } catch (Exception e) {
            throw new DecodeException(jsonMessage, "Unable to decode JSON to Message", e);
        }
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            objectMapper.readTree(jsonMessage); // chỉ check JSON có hợp lệ không
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void destroy() {
        Text.super.destroy();
        System.out.println("Destroyed message decoder (Jackson)");
    }
}
