package negocioimpl;

import java.util.ArrayList;

import daoimpl.TipoDaoImpl;
import entidades.Tipo;
import negocio.ITipoNegocio;

public class TipoNegocioImpl implements ITipoNegocio {
	
	TipoDaoImpl tipoDaoImpl = new TipoDaoImpl();
	
	public ArrayList<Tipo> getTipos(String tipoBuscar) {
		return tipoDaoImpl.getTipos(tipoBuscar);
	}
}
