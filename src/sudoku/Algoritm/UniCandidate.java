package sudoku.Algoritm;

import sudoku.gui.model.DataBase;
import sudoku.repository.Kvadrant;
import sudoku.repository.Polje;
import sudoku.repository.Teren;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniCandidate extends  AlgoritamInter{
    private Map.Entry<Integer, ArrayList<Integer>> resenje;
    private  int broj;


    public UniCandidate() {
        this.broj=0;
    }

    @Override
    public Object pocni() {
        if(uniiCandidate()){
            System.out.println("Trenutni algoritam  uniCnadidate ");
            DataBase.getInstance().removePolje(resenje.getKey(),broj);
            return  resenje;
        }

        return null;
    }

    /*
    Prolazimo kroz kvadrante u datom sudoku-u
    za svako polje koje ne poseduje broj zovemo f-ju isItSingle
    ukoliko vrati broj koji nije 0 znaci da smo uspeli da nadjemo odg candidata i pri tom algoritam je uspesan
     */


    private boolean uniiCandidate(){
        System.out.println("******************************\n TREN ALGROGIRAM Unique Candidate \n ****************************** ");
        for (Kvadrant kvadrant: Teren.getTeren().getKvadrants()){
            //System.out.println("Trenutni kvadrant "+ kvadrant);
            for (Polje p: kvadrant.getPoljaUKvadrantu()){
                if(p.isPosedujeBroj() || p.getBroj()!=0)continue;
               // System.out.println("Posmatramo polje " + p);
                 broj=isItSingle(p,kvadrant.getPoljaUKvadrantu());
                if(broj!=0){
                   resenje=pronadjiResenje(DataBase.getInstance().getOdgIntPolja(p.getiKord(),p.getjKord()));

                    return  true;
                }
            }


        }




        return  false;
    }
    /*
        isItSingle posmatra dato polje i njegove mogucnosti sa poljima u njegovom kvadrantu
        ako za dati broj posmatrano polje ima mogucnost tog broja prelazi se na sledeci
        ukoliko ne postoji takav broj Polje p nije validno za uniCandidate
     */
    private int isItSingle(Polje p, List<Polje> poljaUKvadrantu) {
        boolean flag=false;
        int br=0;
        int j=0;
        //System.out.println("Za dato polje moguce "+ DataBase.getInstance().getRed().get(DataBase.getInstance().getOdgIntPolja(p.getiKord(),p.getjKord())));
        for(int i : DataBase.getInstance().getRed().get(DataBase.getInstance().getOdgIntPolja(p.getiKord(),p.getjKord()))){
            flag=false;
            br=i;
           // System.out.println("Posmatramo za polje broj "+ br);
            for(Polje p2 : poljaUKvadrantu){

                if(p.equals(p2) || p2.isPosedujeBroj() || p2.getBroj()!=0)continue;
              //  System.out.println("U drugom for tren polje "+ p2);
                if(DataBase.getInstance().getRed().get(DataBase.getInstance().getOdgIntPolja(p2.getiKord(),p2.getjKord())).contains(i)){
                   flag=true;
                   br=0;
                  //  System.out.println("Dati br je ponovljen");
                }

                if(flag)break;
            }
            //System.out.println("Tren stanje flaga "+ flag);
            if(!flag){
                setPosBrZaSol(j);
                break;
            }
            j++;
        }

        if(!flag)return  br;

        setPosBrZaSol(0);
        return  0;
    }

    private Map.Entry<Integer,ArrayList<Integer>> pronadjiResenje(int i){
        Map.Entry<Integer,ArrayList<Integer>> test=null;

        for(Map.Entry<Integer,ArrayList<Integer>>  e: DataBase.getInstance().getRed().entrySet()){
            if(e.getKey()==i){
                test=e;
                break;
            }
        }

        return  test;
    }


}
