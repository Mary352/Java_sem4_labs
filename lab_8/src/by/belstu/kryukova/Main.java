package by.belstu.kryukova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
	//-------1----------------------------------------------------------
        URL rw = null;
        String urlName = "https://www.rw.by/";

        try {
            rw = new URL(urlName);
        }
        // некорректно заданы протокол, доменное имя или путь к файлу
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        if (rw == null)
            throw new RuntimeException();
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(rw.openStream())))
        {
            String line = "";
            while ((line = bufReader.readLine()) != null)
            {
                System.out.println(line);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
