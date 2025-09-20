package iuh.fit.se.websocketsample;


import java.io.Serializable;

public class Message implements Serializable {
    private String userName;
    private String message;

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message [userName=" + userName + ", message=" + message + "]";
    }
}
