import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface INode {
    String getCode();

    String getRenderer();

    Stream<INode> flatten();


}
