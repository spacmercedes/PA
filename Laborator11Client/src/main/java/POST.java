//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//public class Test {
//    public static void POST(String[] args) throws ClientProtocolException, IOException {
//        HttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost("");
////        StringEntity input = new StringEntity('persons');
////        post.setEntity(input);
//        HttpResponse response = client.execute(post);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = " ";
//        while ((line = rd.readLine()) != null) {
//            System.out.println(line);
//        }
//    }
//}
