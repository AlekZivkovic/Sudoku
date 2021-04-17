package sudoku.repository;

import java.util.ArrayList;

public class Teren {
    private static Teren teren;
    private ArrayList<Polje>polja;
    private  ArrayList<Kvadrant>kvadrants;

    public Teren() {
        this.polja=new ArrayList<>();
        this.kvadrants=new ArrayList<>();
        generisiKvadrante();
        generisiPolja();

    }
    public void generisiKvadrante(){
        Kvadrant prvi=new Kvadrant(0,0,2,2);
        Kvadrant drugi=new Kvadrant(0,3,2,5);
        Kvadrant treci=new Kvadrant(0,6,2,8);
        Kvadrant cetvr=new Kvadrant(3,0,5,2);
        Kvadrant peti=new Kvadrant(3,3,5,5);
        Kvadrant sest=new Kvadrant(3,6,5,8);
        Kvadrant sedmi=new Kvadrant(6,0,8,2);
        Kvadrant osmi=new Kvadrant(6,3,8,5);
        Kvadrant devet=new Kvadrant(6,6,8,8);
        kvadrants.add(prvi);
        kvadrants.add(drugi);
        kvadrants.add(treci);
        kvadrants.add(cetvr);
        kvadrants.add(peti);
        kvadrants.add(sest);
        kvadrants.add(sedmi);
        kvadrants.add(osmi);
        kvadrants.add(devet);
    }

    public void generisiPolja(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                Polje p=new Polje(i,j);
                if(!polja.contains(p)){
                    polja.add(p);
                    //System.out.println("dodao");
                   for (Kvadrant k: kvadrants) {
                       if (k.isUKvadrantu(p)){
                           p.setKvadrant(k);
                           k.dodajUkvadrant(p);
                           //System.out.println("Usao za kvadrant: "+ k.getgI() +" "+ k.getgJ());
                       break;
                       }
                   }
                }

            }
        }

    }

    public boolean dodajBrojNaPolje(int i,int j,int broj){
        Polje polje=getOdgPolje(i,j);
        if(polje==null)
            return false;

        polja.get(polja.indexOf(polje)).dodajBroj(broj);
        return  true;
    }

    public Polje getOdgPolje(int i, int j){
        if(i<0 || i>=10 || j<0 || j>=10)
            return null;

        for(Polje p: this.polja){
            if(p.getjKord()==j && p.getiKord()==i)
                return  p;
        }

        return  null;
    }

    public Polje.Koordinata getKoordinate(Polje polje){
        return polje.getKoordinate();
    }




    public ArrayList<Polje> getPolja() {
        return polja;
    }
    public static Teren getTeren(){
        if(teren==null)
            return  teren=new Teren();
        return  teren;
    }

    public ArrayList<Kvadrant> getKvadrants() {
        return kvadrants;
    }
}
