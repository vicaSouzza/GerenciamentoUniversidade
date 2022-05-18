package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import controller.Controller;
import controller.Opcao;
import entidade.Cadastro;
import entidade.Curso;
import entidade.Aluno;

public class ViewTerminal implements View{

	@Override
	public Opcao menu() {
		try {
			
			System.out.println("Entre com uma das opcoes:");
			for(Opcao o: Opcao.values()) {
				System.out.println("" + o.codigo + " - " + o.descricao);
			}
			
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String entrada = in.nextLine();
			int opcaoCodigo = Integer.parseInt(entrada);
			try {
				
				if(!Controller.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
					throw new InputMismatchException("Opcao nao corresponde a nenhum codigo listado");
				}
				return Controller.opcoesByCodigos.get(opcaoCodigo);
				
			}catch(NumberFormatException e) {
				throw new InputMismatchException("Opcao entrada nao eh um numero inteiro");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			
		}
		return menu();
			
	}

	@Override
	public void listarAlunos(Cadastro cadastro) {
		System.out.println("Todos os Alunos cadastrados: ");
		for(Aluno f: cadastro.getAlunos()) {
			System.out.println(f);
		}
	}

	@Override
	public void listarCursos(Cadastro cadastro) {
		System.out.println("Todos os Cursos cadastrados: ");
		for(Curso d: cadastro.getCursos()) {
			System.out.println(d);
		}
	}
	

	@Override
	public void listarAlunosFromCurso(Cadastro cadastro, Curso curso) {
		System.out.println("Todos os Alunos do curso: ");
		for(Aluno f: cadastro.getAlunosFromCurso(curso)) {
			System.out.println(f);
		}
	}

	@Override
	public void listarCursosFromAluno(Cadastro cadastro, Aluno aluno) {
		System.out.println("Todos os Cursos do aluno: ");
		for(Curso d: cadastro.getCursosFromAluno(aluno.getId())) {
			System.out.println(d);
		}
	}



	@Override
	public Curso addCurso() {
		return getNewCurso();
	}

	@Override
	public Aluno addAluno() {
		return getNewAluno();
	}

	@Override
	public Curso getCursoFromLista(Cadastro cadastro) {
		System.out.println("Escolha um curso");
		Curso curso = escolherCurso(cadastro);
		if(!cadastro.getCursos().contains(curso)) {
			System.out.println("Nao existe o curso com as informacoes entradas");
			return null;
		}
		
		return curso;
	}

	@Override
	public Aluno getAlunoFromLista(Cadastro cadastro) {
		System.out.println("Entre com o id do aluno");
		listarAlunos(cadastro);
		System.out.println("Entre com o id do aluno");
		String id = getString();
		
		Aluno aluno = cadastro.getAlunoFromId(id);
		if(aluno==null) {
			System.out.println("Nao existe o id deste aluno");
		}
		
		return aluno;
	}

	
	
	public Aluno getNewAluno() {
		System.out.println("Entre com os dados do aluno");
		String id = getIdAluno();
		String nome = getNomeAluno();
		return new Aluno(id, nome);
	}
	
	public String getIdAluno() {
		System.out.println("Entre com o id do aluno");
		return getString();
	}
	
	public String getNomeAluno() {
		System.out.println("Entre com a nome do aluno");
		return getString();
	}
	
	
	
	public Curso getNewCurso() {
		System.out.println("Entre com os dados do curso");
		String nome = getNome();
		String tipo = getTipoCurso();
		String ano = getAnoCurso();
		return new Curso(nome,tipo,ano);
	}
	
	public String getNome() {
		System.out.println("Entre com o nome do curso");
		return getString();
	}
	
	public String getTipoCurso() {
		System.out.println("Entre com a tipo do curso");
		return getString();
	}
	
	public String getAnoCurso() {
		System.out.println("Entre com ano do curso");
		return getString();
	}
	
	private Curso escolherCurso(Cadastro cadastro) {
		String nome = escolherNomesCursos(cadastro);
		String tipo = escolherTipoCursos(cadastro);
		String ano = escolherAnoCursos(cadastro);
		return new Curso(nome, tipo, ano);
	}
	
	private String escolherNomesCursos(Cadastro cadastro) {
		Set<String> nomes = new TreeSet<>();
		for(Curso d: cadastro.getCursos()) {
			nomes.add(d.getNome());
		}
		System.out.println("Escolha um dos nomes");
		for(String nome: nomes) {
			System.out.println(nome);
		}
		System.out.println("Entre com o nome escolhido:");
		return getString();
	}
	private String escolherTipoCursos(Cadastro cadastro) {
		Set<String> tipos = new TreeSet<>();
		for(Curso d: cadastro.getCursos()) {
			tipos.add(d.getTipo());
		}
		System.out.println("Escolha um dos niveis");
		for(String tipo: tipos) {
			System.out.println(tipo);
		}
		System.out.println("Entre com o tipo escolhida:");
		return getString();
	}
	
	private String escolherAnoCursos(Cadastro cadastro) {
		Set<String> anos = new TreeSet<>();
		for(Curso d: cadastro.getCursos()) {
			anos.add(d.getAno());
		}
		System.out.println("Escolha um dos anos");
		for(String ano: anos) {
			System.out.println(ano);
		}
		System.out.println("Entre com o ano escolhido:");
		return getString();
	}
	
	
	
	public String getString() {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		return str.trim();
	}
}
