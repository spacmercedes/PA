import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private List <Client> userList = new ArrayList<>();
    private String imgFilePath;


    public Report(List<Client> userList, String imgFilePath) {
        this.userList = userList;
        this.imgFilePath = imgFilePath;
    }

    public List<Client> getUserList() {
        return userList;
    }

    public void setUserList(List<Client> userList) {
        this.userList = userList;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public void call() throws IOException, TemplateException{
        Configuration config = new Configuration();
        Template template = config.getTemplate("template.ftl");
        FileWriter fos = new FileWriter("representation.html");
        template.process(this,fos);
    }
}