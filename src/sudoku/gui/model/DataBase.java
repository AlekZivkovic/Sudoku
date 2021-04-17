package sudoku.gui.model;

import sudoku.Algoritm.*;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.utils.SanseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataBase {
    private  static DataBase instance;
    private AlgoritamInter algoritam;
    private Map<Integer, ArrayList<Integer>> red;
    private boolean unicat;
    private  int brPraznih;
    private int alFlag;
    private  boolean grouped;
    private List<AlgoritamInter> lista;
    private  boolean geneRed;


    public Map<Integer, ArrayList<Integer>> getRed() {
        return red;
    }



    private DataBase() {
        this.brPraznih=0;
        this.red=new HashMap<>();
        this.unicat=true;
        this.grouped=false;
        this.lista=new ArrayList<>();
        this.geneRed=true;
    }

    public void postaviAlgoritam(int flag){
        alFlag=flag;
        switch (flag){
            case 0: algoritam=new SoleCandidate();break;
            case 1: algoritam=new NakedPairs();break;
            case 2: algoritam=new XWing();break;
            case 3: grouped=true;
        }
    }


    public void generisiRed() {
        int bPraznih=0;
        for(Polje p : Teren.getTeren().getPolja()){

            if(!p.isPosedujeBroj() && p.getBroj()==0) {
                red.put(Teren.getTeren().getPolja().indexOf(p), SanseUtil.getMoguciBr(p));
                bPraznih++;
            }
        }
        setBrPraznih(bPraznih);
    }
    public   void removePolje(int polje1,int broj ){
        Polje polje=Teren.getTeren().getPolja().get((polje1));

        for (Map.Entry<Integer,ArrayList<Integer>> e: red.entrySet()){
            Polje tren=Teren.getTeren().getPolja().get(e.getKey());
            if(polje.equals(tren))continue;
            if(tren.getiKord() ==polje.getiKord() || tren.getjKord()== polje.getjKord() || polje.getKvadrant().equals(tren.getKvadrant())){
                if(e.getValue().contains(broj)){
                    int fl=0;
                    for(;fl<e.getValue().size();fl++)if(e.getValue().get(fl)==broj)break;
                    //    System.out.println("pre bisanja "+ red.get(e.getKey()));
                    e.getValue().remove(fl);
                    //System.out.println("Izbrisano "+ red.get(e.getKey()));
                }
            }
        }
        red.remove(polje1);
    }



    public  List<AlgoritamInter> getGrouped(){
        if(lista.isEmpty())popuniListu();

        return lista;
    }

    private void popuniListu() {
        lista.add(new UniCandidate());
        lista.add(new XWing());
        lista.add(new NakedPairs());
        lista.add(new SoleCandidate());
    }
    public boolean isItGud(Map<Integer, ArrayList<Integer>> copy) {

        for(Map.Entry<Integer, ArrayList<Integer>> i: copy.entrySet()){
            if(i.getValue().size()>=2)continue;
            if(i.getValue().isEmpty())return  false;
            Polje ip=SanseUtil.pronadjiPoljeFixed(i);
            for(Map.Entry<Integer, ArrayList<Integer>> j : copy.entrySet()){
                if(j.getValue().size()>=2 || i.equals(j) )continue;
                if(j.getValue().isEmpty())return false;
                Polje jp=SanseUtil.pronadjiPoljeFixed(j);
                if(ip.getiKord()==jp.getiKord()|| ip.getjKord()==jp.getjKord() || ip.getKvadrant().equals(jp.getKvadrant())){
                    if(i.getValue().get(0)==j.getValue().get(0))return  false;
                }
            }
        }

        return  true;
    }
    public int getOdgIntPolja(int i, int j){
        int br=0;
        for(Map.Entry<Integer,ArrayList<Integer>>  p1:red.entrySet() ){
            Polje p=SanseUtil.pronadjiPoljeFixed(p1);
            if(p.getiKord()==i && p.getjKord()==j){
                br=p1.getKey();
                break;
            }
        }


        return  br;
    }



    public boolean isGrouped() {
        return grouped;
    }

    public void setAlgoritam(AlgoritamInter algoritam) {
        this.algoritam = algoritam;
    }


    public int getBrPraznih() {
        return brPraznih;
    }

    public void setBrPraznih(int brPraznih) {
        this.brPraznih = brPraznih;
    }

    public AlgoritamInter getAlgoritam() {
        return algoritam;
    }

    public static DataBase getInstance() {
        if(instance==null)
            instance=new DataBase();
        return instance;
    }
    public boolean isGeneRed() {
        return geneRed;
    }

    public void setGeneRed(boolean geneRed) {
        this.geneRed = geneRed;
    }
    public boolean isUnicat() {
        return unicat;
    }

    public void setUnicat(boolean unicat) {
        this.unicat = unicat;
    }

    public int getAlFlag() {
        return alFlag;
    }

    public void setRed(Map<Integer, ArrayList<Integer>> red) {
        this.red = red;
    }
}
