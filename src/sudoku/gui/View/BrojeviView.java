package sudoku.gui.View;

import sudoku.gui.Controller.BrojeviController;
import sudoku.gui.Controller.DodajBrojController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class BrojeviView extends GridPane {
    private DodajBrojController dodajBrojController;

    public BrojeviView(DodajBrojController dodajBrojController) {

        this.dodajBrojController=dodajBrojController;
        addElem();
    }

    private void addElem() {
        int broj=1;
        Button btn;
         for(int i=0;i<2;i++){
           for(int j=0;j<4;j++){
                 btn=new Button(String.valueOf(broj));
               this.add(btn,j,i);
               btn.setOnAction(new BrojeviController(broj,dodajBrojController));
                broj++;

            }
        }
        btn=new Button("9");
        this.add(btn,4,1);
        btn.setOnAction(new BrojeviController(9,dodajBrojController));

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode c=event.getCode();
                if(c==KeyCode.DIGIT1 ||c==KeyCode.DIGIT2 || c==KeyCode.DIGIT3 || c==KeyCode.DIGIT4 || c==KeyCode.DIGIT5 ||
                        c==KeyCode.DIGIT6 || c==KeyCode.DIGIT7 || c==KeyCode.DIGIT8 || c==KeyCode.DIGIT9){
                   // System.out.println(c.toString().substring(5));
                    dodajBrojController.setVrednsot(Integer.valueOf(c.toString().substring(5)));
                }


            }
        });

    }





}
