package Compulsory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

public class Main {

    public static void  main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String absolutePath = "C:\\Users\\spacm\\Documents\\GitHub\\PA\\Compulsory12\\src\\Compulsory\\newpackage\\ClassDemo";
        boolean found = false;
        String pathToJarOrClasses = absolutePath.substring(0, absolutePath.lastIndexOf("\\"));
        String packageAndClass = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1);

        try {
            File path = new File(pathToJarOrClasses);
            if (path.exists()) {
                URL url = path.toURI().toURL();
                MyClassloader classLoader = new MyClassloader();
                classLoader.addURL(url);
                Class cls = Class.forName(packageAndClass);
                System.out.println("Path: " + pathToJarOrClasses);
                System.out.println("PackageAndClass: " + packageAndClass);
            }
        } catch (ClassNotFoundException ex) {
            while (found == false) {
                try {
                    packageAndClass = pathToJarOrClasses.substring(pathToJarOrClasses.lastIndexOf("\\") + 1) + "." + packageAndClass;
                    pathToJarOrClasses = pathToJarOrClasses.substring(0, pathToJarOrClasses.lastIndexOf("\\"));
                    File path = new File(pathToJarOrClasses);
                    if (path.exists()) {
                        URL url = path.toURI().toURL();
                        MyClassloader classLoader = new MyClassloader();
                        classLoader.addURL(url);
                        Class cls = Class.forName(packageAndClass);
                        System.out.println("Classname: "+cls.getName());
                        System.out.println("Path: " + pathToJarOrClasses);
                        System.out.println("PackageAndClass: " + packageAndClass);
                        System.out.println("Methods:");
                        for(var method:Class.forName(packageAndClass).getMethods()){
                            System.out.printf("%s %s(",method.getReturnType(),method.getName());
                            for(var parameter:method.getParameterTypes()){
                                System.out.printf(" %s ",parameter.getCanonicalName());
                            }
                            System.out.printf(")\n");
                        }
                        System.out.println("\n\n\n");
                        System.out.println("Test methods:");
                        for(var method:Class.forName(packageAndClass).getMethods()){
                            if(method.isAnnotationPresent(Test.class)){
                                try{
                                    method.invoke(null);
                                }catch(Throwable exception){
                                    System.out.println(exception.getMessage());
                                }
                            }
                        }
                        break;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }


}
