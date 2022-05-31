//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
    public MyClassLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    public void addURL(URL url) {
        super.addURL(url);
    }
}
