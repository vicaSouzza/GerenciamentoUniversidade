package entidade;

import java.util.Objects;

public class Aluno implements Comparable<Aluno>{
	
	private String id;
	private String nome;
	
	public Aluno(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Aluno() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Aluno [id= " + id + ", nome= " + nome + "]";
	}

	@Override
	public int compareTo(Aluno o) {

		return this.id.compareTo(o.id);
	}
	
	
}
