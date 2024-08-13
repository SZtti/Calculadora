import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexao ConBD = new Conexao();
		
		boolean resp = ConBD.ConectaBD("root", "root", "localhost", "3306", "Academico");
		
		if (resp) {
			System.out.println("Funcionou!!!!!!!!!!!");
		}
		else {
			System.out.println("Foi triste :(");
		}
		
		ConBD.ExecutaSQL("Insert into Aluno(Nome, Matricula, Curso, Turno) VALUES ('Sara', '1234',1,2)");
		
		String ComandoParam = "Insert into Aluno(Nome, Matricula, Curso, Turno) VALUES (?,?,?,?);";
		ArrayList<String> ListaParam = new ArrayList<String>();
		ListaParam.add("Pedro");
		ListaParam.add("1456");
		ListaParam.add("1");
		ListaParam.add("2");
		ConBD.ExecutaSQL(ComandoParam, ListaParam);
		
		List<Aluno> Lista = ConBD.ExecutaConsulta("Select Nome, Matricula, Curso, Turno From Aluno;");
		
		for(int cont=0; cont<Lista.size();cont++) {
			System.out.println(Lista.get(cont).getNome());
		}
	}

}
