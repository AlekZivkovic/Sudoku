package sudoku.Algoritm;

import sudoku.gui.model.DataBase;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.utils.SanseUtil;

import java.util.*;


public class SoleCandidate extends AlgoritamInter{
    private Map<Integer,Integer> redosled;

    public SoleCandidate(){
        super();

    }



    @Override
    public Object pocni() {
        System.out.println("******************************\n TREN ALGROGIRAM Sole Candidate \n ****************************** ");
        geneRed();


        return solAlrgoritam();
    }



    public void geneRed(){
        Map<Integer,Integer>probna=new TreeMap<>();

        for(Map.Entry<Integer,ArrayList<Integer>> e: DataBase.getInstance().getRed().entrySet()){
            probna.put(e.getKey(),e.getValue().size());
        }
        redosled=SanseUtil.sortByValue(probna);
    }
    /*
       Sole Candidate funkcionise tako sto za broj sa najmanje moguih brojeva uzima jedan broj od tih vrednosti
       Da bi uzeo jednu od tih vrednosti trenutno polje  se uporedjuje sa poljima koji imaju za jedan vise mogucnost
       Ta polja moraju da imaju istu i ili j kordinatu.
       Ukoliko se desi da neko od tih polja poseduje trenutnu moguci broj prelazi se na sledeci
       Ako se desi da se svaki nalazi u polju koji uporedjujemo uzima se prvi broj
     */


    public Map.Entry solAlrgoritam(){
        Map.Entry<Integer,Integer> entry=redosled.entrySet().iterator().next();
        Map.Entry<Integer, List<Integer>> trenuti;
        int key=entry.getKey();
        int flag=0;
        ArrayList<Integer> broj=DataBase.getInstance().getRed().get(key);
        if(broj.size()== 0)return  null;


        boolean fl=false;
        Polje trenPolje=Teren.getTeren().getPolja().get(key);
        System.out.println("Za polje: "+ trenPolje.getiKord() + " " + trenPolje.getjKord());
        System.out.println( "Key : "+key+ "\n"+broj);
       for(Map.Entry<Integer,Integer> e : redosled.entrySet()) {
                if(e.getKey()==key)continue;
                   Polje p = Teren.getTeren().getPolja().get(e.getKey());

                   if (trenPolje.getiKord() == p.getiKord() || trenPolje.getjKord() == p.getjKord()) {
                       ArrayList<Integer> moguci = DataBase.getInstance().getRed().get(e.getKey());
                       if (moguci.size()<=broj.size()+1 && broj.size()!=1) {

                           System.out.println("usli za Polje: "+ p.getiKord() + " "+ p.getjKord());
                           System.out.println("Usli u while");
                           System.out.println("Moguci brojevi za P "+ moguci);
                           int probni=flag;
                           while (probni<broj.size()){
                               if(!moguci.contains(broj.get(probni))){
                                   flag=probni;
                                   fl=true;
                                   break;
                               }else{
                                   probni++;
                               }
                           }

                       }


                   }
               if(fl)break;



       }

       System.out.println(" izasao sa " + flag);

        DataBase.getInstance().removePolje(key,broj.get(flag));

        DataBase.getInstance().setBrPraznih(DataBase.getInstance().getBrPraznih()-1);
        setPosBrZaSol(flag);
        return trenuti=new Map.Entry<Integer, List<Integer>>() {
            @Override
            public Integer getKey() {
                return key;
            }

            @Override
            public List<Integer> getValue() {
                return broj;
            }

            @Override
            public List<Integer> setValue(List<Integer> value) {
                return null;
            }
        };
    }




}

