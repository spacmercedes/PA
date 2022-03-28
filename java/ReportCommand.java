
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import freemarker.template.Configuration;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    @Override
    public void execute(Catalog catalog) {
        Configuration configuration;
        configuration = new Configuration(new Version(2, 3, 23));

        try {
            configuration.setDirectoryForTemplateLoading(Paths.get("C:\\Users\\spacm\\IdeaProjects\\Laborator5Homework\\src\\main\\java").toFile());
        } catch (Exception e) {
            System.err.println("Eroare " + e);
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> inp = new HashMap<>();
        inp.put("title", "catalog");


        Item i = new Article("article","Cursuri", "src/main/java/cursuri.txt","1998","Ion Creanga");
        inp.put("article", i);
        inp.put("catalog", catalog.getList());

        Template template;
        try {
            template =  configuration.getTemplate("content.ftl");
            FileWriter file = new FileWriter("rez.html");
            template.process(inp, file);
            Desktop d = Desktop.getDesktop();
            d.open(Paths.get("rez.html").toFile());
        } catch (IOException e) {
            System.err.println("Error " + e);
        } catch (Exception e) {

            System.err.println("Error " + e);
        }


    }
}