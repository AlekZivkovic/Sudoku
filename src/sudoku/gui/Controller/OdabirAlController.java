package sudoku.gui.Controller;

import sudoku.gui.MainFrame;
import sudoku.gui.View.GlavniView;
import sudoku.gui.View.PickerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import sudoku.gui.model.DataBase;


public class OdabirAlController implements EventHandler<ActionEvent> {
    private PickerView pickerView;
    private int flag;

    public OdabirAlController(int flag, PickerView pickerView) {
        this.pickerView=pickerView;
        this.flag = flag;
    }



    @Override
    public void handle(ActionEvent event) {
       DataBase.getInstance().postaviAlgoritam(flag);
       MainFrame.getInstance().setScene(new Scene(new GlavniView(),1000,500));

    }


}
