package sudoku.gui;

import sudoku.core.Gui;
import sudoku.gui.View.PickerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public  class MainFrame extends Application implements Gui {
    private static Stage instance;


    @Override
    public void start(Stage primaryStage) throws Exception {
        instance= primaryStage;
        instance.setTitle("Sudoku Solver");
        instance.setScene(new Scene(new PickerView(),300,350));
        instance.show();
    }


    @Override
    public void started() {
        launch();
    }

    public static Stage getInstance() {
        return instance;
    }
}
