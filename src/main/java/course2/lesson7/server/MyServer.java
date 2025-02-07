package course2.lesson7.server;

import course2.lesson7.constans.Constans;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Логика сервера.
 */

public class MyServer {
    public AuthService getAuthService() {
        return authService;
    }

    /**
     * Сервис аутентификации.
     */
    private AuthService authService;


    /**
     * Активные клиенты.
     */

    private List<ClientHandler> clients;

    public List <String> clients1;


    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constans.SERVER_PORT)) {
            authService = new BaseAuthService();//
            authService.start();

            clients = new ArrayList<>();
            clients1 = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения...");
                Socket socket = server.accept();
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

//        clients.forEach(client -> client.sendMessage(message));
        for (ClientHandler client : clients){
            client.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void listUsers(String client){
        clients1.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constans.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream().map(c ->c.getName() )
                .collect(Collectors.joining(" ")));
//        for (ClientHandler clientHandler : clients){
//            sb.append(clientHandler.getName()).append(" ");
//        }
        return sb.toString();
    }
}
