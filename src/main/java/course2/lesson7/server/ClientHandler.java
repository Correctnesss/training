package course2.lesson7.server;

import course2.lesson7.constans.Constans;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private BdAuthService bdAuthService;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean clientConnection;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentification();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблема при создании обработчика");
        }
    }

    // auth login pass

    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();

            if (str.startsWith(Constans.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");   //3
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);


                if (nick != null) {
//                    if (server.clients1.isEmpty()) {
//                        name = nick;
//                        sendMessage(Constans.AUTH_COMMAND + " " + nick);
//                        server.broadcastMessage(nick + " вошел в чат");
//                        server.subscribe(this);
//                        server.listUsers(nick);
//                        return;
//                    }
                    for (String client : server.clients1) {
                        clientConnection = client.equals(nick);
                        if (clientConnection) {
                            break;
                        }
                    }
                    if (clientConnection) {
                        sendMessage("Данный пользователь уже в сети");
                    } else {
                        name = nick;
                        sendMessage(Constans.AUTH_OK_COMMAND + " " + name);
                        server.broadcastMessage(nick + " вошел в чат");
                        server.subscribe(this);
                        server.listUsers(name);
                        return;
                    }
                    //Дописать проверку что такого ника нет в чате
                    //Авторизовались
//                    name = nick;
//                    sendMessage(Constans.AUTH_COMMAND + " " + nick);
//                    server.broadcastMessage(nick + " вошел в чат");
//                    server.subscribe(this);
//                    server.listUsers(nick);
//                    return;
                } else {
                    sendMessage("Неверные логин/пароль");
                }

            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            //hint: можем получать команду
            if (messageFromClient.startsWith(Constans.CLIENTS_LIST_COMMAND)) {
                server.getActiveClients();
            } else {
                System.out.println("Сообщение от " + name + ": " + messageFromClient);

                if (messageFromClient.startsWith("/change")) {
                    String[] tokens = messageFromClient.split("\\s+");
                    server.changeNick(tokens[1], name);
                    server.broadcastMessage(name + " изменил ник на " + tokens[1]);
                    name = tokens[1];
                } else if (messageFromClient.equals(Constans.END_COMMAND)) {
                    sendMessage(Constans.END_COMMAND);
                    break;
                } else if (messageFromClient.startsWith(Constans.AUTH_COMMAND)) {
                    continue;
                } else {
                    server.broadcastMessage(name + ": " + messageFromClient);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            out.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            socket.close();
        } catch (IOException ex) {
            //ignore
        }
    }
}
