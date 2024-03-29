package com.gestaosimples.servico.domain.enuns;

public enum Perfil {
    A("A", "ROLE_ADMIN"), //
    C("C", "ROLE_CLIENTE");

    private String codigo;
    private String descricao;

    private Perfil(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil toEnum(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Perfil tipo : Perfil.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("id inválido: " + codigo);
    }

}
