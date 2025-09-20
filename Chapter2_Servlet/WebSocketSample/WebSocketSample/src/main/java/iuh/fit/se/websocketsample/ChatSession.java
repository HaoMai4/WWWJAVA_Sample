package iuh.fit.se.websocketsample;


import jakarta.annotation.PostConstruct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChatSession implements Serializable {
    private Map<String, String> users = new HashMap<>();

    public ChatSession(){}

    @PostConstruct
    public void init(){
        users = new HashMap<>();
    }

    /**
     * @return the users
     */
    public Map<String, String> getUsers() {
        return users;
    }

    /**
     * @param for the users
     */
    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
