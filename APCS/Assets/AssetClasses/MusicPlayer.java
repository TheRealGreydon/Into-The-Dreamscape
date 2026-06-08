package APCS.Assets.AssetClasses;

import java.io.File;
import javax.sound.sampled.*;

public class MusicPlayer extends Thread
{
    private SourceDataLine line;
    private File file;
    private int volume = 100;
    private boolean paused = false, stopped = false,looping = false,playing = false;
    private final Object pauseLock = new Object();

    public MusicPlayer(String path) {file = new File(path);line = null;}

    public String getFile() {return file.getPath();}
    public void setFile(String path) {file = new File(path);}

    public void setVolume(int level)
    {
        volume = Math.max(0, Math.min(100, level));
        if (line != null && line.isControlSupported(FloatControl.Type.MASTER_GAIN))
        {
            FloatControl gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float dB = min + (volume / 100.0f) * (max - min);
            gainControl.setValue(dB);
        }
    }
    public int getVolume() {return volume;}
    public void pausePlayback() {paused = true; if (line != null && line.isRunning()) {line.stop();}}
    public void resumePlayback() {synchronized (pauseLock) {paused = false;if (line != null && !line.isRunning()) {line.start();}pauseLock.notifyAll();}}
    public void stopPlayback()
    {
        stopped = true;
        paused = false;
        playing = false;
        synchronized (pauseLock) {pauseLock.notifyAll();}
        if (line != null && line.isRunning()) {line.stop();line.close();}
    }

    public boolean isPlaying() {return playing;}
    public void setLooping(boolean x) {looping = x;}
    public boolean isLooping() {return looping;}
    public boolean isPaused() {return paused;}
    public boolean isStopped() {return stopped;}

    public void run()
    {
        do
        {
            stopped = false;
            paused = false;
            playing = true;
            playOnce();
            playing = false;
        }
        while (looping && !stopped);
    }
    private void playOnce()
    {
        try
        {
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();

            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                    false);

            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);

            if (line != null)
            {
                line.open(decodedFormat);
                setVolume(volume);
                line.start();

                byte[] data = new byte[4096];
                int nBytesRead;

                while (!stopped && (nBytesRead = din.read(data, 0, data.length)) != -1)
                {
                    synchronized (pauseLock)
                    {
                        while (paused && !stopped)
                        {
                            try { pauseLock.wait(); }
                            catch (InterruptedException e) { return; }
                        }
                    }
                    if (stopped) break;
                    line.write(data, 0, nBytesRead);
                }

                if (!stopped)
                {
                    line.drain();
                }
                line.stop();
                line.close();
                din.close();
            }
        }
        catch (Exception e) {}
    }
}
