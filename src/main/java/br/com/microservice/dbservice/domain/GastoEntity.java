package br.com.microservice.dbservice.domain;


import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Table("tab_gasto")
public class GastoEntity {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id;

    private String descricao;
    private BigDecimal valor;
    private Long usuario;
    private Instant data;
    private Long categoria;

    public GastoEntity(UUID id, String descricao, BigDecimal valor, Long usuario, Instant data, Long categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.data = data;
        this.categoria = categoria;
    }

    public GastoEntity(String descricao, BigDecimal valor, Long usuario, Instant data, Long categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.data = data;
        this.categoria = categoria;
    }

    public GastoEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GastoEntity that = (GastoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
