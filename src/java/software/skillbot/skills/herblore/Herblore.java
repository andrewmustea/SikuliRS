package software.skillbot.skills.herblore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import software.skillbot.skills.interfaces.Skill;

public class Herblore extends Application implements Skill, EventHandler<ActionEvent>
{
    public void run(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

    }

    public void handle(ActionEvent event)
    {

    }
}
