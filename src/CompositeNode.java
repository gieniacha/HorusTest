import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositeNode implements ICompositeNode {
    private INode node;
    private List<INode> nodes = new ArrayList<>();

    public CompositeNode(String code, String renderer, List<INode> nodesList) {
        this.node = new Node(code, renderer);
        nodes.addAll(nodesList);
    }

    public CompositeNode(INode node, List<INode> nodes) {
        this.node = node;
        this.nodes = nodes;
    }

    @Override
    public String getCode() {
        return node.getCode();
    }

    @Override
    public String getRenderer() {
        return node.getRenderer();
    }

    @Override
    public List<INode> getNodes() {
        List<INode> listOfNodes = new ArrayList<>();

        listOfNodes.add(this.node);
        nodes.forEach(n -> {
            if (n instanceof ICompositeNode) {
                listOfNodes.addAll(((CompositeNode) n).getNodes());
            }
            else{
                listOfNodes.add(n);
            }
        });
        return listOfNodes;

    }

    @Override
    public String toString() {
        return "CompositeNode{" +
                "code='" + getCode() + '\'' +
                ", renderer='" + getRenderer() + '\'' +
                ", nodes=" + nodes +
                '}';
    }

}
