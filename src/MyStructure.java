import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyStructure implements IMyStructure {

    private List<INode> nodes;

    public MyStructure(List<INode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public INode findByCode(String code) {
        return findNode(n -> n.getCode().equals(code));
    }

    @Override
    public INode findByRenderer(String renderer) {

        return findNode(n -> n.getRenderer().equals(renderer));
    }

    private INode findNode(Predicate<INode> predicate) {

        return getFlattenedStructure(nodes)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {

        return (int) getFlattenedStructure(nodes)
                .count();
    }

    private Stream<INode> getFlattenedStructure(List<INode> nodes) {

        List<INode> nodesStream = new ArrayList<>();

        nodes.forEach(n -> {
            if (n instanceof ICompositeNode) {
                nodesStream.addAll(getFlatCompositeNode((ICompositeNode) n));
            } else {
                nodesStream.add(n);
            }
        });

        return nodesStream.stream();
    }

    private List<INode> getFlatCompositeNode(ICompositeNode compositeNode) {

        List<INode> listOfNodes = new ArrayList<>();

        listOfNodes.add(new Node(compositeNode.getCode(), compositeNode.getRenderer()));

        compositeNode.getNodes().forEach(n -> {
            if (n instanceof ICompositeNode) {
                listOfNodes.addAll(getFlatCompositeNode((ICompositeNode) n));
            } else {
                listOfNodes.add(n);
            }
        });
        return listOfNodes;

    }

    public void addNode(INode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

}

