package sudoku.Algoritm;

import sudoku.gui.model.DataBase;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import sudoku.utils.SanseUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
    X / Y  WING
    dosta koplikovan za kodiranje, takodje i za primenu
 */
//Sudoku Guy
public class XWing extends  AlgoritamInter {
    private  String resenje;
    private Map.Entry<Integer, ArrayList<Integer>> frist;
    private Map.Entry<Integer, ArrayList<Integer>> secnd;
    private Map.Entry<Integer, ArrayList<Integer>> third;
    private Map.Entry<Integer, ArrayList<Integer>> forth;
    private int broj;
    public XWing() {
        super();
        this.frist =null;
        this.secnd=null;
        this.third=null;
        this.forth=null;
        this.broj=-1;
    }

    /*
        Summary algoritma:
            Ukoliko f-ja  xWing() uspe da nadje kandidate koji su validni za wing ulazi se u probnu fazu
            U probnoj fazi skida se poziva se f-ja smanji() koja provera dal nece doci do nekog "gubitka" u redu
            Ukoliko je i probna faza ok algoritam je uspesan
     */
    @Override
    public Object pocni() {

        if(xWing()){
            if(smanji()){
                return resenje;
            }

        }

        return null;
    }
    /*
        Smanji
            Vrsi skidanje vrednosti sa polja koje poseduju dati broj x/y winga
            na kraju se vrsi cista provera dal je moguce izvrsiti ovaj cin
     */

    private boolean smanji() {
        Map<Integer, ArrayList<Integer>> copy=DataBase.getInstance().getRed();
        StringBuilder sb=new StringBuilder();
        sb.append("Pronadjen xWing par :\n " + SanseUtil.pronadjiPoljeFixed(frist)+ ", "+ SanseUtil.pronadjiPoljeFixed(secnd));
        sb.append("\n"+ SanseUtil.pronadjiPoljeFixed(third)+ ", "+ SanseUtil.pronadjiPoljeFixed(forth)+"\n");
        sb.append("Ukidamo mogucnost " + broj + " za:\n " );
        int fl=4;
        for(Map.Entry<Integer, ArrayList<Integer>> e : copy.entrySet()){

            if(e.equals(frist) || e.equals(secnd) || e.equals(third) || e.equals(forth))continue;
            if(SanseUtil.pronadjiPoljeFixed(e).isPosedujeBroj())continue;

            if(SanseUtil.pronadjiPoljeFixed(e).getiKord() == SanseUtil.pronadjiPoljeFixed(frist).getiKord() ||
                    SanseUtil.pronadjiPoljeFixed(e).getjKord() == SanseUtil.pronadjiPoljeFixed(frist).getjKord()){
                if(e.getValue().contains(broj)){
                    sb.append(", "+SanseUtil.pronadjiPoljeFixed(e));
                    fl--;
                    copy.put(e.getKey(), (ArrayList<Integer>) SanseUtil.removeFromMapValue(e.getValue(),broj));
                    if(copy.get(e.getKey()).size()==1)DataBase.getInstance().setUnicat(true);
                }
            }
            if(SanseUtil.pronadjiPoljeFixed(e).getjKord() == SanseUtil.pronadjiPoljeFixed(secnd).getjKord()){
                if(e.getValue().contains(broj)){
                    sb.append(", "+SanseUtil.pronadjiPoljeFixed(e));
                    fl--;
                    //red.put(e.getKey(),(ArrayList<Integer>) SanseUtil.removeFromMapValue(e.getValue(),broj));
                   // if(red.get(e.getKey()).size()==1)setUnicat(true);
                    copy.put(e.getKey(), (ArrayList<Integer>) SanseUtil.removeFromMapValue(e.getValue(),broj));
                    if(copy.get(e.getKey()).size()==1)DataBase.getInstance().setUnicat(true);
                }
            }
            if(SanseUtil.pronadjiPoljeFixed(e).getiKord() == SanseUtil.pronadjiPoljeFixed(forth).getiKord()){
                if(e.getValue().contains(broj)){
                    sb.append(", "+ SanseUtil.pronadjiPoljeFixed(e));
                    fl--;
                    //red.put(e.getKey(),(ArrayList<Integer>) SanseUtil.removeFromMapValue(e.getValue(),broj));
                    //if(red.get(e.getKey()).size()==1)setUnicat(true);
                    copy.put(e.getKey(), (ArrayList<Integer>) SanseUtil.removeFromMapValue(e.getValue(),broj));
                    if(copy.get(e.getKey()).size()==1)DataBase.getInstance().setUnicat(true);
                }
            }
            if(fl<=0){
                fl=4;
                sb.append("\n");
            }
        }

        if(DataBase.getInstance().isItGud(copy)){
            System.out.println("Resenja za XWing valja");
            DataBase.getInstance().setRed(copy);
            resenje=sb.toString();
            return true;
        }
        System.out.println("Nije validno");
        return false;
    }

/*
    xWing()
        da bi x wing bio validan makar jedno polje od 4 moraju da imaju samo dve mogucnosti (u suprotnom resavanje preko
        wing-a bi se svelo na cistu ludost)
        Kada se nadje kandidat za xWing za njegove mogucnosti se provera dal je moguce napraviti dati wing
        Da bi wing bio uspesan:
            U zavisnosti dal radimo preko preko x ili y:
                Ako radimo preko x, od tren polja po I kordinati sme da bude samo jedno polje koje moze da poseduje dati
                br dok vertikalno sme da bude n polja.

                Ako radimo preko y, od tren polja po J kordinati sme da bude samo jedno polje koje moze da poseduje dati
                br dok horizontalno sme da bude n polja
            Sve ovo vazi i sa simetricna dva polja
 */


    private  boolean xWing(){
        frist=null;
        secnd=null;
        third=null;
        forth=null;
        broj=-1;
        resenje=" ";
       boolean flag=false;

        for(Map.Entry<Integer, ArrayList<Integer>> tren: DataBase.getInstance().getRed().entrySet()){
            if(SanseUtil.pronadjiPoljeFixed(tren).isPosedujeBroj() || tren.getValue().size()!=2)continue;
            frist =tren;
            System.out.println("Tren polje "+ SanseUtil.pronadjiPoljeFixed(frist)+"\n ==========================================");
            for(Integer e: tren.getValue()) {
                System.out.println("======================\n         broj: "+e+ " \n ==========================" );
                System.out.println("TREN P: "+SanseUtil.pronadjiPoljeFixed(frist) );
               flag= pronadjiPar(tren,e,false);
               if(flag==true){
                   broj=e;
                   break;
               }
               flag=pronadjiPar(tren,e,true);
                if(flag==true){
                    broj=e;
                    break;
                }
            }

            if(flag==true)break;
            else continue;
        }

        if(frist!=null && secnd!=null && third!=null && forth!=null)return  true;


        return  false;
    }

    /*
        pronadjiPar
            Vrsi prolanazenja datih mogucih polja. Radi tako sto se prave dve liste i u njih se stavljaju moguci kandidati
            Ukoliko se radi y wing:
                Lista vMogucih mora da sadzi samo jedan kandidat dok hMogucih makar 2
            Ukoliko se radi x wing :
                Lista hMogucih mora da sadzi samo jedan kandidat dok vMogucih makar 2

           Ukoliko su nam tacni dati uslovi poziva se f-ja solve

     */

    private boolean pronadjiPar(Map.Entry<Integer,ArrayList<Integer>> tren,int broj,boolean poVertikali){
        List< Map.Entry<Integer,ArrayList<Integer>>> hMoguci=new ArrayList<>();
        List< Map.Entry<Integer,ArrayList<Integer>>> vMoguci=new ArrayList<>();

        for(Map.Entry<Integer,ArrayList<Integer>> e: DataBase.getInstance().getRed().entrySet()){
            if(tren.equals(e))continue;

            if(SanseUtil.pronadjiPoljeFixed(e)==null ||SanseUtil.pronadjiPoljeFixed(e).isPosedujeBroj())continue;

                if (((Polje) Teren.getTeren().getPolja().get(e.getKey())).getiKord() == ((Polje) Teren.getTeren().getPolja().get(tren.getKey())).getiKord()) {

                    if (e.getValue().contains(broj)) {
                       // System.out.println(SanseUtil.pronadjiPolje(e).getBroj() + " za slucaj sa 3");
                       hMoguci.add(e);
                    }
                }

                if (SanseUtil.pronadjiPoljeFixed(e).getjKord() == SanseUtil.pronadjiPoljeFixed(tren).getjKord()) {
                    if (e.getValue().contains(broj)) {
                        //System.out.println(SanseUtil.pronadjiPoljeFixed(e).getBroj()+ " za slucaj sa 3  "+ e.getValue());
                        //System.out.println("Jot kord od koga gledamo "+ ((Polje) Teren.getTeren().getPolja().get(tren.getKey())).getjKord() +
                          //      " Kord od e "+ ((Polje) Teren.getTeren().getPolja().get(e.getKey())).getjKord() + "i  i kord "+((Polje) Teren.getTeren().getPolja().get(e.getKey())).getiKord());

                        vMoguci.add(e);
                    }
                }
        }

        if(vMoguci.isEmpty() || hMoguci.isEmpty() )return false;

        System.out.println("Nasli smo liste V: "+ vMoguci + "\n H: "+ hMoguci);
        System.out.println("PoVertiakli je " + poVertikali);
        if(poVertikali){
            if(vMoguci.size()!=1  || hMoguci.size()<2)return  false;
            solve(vMoguci,hMoguci,broj,tren,poVertikali);
        }else{
            if(hMoguci.size()!=1 || vMoguci.size()<2)return false;
            solve(hMoguci,vMoguci,broj,tren,poVertikali);
        }

        if(secnd!=null && third !=null && forth!=null){
            return  true;
        }
        secnd=null;
        third=null;
        forth=null;

        return  false;
    }

    /*
        solve
          Kandaidat hMogucih ne sme da bude u istom kvadrantu kao i nasa prva (trenutna) tacka
          Kandidat iz vMogucih ne sme da bude u istom kvadrantu kao i kandaidat hMogucih a i prva tacka.

          Za dva kandidata pravi se test polje

          U zavisnosti  dal radimo preko x/y winga:
                Y Wing():
                    test polje uzima i kord od kandidata hMogucih
                                     j kord od kandidata vMogucih
                X Wing():
                    test polje uzima i kord od kandidata vMogucih
                                     j kord od kandidata hMogucih

          Vrsi se dalja testiranja datog Test polja pri cemu za njega isto moraju da vaze zadati uslovi
          pri cemu se za detaljniju proveru ulazi u f-ju izmedju.

     */



    private void solve(List<Map.Entry<Integer, ArrayList<Integer>>> hMoguci, List<Map.Entry<Integer, ArrayList<Integer>>> vMoguci, int broj, Map.Entry<Integer, ArrayList<Integer>> tren,boolean poVertikali) {


        for(Map.Entry<Integer, ArrayList<Integer>> horizontalan: hMoguci){
            if(SanseUtil.pronadjiPoljeFixed(horizontalan).getKvadrant().equals(SanseUtil.pronadjiPoljeFixed(frist).getKvadrant()))continue;
            System.out.println("Posmatramo polje " + SanseUtil.pronadjiPoljeFixed(horizontalan) );
           for (Map.Entry<Integer, ArrayList<Integer>> vertikalan : vMoguci){
               if(SanseUtil.pronadjiPoljeFixed(horizontalan).getKvadrant().equals(SanseUtil.pronadjiPoljeFixed(vertikalan).getKvadrant()) ||
                SanseUtil.pronadjiPoljeFixed(vertikalan).getKvadrant().equals(SanseUtil.pronadjiPoljeFixed(frist).getKvadrant()))continue;

               Polje test=null;
               if(poVertikali){
                   test=Teren.getTeren().getOdgPolje(SanseUtil.pronadjiPoljeFixed(horizontalan).getiKord(),
                           SanseUtil.pronadjiPoljeFixed(vertikalan).getjKord());
               }else{
                   test=Teren.getTeren().getOdgPolje(SanseUtil.pronadjiPoljeFixed(vertikalan).getiKord(),
                           SanseUtil.pronadjiPoljeFixed(horizontalan).getjKord());
               }

               System.out.println("Za polje " + SanseUtil.pronadjiPoljeFixed(vertikalan));
               System.out.println("Nasli smo "+ test);
               if(test== null || test.getBroj()!=0 || test.getKvadrant().equals(frist) || izmedju(test,broj,vertikalan,horizontalan,poVertikali))continue;


               if((DataBase.getInstance().getRed().get(Teren.getTeren().getPolja().indexOf(test))).contains(broj)){
                    secnd=horizontalan;
                    third=vertikalan;
                    for (Map.Entry<Integer, ArrayList<Integer>> e1 : DataBase.getInstance().getRed().entrySet()){
                        if(e1.getKey().equals(Teren.getTeren().getPolja().indexOf(test))){
                            forth=e1;
                            System.out.println("Dato polje valja za: ");
                            System.out.println(frist+ ",  "+ secnd);
                            System.out.println(third+", "+forth);
                            return;
                        }
                    }
               }


           }
        }



    }

    private boolean izmedju(Polje test, int broj, Map.Entry<Integer, ArrayList<Integer>> horizontal, Map.Entry<Integer, ArrayList<Integer>> vertikalan, boolean poVertikali) {
        int testInt=DataBase.getInstance().getOdgIntPolja(test.getiKord(),test.getjKord());
        System.out.println("Unutar izmejdu");


        for(Map.Entry<Integer, ArrayList<Integer>> e: DataBase.getInstance().getRed().entrySet()){
            if(testInt==e.getKey() || vertikalan.equals(e) || horizontal.equals(e))continue;
            if(poVertikali){
                if(test.getjKord()==SanseUtil.pronadjiPoljeFixed(e).getjKord() && e.getValue().contains(broj)){
                    System.out.println("Test polje " + test +" i e "+ SanseUtil.pronadjiPoljeFixed(e)+ " imaju isti br za"+ broj);
                    return  true;
                }
            }else{
                if(test.getiKord()==SanseUtil.pronadjiPoljeFixed(e).getiKord() && e.getValue().contains(broj)){
                    System.out.println("Test polje " + test +" i e "+ SanseUtil.pronadjiPoljeFixed(e)+ " imaju isti br za"+ broj);
                    return  true;
                }
            }
        }



    return  false;
    }


}
