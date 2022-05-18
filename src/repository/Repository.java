package repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import java.util.Set;

import entidade.Cadastro;
import entidade.Curso;
import entidade.Aluno;

public class Repository {
	
	private String alunoPath;
	private String cursoPath;
	private String relacaoPath;
	
	private Cadastro cadastroInput;
	
	public Repository(String aAlunoPath, String aCursoPath, String aRelacaoPath) {
		this.alunoPath = aAlunoPath;
		this.cursoPath = aCursoPath;
		this.relacaoPath = aRelacaoPath;
	}
    
	public Cadastro getCadastro(){
		
        this.cadastroInput = new Cadastro();
        
        loadAlunos();
        loadCursos();
        
        return loadRelacoes();
    }

	private Cadastro loadRelacoes(){
		
        try (   InputStream is = new FileInputStream(this.relacaoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String idAluno = palavras[0];
                String nome = palavras[2];
                String tipo = palavras[3];
				String ano = palavras[4];

                Curso curso = new Curso(nome, tipo,ano);
                Aluno aluno = this.cadastroInput.getAlunoFromId(idAluno);
            
                cadastroInput.addRelacaoAlunoCurso(aluno, curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return this.cadastroInput; 

    }

	private void loadAlunos(){

        try (   InputStream is = new FileInputStream(this.alunoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String id = palavras[0];
                String nome = palavras[1];

                Aluno aluno = new Aluno(id, nome);
                this.cadastroInput.addAluno(aluno);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void loadCursos(){

        try (   InputStream is = new FileInputStream(this.cursoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String nome = palavras[0];
                String tipo = palavras[1];
                String ano = palavras[2];

                Curso curso = new Curso(nome,tipo, ano);
                this.cadastroInput.addCurso(curso);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	
	public void saveCadastro(Cadastro cadastroOutput){
		
		saveAlunos(cadastroOutput.getAlunos());
		saveCurso(cadastroOutput.getCursos());
		saveRelacoes(cadastroOutput);

    }

	private void saveRelacoes(Cadastro cadastroOutput){

        try(    OutputStream os = new FileOutputStream(this.relacaoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Aluno aluno: cadastroOutput.getAlunos()){
                for(Curso curso: cadastroOutput.getCursosFromAluno(aluno.getId())){
                    pw.println(aluno.getId()+","+ aluno.getNome()+","+curso.getNome()+ ","+curso.getTipo()+","+ curso.getAno());
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void saveAlunos(Set<Aluno> alunosOutput){

        try(    OutputStream os = new FileOutputStream(this.alunoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Aluno aluno: alunosOutput){
                pw.println(aluno.getId()+","+aluno.getNome());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


	private void saveCurso(Set<Curso> cursoOutput){

        try(    OutputStream os = new FileOutputStream(this.cursoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Curso curso: cursoOutput){
                pw.println(curso.getNome()+","+curso.getTipo()+","+curso.getAno());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


}