package course2.lesson7.client;

import course2.lesson7.constans.Constans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {


    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;


    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        prepareUI();
    }

    private void openConnection() throws IOException, InterruptedException {
        socket = new Socket(Constans.SERVER_ADDRESS, Constans.SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        long a = System.currentTimeMillis();

        Thread auth = new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dataInputStream.readUTF();
                    if (messageFromServer.equals("/end")) {
                        textArea.append("Соединение с сервером разорвано");
                        textArea.append("\n");
                        break;
                    }
//                    else if (System.currentTimeMillis() - a > 1111000) {
//                        System.out.println(a);
//                        break;
//                    }
                    else if (messageFromServer.startsWith(Constans.AUTH_OK_COMMAND)) {
                        String[] tokens = messageFromServer.split("\\s");
                        this.login = tokens[1];
                        textArea.append("Успешно авторизован как " + login);
                        textArea.append("\n");
                        dataOutputStream.writeUTF(Constans.AUTH_OK_COMMAND);
                    } else if (messageFromServer.startsWith(Constans.CLIENTS_LIST_COMMAND)) {
                        //

                    } else {
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                    }
                }
                textArea.append("Соединение разорвано");
                textField.setEnabled(false);
                closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(7000);
//                while (true) {
//                    if (dataInputStream.readUTF().startsWith("/authok")) {
//                        break;
//                    } else closeConnection();
//                }
////                textArea.append("Соединение разорвано");
////                textField.setEnabled(false);
////                closeConnection();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
        auth.start();
//        thread.start();

    }

    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {

        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {

        }

        try {
            socket.close();
        } catch (Exception ex) {

        }
    }

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void prepareUI() {
        setBounds(200, 200, 500, 500);
        setTitle("EchoClient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);


        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("Send");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        JPanel loginPanel = new JPanel(new BorderLayout());
        JTextField loginField = new JTextField();
        loginPanel.add(loginField, BorderLayout.WEST);
        JTextField passField = new JTextField();
        loginPanel.add(passField, BorderLayout.CENTER);
        JButton authButton = new JButton("Авторизоваться");
        loginPanel.add(authButton, BorderLayout.EAST);
        add(loginPanel, BorderLayout.NORTH);

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constans.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EchoClient());
    }


}
