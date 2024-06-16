package negocio;

import java.util.ArrayList;

import entidad.Tipo;

public interface ITipoNegocio {
	public ArrayList<Tipo> getTipos(String tipoBuscar);
}
