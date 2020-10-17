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

    public Stream<INode> getFlattenedStructure(List<INode> nodes) {

        List<INode> nodesStream = new ArrayList<>();
        for (INode n : nodes) {
            if (n instanceof ICompositeNode) {
                nodesStream.addAll(((CompositeNode) n).getNodes());
            } else {
                nodesStream.add(n);
            }
        }
        return nodesStream.stream();
    }

    public void addNode(INode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

}

