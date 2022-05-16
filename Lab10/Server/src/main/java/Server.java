import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    // Define the port on which the server is listening
    public List<Client> clientList = new ArrayList<>();
    public List<Client> clientLoginList = new ArrayList<>();
    public static final int PORT = 8100;
    boolean running = true;
    static private boolean shouldShutDown = false;
    static public volatile int threadCount = 0;
    private static ServerSocket serverSocket = null;
//
//    static public void stopServer() { //opreste brusc
//        System.out.println("Server was stopped by a client");
//        System.exit(0);
//    }

    static public void stopServerGracefully() {
        System.out.println("Server initiated by a client gracefully");
        shouldShutDown = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Server() throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                new ClientThread(socket, this).start();
                threadCount++;
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
        System.out.println("Number of threads: " + threadCount);
        while (threadCount > 0) {
            Thread.onSpinWait();
        }
        System.out.println("Number of threads: " + threadCount);
    }

//    public void setRunning(boolean running) {
//        this.running = running;
//    }

    public int findClient(String name)
    {
        for(int i=0; i<clientList.size(); i++)
            if(clientList.get(i).getName().equals(name))
                return i;
        return -1;
    }

//    public boolean findLoginClient(String name)
//    {
//        for(Client c:clientLoginList)
//            if(c.getName().equals(name))
//                return true;
//        return false;
//    }

    public void register(String name){
        clientList.add(new Client(name));
    }
    public void login(String name){
        for(Client c: clientList)
            if(c.getName().equals(name))
                clientLoginList.add(c);
    }
    public List<Client> getFriends(String name)
    { List<Client> friendList = new ArrayList<>();
        for(Client c: clientLoginList)
            if(c.getName().equals(name))
            { friendList = c.getFriends();break;}
        return friendList;
    }
    public boolean areFriends(String name1, String name2)
    {
        for(Client c: clientLoginList)
            if(c.getName().equals(name1))
            { for(Client f: c.getFriends())
                if(f.getName().equals(name2))
                    return true;}
        return false;
    }
    public void addNewFriend(String name1, String name2) {
        int f1 = this.findClient(name1);
        int f2 = this.findClient(name2);
        clientList.get(f1).addFriend(clientList.get(f2));
        clientList.get(f2).addFriend(clientList.get(f1));
    }
    public List<String> getMessages(String name)
    { List<String> mess = new ArrayList<>();
        for(Client c: clientLoginList)
            if(c.getName().equals(name))
                mess=c.getMessages();
        return mess;
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server ();
    }
}