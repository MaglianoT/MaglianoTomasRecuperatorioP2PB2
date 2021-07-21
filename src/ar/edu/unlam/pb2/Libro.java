package ar.edu.unlam.pb2;

public class Libro {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	private Integer codigo;
	private String autor;
	private String nombre;
	private Double precio;
	
	public Libro (Integer codigo, String nombre, String autor, Double precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getAutor() {
		return autor;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPrecio() {
		return precio;
	}

}
