package ar.edu.unlam.pb2;

public abstract class Libro implements Comparable <Libro>{
	
	private Integer codigo;
	private String autor;
	private String nombre;
	private Double precio;
	private TipoDeLibro tipo;

	
	public Libro (Integer codigo, TipoDeLibro tipo, String nombre, String autor, Double precio) {
		this.codigo = codigo;
		this.tipo = tipo;
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

	public TipoDeLibro getTipo() {
		return tipo;
	}

	@Override
	public int compareTo(Libro o) {
		
		return this.getCodigo() - o.getCodigo();
	}


}
