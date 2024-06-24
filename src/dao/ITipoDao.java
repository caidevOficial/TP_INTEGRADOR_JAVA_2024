package dao;

import java.util.ArrayList;

import entidades.Tipo;

public interface ITipoDao {
	public ArrayList<Tipo> getTipos(String tipo);
}
