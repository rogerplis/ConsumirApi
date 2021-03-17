package com.example.consumirapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {
    public static BufferedReader bufferedReader = null;
    public static  String getDados(String uri){

        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String linha = bufferedReader.readLine();;

            while (linha != null)
            {
                stringBuilder.append(linha).append("\n");
                linha = bufferedReader.readLine();

            }
        return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(bufferedReader != null) {
                try{
                  bufferedReader.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
