package com.example.projectmedilog;


import com.example.projectmedilog.client.ReadThreadClient;
import com.example.projectmedilog.client.WriteThreadClient;
import com.example.projectmedilog.util.NetworkInformation;
import com.example.projectmedilog.util.NetworkUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class chatController implements Initializable {

    @FXML
    private Label LB_username;

    @FXML
    private Button BTN_Search;

    @FXML
    private ImageView BTN_Send;

    @FXML
    public ListView<ChatMessage> LV_chat;

    @FXML
    private ListView<String> LV_users;

    @FXML
    private TextField TF_MessageField;

    @FXML
    private TextField TF_Search;

    @FXML
    private AnchorPane chatArea;

    @FXML
    private AnchorPane inboxArea;
    public ObservableList<ChatMessage> data;

    @FXML
    void onClickedBTN_Search(ActionEvent event) {
        if (TF_Search.getText().equals("")) {
            TF_Search.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Search.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        } else {
            TF_Search.setBackground(Background.fill(Color.TRANSPARENT));
            TF_Search.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
        }


    }


    @FXML
    void onClickedBTN_Send(MouseEvent event) {
        if (TF_MessageField.getText().equals("")) {
            TF_MessageField.setBackground(Background.fill(Color.TRANSPARENT));
            TF_MessageField.setStyle("-fx-border-color: #ff0000 ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");
            TF_MessageField.setPromptText("Message Field is Empty");
        } else {
            String messageContent = TF_MessageField.getText(); // Get the message content from the TextField
            String sender = usertype.getUserName(); // Set the sender name
            String receiver = LV_users.getSelectionModel().getSelectedItem(); // Get the selected receiver name
            Boolean isSender = true; // Set isSender to true for sender messages

            // Create a ChatMessage object with the message details
            ChatMessage message = new ChatMessage(sender, receiver, messageContent, isSender);

            // Add the message to the data list
            data.add(message);

            // Clear the TextField after sending the message
            TF_MessageField.clear();
            TF_MessageField.setBackground(Background.fill(Color.TRANSPARENT));
            TF_MessageField.setStyle("-fx-border-color: #0080FF ; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 100;");

            // Store the message in the database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog", "root", "")) {
                String sql = "INSERT INTO messages (sender_username, receiver_username, message) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, sender);
                    statement.setString(2, receiver);
                    statement.setString(3, messageContent);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
//
                Thread writeThread = new Thread(new WriteThreadClient(networkInformation, clientName, receiver, messageContent));
                writeThread.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    // search from database

    static class ChatMessage {
        private String sender;
        private String receiver;
        private String content;
        private Boolean isSender;

        public ChatMessage(String sender, String receiver, String content, Boolean isSender) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
            this.isSender = isSender;

        }

        public String getSender() {
            return sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public String getContent() {
            return content;
        }

        public Boolean getIsSender() {
            return isSender;
        }


    }

    @FXML
    void onClickedLV_users(MouseEvent event) {
        String username = LV_users.getSelectionModel().getSelectedItem();
        String myself = usertype.getUserName();
        LB_username.setText(username);

// Load chat from database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog", "root", "")) {
            String sql = "SELECT * FROM messages WHERE (receiver_username = ? and sender_username = ?) or (receiver_username = ? and sender_username = ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, myself);
                statement.setString(2, username);
                statement.setString(3, username);
                statement.setString(4, myself);
                ResultSet resultSet = statement.executeQuery();
                data = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String sender = resultSet.getString("sender_username");
                    String receiver = resultSet.getString("receiver_username");
                    String content = resultSet.getString("message");
                    boolean isSender = sender.equals(myself);
                    ChatMessage message = new ChatMessage(sender, receiver, content, isSender);
                    data.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Set the cell factory for the ListView

        LV_chat.setCellFactory(param -> new ListCell<ChatMessage>() {
            @Override
            protected void updateItem(ChatMessage chatMessage, boolean empty) {
                super.updateItem(chatMessage, empty);

                if (empty || chatMessage == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Set the text for the cell
                    setText(chatMessage.getContent());

                    // Set the alignment based on isSender flag
                    if (chatMessage.getIsSender()) {
                        setTextFill(Color.BLACK); // Set text color for sender
                        //set text bold
                        setStyle("-fx-font-weight: bold");
                        setAlignment(Pos.CENTER_RIGHT);
                    } else {
                        setTextFill(Color.BLUE); // Set text color for receiver
                        setStyle("-fx-font-weight: bold");
                        setAlignment(Pos.CENTER_LEFT);
                    }
                }
            }
        });

// Set the items for the ListView
        LV_chat.setItems(data);

    }

    NetworkInformation networkInformation;
    String clientName = usertype.getUserName();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;

            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            networkInformation = new NetworkInformation(networkUtil);
            Thread readThread = new Thread(new ReadThreadClient(networkInformation));


            readThread.start();

        } catch (Exception e) {
            System.out.println(e);
        }
//


        TF_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<String> data;

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog", "root", "")) {
                String sql = "";
                //check user type
                System.out.println(usertype.getType());
                if (usertype.getType() == "Doctor") {
                    sql = "SELECT * FROM users WHERE username LIKE ?";
                } else if (usertype.getType() == "User") {
                    sql = "SELECT * FROM doctors WHERE username LIKE ?";
                } else {
                    sql = "SELECT * FROM users WHERE username LIKE ?";
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, "%" + newValue + "%");
                    ResultSet resultSet = statement.executeQuery();
                    data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        String username = resultSet.getString("username");
                        data.add(username);
                    }
                    LV_users.setItems(data);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        //load all patient in listview
        ObservableList<String> data;
        String username = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmedilog", "root", "")) {
            String sql = "";
            //check user type
            System.out.println(usertype.getType());
            if (usertype.getType() == "Doctor") {
                sql = "SELECT * FROM users";
            } else if (usertype.getType() == "User") {
                sql = "SELECT * FROM doctors";
            } else {
                sql = "SELECT * FROM users";
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                data = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    username = resultSet.getString("username");
                    data.add(username);
                }
                LV_users.setItems(data);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   call Client class
//    public void callClient() throws IOException {
//        Client client = new Client();
//        client.startClient();
//    }
    }

    public void showMessage(String from, String message) {
        data = FXCollections.observableArrayList();
        data.add(new ChatMessage(from, usertype.getUserName(), message, false));

        try {
            Platform.runLater(() -> {
                //alert message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("You have a new message from " + from);
                alert.showAndWait();

                //LV_chat.setItems(data) ;
            });
        } catch (NullPointerException e) {
            System.out.println("NullPointerException" + e);
        }

    }


    @FXML
    void onClickedTF_Search(ActionEvent event) {


    }
}
