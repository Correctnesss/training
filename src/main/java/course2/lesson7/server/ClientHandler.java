package course2.lesson7.server;

import course2.lesson7.constans.Constans;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

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
                    //Дописать проверку что такого ника нет в чате
                    //Авторизовались
                    name = nick;
                    sendMessage(Constans.AUTH_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чат");
                    server.subscribe(this);
                    return;
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
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constans.END_COMMAND)) {
                break;
            }
            server.broadcastMessage(name + ": " + messageFromClient);
        }
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
