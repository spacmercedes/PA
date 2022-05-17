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
import java.util.ArrayList;
import java.util.List;

class ClientThread extends Thread {
    private static Integer TIMEOUT_TIME = 3600_000;
    private Socket socket = null;
    private boolean logged = false;
    private String username = "";
    Server server;

    public ClientThread (Socket socket,Server server) throws SocketException {
        this.socket = socket ;
        this.server = server;
        socket.setSoTimeout(TIMEOUT_TIME);
    }

    public void run () {
        try {
            while (true) {
                // Primeste cererea din input de la client la server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                //Trimite raspunsul in output, de la server la client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String raspuns = "";
                if (request.compareTo("stop") == 0) {
                    raspuns = "Serverul a fost inchis...";
                    out.println(raspuns);
                    out.flush();
                    Server.stopServerGracefully();
                } else if (request.equals("exit")) {
                    System.out.println("Clientul s-a deconectat. Opreste thread...");
                    return;
                }
                if (!logged) {
                    if (request.length() > 8 && request.substring(0, 8).equals("register")) {
                        String nameReceived = request.substring(9);
                        String regex = "^.*[a-zA-Z0-9]+.*$";
                        if(nameReceived.matches(regex)){
                            if(server.findClient(nameReceived)>-1)
                            {
                                raspuns = "User cu numele "+nameReceived+" este deja inregistrat";
                            } else
                            {   server.register(nameReceived);
                                System.out.println(nameReceived);
                                raspuns = "User created!";
                            }}
                        else
                            raspuns = "Username-ul trebuie sa aiba cel putin o litera.";
                        out.println(raspuns);
                        out.flush();
                    } else if (request.length() > 5 && request.substring(0, 5).equals("login")) {
                        String nameReceived = request.substring(6);
                        if(server.findClient(nameReceived)>-1)
                        {
                            raspuns = "Userul "+nameReceived+" este logat!";
                            username = nameReceived;
                            logged = true;
                            server.login(nameReceived);
                        } else {
                            raspuns = "Userul cu numele "+nameReceived+" nu exista!";
                        }
                        out.println(raspuns);
                        out.flush();
                    } else {
                        raspuns = "Nu esti logat!";
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
                        //Verifica daca sunt deja prieteni
                        if (server.areFriends(username,friend)) {
                            existent.add(friend);
                        } else {
                            //Verifica daca exista astfel de user
                            if (server.findClient(friend)>-1) {
                                //Trebuie sa-l adaug pe username la friend list de friends si invers
                                server.addNewFriend(username,friend);
                                added.add(friend);
                            } else {
                                notFound.add(friend);
                            }
                        }
                    }

                    if (added.size() > 0) {
                        raspuns = "Au fost adaugati prietenii: ";
                        for (String name : added) {
                            raspuns += name + " ";
                        }
                    }
                    if (existent.size() > 0) {
                        raspuns += " | Acesti prieteni sunt deja in lista: ";
                        for (String name : existent) {
                            raspuns += name + " ";
                        }
                    }
                    if (notFound.size() > 0) {
                        raspuns += " | Aceste username-uri nu sunt inregistrate : ";
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
                    //Aflu lista tuturor prietenilor
                    List<Client> friendList = server.getFriends(username);
                    //Adaug mesajul la fiecare prieten
                    friendList.forEach(f -> f.addMessage(messageText));

                    raspuns = "Se trimite mesajul: \"" + messageText + "\" la urmatorii prieteni: ";
                    for (Client f : friendList)
                        raspuns += f.getName() + " ";
                    out.println(raspuns);
                    out.flush();
                }
                else if (request.length()>0 && request.equals("read")) {
                    List<String> messageList = server.getMessages(username);
                    if(messageList.isEmpty())
                    {raspuns ="Nu exista mesaje!";}
                    else
                    {
                        raspuns ="Mesaje: ";
                        for(String s:messageList)
                            raspuns += s +" ";
                    }
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                }
                else if(request.length()>0 && request.equals("graph")){

                    Graph<String, MyEdge> g = new SimpleWeightedGraph<>(MyEdge.class);

                    for (Client client : server.clientList) {
                        g.addVertex(client.getName());
                    }

                    for (Client client1 : server.clientList) {
                        for (Client client2 : server.getFriends(client1.getName())) {
                            g.addEdge(client1.getName(), client2.getName());
                        }
                    }

                    JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<>(g);
                    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
                    layout.execute(graphAdapter.getDefaultParent());

                    BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 7, Color.PINK, true, null);
                    File imgFile = new File("src/main/java/graph.png");
                    ImageIO.write(image, "PNG", imgFile);

                    out.println(g);
                    out.flush();

                    Report report = new Report(server.clientList, imgFile.getPath());
                    report.call();
                }
                else if (request.length()>0 && request.equals("upload")) {
                    STFTPClient stftpClient = new STFTPClient();
                    stftpClient.connect();
                    stftpClient.upload("C:/Users/spacm/Documents/GitHub/PA/Lab10/Server/representation.html", "/");
                    stftpClient.download("/", "C:/Users/spacm/Documents/GitHub/PA/Lab10/Server/representation2.html");
                    stftpClient.disconnect();
                    raspuns = "File uploaded";
                    out.println(raspuns);
                    out.flush();
                }
                else {
                    raspuns = "Cerere invalida! ";
                    out.println(raspuns);
                    out.flush();
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.printf("%d secunde au trecut de la ultima cerere. Timeout.", TIMEOUT_TIME / 1000);
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        catch (SftpException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Thread-ul s-a oprit");
                Server.threadCount--;
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}