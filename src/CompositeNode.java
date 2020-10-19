import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeNode implements ICompositeNode {
    private final String code;
    private final String renderer;
    private final List<INode> nodes = new ArrayList<>();

    public CompositeNode(String code, String renderer, List<INode> nodes) {
        this.code=code;
        this.renderer=renderer;
        this.nodes.addAll(nodes);
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
        return Collections.unmodifiableList(nodes);          // return nodes from CompositeNode
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
