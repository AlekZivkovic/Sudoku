package sudoku.Algoritm;

import sudoku.gui.model.DataBase;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.utils.SanseUtil;

import java.util.ArrayList;
import java.util.Map;


//#TODO Napraviti da pokazuje tacne brojeve
//#TODO Videti zbog cega izbacuje duple br   4 4 u jednom bloku/redu

public class NakedPairs extends AlgoritamInter {
    private int posecen[][];
    private  String text;
    private  Map<Integer,ArrayList<Integer>> copija;

    public NakedPairs() {
        super();
        this.posecen=new int[81][81];
        this.text=new String();
        this.text=" ";
        srediPosecene();
    }

    private void srediPosecene() {
        for(int i=0;i<81;i++)
            for (int j=0;j<81;j++)
                    posecen[i][j]=-1;
    }


    @Override
    public Object pocni() {
        copija=DataBase.getInstance().getRed();
        int flag=NakedAl();
        if(flag==-2){
            System.out.println("******************************\n TREN ALGROGIRAM NAKED PAIRS \n ****************************** ");
            String text1=text;
            text="\n";
            return  text1;
        }

        return null;
    }


    public int NakedAl(){

        boolean fl=false;
        for(Map.Entry<Integer,ArrayList<Integer>> e : DataBase.getInstance().getRed().entrySet()){
            if(e.getValue().size()==2){
                fl= nakedDouble(e,true);
               fl= nakedDouble(e,false);
            }
            if(fl)break;
        }

        if(fl)return -2;

    return  -1;
    }


    /*
    nakedDouble f-ja sacinja je iz X i Y mogucnosti
    Da bi algoritam bio validan mora da bude:
      Tren polje, u nasem slucaju e , i polje e1 imaju istu i/j kordinatu
      Da oba polja mogu da poseduju samo 2 broja i da imaju iste moguce vrednosti
      Pri smanjivanu ne sme da se desi da neko polje zbog njih izgubi mogucnost da poseduje broj
    */
    //ako smanji vrati false znaci da zadati naked pair nije validan
    public boolean nakedDouble(Map.Entry<Integer,ArrayList<Integer>> e, boolean jot){
        ArrayList<Integer>moguc=e.getValue();
        StringBuilder sb=new StringBuilder();

        for(Map.Entry<Integer,ArrayList<Integer>> e1 : DataBase.getInstance().getRed().entrySet()){
            if(e.equals(e1))continue;

            if(jot){
                if(Teren.getTeren().getPolja().get(e.getKey()).getiKord()==Teren.getTeren().getPolja().get(e1.getKey()).getiKord()){
                    if(e1.getValue().size()==2 && moguc.containsAll(e1.getValue()) && posecen[e.getKey()][e1.getKey()]==-1){
                          if( !smanjii(e,e1, jot)){
                              System.out.println("Vracamo false za "+ e.getKey() + " i "+ e1.getKey());
                              return false;
                          }
                           posecen[e.getKey()][e1.getKey()]=1;
                            posecen[e1.getKey()][e.getKey()]=1;
                        System.out.println("Usli smo u Double I  za " + SanseUtil.pronadjiPoljeFixed(e) +" "+ SanseUtil.pronadjiPoljeFixed(e1));
                        sbText(e,e1,null);
                    return  true;
                    }

                }
            }else{
                if(Teren.getTeren().getPolja().get(e.getKey()).getjKord()==Teren.getTeren().getPolja().get(e1.getKey()).getjKord()){
                        if(e1.getValue().size()==2 && moguc.containsAll(e1.getValue()) && posecen[e.getKey()][e1.getKey()]==-1){

                            if( !smanjii(e,e1, jot))return  false;
                            posecen[e.getKey()][e1.getKey()]=1;
                            posecen[e1.getKey()][e.getKey()]=1;
                            System.out.println("Usli smo u Double J  za " +SanseUtil.pronadjiPoljeFixed(e) +" "+ SanseUtil.pronadjiPoljeFixed(e1));
                            sbText(e,e1,null);
                        return true;
                        }


                }
            }
        }

    return  false;
    }


    private   boolean smanjii(Map.Entry<Integer, ArrayList<Integer>> e, Map.Entry<Integer, ArrayList<Integer>> e1, boolean isJot){
        Map<Integer, ArrayList<Integer>> copy=DataBase.getInstance().getRed();
       Polje polje=SanseUtil.pronadjiPoljeFixed(e);
       StringBuilder sb=new StringBuilder();

        for(Map.Entry<Integer,ArrayList<Integer>> e2 : copy.entrySet()){
            if(e2.equals(e) || e2.equals(e1))continue;
            Polje p2=SanseUtil.pronadjiPoljeFixed(e2);
            if(!isJot){

                if(polje.getjKord()==p2.getjKord() ){
                    for(Integer i : e.getValue()){
                        if(e2.getValue().contains(i)) {
                            copy.put(e2.getKey(),(ArrayList<Integer>) SanseUtil.removeFromMapValue(e2.getValue(),i));
                            sb.append("Za polje "+ SanseUtil.pronadjiPoljeFixed(e2) +"skidamo mogucnost"+ " "+ i+"\n");
                        }
                    }

                }
            }else {

                if (polje.getiKord() == p2.getiKord() ) {

                    for (Integer i : e.getValue()) {
                        if (e2.getValue().contains(i)) {
                            copy.put(e2.getKey(),(ArrayList<Integer>) SanseUtil.removeFromMapValue(e2.getValue(),i));
                            sb.append("Za polje "+SanseUtil.pronadjiPoljeFixed(e2) +"skidamo mogucnost"+ " "+ i+"\n");
                        }
                    }

                }

            }
        }

        //dodajemo na dati string
        text+=sb.toString();
        if(DataBase.getInstance().isItGud(copy)){
            System.out.println("Resenja za Naked Pair valja");
            DataBase.getInstance().setRed(copy);
            text+=sb.toString();
            return true;
        }

        System.out.println("Naked Pair nije validan");
        text=" ";
        DataBase.getInstance().setRed(copija);
        return  false;
    }

    private void sbText(Map.Entry<Integer,ArrayList<Integer>> e,Map.Entry<Integer,ArrayList<Integer>> e2,Map.Entry<Integer,ArrayList<Integer>> e3){
        StringBuilder sb=new StringBuilder();
        if(e3==null){
            sb.append("Polja "+ SanseUtil.pronadjiPoljeFixed(e) +" i "+ SanseUtil.pronadjiPoljeFixed(e2)+ " cine naked pair" + e.getValue()+"\n");
        }else{
            sb.append("Polja "+ SanseUtil.pronadjiPoljeFixed(e) +", "+ SanseUtil.pronadjiPoljeFixed(e2)+ " i "+ SanseUtil.pronadjiPoljeFixed(e3)+" cine naked pair" + e.getValue()+"\n");
        }
        text=text+sb.toString();
    }

}
