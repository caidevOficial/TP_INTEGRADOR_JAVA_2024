package dao;

import java.util.ArrayList;

import entidad.Tipo;

public interface ITipoDao {
	public ArrayList<Tipo> getTipos(String tipo);
}
