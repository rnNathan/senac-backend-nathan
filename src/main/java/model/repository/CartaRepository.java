package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.entity.Carta;

public class CartaRepository implements BaseRepository<Carta>{
	
	
	public ArrayList<Carta> sortearSeisCartas(){
		
		ArrayList<Carta> cartaSorteadas = new ArrayList<Carta> ();
			
			String sql = "select * from carta " +
						" order by rand() " + 
						" limit 6 ";
			
			Connection conn = Banco.getConnection();
			PreparedStatement pstmt = Banco.getPreparedStatement(conn, sql);
			
			try {
				ResultSet resultado = pstmt.executeQuery();
				
				while (resultado.next()) {
					
					Carta c = new Carta();
					c.setId(resultado.getInt("id"));
					c.setNome(resultado.getString("nome"));
					c.setForca(resultado.getInt("forca"));
					c.setInteligencia(resultado.getInt("inteligencia"));
					c.setVelocidade(resultado.getInt("velocidade"));
					
					if(resultado.getDate("DATA_CADASTRO") != null) {
							c.setDataCadastro(resultado.getDate("data_cadastro").toLocalDate());
					}
					
					cartaSorteadas.add(c);
				}
			}catch (SQLException e) {
				System.out.println("Erro ao sortear cartas");
				System.out.println("ERRO: " + e.getMessage());
			
			}finally {
				Banco.closeStatement(pstmt);
				Banco.closeConnection(conn);
				
			}
			
		return cartaSorteadas;
		
		
	}
		
	
		
			

	@Override
	public Carta salvar(Carta novaCarta) {
		String query = "INSERT INTO carta (nome, forca, inteligencia, velocidade, data_cadastrada) value (?, ?, ? , ? , ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt  = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setString(1,novaCarta.getNome());
			pstmt.setInt(2,novaCarta.getForca());
			pstmt.setInt(3,novaCarta.getInteligencia());
			pstmt.setInt(4,novaCarta.getVelocidade());
			pstmt.setDate(5, Date.valueOf(LocalDate.now()));
			
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
		
			if(resultado.next()) {
				novaCarta.setId(resultado.getInt(1));
			}
			
		}catch (SQLException e) {
			System.out.println("Erro ao salvar cartas");
			System.out.println("ERRO: " + e.getMessage());
			
		}finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);

		}
			
		return novaCarta;
	}

		

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Carta entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Carta consultarTodosId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carta> consultarTodasCartas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
