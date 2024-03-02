package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluir= false;
		String query = "DELETE FROM carta WHERTE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluir = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir carta");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluir;
	}

	@Override
	public boolean alterar(Carta novaCarta) {
		boolean alterar = false;
		String query = " UPDATE carta SET nome = ?, forca = ?, inteligencia = ?, "
			     + "       velocidade = ?, data_cadastro = ? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			
			pstmt.setString(1, novaCarta.getNome());
			pstmt.setInt(2, novaCarta.getForca());
			pstmt.setInt(3, novaCarta.getInteligencia());
			pstmt.setInt(4, novaCarta.getVelocidade());
			pstmt.setDate(5, Date.valueOf(novaCarta.getDataCadastro()));
			
			alterar = pstmt.executeUpdate() == 1;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar carta");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
			
		}
		return alterar;
	}

	@Override
	public Carta consultarTodosId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Carta carta = new Carta();
		String query = "SELECT * FROM carta WHERE id = " + id;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				carta.setId(Integer.parseInt(resultado.getString(id)));
				carta.setForca(resultado.getInt("FORCA"));
				carta.setInteligencia(resultado.getInt("INTELIGENCIA"));
				carta.setVelocidade(resultado.getInt("VELOCIDADE"));
				if (resultado.getDate("DATA_CADASTRO") != null) {
					carta.setDataCadastro(resultado.getDate("DATA_CADASTRO").toLocalDate());
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar consultar carta com id (" + id + ")");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return null;
	}

	@Override
	public ArrayList<Carta> consultarTodasCartas() {
		ArrayList<Carta> cartas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM carta";
		
		try {
			
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				Carta carta = new Carta();
				carta.setId(Integer.parseInt(resultado.getString("id")));
				carta.setForca(resultado.getInt("FORCA"));
				carta.setInteligencia(resultado.getInt("INTELIGENCIA"));
				carta.setVelocidade(resultado.getInt("VELOCIDADE"));
				if (resultado.getDate("DATA_CADASTRO")== null) {
					carta.setDataCadastro(resultado.getDate("DATA_CADASTRO").toLocalDate());
						
				}
				
				cartas.add(carta);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar consultar todas as cartas");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return cartas;
	}
	
	

}
