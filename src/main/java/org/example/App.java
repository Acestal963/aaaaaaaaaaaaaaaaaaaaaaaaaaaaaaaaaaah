package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static final String url="https://www.imdb.com/name/nm0000115/";
    public static void main( String[] args )
    {
        if(getStatusConnectionCode(url)==200){

            Document dc= getHtmlDocument(url);
           //System.out.println(dc);
            Elements entradas=dc.select("a.ipc-primary-image-list-card__title"); //Busqueda de las peliculas en el jason principal (puercote)
            Elements entradas1=dc.select("a");
            System.out.println(entradas.size());
            for(Element elem : entradas){
                System.out.println(elem.text());
                System.out.println("URL: "+"https://www.imdb.com"+elem.attr("href"));
            }
        }
    }


    public static int getStatusConnectionCode (String url){
        Connection.Response respomse=null;
        try{
            respomse= Jsoup.connect(url).userAgent("Mozilla Firefox 126.0.1").timeout(100000).ignoreHttpErrors(true).execute();
        }catch(IOException e){
            System.out.println("Excepcion al obtener el Status COde: "+ e.getMessage());
        }
        return respomse.statusCode();
    }

    public static Document getHtmlDocument(String url){
        Document doc=null;
        try{
            doc=Jsoup.connect(url).userAgent("Mozilla Firefox 126.0.1").timeout(100000).get();
        }catch(IOException e){
            System.out.println("Excepcion al obtener el Htlm de la página: "+ e.getMessage());
        }
        return doc;
    }
}


