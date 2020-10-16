import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CompositeNode implements ICompositeNode {
    private String code;
    private String renderer;

    private List<INode> nodes = new ArrayList<>();

    public CompositeNode(String code, String renderer, List<INode> nodesData) {
        this.code = code;
        this.renderer = renderer;
        addNodes(nodesData);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getRenderer() {
        return renderer;
    }

    @Override
    public List<INode> getNodes() {
        return nodes;
    }

    public void addNodes(List<INode> nodesData) {
        nodes.addAll(nodesData);
    }

    @Override
    public String toString() {
        return "CompositeNode{" +
                "code='" + code + '\'' +
                ", renderer='" + renderer + '\'' +
                ", nodes=" + nodes +
                '}';
    }

    public Stream<INode> flatten() {

        return Stream.concat(
                Stream.of(this),
                nodes.stream().flatMap(INode::flatten));
    }

}
