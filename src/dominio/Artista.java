package dominio;

import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String nome;
    final private List<Album> albuns;
    
    public Artista(String nome) {
        this.nome = nome;
        this.albuns = new ArrayList<>();
    }
    public Artista(String nome, List<Album> albuns) {
        this.nome = nome;
        this.albuns = albuns;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Album> getAlbuns() {
        return albuns;
    }

    public void addAlbum(Album album) {
        this.albuns.add(album);
    }

    public void addAlbuns(List<Album> albuns){
        this.albuns.addAll(albuns);
    }
    
}