package sudoku.gui.View;

import sudoku.gui.Controller.OdabirAlController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PickerView extends BorderPane {
    private Button btnSol;
    private Button btnUni;
    private  Button btnXW;
    private  Button btnGrp;

    public PickerView (){
        initElem();
        addElem();
    }

    private void addElem() {
        VBox vBox=new VBox(15);
        vBox.setPadding(new Insets(25));
        vBox.setAlignment(Pos.CENTER);

        btnSol.setText("Sole Candidate");
        btnSol.setStyle("-fx-background-color: #6286b3; -fx-text-fill: #fafafa");
        btnSol.setPrefHeight(20);
        btnSol.setPrefWidth(110);

        btnUni.setText("Naked Pairs");
        btnUni.setStyle("-fx-background-color: #6286b3; -fx-text-fill: #fafafa");
        btnUni.setPrefHeight(20);
        btnUni.setPrefWidth(110);

        btnXW.setText("XWing");
        btnXW.setStyle("-fx-background-color: #6286b3; -fx-text-fill: #fafafa");
        btnXW.setPrefHeight(20);
        btnXW.setPrefWidth(110);

        btnGrp.setText("All together");
        btnGrp.setStyle("-fx-background-color: #6286b3; -fx-text-fill: #fafafa");
        btnGrp.setPrefHeight(20);
        btnGrp.setPrefWidth(110);

        btnSol.setOnAction(new OdabirAlController(0,this));
        btnUni.setOnAction(new OdabirAlController(1,this));
        btnXW.setOnAction(new OdabirAlController(2,this));
        btnGrp.setOnAction(new OdabirAlController(3,this));
        vBox.getChildren().add(btnUni);
        vBox.getChildren().add(btnSol);
        vBox.getChildren().addAll(btnXW,btnGrp);
        this.setCenter(vBox);


    }

    private void initElem() {
        this.btnSol=new Button();
        this.btnUni=new Button();
        this.btnXW=new Button();
        this.btnGrp=new Button();

    }

}
