import freemarker.cache.FileTemplateLoader;
import freemarker.template.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {

    private List <Client> userList;
    private String imgFilePath;


    public Report(List<Client> userList, String imgFilePath) {
        this.userList = userList;
        this.imgFilePath = imgFilePath;
    }

    public List<Client> getUserList() {
        return userList;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void call() throws IOException, TemplateException{
        Configuration config = new Configuration(new Version(2,3,23));
        FileTemplateLoader ftl = new FileTemplateLoader(new File("C://Users//spacm//Documents//GitHub//PA//Lab10//Server"));
        config.setTemplateLoader(ftl);
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> map = new HashMap<>();
        map.put("clienti",getUserList());
        map.put("image",getImgFilePath());


        Template template = config.getTemplate("template.ftl");
        FileWriter file = new FileWriter("representation.html");
        template.process(map, file);

    }
}