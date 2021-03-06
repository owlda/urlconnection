import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            Thread.sleep(1000);
            System.out.println("Content: ");
            String parseData = "";
            int count = 0;
            int links = 0;
            int scripts = 0;
            int counter_scripts = 0;
            while( parseData != null){
                parseData = inputReader.readLine();
                count++;
                System.out.println(parseData);
                String scrap_link = "link";
                String scrap_script = "script";

                try{
                    if (parseData.contains(scrap_link))
                    {
                        links++;
                    }
                }catch (NullPointerException e){
                    System.out.println("NullPointerException"+ e.getMessage());
                }
                try{
                    if (parseData.contains(scrap_script))
                    {
                        scripts++;
                    }
                }catch (NullPointerException e){
                    System.out.println("NullPointerException"+ e.getMessage());
                }

                try{

                    String patternString = "script";
                    Pattern pattern = Pattern.compile(patternString);
                    Matcher matcher = pattern.matcher(parseData);

                    while (matcher.find()) {
                        counter_scripts++;
                    }
                }catch (NullPointerException e){
                    System.out.println("NullPointerException"+ e.getMessage());
                }

            }
            System.out.println("Site lines: "+ count);
            System.out.println("Site links: "+ links);
            System.out.println("Site script bibliotheques: "+ counter_scripts);
        }
        catch(MalformedURLException ex){

            System.out.println("Bad URL "+ ex.getMessage());
        }
        catch (IOException e){
            System.out.println("IOException"+ e.getMessage());
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException"+ e.getMessage());
        }


    }
}
