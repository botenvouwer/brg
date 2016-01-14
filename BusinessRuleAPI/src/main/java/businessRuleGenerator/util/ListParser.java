package businessRuleGenerator.util;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by william on 14-Jan-16.
 */
public class ListParser {

    public static Map<String, String> loadList(String stringList, String separator) throws Exception {
        Map<String, String> list = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        InputStream is = new ByteArrayInputStream(stringList.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        try {

            int q = 0;
            while ((line = br.readLine()) != null){

                q++;

                if(!line.contains(separator)){
                    throw new UtilException("Line "+q+" does not contain separator " + separator);
                }

                String[] splitElement = line.split(separator);

                if(splitElement.length != 2){
                    throw new UtilException("Line does not have key->value syntax");
                }

                list.put(splitElement[0], splitElement[1]);
            }
        } catch (IOException e) {
            throw new UtilException("Can not parse String (check your syntax)");
        }

        br.close();
        is.close();

        return list;
    }
}
