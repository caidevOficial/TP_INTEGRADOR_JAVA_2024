package negocioImpl;

import java.util.ArrayList;

import daoImpl.TipoDaoImpl;
import entidad.Tipo;
import negocio.ITipoNegocio;

public class TipoNegocioImpl implements ITipoNegocio {
	
	TipoDaoImpl tipoDaoImpl = new TipoDaoImpl();
	
	public ArrayList<Tipo> getTipos(String tipoBuscar) {
		return tipoDaoImpl.getTipos(tipoBuscar);
	}
}
