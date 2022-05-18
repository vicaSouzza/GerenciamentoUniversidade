package controller;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import entidade.Aluno;
import entidade.Cadastro;
import entidade.Curso;
import repository.Repository;
import view.View;

public class Controller {
	
	private View view;
	private Repository model;
	private Cadastro cadastro;
	
	public static Map<Integer, Opcao> opcoesByCodigos = new TreeMap<>();
	{
		for(Opcao o: Opcao.values()) {
			opcoesByCodigos.put(o.codigo, o);
		}
	}
	
	public Controller(View view, Repository model) {
		this.view = view;
		this.model = model;
	}
	
	public void init() {
		cadastro = model.getCadastro();
		
		Opcao codigo = null;
		while(codigo!=Opcao.SAIR) {
			codigo = view.menu();
			switch (codigo) {
			case LISTA_ALUNOS: {listarAlunos(); break;}
			case LISTA_CURSOS: {listarCursos(); break;}
			case ADICIONA_ALUNO: {adicionarAluno(); break;}
			case ADICIONA_CURSO: {adicionarCurso(); break;}
			case LISTA_ALUNOS_FROM_CURSO: {listaAlunosFromCurso(); break;}
			case LISTA_CURSO_FROM_ALUNO: {listaCursosFromAluno(); break;}
			case ADICIONA_RELACAO: {addRelacao(); break;}
			case SAIR: {terminar(); break;}
			default:
				throw new IllegalArgumentException("Unexpected value: " + codigo);
			}
		}
	}
	
	private void listarAlunos() {
		view.listarAlunos(cadastro);
	}
	
	private void listarCursos() {
		view.listarCursos(cadastro);
	}
	
	private void adicionarAluno() {
		Aluno aluno = view.addAluno();
		this.cadastro.addAluno(aluno);
		model.saveCadastro(cadastro);
	}
	
	private void adicionarCurso() {
		Curso curso = view.addCurso();
		this.cadastro.addCurso(curso);
		model.saveCadastro(cadastro);
	}
	
	private void listaAlunosFromCurso() {
		Curso curso = view.getCursoFromLista(cadastro);
		if(curso==null) return;
		view.listarAlunosFromCurso(cadastro, curso);
	}
	
	
	private void listaCursosFromAluno() {
		Aluno aluno = view.getAlunoFromLista(cadastro);
		if(aluno==null) return;
		view.listarCursosFromAluno(cadastro, aluno);
	}
	
	private void addRelacao() {
		Aluno aluno = view.getAlunoFromLista(cadastro);
		if(aluno==null) return;
		Curso curso = view.getCursoFromLista(cadastro);
		if(curso==null) return;
		cadastro.addRelacaoAlunoCurso(aluno, curso);
		model.saveCadastro(cadastro);
	}
	
	private void terminar() {
		System.out.println("Programa Finalizado");
		
	}

}
