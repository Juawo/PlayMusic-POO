import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dominio.Musica;

public class AudioPlayer {
    private Clip audioClip;
    private boolean isPlaying = false;
    private String musicTitleIsPlaying;
    private int iterador = 0;

    public String getMusicTitleIsPlaying() {
        return musicTitleIsPlaying;
    }

    public Clip getAudioClip() {
        return this.audioClip;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void isPlaying() {
        this.isPlaying = !isPlaying;
    }

    // Carregando um arquivo e setando no tipo Clip
    public void loadAudio(String filepath) {
        System.out.println("Carregando" + filepath);

        try {
            // Criando arquivo com base no caminho passado
            File audioFile = new File(filepath);

            // Criando um fluxo de aúdio com base no arquivo criado
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Configurando uma "linha" de aúdio que é carregada na memória, ou seja, um
            // Clip

            // Criando a especificação do formato (MP3, WAV...) do fluxo de aúdio que
            // criamos -> Para que o Clip saiba como configurar
            AudioFormat format = audioStream.getFormat();

            // Configurando informações para poder instanciar (colocar, usar) um Clip, aqui
            // geramos um Line generico que não possue os metódos que precisamos para
            // executar o programa
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Transformando o Line em Clip, porque o Clip possui os métodos
            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            // Capturando "erros/exceções" e exibindo o erro para o usuário
        } catch (UnsupportedAudioFileException exception) {
            System.out.println("O formato do arquivo de áudio não é suportado");
            exception.printStackTrace();
        } catch (LineUnavailableException exception) {
            System.out.println("A linha de áudio não está disponível.");
            exception.printStackTrace();
        } catch (IOException exception) {
            System.out.println("Erro ao ler o arquivo de áudio");
            exception.printStackTrace();
        }
    }

    // Tocando o arquivo de música
    public void playAudio() {
        System.out.println("PlayAudio");
        if (audioClip != null && !isPlaying) {
            System.out.println("PlayAudio Start");
            audioClip.start();
            this.isPlaying = true;
        }
    }

    // Parando o arquivo de música
    public void stopAudio() {
        if (audioClip != null && isPlaying) {
            System.out.println("StopAudio");
            audioClip.stop();
            this.isPlaying = false;
        }
    }

    // Tocar próxima música
    public void nextAudio(List<Musica> musicas) {
        System.out.println("Próxima música");
        stopAudio();
        if (iterador < (musicas.size() - 1)) {
            iterador++;
            loadAudio(musicas.get(iterador).getArquivoAudio());
            playAudio();
        } else if (iterador >= (musicas.size() - 1)) {
            iterador = 0;
            loadAudio(musicas.get(iterador).getArquivoAudio());
            playAudio();
        }
    }

    // Tocar música anterior
    public void previousAudio(List<Musica> musicas) {
        System.out.println("Música anterior");
        stopAudio();
        if (iterador > 0 && iterador <= (musicas.size() - 1)) {
            iterador--;
            loadAudio(musicas.get(iterador).getArquivoAudio());
            playAudio();
        } else if (iterador == 0) {
            iterador = musicas.size() - 1;
            loadAudio(musicas.get(iterador).getArquivoAudio());
            playAudio();
        }
    }
}
