package com.example.projectmedilog.client;
import com.example.projectmedilog.chatController;
import com.example.projectmedilog.usertype;
import com.example.projectmedilog.util.NetworkInformation;
import com.example.projectmedilog.util.NetworkUtil;

import java.io.IOException;
import java.util.Arrays;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private NetworkInformation networkInformation;
    public ReadThreadClient(NetworkInformation networkInformation) {
        this.networkInformation = networkInformation;
    }

    public void run() {
        try {
            while (true) {
                try {
                    Message message = (Message) networkInformation.getNetworkUtil().read();
                    if (message == null) {
                        continue;
                    }
                    if (message.getFrom().equalsIgnoreCase("server")) {
                        System.out.println("Your Inbox:");
                        String[] messages = message.getText().split("~");
                        Arrays.stream(messages).forEach(System.out::println);
                    } else {
                        System.out.println("1From: " + message.getFrom() + " Message: " + message.getText());
                        //make chatController instance
                        //call showMessage method
                        chatController chatController = new chatController();

//                        chatController.ChatMessage msg = new chatController.ChatMessage(message.getFrom(), usertype.getUserName(), message.getText(), false);
                        chatController.showMessage(message.getFrom(), message.getText());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
//        finally {
//            try {
//                networkUtil.closeConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
