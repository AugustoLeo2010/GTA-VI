package dt2.gtavi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "armas")
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Este campo é obrigatório.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "Este campo é obrigatório.")
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "imagem")
    private String imagem;

    @Column(name = "descricao")
    private String descricao;

    public Arma() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

