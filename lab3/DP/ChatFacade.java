package DP;

import java.io.IOException;

public class ChatFacade {
    private final BaseChat baseChat;
    Thread chatListener;

    public ChatFacade(String name) throws Exception {
        baseChat = new BaseChat(name);

        // Start listening to chat
        chatListener = baseChat.getListener();
        chatListener.start();

        // Hello
        baseChat.broadcast("h:" + baseChat.getPort() + " " + baseChat.getClientName());
    }

    public void close() {

        // Disconnect / Goodbye signal
        baseChat.broadcast("d:" + baseChat.getClientName());

        // Close socket
        try {
            baseChat.ss.close();
        } catch (IOException ignored) {
        }

        // Stop listening to chat
        chatListener.interrupt();
        try {
            chatListener.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // broadcast from client side only sends messages to active clients, but facade
    // has access to normal broadcast and can use it to for example send hello signal.
    public void broadcast(String msg) throws IOException {
        for(Integer port: baseChat.getPorts()){
            baseChat.sendMessage(port, "b: " + baseChat.getClientName() + ": " + msg);
        }
    }

    public void sendMessage(String targetName, String msg) {
        try {
            baseChat.sendMessage(targetName, "m:" + baseChat.getClientName() + ": " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getActiveUsers() {
        String e = baseChat.getActiveUsers();
        if(e.length() < 1)
            System.out.println("No active users in the chat");
        else
            System.out.println(e);
    }

    public static int getMaxNameLen() {
        return BaseChat.getMaxNameLen();
    }
}
