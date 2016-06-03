package pp2.scrum.dominio.interfaz;

import java.awt.event.ActionListener;

import pp2.scrum.dominio.Resultado;

public interface Comando<T>
{

    Resultado Execute(final T homeController, ActionListener al);

}