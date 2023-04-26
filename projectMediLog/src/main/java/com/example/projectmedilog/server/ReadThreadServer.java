package com.example.projectmedilog.server;

import com.example.projectmedilog.client.Message;
import com.example.projectmedilog.util.NetworkInformation;

import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private final HashMap<String, NetworkInformation> clientNetworkInformationMap;
    private final NetworkInformation senderNetworkInformation;

    public ReadThreadServer(HashMap<String, NetworkInformation> clientNetworkInformationMap, NetworkInformation senderNetworkInformation) {
        this.clientNetworkInformationMap = clientNetworkInformationMap;
        this.senderNetworkInformation = senderNetworkInformation;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = (Message) senderNetworkInformation.getNetworkUtil().read();
                if (message == null) {
                    continue;
                }
                NetworkInformation receiverNetworkInformation = clientNetworkInformationMap.get(message.getTo());

                System.out.println(receiverNetworkInformation);

                if (message.getText().equalsIgnoreCase("inbox") && message.getTo().equalsIgnoreCase("server")) {
                    StringBuilder inboxMessages = new StringBuilder();
                    for (String msg : senderNetworkInformation.getInbox()) {
                        inboxMessages.append(msg).append("~");
                    }
                    Message inboxMessage = new Message();
                    inboxMessage.setFrom("server");
                    inboxMessage.setTo(message.getFrom());
                    inboxMessage.setText(inboxMessages.toString());
                    senderNetworkInformation.getNetworkUtil().write(inboxMessage);
                } else {
                    receiverNetworkInformation.getInbox().add("2From: " + message.getFrom() + " Message:" + message.getText());
                    receiverNetworkInformation.getNetworkUtil().write(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }
}