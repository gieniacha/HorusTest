import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyStructure implements IMyStructure {

    private List<INode> nodes = new ArrayList<>();

    @Override
    public INode findByCode(String code) {

        return findNode(n -> n.getCode().equals(code));
    }

    @Override
    public INode findByRenderer(String renderer) {

        return findNode(n -> n.getCode().equals(renderer));
    }

    private INode findNode(Predicate<INode> predicate){

        return flattenStructure(nodes)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {

        return(int) flattenStructure(nodes)
                .count();

    }

    private Stream<INode> flattenStructure(List<INode> nodes){

        return nodes.stream().flatMap(INode::flatten);
    }

    public void addNode(INode node) {

        nodes.add(node);
    }


}

