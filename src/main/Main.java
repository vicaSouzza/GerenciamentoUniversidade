package main;

import controller.Controller;
import repository.Repository;
import view.View;
import view.ViewTerminal;

class Main {
	
	public static void main(String[] args) {
		View view = new ViewTerminal();
		String alunoPath = "database/aluno.csv";
		String cursoPath = "database/cursos.csv";
		String relacaoPath = "database/aluno_curso.csv";
		Repository model = new Repository(alunoPath, cursoPath, relacaoPath);
		
		new Controller(view, model).init();
	}

}
