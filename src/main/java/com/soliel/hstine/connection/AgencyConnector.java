package com.soliel.hstine.connection;

import com.google.common.io.ByteStreams;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

/**
 * Created by HStine on 3/29/2018.
 */
public class AgencyConnector
{

    private Properties configProperties;
    private String endpoint;
    InputStream certInput;
    public File certFile;
    public String certPath;


    public AgencyConnector()
    {
        loadConfigurations();

        /**
        try
        {
            String tempDir = System.getProperty("java.io.tmpdir");

            certInput = getClass().getClassLoader().getResourceAsStream("controlPanelUser.p12");
            //certInput = this.getClass().getClassLoader().getResourceAsStream("resources/controlPanelUser.p12");
            //File f = new File("resources");
            File f = new File("src/main/resources");
            File f2 = new File(".");
            System.out.println("Here: " + f2.list());
            System.out.println("path: " + f.getAbsolutePath() + " : " + f.list());
            certFile = new File(tempDir + "/controlPanel.p12");
            certFile.deleteOnExit();
            certPath = certFile.getPath();
            if (certInput==null)
            {
                System.out.println("certInput null");
            }
            if (certFile==null)
            {
                System.out.println("certFile null");
            }
            Files.copy(certInput, certFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            if( certInput != null )
            {
                try { certInput.close(); }
                catch( IOException e ) { e.printStackTrace(); }
            }
        }**/
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("172.16.2.172"));

        File trust = new File("src/main/resources/truststore.jks");
        File cert = new File("src/main/resources/controlPanelUser.p12");
        //File cert = new File("src/main/resources/client8.p12");
        //File cert = new File("src/main/resources/controlConfig.properties");
        //File cert = new File("src/main/resources/controlConfig.propers");

        System.setProperty("javax.net.ssl.trustStore", trust.getAbsolutePath() );
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
        System.setProperty("javax.net.ssl.keyStore", cert.getAbsolutePath() );
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

        endpoint = configProperties.get( "NBIS_ENDPOINT" ) + "" + configProperties.get( "PORT" ) + "/" + configProperties.get( "AGENCY_PATH" );
    }

    public String getApplicants()
    {
        try
        {
            InputStream input = null;
            HttpURLConnection conn = null;
            String urlstring = endpoint + "/eapp/applicants";
            URL url = new URL(urlstring);
            if (endpoint.contains("https"))
            {
                conn = (HttpsURLConnection) url.openConnection();
            }
            else
            {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod("GET");
            conn.setRequestProperty( "username", "controlPanelUser" );
            conn.setDoOutput( false );
            conn.setUseCaches(false);

            input = conn.getInputStream();
            byte[] responseBytes = ByteStreams.toByteArray(input);
            String response = new String( responseBytes );

            return response;
        }
        catch (MalformedURLException mue)
        {
            mue.printStackTrace();
            return null;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
    }

    public String submitApplicant(String applicantId, String filePath)
    {

        try
        {
            InputStream input = null;
            HttpURLConnection conn = null;

            String boundary = "===" + System.currentTimeMillis() + "===";

            String urlstring = endpoint + "/eapp/submitPackage?applicantId=" + applicantId;
            URL url = new URL(urlstring);

            if (endpoint.contains("https"))
            {
                conn = (HttpsURLConnection) url.openConnection();
            }
            else
            {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "username", "controlPanelUser" );
            conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setDoOutput( true );
            conn.setUseCaches(false);

            /**
            OutputStream outStream = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

            writer.append("--").append(boundary).append("\r\n");

            writer.append("Content-Disposition: form-data; name=\"").append("file").append("\"; filename=\"")
                  .append(filePath).append('"').append("\r\n");

            writer.append("Content-Type: ").append("application/pdf").append("\r\n");

            writer.append("Content-Transfer-Encoding: binary").append("\r\n");

            writer.append("\r\n");**/

            input = conn.getInputStream();
            byte[] responseBytes = ByteStreams.toByteArray(input);
            String response = new String( responseBytes );

            return response;
        }
        catch (MalformedURLException mue)
        {
            mue.printStackTrace();
            return null;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
    }


    private void loadConfigurations()
    {
        InputStream propertiesinput = getClass().getClassLoader().getResourceAsStream("ControlConfig.properties");
        configProperties = new Properties();
        try
        {
            configProperties.load(propertiesinput);
        }
        catch (IOException ioe)
        {
            System.out.println("Cannot access config properties file.");
            ioe.printStackTrace();
        }
    }
}
