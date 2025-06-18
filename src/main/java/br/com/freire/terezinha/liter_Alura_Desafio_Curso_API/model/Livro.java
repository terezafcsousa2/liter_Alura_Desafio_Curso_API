package br.com.freire.terezinha.liter_Alura_Desafio_Curso_API.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;

    @Column(name = "download_count")
    private Integer downloadCount;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Construtores
    public Livro() {}

    public Livro(String titulo, String idioma, Integer downloadCount, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloadCount = downloadCount;
        this.autor = autor;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) {
        this.autor = autor; }

    @Override
    public String toString() {
        return String.format("Livro{id=%d, titulo='%s', idioma='%s', downloadCount=%d}", id, titulo, idioma, downloadCount);
    }
}
