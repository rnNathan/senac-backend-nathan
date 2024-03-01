package model.repository;

import java.util.ArrayList;


public interface BaseRepository <T> {
	
	
	public T salvar(T novaEntidade);
	
		
	public boolean excluir(int id);
	
		
	public boolean alterar (T entidade);
		
	
	public T consultarTodosId(int id);
		
		
	public ArrayList<T> consultarTodasCartas();
	
	
}
