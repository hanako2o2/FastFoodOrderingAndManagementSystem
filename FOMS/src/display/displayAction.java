package display;

import action.Action;
import java.util.*;

/**
* @author Boey, Madelyn, Owen, Rais, Rithika
* @version 25/4/2024
*/
public class displayAction {
    /**
    * a method to show the menu
    * @param actions a list of actions
    * @return action selected
    */
    public static Action showMenu(List<Action> actions) {
        Scanner scanner = new Scanner(System.in);
        List<Character> freeChars = new ArrayList<Character>();
        Map<Character, Action> keyToActionMap = new HashMap<>();

        for (char j = 'a'; j <= 'z'; j++) {
            freeChars.add(j);
        }

        for (Action action : actions) {
            char c = freeChars.get(0);
            freeChars.remove(Character.valueOf(c));
            keyToActionMap.put(c, action);
            System.out.println(c + ": " + action.description());
        }

        char key;
        do {
            key = scanner.next().charAt(0);
        } while (!keyToActionMap.containsKey(key));
        
        return keyToActionMap.get(key);
    }
}
