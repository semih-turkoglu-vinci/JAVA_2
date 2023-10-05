package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <E> List<E> allMatches(List<E> list, Predicate<E> match) {
        List<E> list1 = new ArrayList<E>();
        for (E i : list) {
            if (match.test(i))
                list1.add(i);
        }
        //TODO
        return list1;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static <T, E> List<T> transformAll(List<E> list, Function<E, T> transform) {
        List<T> liste2 = new ArrayList<>();
        for (E i : list) {
            liste2.add(transform.apply(i));
        }
        //TODO
        return liste2;
    }

    public static <E> List <E> filter(List<E> list, Predicate<E> match){
        return list.stream().filter(match).collect(Collectors.toList());
    }

    public static <T, E> List<T> map(List<E> list, Function<E, T> transform){
        return list.stream().map(transform).collect(Collectors.toList());
    }


}
