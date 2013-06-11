/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.itextpdf.text.BaseColor;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

/**
 *
 * @author thijs
 */
public class EmailPdf {

    // email configuration
    private static String smtpHost = "smtp.mail.yahoo.com";
    private static int smtpPort = 587;
    private static String sender = "projectdryves@yahoo.nl";
    private static String pass = "carp00l!";
    
    
    // variables of http session, managed by getters/setters
    private String recipient;
    private int rideId;
    private String rideDate; // moet misschien veranderd worden naar Date?
    private double ridePrice;
    
    
    // fonts used in pdf
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public void email() {
        
        // the email body
        String content = "Beste heer/mevrouw,\n\n"
                + "Hierbij uw factuur in pdf formaat.\n"
                + "Groeten,\n\n"
                + "Dryves";
        // the email subject
        String subject = "Dryves factuur";

        // put email properties into session
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, null);

        ByteArrayOutputStream outputStream = null;

        try {
            // construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);

            // write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream);
            byte[] bytes = outputStream.toByteArray();

            // construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("factuur.pdf");

            // construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            // create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(getRecipient());

            // construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            // connect to smtp server
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, sender, pass);
            
            // send email
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            
            // close connection
            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // clean up
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     * Writes the content of a PDF file (using iText API) to the
     * {@link OutputStream}.
     *
     * @param outputStream {@link OutputStream}.
     * @throws Exception
     */
    public void writePdf(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        // pdf metadata
        document.addTitle("Dryves Factuur");
        document.addSubject("Dryves Facturatie");
        document.addKeywords("iText, email");
        document.addAuthor("Dryves");
        document.addCreator("Dryves");

        // pdf body
        Paragraph paragraph1 = new Paragraph();
        paragraph1.add(new Paragraph("Dryves factuur", catFont));
        paragraph1.add(new Paragraph(" "));
        paragraph1.add(new Paragraph("Factuur aangemaakt op " + new Date()));
        document.add(paragraph1);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(new Chunk("Rit id: " + getRitId() + "\n"
                + "Prijs: €" + getPrijs() + "\n\n"
                + "De vermelde prijs a.u.b. overmaken op rekeningnummer 11.22.33.44"));
        document.add(paragraph2);

        document.close();
    }

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the ritId
     */
    public int getRitId() {
        return rideId;
    }

    /**
     * @param ritId the ritId to set
     */
    public void setRitId(int ritId) {
        this.rideId = ritId;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return rideDate;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.rideDate = datum;
    }

    /**
     * @return the prijs
     */
    public double getPrijs() {
        return ridePrice;
    }

    /**
     * @param prijs the prijs to set
     */
    public void setPrijs(double prijs) {
        this.ridePrice = prijs;
    }
}
