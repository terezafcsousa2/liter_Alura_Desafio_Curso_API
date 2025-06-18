package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "ano_nascimento")
    private Integer anoNascimento;
    @Column(name = "ano_falecimento")
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<Livro> livros;

    public Autor(Long id, String nome, Integer anoNascimento, Integer anoFalecimento) {

        this.id = id;
        this.nome = nome;
        this. anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;

    }
    public Autor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }


    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                ", livros=" + livros.stream().map(Livro::getTitulo).toList() +
                '}';
    }

}
