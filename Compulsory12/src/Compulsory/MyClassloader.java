package Compulsory;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassloader extends URLClassLoader {
    public MyClassloader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
