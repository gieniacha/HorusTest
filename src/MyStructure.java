import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStructure implements IMyStructure {

    private List<INode> nodes;

    public MyStructure(List<INode> nodes) {                                             // constructor for MyStructure using a list of gives nodes
        this.nodes = nodes;
    }

    @Override
    public INode findByCode(String code) {                                              // finds a Node by comparing String code, using a functional predicate interface
        return findNode(n -> n.getCode().equals(code));
    }

    @Override
    public INode findByRenderer(String renderer) {

        return findNode(n -> n.getRenderer().equals(renderer));                         // finds a Node by comparing String renderer, using a functional predicate interface
    }

    private INode findNode(Predicate<INode> predicate) {                                // returns a Node by using a given implementation of functional predicate interface
        // method uses flattened Stream of MyStructure
        return getFlattenedStructureStream(nodes)                                       // if no Node is found a null is returned
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {

        return (int) getFlattenedStructureStream(nodes)                                 // counts number of all nodes in a structure using a flattened Stream
                .count();
    }

    private Stream<INode> getFlattenedStructureStream(List<INode> nodes) {              // return a stream of all nodes in MyStructure

        List<INode> listOfNodes = new ArrayList<>();

        nodes.forEach(n -> listOfNodes.addAll(getListOfNodes(n)));                      // goes through all Nodes and CompositeNodes in a first layer of MyStructure

        return listOfNodes.stream();
    }

    private List<INode> getFlatCompositeNode(ICompositeNode compositeNode) {            // returns a List of all Nodes in a CompositeNode
                                                                                        // its a recursive method to return all nodes
        List<INode> listOfNodes = new ArrayList<>();

        listOfNodes.add(new Node(compositeNode.getCode(), compositeNode.getRenderer()));

        compositeNode.getNodes().forEach(n -> listOfNodes.addAll(getListOfNodes(n)));

        return listOfNodes;

    }

    private List<INode> getListOfNodes(INode compositeNode) {                           // a method which checks if a given Node is an instance of
                                                                                        // ICompositeNode or INode.
        if (compositeNode instanceof ICompositeNode) {                                  // if it is a CompositeNode, it continues to go recursively through CompositeNode
            return getFlatCompositeNode((ICompositeNode) compositeNode);                // if it is a Node, it returns it as a single-element List
        } else {
            return Stream.of((Node) compositeNode).collect(Collectors.toList());
        }
    }

    public void addNode(INode node) {                                                   // add a Node to MyStructure
        if (nodes == null) {                                                            // it initializes nodes List if it wasnt before
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

}

