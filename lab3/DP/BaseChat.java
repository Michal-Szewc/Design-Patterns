package DP;

import java.io.*;
import java.net.*;
import java.util.Collection;
import java.util.HashMap;

import static java.lang.Thread.interrupted;

// message types
//  H - hello       - broadcast that you join chat
//  W - welcome     - answer to hello broadcast
//  D - disconnect  - message that you are disconnecting from chat
//  B - broadcast   - user broadcast message
//  M - message     - normal message from user

// hello message structure
// [H][:][PORT][SPACE][NAME]

// welcome message structure
// [W][:][PORT][SPACE][NAME]

// disconnect message structure
// [D][:][NAME]

// broadcast message structure
// [B][:][MSG]

// message structure
// [M][:][MSG]

public class BaseChat {
    private final String clientName;
    private final HashMap<String, Integer> clientMap = new HashMap<>();
    public ServerSocket ss;
    private int port;

    private static final int max_name_len = 25;
    private static final int min_port_num = 6000;
    private static final int max_port_num = 6099;

    public BaseChat(String _clientName) throws Exception {
        clientName = _clientName.substring(0, Math.min(_clientName.length(), max_name_len));
        port = -1;
        for (int i = min_port_num; i <= max_port_num; i++) {
            try {
                ss = new ServerSocket(i);
                port = i;
                break;
            } catch (IOException ignored) {}
        }
        if(port == -1) {
            throw new Exception("Couldn't join chat - No available port");
        }
    }

    // chat works only on this device
    public void sendMessage(int port, String msg) throws IOException {
        try (Socket ss = new Socket("127.0.0.1", port)) {
            DataOutputStream out = new DataOutputStream(ss.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException ignored) {}
    }

    public void sendMessage(String receiverName, String msg) throws IOException {
        if (!clientMap.containsKey(receiverName)) {
            System.out.println("Couldn't find user with that name");
        }
        else {
            sendMessage(clientMap.get(receiverName), msg);
        }
    }

    public void broadcast(String msg) {
        for (int i = min_port_num; i <= max_port_num; i++) {
            if (i != port) {
                try {
                    sendMessage(i, msg);
                } catch (IOException ignored) {}
            }
        }
    }

    private String receiveMessage() throws IOException {
        String msg = "";
        try {
            Socket senderSocket = ss.accept();
            DataInputStream senderInStream = new DataInputStream(senderSocket.getInputStream());
            msg = senderInStream.readUTF();

            senderSocket.getInputStream().close();
            senderSocket.close();
        }
        catch (SocketException ignored) {}
        return msg;
    }

    public String checkMessages() {
        try {
            String msg = receiveMessage();
            if (msg.length() < 1)
                return "";

            switch (msg.charAt(0)) {
                case 'h':
                case 'w':
                    return onIntroduction(msg);
                case 'm':
                    return "message " + msg.substring(2);
                case 'd':
                    String name = getSenderName(msg, 2);
                    clientMap.remove(name);
                    return name + " left the chat";
                case 'b':
                    return "broadcast " + msg.substring(2);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "unrecognized message received";
    }

    private String getSenderName(String msg, int nameIndex) {
        char[] name = new char[max_name_len];
        msg.getChars(nameIndex, msg.length(), name, 0);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < max_name_len; i++) {
            if (name[i] == 0)
                break;
            stringBuilder.append(name[i]);
        }
        return stringBuilder.toString();
    }

    public String getActiveUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : clientMap.keySet()) {
            stringBuilder.append(name);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String onIntroduction(String msg) {
        char[] portT = new char[4];
        msg.getChars(2, 6, portT, 0);
        int port = Integer.parseInt(String.valueOf(portT));

        String name = getSenderName(msg, 7);

        // Add new user to map of clients
        clientMap.put(name, port);
        if (msg.charAt(0) == 'h') {
            try {

                // if new user (hello signal) send welcome signal
                sendMessage(port, "w:" + port + " " + clientName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ("new user joined: " + name);
    }

    public Thread getListener() {
        return new Thread(() -> {
            while(!interrupted()) {
                System.out.println(this.checkMessages());
            }
        });
    }

    public int getPort() {
        return port;
    }
    public Collection<Integer> getPorts() {
        return clientMap.values();
    }
    public String getClientName() {
        return clientName;
    }

    public static int getMaxNameLen() {
        return max_name_len;
    }
}
