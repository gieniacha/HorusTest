import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ICompositeNode extends INode {
    List<INode> getNodes();

}
