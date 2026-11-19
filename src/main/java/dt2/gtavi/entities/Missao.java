package dt2.gtavi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "missoes")
public class Missao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Este campo é obrigatório.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "local")
    private String local;

    @NotBlank(message = "Este campo é obrigatório.")
    @Column(name = "dificuldade", nullable = false)
    private String dificuldade;

    @Column(name = "imagem")
    private String imagem;

    @Column(name = "descricao")
    private String descricao;

    public Missao() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

