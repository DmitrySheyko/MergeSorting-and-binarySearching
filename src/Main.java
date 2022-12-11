import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Dog> unsortedDogs = Stream.of(
                        "Жучка",
                        "Шарик",
                        "Бобик",
                        "Тузик",
                        "Белка",
                        "Стрелка",
                        "Сопелка",
                        "Мухтар",
                        "Рэкс")
                .map(Dog::new)
                .collect(Collectors.toList());

        Dog dog = new Dog("Стрелка");
        String result = search(unsortedDogs, dog)
                .map(d -> "А вот и собака по кличке " + d + " нашлась")
                .orElseGet(() -> "Нет собаки по кличке " + dog + " :(");
        System.out.println(result);
    }

    private static <T extends Comparable<T>> Optional<T> search(List<T> unsortedList, T searchObject) {
        List<T> sortedList = mergeSort(unsortedList);
        int idx = genericSearching(sortedList, searchObject);
        System.out.println(idx);
        return Optional.of(sortedList.get(idx));
    }

    private static <T extends Comparable<T>> List<T> mergeSort(List<T> unsortedList) {
        if (unsortedList.size() <= 1) {
            return unsortedList;
        }
        List<T> sortedList = new ArrayList<>(unsortedList.size());
        List<T> leftList = mergeSort(unsortedList.subList(0, unsortedList.size() / 2));
        List<T> rightList = mergeSort(unsortedList.subList(unsortedList.size() / 2, unsortedList.size()));
        int leftIdx = 0;
        int rightIdx = 0;
        int resultIdx = 0;
        while (leftIdx < leftList.size() && rightIdx < rightList.size()) {
            if (leftList.get(leftIdx).compareTo(rightList.get(rightIdx)) <= 0) {
                sortedList.add(resultIdx, leftList.get(leftIdx));
                leftIdx++;
            } else {
                sortedList.add(resultIdx, rightList.get(rightIdx));
                rightIdx++;
            }
            resultIdx++;
        }
        while (leftIdx < leftList.size()) {
            sortedList.add(resultIdx, leftList.get(leftIdx));
            leftIdx++;
            resultIdx++;
        }
        while (rightIdx < rightList.size()) {
            sortedList.add(resultIdx, rightList.get(rightIdx));
            rightIdx++;
            resultIdx++;
        }
        return sortedList;
    }

    public static <T extends Comparable<T>> int genericSearching(List<T> sortedList, T searchObject) {
        if (sortedList.size() == 0) {
            return -1;
        }
        if (sortedList.size() == 1) {
            return sortedList.get(0).equals(searchObject) ? 0 : -1;
        }
        int min = 0;
        int max = sortedList.size();
        List<T> listForSearch = sortedList.subList(min, max);
        T midleObject = listForSearch.get(listForSearch.size() / 2);
        if (midleObject.equals(searchObject)) {
            return listForSearch.size() / 2;
        } else if (midleObject.compareTo(searchObject) > 0) {
            return genericSearching(listForSearch.subList(min, listForSearch.size() / 2), searchObject);
        } else {
            return genericSearching(listForSearch.subList(listForSearch.size() / 2, max), searchObject);
        }
    }
}