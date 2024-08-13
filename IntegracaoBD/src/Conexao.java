import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {

	private Connection Con;

	public boolean ConectaBD(String Usuario, String Senha, String Servidor, String Porta, String BancoDados) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String URL = "jdbc:mysql://" + Servidor + ":" + Porta + "/" + BancoDados;
			this.Con = DriverManager.getConnection(URL, Usuario, Senha);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return false;
	}

	Connection getConexao() {
		return this.Con;
	}

	boolean ExecutaSQL(String ComandoSQL) {
		try {
			Statement st = this.Con.createStatement();
			return st.execute(ComandoSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	boolean ExecutaSQL(String ComandoSQL, ArrayList<String> Params) {
		try {
			PreparedStatement pst = this.Con.prepareStatement(ComandoSQL);
			pst.clearParameters();
			for(int cont = 0; cont < Params.size();cont++) {
				pst.setString(cont+1, Params.get(cont));
			}
			
			return pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	 List<Aluno> ExecutaConsulta(String ComandoSQL){
		try {
			Statement st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery(ComandoSQL);
			List<Aluno> Lista = new ArrayList<Aluno>();
			while(resultado.next()) {
				Aluno A1 = new Aluno();
				A1.setNome(resultado.getString(1));
				A1.setMatricula(resultado.getString(2));
				A1.setCurso(resultado.getInt(3));
				A1.setTurno(resultado.getInt(4));
				Lista.add(A1);
			}
			return Lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Aluno>();
	}
	
}
