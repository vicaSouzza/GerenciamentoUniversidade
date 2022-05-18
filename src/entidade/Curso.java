package entidade;

import java.util.Objects;

public class Curso implements Comparable<Curso>{
	
	private String nome;
	private String tipo;
	private String ano;
	
	
	public Curso() {
	}


	public Curso(String nome, String tipo, String ano) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.ano = ano;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ano, nome, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(ano, other.ano) && Objects.equals(nome, other.nome)
				&& Objects.equals(tipo, other.tipo);
	}


	@Override
	public String toString() {
		return "Curso [nome= " + nome + ", tipo= " + tipo + ", ano= " + ano + "]";
	}


	@Override
	public int compareTo(Curso o) {
			if(!this.tipo.equals(o.tipo)) {
				return this.tipo.compareTo(o.tipo);
			}else if(!this.ano.equals(o.ano)) {
				return this.ano.compareTo(o.ano);
			}
			return this.nome.compareTo(o.nome);
		}
	}
	

