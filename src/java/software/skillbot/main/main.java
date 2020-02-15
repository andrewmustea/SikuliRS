package software.skillbot.main;

import common.enums.Skills;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application implements EventHandler<ActionEvent>
{
    /**
     * main method
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * JavaFX will call start when first loading
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("SikuliRS by Zeni Jr");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(300, 300);
        grid.setVgap(5);
        grid.setHgap(5);

        SkillsComboBox = new ComboBox<Skills>();
        SkillsComboBox.getItems().setAll(Skills.values());
        SkillButton = new Button("Run");

        grid.add(new Text("Skill:"), 0, 0);
        grid.add(SkillsComboBox, 1, 0);
        grid.add(SkillButton, 0, 1);

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param event
     */
    public void handle(ActionEvent event)
    {
        if (event.getSource()== SkillButton)
        {
            System.out.println(SkillsComboBox.getValue().toString());
        }
    }

    Button SkillButton;
    ComboBox<Skills> SkillsComboBox;
}
