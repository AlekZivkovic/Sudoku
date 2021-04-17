package sudoku.gui.Controller;

import javafx.stage.Modality;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.utils.SanseUtil;
import sudoku.gui.View.BrojeviView;
import sudoku.gui.View.GlavniView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DodajBrojController implements EventHandler<ActionEvent> {
    private int polje;
    private GlavniView glavniView;
    private  int vrednsot;
    private  Stage odabir;


    public DodajBrojController(int polje, GlavniView glavniView) {
        this.polje=polje;
        this.glavniView=glavniView;


    }

    @Override
    public void handle(ActionEvent event) {
         odabir=new Stage();
         odabir.initModality(Modality.APPLICATION_MODAL);
         odabir.setScene(new Scene(new BrojeviView(this),100,55));
         odabir.show();



    }

    public void setVrednsot(int vrednsot) {
        if(SanseUtil.getMoguciBr(Teren.getTeren().getPolja().get(polje)).contains(vrednsot)){
            this.vrednsot = vrednsot;
            uzeta();
            odabir.close();


        }
    }

    private void uzeta() {
        ((Button)glavniView.getGridPane().getChildren().get(polje)).setText(String.valueOf(vrednsot));
        ((Polje) Teren.getTeren().getPolja().get(polje)).dodajBroj(vrednsot);
    }
}
