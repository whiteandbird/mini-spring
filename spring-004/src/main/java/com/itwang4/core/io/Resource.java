package com.itwang4.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源获取
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
