package service;

import java.util.List;

import model.entity.Jogador;
import model.repository.JogadorRepository;

public class JogadorService {

	private JogadorRepository jogadorRepository = new JogadorRepository();
	
	public Jogador salvar (Jogador novoJogador) {
		return jogadorRepository.salvar(novoJogador);
	}
	
	public boolean atualizar(Jogador jogadorEditado) {
		return jogadorRepository.alterar(jogadorEditado);
	}
	
	public boolean excluir (int id) {
		return jogadorRepository.excluir(id);
		
	}
	
	public Jogador consultarPorId(int id) {
		return jogadorRepository.consultarPorId(id);
		
	}
	
	public List<Jogador> consultarTodos (){
		return jogadorRepository.consultarTodos();
	}
}
