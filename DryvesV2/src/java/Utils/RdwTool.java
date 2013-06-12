/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Patrick
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;

public class RdwTool {

    /**
     *
     * @param numberplate a dutch license plate.
     * @return
     * @throws IOException
     */
    public static String getCarJSON(String licensePlate) throws IOException {
        
        //clean the license plate string if non alphanumeric characters.
        licensePlate = cleanLicensePlate(licensePlate);
        System.out.println(licensePlate);
        
        //string for connecting to the RDW API.
        //returned fields are determined in the $select clause. See for additional fields the respective documentation at the azure datamarket.
        //we're filtering on license plate in $filter. the license plate is provided by the user.
        String dmUrl = "https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/"
                + "KENT_VRTG_O_DAT?$format=json&$select=Aantalzitplaatsen,Handelsbenaming,Merk&$filter=Kenteken%20eq%20%27" + licensePlate + "%27";

        //setting up the correct format for the accountkey
        String accountKey = "vRedoLtl8GWiKh3lKokeXLitDylXpWkrAlKIkAZPc/k";
        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);

        //connect to the URL with correct properties
        URL url = new URL(dmUrl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

        //write the JSON reply to a string and return it
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        String jsonText = sb.toString();
        return jsonText;
    }

    private static String cleanLicensePlate(String licensePlate) {
        return licensePlate.replaceAll("\\W+", "");
    }
}
