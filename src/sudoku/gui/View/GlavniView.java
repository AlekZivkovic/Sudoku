package sudoku.gui.View;

import sudoku.gui.Controller.DodajBrojController;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.gui.Controller.AlController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


import java.util.List;

public class GlavniView extends HBox {
    private List<Button> buttons;
    private ConsolView consolView;
    private GridPane gridPane;

    public GlavniView(){
        initElem();
        addElem();
    }

    private void initElem() {
        consolView =new ConsolView();
        gridPane=napraviDugmad();
    }

    private GridPane napraviDugmad() {
        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(8));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        int flag=0;
        int j=-1,i=0;
        for(Polje p: Teren.getTeren().getPolja()){
            Button button=new Button();
            button.setPrefSize(20,20);
            button.setStyle("-fx-background-color: #7dbbd1");
            if(j<8){
                j++;
            }else{
                j=0;
                i++;
            }
            String style=dodajStyle(i,j);
            if(style!=null)
                button.setStyle(style);

            gridPane.add(button,j,i);
            button.setOnAction(new DodajBrojController(Teren.getTeren().getPolja().indexOf(p),this));

        }
        return  gridPane;
    }

    private void addElem() {
        this.setSpacing(20);
        this.setPadding(new Insets(10));


        gridPane.setPadding(new Insets(100,150,0,150));
        gridPane.setAlignment(Pos.TOP_CENTER);
        BorderPane bp=new BorderPane();


        bp.setCenter(gridPane);

        HBox hBox=new HBox(50);
        Button btnKorak=new Button("Sledeci korak");
        Button btnFull=new Button("Resi");
        btnFull.setPrefWidth(80);

        btnKorak.setOnAction(new AlController(this,false));
        btnFull.setOnAction(new AlController(this,true));

        hBox.getChildren().addAll(btnFull,btnKorak);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        bp.setBottom(hBox);
        this.getChildren().addAll(bp,consolView);
        consolView.println("Komande conosle:");
        consolView.println("goBack - vraća na izbor algoritma");
        consolView.println("exit - prekid rada programa");
    }

    private String dodajStyle(int i,int j){
        if(i==2 && j==2)
            return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 0 3px 3px 0;";
        if(i==6 && j==2)
            return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 3px 3px 0 0;";
        if(i==2 && j==6)
            return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 0 0 3px 3px;";
        if(i==6 && j==6)
            return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 3px 0 0 3px;";
        if(j==2) {
           return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 0 3px 0 0;";
        }
        if(j==6) {
           return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 0 0 0 3px;";
        }
        if(i==2 && j!=6 && j!=2) {
            return "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 0 0 3px 0;";
        }
        if(i==6 && j!=6 && j!=2) {
            return  "-fx-background-color: #7dbbd1; -fx-border-color: #000000; -fx-border-width: 3px 0 0 0;";
        }

        return  null;

    }

    public ConsolView getConsolView() {
        return consolView;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
