package com.itwang5.core.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url){
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        log.info("url请求,{}", url);
        URLConnection urlConnection = this.url.openConnection();
        try{
            return urlConnection.getInputStream();
        }catch (IOException e){
            if(urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection)urlConnection).disconnect();
            }
            throw e;
        }
    }
}
