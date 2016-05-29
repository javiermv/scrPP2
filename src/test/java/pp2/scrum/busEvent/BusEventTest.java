/**
 * 
 */
package pp2.scrum.busEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import junit.framework.TestCase;

/**
 * @author yoshknight
 *
 */
public class BusEventTest extends TestCase implements ActionListener{
    BusEvent bus;
    boolean test1;
    /**
     * @param name
     */
    public BusEventTest(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        bus= new BusEvent();
        test1=false;
    }

    /**
     * Test method for {@link pp2.scrum.busEvent.BusEvent#BusEvent()}.
     */
    public final void testBusEvent() {
        BusEvent bus2 = new BusEvent();
    }

    /**
     * Test method for {@link pp2.scrum.busEvent.BusEvent#register(java.awt.event.ActionListener)}.
     */
    public final void testRegister() {
        bus.register(this);
    }

    /**
     * Test method for {@link pp2.scrum.busEvent.BusEvent#unregister(java.awt.event.ActionListener)}.
     */
    public final void testUnregister() {
        bus.register(this);
        bus.unregister(this);
    }

    /**
     * Test method for {@link pp2.scrum.busEvent.BusEvent#postEvent(java.awt.event.ActionEvent)}.
     */
    public final void testPostEvent() {
        bus.register(this);
        try {
            assertFalse(test1);
            bus.postEvent(new ActionEvent(this, 1, "test1"));
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            assertTrue(test1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            fail("Fallo el posteo del evento por interrupcion del treath");
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("test1") )
            test1=true;
            
        
    }

}
