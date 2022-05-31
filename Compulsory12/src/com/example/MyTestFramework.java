package com.example;

import javax.tools.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class MyTestFramework {

    public static List getClassesNamesFromJar(String jarName) {

        ArrayList classes = new ArrayList();
        System.out.println("JAR: " + jarName);
        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {break;}
                if (jarEntry.getName().endsWith(".class")) {
                    System.out.println("FOUND CLASSES " + jarEntry.getName());
                    classes.add(jarEntry.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static final class MyDiagnosticListener implements DiagnosticListener {
        @Override
        public void report(Diagnostic diagnostic) {
            System.out.println(diagnostic);
        }
    }

    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;

        //CITESTE INPUT-UL DE LA TASTATURA
        Scanner sc = new Scanner(System.in);
        String projectURL = sc.nextLine();//PRIMESTE URL CATRE PROJECT/JAR
        String className = sc.nextLine();//PRIMESTE DENUMIREA CLASEI

        com.example.MyClassLoader myLoader = new com.example.MyClassLoader();
        File path = new File(projectURL);
        if (path.exists()) {
            URL url = path.toURI().toURL();
            myLoader.addURL(url);//ADAUGI URL LA CLASSPATH
        }

        //INCARCA CLASELE
        Class newClass = myLoader.loadClass(className);
        System.out.println(newClass.getPackage());
        if (newClass.isInterface()) {
            System.out.println("ACEASTA ESTE O INTERFATA.");
        } else {
            System.out.println("ACEASTA NU ESTE O INTERFATA.");
        }

        //PREIA CAMPURILE
        System.out.println("\nCAMPURI:");
        for (Field camp : newClass.getDeclaredFields()) {
            System.out.println(camp);
        }

        //PREIA CONSTRUCTORI
        System.out.println("\nCONSTRUCTORI:");
        for (Constructor c : newClass.getConstructors()) {
            System.out.println(c);
        }

        //PREIA TOATE METODELE DIN CLASE
        System.out.println("\nMETODE:");
        for (Method metoda : newClass.getMethods()) {
            System.out.println(metoda);
        }


        System.out.println("\nTESTE EXECUTATE:");
        for (Method m : newClass.getMethods()) {
            //EXECUTAREA TESTELOR
            if (m.isAnnotationPresent(com.example.Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("TEST %s ESUAT: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("TESTE TRECUTE: %d, TESTE ESUATE: %d%n", passed, failed);
        List classes = getClassesNamesFromJar("C:\\Users\\spacm\\Documents\\GitHub\\PA\\Compulsory12\\MyProgram.jar");
        for(Object clasa :classes){
            System.out.println(clasa);
        }

        //JCOMPILER SI TESTE NON-STATICE
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(listener, null, null);

        File file = new File("C:\\Users\\spacm\\Documents\\GitHub\\PA\\Compulsory12\\src\\MyProgram.java");
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, javaFileObjects);
        if (task.call()) {
            System.out.println("COMPILAT CU SUCCES");
        }
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        passed = 0; failed = 0;
        String projectURL1 = "C:\\Users\\spacm\\Documents\\GitHub\\PA\\Compulsory12\\src";
        String className1 = "MyProgram";

        MyClassLoader myLoaderJ = new MyClassLoader();
        File pathJ = new File(projectURL1);
        if (pathJ.exists()) {
            URL url = path.toURI().toURL();
            myLoaderJ.addURL(url);//ADAUGI URL LA CLASSPATH
        }

        Class newClass1 = myLoader.loadClass(className1);
        System.out.println(newClass1.getPackage());
        Object i = newClass1.newInstance();
        if (newClass1.isInterface()) {
            System.out.println("ACEASTA ESTE O INTERFATA:");
        } else {
            System.out.println("ACEASTA NU ESTE O INTERFATA:");
        }

        //PREIA CAMPURILE
        System.out.println("\nCAMPURI:");
        for (Field camp : newClass1.getDeclaredFields()) {
            System.out.println(camp);
        }

        //PREIA TOATE METODELE DIN CLASE
        System.out.println("\nMETODE:");
        for (Method method : newClass1.getMethods()) {
            System.out.println(method);
        }

        //PREIA CONSTRUCTORI
        System.out.println("\nCONSTRUCTORI:");
        for (Constructor constructor : newClass1.getConstructors()) {
            System.out.println(constructor);
        }

        System.out.println("\nTESTE EXECUTATE:");
        //EXECUTAREA TESTELOR
        for (Method m : newClass1.getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    if( Modifier.isStatic(m.getModifiers()))
                    {
                        m.invoke(null);
                        passed++;
                    } else {
                        m.invoke(i);
                        passed++;
                    }
                } catch (Throwable ex) {
                    System.out.printf("TEST %s ESUAT: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.println("TESTE TRECUTE: "+ passed +"  TESTE ESUATE: "+failed);
    }


    }
