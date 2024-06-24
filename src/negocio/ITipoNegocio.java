package negocio;

import java.util.ArrayList;

import entidades.Tipo;

public interface ITipoNegocio {
	public ArrayList<Tipo> getTipos(String tipoBuscar);
}
