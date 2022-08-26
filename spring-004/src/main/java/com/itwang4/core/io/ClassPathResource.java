package com.itwang4.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.itwang4.util.ClassUtils.getDefaultClassLoader;

public class ClassPathResource implements Resource{

    private final String classPath;

    private final ClassLoader classLoader;

    public ClassPathResource(String classPath){
        this(classPath, null);
    }

    public ClassPathResource(String classPath, ClassLoader classLoader){
        this.classPath = classPath;
        this.classLoader = classLoader == null ? getDefaultClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(classPath);
        if(resourceAsStream == null){
            throw new FileNotFoundException(this.classPath + " can not be opened because it is not exists");
        }
        return resourceAsStream;
    }
}
