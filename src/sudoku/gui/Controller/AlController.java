package sudoku.gui.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sudoku.Algoritm.*;
import sudoku.gui.View.GlavniView;
import sudoku.repository.Teren;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import sudoku.gui.model.DataBase;
import sudoku.utils.SanseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlController implements EventHandler<ActionEvent> {
    private GlavniView glavniView;
    private boolean uraditiSve;


    public AlController(GlavniView glavniView, boolean uraditiSve) {
        this.glavniView = glavniView;
        this.uraditiSve = uraditiSve;

    }

    @Override
    public void handle(ActionEvent event) {
        if(DataBase.getInstance().isGeneRed()){
            DataBase.getInstance().generisiRed();
           DataBase.getInstance().setGeneRed(false);
        }
        System.out.println(" Ostalo praznih: "+DataBase.getInstance().getBrPraznih());

        while(DataBase.getInstance().getBrPraznih()>0 &&  DataBase.getInstance().getRed() !=null &&
                !DataBase.getInstance().getRed().isEmpty()){
            if(DataBase.getInstance().isGrouped()){
                groupedAl();
            }else {
                if (DataBase.getInstance().getAlgoritam() instanceof NakedPairs) nakedAl();
                if (DataBase.getInstance().getAlgoritam() instanceof SoleCandidate) solAl();
                if (DataBase.getInstance().getAlgoritam() instanceof XWing) nakedAl();
            }
            if(!uraditiSve)break;
        }

    }

    private void groupedAl() {
        List<AlgoritamInter> lista=DataBase.getInstance().getGrouped();

        for(AlgoritamInter e : lista){
            DataBase.getInstance().setAlgoritam(e);
            if(e instanceof UniCandidate  || e instanceof  SoleCandidate){
               if(solAl())break;


            }
            if(e instanceof XWing || e instanceof  NakedPairs){
                if(nakedAl()){


                    break;
                }

            }



        }

    }


    private void ispis(Map.Entry vrednost) {

        glavniView.getConsolView().println("Za Polje:" + SanseUtil.pronadjiPoljeFixed(vrednost));
        glavniView.getConsolView().println("Moguci brojevi: "+ vrednost.getValue());

    }

    private  boolean solAl(){
        Object j=DataBase.getInstance().getAlgoritam().pocni();
        System.out.println(j);
        Map.Entry vrednost=(Map.Entry) j;
        System.out.println(vrednost);
        if(DataBase.getInstance().getAlgoritam() instanceof  SoleCandidate && vrednost == null){
            Map<Integer, ArrayList<Integer>>test=new HashMap<>();
            System.out.println(test.size());
            DataBase.getInstance().setRed(test);

            new Alert(Alert.AlertType.ERROR, "Doslo je do greske pri resavanju", ButtonType.OK).show();
        }
        if(vrednost== null)return  false;

        ispis(vrednost);

        Teren.getTeren().getPolja().get((int)vrednost.getKey()).dodajBroj((Integer) ((List)vrednost.getValue()).get(DataBase.getInstance().getAlgoritam().getPosBrZaSol()));
        ((Button)glavniView.getGridPane().getChildren().get((int)vrednost.getKey())).setText(String.valueOf(((List)vrednost.getValue()).get(DataBase.getInstance().getAlgoritam().getPosBrZaSol())));
        return  true;
    }
    private boolean nakedAl() {
        Object o=DataBase.getInstance().getAlgoritam().pocni();
        if(o instanceof  String){
            glavniView.getConsolView().println((String)o);
            return  true;
        }
        if(o instanceof  Integer){
            int br=(Integer)o;
            ((Button)glavniView.getGridPane().getChildren().get(br)).setText(String.valueOf(Teren.getTeren().getPolja().get(br).getBroj()));
            glavniView.getConsolView().println("Polje "+ br + " ima vrednost "+ Teren.getTeren().getPolja().get(br).getBroj() );
            return  true;
        }

    return false;
    }
}
