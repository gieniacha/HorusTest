import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Node node = new Node("a", "a");
        Node node2 = new Node("b", "b");
        Node node3 = new Node("c", "c");
        Node node4 = new Node("e", "e");
        Node node5 = new Node("f", "f");

        CompositeNode compositeNode = new CompositeNode("d", "d", List.of(node4, node5));
        CompositeNode compositeNode2 = new CompositeNode("g", "g", List.of(compositeNode));
        MyStructure myStructure = new MyStructure();
        System.out.println(myStructure.findByCode("f"));
        System.out.println(myStructure.count());
        myStructure.addNode(node);
        myStructure.addNode(compositeNode);
        myStructure.addNode(node2);
        myStructure.addNode(compositeNode2);
        myStructure.addNode(node3);

        System.out.println(myStructure.findByCode("f"));
        System.out.println(myStructure.count());
        System.out.println("Hello World!");
    }


}
