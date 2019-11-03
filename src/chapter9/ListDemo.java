package chapter9;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.removeAll(new ArrayList<>());
    }
}
