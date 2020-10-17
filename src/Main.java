import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Node node1 = new Node("a", "a");
        Node node2 = new Node("b", "b");
        Node node3 = new Node("c", "c");
        Node node4 = new Node("e", "e");
        Node node5 = new Node("f", "f");

        CompositeNode compositeNode = new CompositeNode("d", "d", List.of(node4, node5));
        CompositeNode compositeNode2 = new CompositeNode("z", "z", List.of(compositeNode));
        CompositeNode compositeNode3 = new CompositeNode("y", "y", List.of(compositeNode2));
        CompositeNode compositeNode4 = new CompositeNode("g", "g", List.of(compositeNode3, node3));

        List<INode> testList = new ArrayList<>();

        testList.add(node1);
        testList.add(node2);
        testList.add(compositeNode4);
        testList.add(node3);
        MyStructure myStructure = new MyStructure(testList);

        myStructure.getFlattenedStructure(testList)
                .forEach(n -> System.out.println(n.getCode()));

        System.out.println(myStructure.findByCode("y"));
        System.out.println(myStructure.count());
        System.out.println("Hello World!");
    }


}
