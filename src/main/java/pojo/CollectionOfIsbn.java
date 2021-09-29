package pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionOfIsbn {
    public static void main(String[] args) {
        String[] collection = new String[8];
        collection[0] = "9781449325862";
        collection[1] = "9781449331818";
        collection[2] = "9781449337711";
        collection[3] = "9781449365035";
        collection[4] = "9781491904244";
        collection[5] = "9781491950296";
        collection[6] = "9781593275846";
        collection[7] = "9781593277574";
        String random = collection[new Random().nextInt(collection.length)];
        System.out.println(random);
    }
    public static String getRandomIsbn() {
        List<String> collectionOfIsbn = new ArrayList<>();
        collectionOfIsbn.add("9781449325862");
        collectionOfIsbn.add("9781449331818");
        collectionOfIsbn.add("9781449337711");
        collectionOfIsbn.add("9781449365035");
        collectionOfIsbn.add("9781491904244");
        collectionOfIsbn.add("9781491950296");
        collectionOfIsbn.add("9781593275846");
        collectionOfIsbn.add("9781593277574");
        Collections.shuffle(collectionOfIsbn);
        String randomIsbn = collectionOfIsbn.get(0);
        System.out.println(randomIsbn);
        return randomIsbn;
    }
}

