
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
    public static void main(String[] args) {
        List<Artista> artistas = new ArrayList<>();

        Musica musica1 = new Musica();
        musica1.setNome("Neófito");
        musica1.setGenero("Rock Cristão");
        musica1.setDuracao(194);
        musica1.setArquivoAudio("./assets/Banda-Resgate-A-terapia.wav");

        Musica musica2 = new Musica();
        musica2.setNome("Terapia");
        musica2.setGenero("Rock Cristão");
        musica2.setDuracao(188);
        musica2.setArquivoAudio("assets/Banda-Resgate-A-terapia.wav");

        Musica musica3 = new Musica();
        musica3.setNome("Jack Joe and Nancy in the Mail");
        musica3.setGenero("Rock Cristão");
        musica3.setDuracao(185);
        musica3.setArquivoAudio("assets/Banda-Resgate-Jack_-Joe-and-Nancy-in-the-Mall.wav");

        List<Musica> musicas1 = new ArrayList<>();
        musicas1.add(musica1);
        musicas1.add(musica2);
        musicas1.add(musica3);

        Album album1 = new Album();
        album1.addMusicas(musicas1);
        album1.setCapa("./assets/BandaResgate-Capa.jpg");

        Artista bandaResgate = new Artista();
        bandaResgate.setNome("Banda Resgate");
        bandaResgate.addAlbum(album1);

        artistas.add(bandaResgate);

        AudioPlayer player = new AudioPlayer();
        JButton playStopButton = new JButton("Play");
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!player.getIsPlaying()) {
                    player.playAudio(artistas.get(0).getMusicasFromAlbum(bandaResgate, 0));
                    playStopButton.setText("Stop");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                player.nextAudio(artistas.get(0).getMusicasFromAlbum(bandaResgate, 0));
            }
        });
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                player.previousAudio(artistas.get(0).getMusicasFromAlbum(bandaResgate, 0));
            }
        });

        JOptionPane.showOptionDialog(
                null,
                "Você está ouvindo BandaResgate",
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                bandaResgate.getAlbuns().get(0).getCapa(),
                new Object[] { previousButton, playStopButton, nextButton },
                playStopButton);

        if (player.getAudioClip() != null) {
            player.getAudioClip().close();
        }
    }
}
