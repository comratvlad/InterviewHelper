package util;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Alarm;
import view.AlarmTemplateBuilder;
import java.util.TimerTask;

public class AlarmTask extends TimerTask {
    private Alarm alarm;
    private AlarmManager context;
    private Stage stage;

    public AlarmTask(Alarm alarm, AlarmManager context, Stage stage) {
        this.alarm = alarm;
        this.context = context;
        this.stage = stage;
    }

    @Override
    public void run() {
        AlarmTemplateBuilder alarmView = new AlarmTemplateBuilder(this);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                alarmView.createNotification(alarm.getFio(), alarm.getPost(), 0, stage);
                Media audio = new Media(this.getClass().getClassLoader().getResource("sounds/msg.mp3").toString());
                final MediaPlayer playerAudio = new MediaPlayer(audio);
                playerAudio.play();
            }
        });
    }

    public void delayTask(int delayHours, int delayMinutes){
        context.addAlarmTask(alarm, delayHours, delayMinutes);
    }
}
