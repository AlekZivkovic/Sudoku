package sudoku.gui.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BrojeviController implements EventHandler<ActionEvent> {
    private  DodajBrojController dodajBrojController;
    private  int broj;

    public BrojeviController(int broj, DodajBrojController dodajBrojController) {
        this.broj=broj;
        this.dodajBrojController=dodajBrojController;
    }

    @Override
    public void handle(ActionEvent event) {
        dodajBrojController.setVrednsot(broj);
    }
}
