package view;

import controller.Opcao;
import entidade.Cadastro;
import entidade.Curso;
import entidade.Aluno;

public interface View {
	
	Opcao menu();
	
	void listarAlunos(Cadastro cadastro);
	void listarCursos(Cadastro cadastro);
	
	void listarAlunosFromCurso(Cadastro cadastro, Curso curso);
	void listarCursosFromAluno(Cadastro cadastro, Aluno aluno);
	
	Curso addCurso();
	Aluno addAluno();

	Curso getCursoFromLista(Cadastro cadastro);
	Aluno getAlunoFromLista(Cadastro cadastro);
	

}
