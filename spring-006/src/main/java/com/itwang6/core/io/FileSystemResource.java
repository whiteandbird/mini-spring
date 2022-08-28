package com.itwang6.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{

    private final String filePath;

    private final File file;

    public FileSystemResource(String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public FileSystemResource(File file){
        this.file = file;
        this.filePath = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }
}
