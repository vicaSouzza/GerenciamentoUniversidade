package entidade;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Cadastro {
	
	Map<Aluno, Set<Curso>> alunos = new TreeMap<>();
	Map<Curso, Set<Aluno>> cursos = new TreeMap<>();
	
	Map<String, Aluno> alunosById = new TreeMap<>();
	
	public Aluno getAlunoFromId(String id) {
		return alunosById.get(id);
	}
	
	public Set<Aluno> getAlunos() {
		return alunos.keySet();
	}
	public void addAluno(Aluno aluno) {
		this.alunos.put(aluno, new TreeSet<>());
		this.alunosById.put(aluno.getId(), aluno);
	}
	
	public Set<Curso> getCursos() {
		return cursos.keySet();
	}
	public void addCurso(Curso curso) {
		this.cursos.put(curso, new TreeSet<>());
	}
	
	public Set<Curso> getCursosFromAluno(String id) {
		return alunos.get(new Aluno(id, ""));
	}
	public Set<Aluno> getAlunosFromCurso(Curso curso) {
		return cursos.get(curso);
	}
	
	public void addRelacaoAlunoCurso(Aluno aluno, Curso curso) {
			this.cursos.get(curso).add(aluno);
			this.alunos.get(aluno).add(curso);
		
	}

}
