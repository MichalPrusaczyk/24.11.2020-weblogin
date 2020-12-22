package root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for(Integer liczba : list) {
            if(liczba % 2 == 0) {
                list.remove(liczba);
            }
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if(iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println(list);

        /************************************************************/

        int[] tab = new int[10];

        for (int liczba : tab) {
            //liczba - kopia
        }

        List<Integer> lista = new ArrayList<>();

        for(Integer liczba : lista) {
            //liczba -
        }

        Integer[] tab2 = new Integer[10];
    }
}
