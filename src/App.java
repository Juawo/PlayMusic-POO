// import java.util.Scanner;

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
        // Scanner scannerInput = new Scanner(System.in);

        List<Artista> artistas = new ArrayList<>();

        Musica musica1 = new Musica();
        musica1.setNome("Neófito");
        musica1.setGenero("Rock Cristão");
        musica1.setDuracao(194);
        musica1.setArquivoAudio("./assets/Banda-Resgate/Banda-Resgate-Neofito.wav");

        Musica musica2 = new Musica();
        musica2.setNome("Terapia");
        musica2.setGenero("Rock Cristão");
        musica2.setDuracao(188);
        musica2.setArquivoAudio("assets/Banda-Resgate/Banda-Resgate-A-terapia.wav");

        Musica musica3 = new Musica();
        musica3.setNome("Jack Joe and Nancy in the Mail");
        musica3.setGenero("Rock Cristão");
        musica3.setDuracao(185);
        musica3.setArquivoAudio("./assets/Banda-Resgate/Banda-Resgate-Jack_-Joe-and-Nancy-in-the-Mall.wav");

        List<Musica> musicas1 = new ArrayList<>();
        musicas1.add(musica1);
        musicas1.add(musica2);
        musicas1.add(musica3);

        Album album1 = new Album();
        album1.addMusicas(musicas1);
        album1.setCapa("./assets/Banda-Resgate/BandaResgate-Capa.jpg");

        Artista bandaResgate = new Artista();
        bandaResgate.setNome("Banda Resgate");
        bandaResgate.addAlbum(album1);

        artistas.add(bandaResgate);

        Musica musica4 = new Musica();
        musica4.setNome("Depois Da Guerra");
        musica4.setGenero("Rock Cristão");
        musica4.setDuracao(194);
        musica4.setArquivoAudio("./assets/Oficina-G3/Oficina-G3-Depois-Da-Guerra.wav");

        Musica musica5 = new Musica();
        musica5.setNome("Meus Proprios Meios");
        musica5.setGenero("Rock Cristão");
        musica5.setDuracao(194);
        musica5.setArquivoAudio("./assets/Oficina-G3/Oficina-G3-Meus-Proprios-Meios.wav");

        List<Musica> musicas2 = new ArrayList<>();
        musicas2.add(musica4);
        musicas2.add(musica5);

        Album album2 = new Album();
        album2.addMusicas(musicas2);
        album2.setCapa("./assets/assets/Oficina-G3/Depois-Da-Guerra-Capa.png");

        Artista oficinaG3 = new Artista();
        oficinaG3.setNome("OficinaG3");
        oficinaG3.addAlbum(album2);

        artistas.add(oficinaG3);

        AudioPlayer player = new AudioPlayer();

        // Em construção - Escolha de album
        // int escolha = 0;
        // System.out.print(" -- Albuns --\n 1 - BandaResgate \n 2 - OficinaG3\n Escolha
        // : ");
        // escolha = scannerInput.nextInt();

        // // if (escolha == 1) {
        // //
        // player.loadAudio(bandaResgate.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio());
        // // } else if (escolha == 2){
        // //
        // player.loadAudio(oficinaG3.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio());

        // // }

        JButton playStopButton = new JButton("Play");
        player.loadAudio(oficinaG3.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio());

        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!player.getIsPlaying()) {
                    player.playAudio();
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
