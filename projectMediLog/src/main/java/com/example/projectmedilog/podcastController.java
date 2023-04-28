package com.example.projectmedilog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class podcastController implements Initializable {
    @FXML
    private ImageView nextButton;

    @FXML
    private ImageView pauseButton;

    @FXML
    private ImageView playButton;

    @FXML
    private AnchorPane podcast_AnchorPane;

    @FXML
    private ImageView previousButton;

    @FXML
    private ImageView resetButton;

    @FXML
    private Label songLabel;

    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private Slider volumeSlider;
    @FXML
    private Label speedLabel;
    @FXML
    private ListView<String> songListView;

    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    @FXML
    void nextMedia(MouseEvent event) {
        if (songNumber < songs.size() - 1) {

            songNumber++;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());

            playMedia(event);
        } else {

            songNumber = 0;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());

            playMedia(event);
        }
    }

    @FXML
    void pauseMedia(MouseEvent event) {
        cancelTimer();
        mediaPlayer.pause();
    }

    @FXML
    void playMedia(MouseEvent event) {
        beginTimer();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();
    }

    @FXML
    void previousMedia(MouseEvent event) {
        if (songNumber > 0) {

            songNumber--;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());

            playMedia(event);
        } else {

            songNumber = songs.size() - 1;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());

            playMedia(event);
        }
    }

    @FXML
    void resetMedia(MouseEvent event) {
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new ArrayList<File>();

        directory = new File("music");

        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songLabel.setText(songs.get(songNumber).getName());

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });


        songProgressBar.setStyle("-fx-accent: #00FF00;");

    }

    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);

                if (current / end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

    @FXML
    void onMouseEntered_nextMedia(MouseEvent event) {
        nextButton.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEntered_pauseMedia(MouseEvent event) {
        pauseButton.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEntered_playMedia(MouseEvent event) {
        playButton.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEntered_previousMedia(MouseEvent event) {
        previousButton.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEntered_resetMedia(MouseEvent event) {
        resetButton.setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseEntered_volumeSlider(MouseEvent event) {
        volumeSlider.setCursor(Cursor.HAND);
    }

    void stopMedia() {
        mediaPlayer.stop();
        cancelTimer();
    }

}
