
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class InfoCommand implements Command{
    @Override
    public void execute(Catalog c) {
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();

            ParseContext context = new ParseContext();
            for (Item i : c.getList()) {
                FileInputStream input = new FileInputStream(Paths.get(i.getLocation()).toFile());
                parser.parse(input, handler, metadata, context);
                System.out.println(handler.toString());
                String[] metadataNames = metadata.names();
                for (String name : metadataNames) {
                    System.out.println(name + ":" + metadata.get(name));

                }
            }
        }
        catch(Exception e) {
            System.err.println("Eroare la extragere metadate " +e);

        }
    }
}