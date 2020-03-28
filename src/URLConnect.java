import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLConnect {

    public static void main(String[] args) {


        try{

            URL url = new URL("http://pizzaprod.s3-website.us-east-2.amazonaws.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader inputReader = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));

            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            for(Map.Entry<String, List<String>> entry: headerFields.entrySet()){

                String key = entry.getKey();
                List<String> value = entry.getValue();

                System.out.println("key: "+key);
                for (String string: value){ System.out.println("value: "+value); }

            }

        }
        catch(MalformedURLException ex){

            System.out.println("Bad URL "+ ex.getMessage());
        }
        catch (IOException e){
            System.out.println("IOException"+ e.getMessage());
        }


    }
}
