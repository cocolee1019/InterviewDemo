package chapter9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.removeAll(new ArrayList<>());

        LinkedList list1111 = new LinkedList();
        list1111.add(2, "");
    }
}
