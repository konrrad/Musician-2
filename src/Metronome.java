import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Metronome implements ActionListener {
    private AudioClip audioClip;
    private final Timer timer;
    private int beats=0;
    private static final int maxBeats=8;
    URL clickUrl=null;

    public Metronome()
    {
        File click=new File("./Click2.wav");
        try
        {
            URI uri=click.toURI();
            clickUrl=uri.toURL();
            audioClip= Applet.newAudioClip(this.clickUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        timer=new Timer(0,this);
    }
    public void play(int beat)
    {
        timer.setDelay((int) Math.round(((double)60000/(double)beat)));
        beats=0;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(this.beats>=maxBeats)
        {
            timer.stop();
        }
        else
        {
            audioClip.loop();
            beats++;

        }

    }
}
