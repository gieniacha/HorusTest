import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Node node1 = new Node("a", "aa");
        Node node2 = new Node("b", "bb");
        Node node3 = new Node("c", "cc");
        Node node4 = new Node("e", "ee");
        Node node5 = new Node("f", "ff");

        CompositeNode compositeNode = new CompositeNode("d", "dd", List.of(node4, node5));
        CompositeNode compositeNode2 = new CompositeNode("z", "zz", List.of(compositeNode));
        CompositeNode compositeNode3 = new CompositeNode("y", "yy", List.of(compositeNode2));
        CompositeNode compositeNode4 = new CompositeNode("g", "gg", List.of(compositeNode3, node3, compositeNode2));

        List<INode> testList = new ArrayList<>();

        testList.add(node1);
        testList.add(node2);
        testList.add(compositeNode4);
        testList.add(node3);
        MyStructure myStructure = new MyStructure(testList);
        myStructure.addNode(node4);
        System.out.println(myStructure.findByCode("ss"));
        System.out.println(myStructure.findByCode("y"));
        System.out.println(myStructure.count());
    }


}
