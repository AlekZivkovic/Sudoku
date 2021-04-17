package sudoku.utils;

import sudoku.repository.Kvadrant;
import sudoku.repository.Polje;
import sudoku.repository.Teren;

import java.util.*;

public class SanseUtil {

    public  static ArrayList<Integer> getMoguciBr(Polje polje){
        ArrayList<Integer> arBr=new ArrayList<>();
        Set<Integer> br=new TreeSet<>();
        for(Polje p : Teren.getTeren().getPolja()){
              if(p.getBroj()!=0) {
                  if (polje.getiKord() == p.getiKord() || polje.getjKord() == p.getjKord())
                      br.add(p.getBroj());
              }
              if(p.getKvadrant().equals(polje.getKvadrant())){
                  if(p.getBroj()!=0)
                      br.add(p.getBroj());
              }
        }

        for(int i=1;i<10;i++){
            if(!br.contains(i))
                arBr.add(i);
        }
        return  arBr;
    }



    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static  Map<Integer,Integer> updateList(int i, int j, Kvadrant kvadrant, Map<Integer,Integer> redosled){
        int flag;
        for (Polje polje : Teren.getTeren().getPolja()){
            flag=SanseUtil.getMoguciBr(polje).size();

            if((i==polje.getiKord() || j==polje.getjKord() || polje.getKvadrant().equals(kvadrant))&& flag!=0){
                redosled.put(Teren.getTeren().getPolja().indexOf(polje),flag);
            }
        }
    return  SanseUtil.sortByValue(redosled);
    }


    public static Polje pronadjiPoljeFixed(Map.Entry<Integer,ArrayList<Integer>> vrednost){
        return  (((Polje) Teren.getTeren().getPolja().get(vrednost.getKey())));
    }

    public static List<Integer> removeFromMapValue(List<Integer> list, int var){
        Iterator<Integer> itL=list.iterator();
        while (itL.hasNext()){
            int flag=itL.next();
            if(flag==var){
                itL.remove();
                break;
            }
        }
        return list;
    }
}
