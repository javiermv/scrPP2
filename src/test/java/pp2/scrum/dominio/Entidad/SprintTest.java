package pp2.scrum.dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pp2.scrum.controller.UserStoryHelper;
import pp2.scrum.dominio.entidad.Sprint;
import pp2.scrum.dominio.entidad.UserStory;


public class SprintTest extends TestCase {
	
	UserStoryHelper userStory1
			 ,userStory2
			 ,userStory3
			 ,userStory4
			 ;
		
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public SprintTest( String testName ) {
	    super( testName );
	}
	
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
	    return new TestSuite( SprintTest.class );
	}
	
	public void  setUp() {


		userStory1 =  new UserStoryHelper (new UserStory("Titulo1", "Detalle1"));
		userStory2 =  new UserStoryHelper (new UserStory("Titulo2", "Detalle2"));
		userStory3 =  new UserStoryHelper (new UserStory("Titulo3", "Detalle3"));
		userStory4 =  new UserStoryHelper (new UserStory("Titulo4", "Detalle4"));

		
	}
	
	/**
	 * Verifico que se asignen bien los atributos en el setUp
	 */
	public void testSprint() {
		Sprint s = new Sprint(1,null,30,null);
		assertTrue(true);
		s.setDuracion(21);
		assertEquals(s.getDuracion(), 21);
		
		List<UserStoryHelper> historias = new ArrayList<UserStoryHelper>();
		historias.add(userStory2);
		historias.add(userStory3);
		historias.add(userStory4);
		s.setUserStories(historias);
		
		s.setUserStory(userStory1);
		

	}
	
}
