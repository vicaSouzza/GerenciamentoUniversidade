package controller;

public enum Opcao {
	
	LISTA_ALUNOS(1, "Listar todos os aluno cadastrados"), 
	LISTA_CURSOS(2, "Listar todos os cursos cadastrados"), 
	LISTA_ALUNOS_FROM_CURSO(3, "Listar todos os alunos de um curso escolhido"), 
	LISTA_CURSO_FROM_ALUNO(4, "Listar todos os cursos de um aluno escolhido"),
	ADICIONA_ALUNO(5, "Cadastrar um aluno"),
	ADICIONA_CURSO(6, "Cadastrar um novo curso"),
	ADICIONA_RELACAO(7, "Adicionar uma relacao entre um aluno e um curso"),
	SAIR(0, "Sair do programa");
	
	public String descricao;
	public int codigo;
	
	private Opcao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

}
