import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

        return getFlattenedStructureStream(nodes)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {

        return (int) getFlattenedStructureStream(nodes)
                .count();
    }

    private Stream<INode> getFlattenedStructureStream(List<INode> nodes) {

        List<INode> listOfNodes = new ArrayList<>();

        nodes.forEach(n -> listOfNodes.addAll(getCompositeNodesRecursive(n)));

        return listOfNodes.stream();
    }

    private List<INode> getFlatCompositeNode(ICompositeNode compositeNode) {

        List<INode> listOfNodes = new ArrayList<>();

        listOfNodes.add(new Node(compositeNode.getCode(), compositeNode.getRenderer()));

        compositeNode.getNodes().forEach(n -> listOfNodes.addAll(getCompositeNodesRecursive(n)));

        return listOfNodes;

    }

    private List<INode> getCompositeNodesRecursive(INode compositeNode) {

        if (compositeNode instanceof ICompositeNode) {
            return getFlatCompositeNode((ICompositeNode) compositeNode);
        } else {
            return Stream.of(compositeNode).collect(Collectors.toList());
        }
    }

    public void addNode(INode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

}

