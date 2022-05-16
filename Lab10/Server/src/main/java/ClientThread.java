import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import freemarker.template.TemplateException;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

class ClientThread extends Thread {
    private static Integer TIMEOUT_TIME = 360000;
    private Socket socket = null;
    private boolean logged = false;
    private String username = "";
    Server server;

    public ClientThread (Socket socket,Server server) throws SocketException {
        this.socket = socket;
        this.server = server;
        socket.setSoTimeout(TIMEOUT_TIME);
    }
    public void run () {
        try {
            while (true) {
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                // Send the response to the oputput stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String raspuns = "";
                if (request.compareTo("stop") == 0) {
                    raspuns = "Server shutdown...";
                    out.println(raspuns);
                    out.flush();
                    Server.stopServerGracefully();
                } else if (request.equals("exit")) {
                    System.out.println("Client disconnected. Stopping thread...");
                    return;
                }
                if (!logged) {
                    if (request.length() > 8 && request.substring(0, 8).equals("register")) {
                        String nameReceived = request.substring(9);
                        String regex = "^.*[a-zA-Z0-9]+.*$";
                        if(nameReceived.matches(regex)){
                            if(server.findClient(nameReceived)>-1)
                            {
                                raspuns = "User with name "+nameReceived+" is already register";
                            } else
                            {   server.register(nameReceived);
                                System.out.println(nameReceived);
                                raspuns = "User created!";
                            }}
                        else
                            raspuns = "A username must contains at least one letter.";
                        out.println(raspuns);
                        out.flush();
                    } else if (request.length() > 5 && request.substring(0, 5).equals("login")) {
                        String nameReceived = request.substring(6);
                        if(server.findClient(nameReceived)>-1)
                        {
                            raspuns = "User "+nameReceived+" logged in!";
                            username = nameReceived;
                            logged = true;
                            server.login(nameReceived);
                        } else {
                            raspuns = "User with name "+nameReceived+" does not exist!";
                        }
                        out.println(raspuns);
                        out.flush();
                    } else {
                        raspuns = "You are not logged in!";
                        out.println(raspuns);
                        out.flush();
                    }
                }
                else if (request.length() > 6 && request.substring(0, 6).equals("friend")) {
                    String friendList = request.substring(7);
                    var friendArray = friendList.split(" ");

                    List<String> added = new ArrayList<>();
                    List<String> existent = new ArrayList<>();
                    List<String> notFound = new ArrayList<>();

                    for (var friend : friendArray) {
                        //verifica daca sunt deja prieteni
                        if (server.areFriends(username,friend)) {
                            existent.add(friend);
                        } else {
                            //verifica daca exista astfel de user
                            if (server.findClient(friend)>-1) {
                                //trebuie sa-l adaug pe username la friend list de friends si invers
                                server.addNewFriend(username,friend);
                                added.add(friend);
                            } else {
                                notFound.add(friend);
                            }
                        }
                    }

                    if (added.size() > 0) {
                        raspuns = "The following friends were added: ";
                        for (String name : added) {
                            raspuns += name + " ";
                        }
                    }
                    if (existent.size() > 0) {
                        raspuns += " | The following friends were already in friends list: ";
                        for (String name : existent) {
                            raspuns += name + " ";
                        }
                    }
                    if (notFound.size() > 0) {
                        raspuns += " | The following names are not registered: ";
                        for (String name : notFound) {
                            raspuns += name + " ";
                        }
                    }
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                }
                else if (request.length() > 4 && request.substring(0, 4).equals("send")) {
                    String messageText = request.substring(5);
                    //aflu lista tuturor prietenilor
                    List<Client> friendList = server.getFriends(username);
                    //adaug mesajul la fiecare prieten
                    friendList.forEach(f -> f.addMessage(messageText));

                    raspuns = "Sending message: \"" + messageText + "\" to the following friends: ";
                    for (Client f : friendList)
                        raspuns += f.getName() + " ";
                    out.println(raspuns);
                    out.flush();
                }
                else if (request.length()>0 && request.equals("read")) {
                    List<String> messageList = server.getMessages(username);
                    if(messageList.isEmpty())
                    {raspuns ="There are no messages!";}
                    else
                    {
                        raspuns ="Messages: ";
                        for(String s:messageList)
                            raspuns += s +" ";
                    }
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                }else if(request.length()>0 && request.equals("graph")){
 /*
                    G - graf neorientat ponderat
                    V - Toti userii
                    E - Relatiile de prietenie
                    C - Nr de mesaje schimbate intre cei doi
                     */
                    Graph<String, MyEdge> g = new SimpleWeightedGraph<>(MyEdge.class);

                    for (Client client1: server.clientList){
                        g.addVertex(client1.getName());
                    }

                    for (Client client1 : server.clientList){

                        for (Client client2: server.getFriends(client1.getName())){
                            g.addEdge(client1.getName(), client2.getName());

                        }
                    }

                    JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<>(g);
                    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
                    layout.execute(graphAdapter.getDefaultParent());

                    BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 10, Color.WHITE, true, null);
                    File imgFile = new File("src/main/java/graph.png");
                    ImageIO.write(image, "PNG", imgFile);

                    out.println(g);
                    out.flush();

                    Report report = new Report(server.clientList, imgFile.getPath());
                    report.call();


                }
                else if ((logged==true) && request.equals("upload")) {
                    STFTPClient stftpClient = new STFTPClient();
                    stftpClient.connect();
                    stftpClient.upload("src/main/java/graph.png", "/");
                    stftpClient.download("/", "src/main/java/graph2.png");
                    stftpClient.disconnect();
                    raspuns = "File uploaded";
                    out.println(raspuns);
                    out.flush();
                }
                else {
                    raspuns = "Not a valid request ";
                    out.println(raspuns);
                    out.flush();
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.printf("%d seconds passed since the last request. Timeout.", TIMEOUT_TIME / 1000);
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
//        } catch (TemplateException e) {
//            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Thread stopped");
                Server.threadCount--;
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}