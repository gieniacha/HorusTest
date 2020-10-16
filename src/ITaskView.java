import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

interface ITaskView {
  IComponentNode findComponent(String code);

  IComponentNode findComponentByRenderer(String renderer);

  int countComponents();
}

class TaskView implements ITaskView {
  private List<IComponentNode> components;

  @Override
  public IComponentNode findComponent(String code) {
    return findByPredicate(component->component.getCode().equals(code));
  }

  @Override
  public IComponentNode findComponentByRenderer(String renderer) {
    return findByPredicate(component->component.getRenderer().equals(renderer));
  }

  private IComponentNode findByPredicate(Predicate<IComponentNode> predicate){
    return components.stream()
        .map(child->child.getMatchingObject(predicate))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst()
        .orElse(null);
  }

  @Override
  public int countComponents() {
    return components
        .stream()
        .mapToInt(IComponentNode::getChildrenCount)
        .sum();
  }
}

interface IComponentNode {
  String getCode();

  String getRenderer();

  default int getChildrenCount(){
    return 1;
  }

  default Optional<IComponentNode> getMatchingObject(Predicate<IComponentNode> predicate){
    if(predicate.test(this)){
      return Optional.of(this);
    }
    return Optional.empty();
  }
}

interface ICompositeComponentNode extends IComponentNode {
  List<IComponentNode> getComponents();

  @Override
  default int getChildrenCount() {
    return getComponents()
        .stream()
        .mapToInt(IComponentNode::getChildrenCount)
        .sum();
  }
}