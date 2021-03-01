package br.com.nailson.cursomc.domain.enums;

public enum PerfilEnum {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private PerfilEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilEnum toEnum(Integer cod) {
		if(cod==null)
			return null;
		
		for(PerfilEnum x: PerfilEnum.values()) {
			if(cod.equals(x.cod))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
