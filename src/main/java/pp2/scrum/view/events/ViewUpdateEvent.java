/**
 * 
 */
package pp2.scrum.view.events;

import java.awt.event.ActionEvent;

/**
 * @author yoshknight
 *
 */
public class ViewUpdateEvent extends ActionEvent {

    /**
     * @param source
     * @param id
     * @param command
     */
    public ViewUpdateEvent(Object source, int id, String command) {
        super(source, id, command);
        // TODO Auto-generated constructor stub
    }
}
