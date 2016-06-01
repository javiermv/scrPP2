package pp2.scrum.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mockit.Mock;
import mockit.MockUp;
import pp2.scrum.consulta.Consulta;
import pp2.scrum.dominio.Paginacion;
import pp2.scrum.dominio.entidad.UserStory;
import pp2.scrum.dominio.enums.DirOrden;
import pp2.scrum.dominio.enums.Estado;
import pp2.scrum.dominio.interfaz.IConsulta;
import pp2.scrum.dominio.interfaz.IMailGateway;

public class UserStoryPaginadoControllerTest extends TestCase
{
   private MockUp<IConsulta> consultaMock;
   private UserStoryPaginadoController controller;
   private MockUp<IMailGateway> mailGatewayMock;
   
   public UserStoryPaginadoControllerTest( String testName )
   {
       super( testName );
   }

   public static Test suite()
   {
       return new TestSuite( UserStoryPaginadoControllerTest.class );
   }

   protected void setUp()
   {
      consultaMock = new MockUp<IConsulta>(){
         @Mock
         public List<UserStory> ObtenerUserStoriesDB()
         {
            return new ArrayList<UserStory>(Arrays.asList              
               (
                     new UserStory("Titulo1", "Detalle1", "Autor1", "Responsable1", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo2", "Detalle2", "Autor2", "Responsable2", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo3", "Detalle3", "Autor3", "Responsable3", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo4", "Detalle4", "Autor4", "Responsable4", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo5", "Detalle5", "Autor5", "Responsable5", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo6", "Detalle6", "Autor6", "Responsable6", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo7", "Detalle7", "Autor7", "Responsable7", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo8", "Detalle8", "Autor8", "Responsable8", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo9", "Detalle9", "Autor9", "Responsable9", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo10", "Detalle10", "Autor10", "Responsable10", 10, 40, 1, Estado.ToDo, null, null),
                     new UserStory("Titulo11", "Detalle11", "Autor11", "Responsable11", 10, 40, 1, Estado.ToDo, null, null)
               ));
         }
      };
      mailGatewayMock = new MockUp<IMailGateway>(){};
      controller = new UserStoryPaginadoController(consultaMock.getMockInstance(),mailGatewayMock.getMockInstance());
   }
   
   public void testUserPaginadoStoryController()
   {        
      controller.getModel();
      
      assertEquals(controller.getPaginaActual().getPagina(), 1);
      assertEquals(controller.getItemsTotales(), 11);
      assertEquals(controller.getPaginasTotales(), 3);
      assertTrue(controller.getModel() != null);
      List<UserStory> lista = controller.ListarUserStories(null);
      assertEquals(lista.size(), 5);
      controller.setModel(null);
   }
   
   public void testUserPaginadoStoryPaginacion()
   {        
      List<UserStory> listaDefault,lista;
      listaDefault = controller.ListarUserStories(null);
      lista = controller.ObtenerPaginaAnterior();
      assertEquals(listaDefault.get(0).getTitulo(), lista.get(0).getTitulo());
      
      lista = controller.ObtenerPaginaPrimera();
      assertEquals(listaDefault.get(0).getTitulo(), lista.get(0).getTitulo());
      
      lista = controller.ObtenerPaginaSiguiente();
      assertEquals(controller.getPaginaActual().getPagina(), 2);
      
      lista = controller.ObtenerPaginaUltima();
      assertEquals(controller.getPaginaActual().getPagina(), 3);
      
      Paginacion paginacion = new Paginacion("Id", DirOrden.Desc, 1, 5);
      listaDefault = controller.ListarUserStories(paginacion);
      assertEquals(controller.getPaginaActual().getDireccionOrden(), DirOrden.Desc);
      paginacion.setDireccionOrden(DirOrden.Asc);
      paginacion.setItemsPorPagina(4);
      paginacion.setOrdenarPor("Id");
      paginacion.setPagina(2);
      
      assertEquals(controller.getPaginaActual().getDireccionOrden(), DirOrden.Asc);
      assertEquals(controller.getPaginaActual().getPagina(), 2);
      
   }
   
   public void testConsulta()
   { 
      Consulta consulta = new Consulta();
      consulta.ObtenerUserStoriesDB();
   }
}
