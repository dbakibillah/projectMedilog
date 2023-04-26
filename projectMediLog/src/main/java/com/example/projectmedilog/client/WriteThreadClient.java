package com.example.projectmedilog.client;

import com.example.projectmedilog.util.NetworkInformation;
import com.example.projectmedilog.util.NetworkUtil;


import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {
    private NetworkInformation networkInformation;
    private String clientName;
    private String to;
    private String text;


    public WriteThreadClient(NetworkInformation networkInformation, String clientName, String to, String text) {
        System.out.println(networkInformation + "-1-" + clientName + "-4-" + to + "-3-" + text);
        this.networkInformation = networkInformation;
        this.clientName = clientName;
        this.to = to;
        this.text = text;
    }

    public void run() {
        try {
            //Scanner input = new Scanner(System.in);

//            while (true) {
                //String[] s = input.nextLine().split(",");

//                if (s.length != 2) {
//                    System.out.println("Invalid input format.");
//                    continue;
//                }
                String recipient = to;
                String messageBody = text;
                Message message = new Message();
                message.setFrom(clientName);
                message.setTo(recipient);
                message.setText(messageBody);
                try {
                    networkInformation.getNetworkUtil().write(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

