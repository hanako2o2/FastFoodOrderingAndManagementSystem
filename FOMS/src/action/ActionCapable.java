package action;

import java.util.List;

/**
 * capable actions of the user
 */
public interface ActionCapable {
    /**
     * @return list of actions that can be done
     */
    List<Action> allowableActions();
}
