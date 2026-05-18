package APCS.Assets.AssetClasses;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Music extends Thread
{
    private String mp3FilePath;
    private SourceDataLine line;
    //private boolean aborted;    
	
    public Music(String path)
    {
	this.mp3FilePath = path;
        this.line = null;
    }
        
    public Music()
    {
        this.mp3FilePath = null;
        this.line = null;
    }
        
    public String getFile()
    {
        return mp3FilePath;
    }
        
    public void setFile(String path)
    {
        this.mp3FilePath = path;
    }
    
    public void stopPlayBack()
    {
        //aborted = true;
        line.stop();
    }
    
    public void mp3Player_Play()
    {
        String mp3FilePath = this.mp3FilePath;
        
            if(mp3FilePath != null)
            {
                AudioInputStream din = null;
                try {

                    File file = new File(mp3FilePath);

                    AudioInputStream in = AudioSystem.getAudioInputStream(file);
                    AudioFormat baseFormat = in.getFormat();

                    AudioFormat decodedFormat = new AudioFormat(
                                    AudioFormat.Encoding.PCM_SIGNED,
                                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                                    false);

                    din = AudioSystem.getAudioInputStream(decodedFormat, in);
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
                    line = (SourceDataLine) AudioSystem.getLine(info);

                    if(line != null) 
                    {
                        line.open(decodedFormat);
                        byte[] data = new byte[4096];
                        // Start
                        line.start();
                        int nBytesRead;

                        while ((nBytesRead = din.read(data, 0, data.length)) != -1) 
                        {	
                                line.write(data, 0, nBytesRead);
                        }
                        // Stop
                        line.drain();
                        line.stop();
                        line.close();
                        din.close();
                    }
            }
            catch(Exception e) 
            {
                    e.printStackTrace();
            }
            finally 
            {
                    if(din != null) 
                    {
                            try 
                            {
                                din.close(); 
                            } 
                            catch(IOException e) 
                            { 
                            }
                    }
            }
        }
                
	}
	
    public void run()
    {
        mp3Player_Play();
    }
}

