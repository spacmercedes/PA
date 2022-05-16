import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private List<Client> friends = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public Client() { }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name,  List<String> messages) {
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getFriends() {
        return friends;
    }

    public void setFriends(List<Client> friends) {
        this.friends = friends;
    }

    public void addFriend(Client c){ friends.add(c); }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage( String text){messages.add(text);}

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", messages=" + messages +
                '}';
    }
}