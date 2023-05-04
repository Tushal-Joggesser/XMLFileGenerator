/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rest.famu.resources;

import com.xmlgenerator.xmlgenerator.FATCA.FATCA;
import com.xmlgenerator.xmlgenerator.FATCA.FATCA_OECD;
import com.xmlgenerator.xmlgenerator.FATCA.FaAddress;
import com.xmlgenerator.xmlgenerator.FATCA.FaAddressFix;
import com.xmlgenerator.xmlgenerator.FATCA.FaDocSpec;
import com.xmlgenerator.xmlgenerator.FATCA.FaMessageSpecs;
import com.xmlgenerator.xmlgenerator.FATCA.FaNillReport;
import com.xmlgenerator.xmlgenerator.FATCA.FaReportingFI;
import com.xmlgenerator.xmlgenerator.FATCA.FaReportingGroup;
import com.xmlgenerator.xmlgenerator.FATCA.FaSponsor;
import com.xmlgenerator.xmlgenerator.FATCA.FaTin;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static javax.xml.bind.Marshaller.JAXB_ENCODING;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Tushal Joggesser
 */
public class FaReq extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //    http://localhost:8080/FAMU-1/FaReq?SendingCompanyIN=20384038&TransmittingCountry=MU&ReceivingCountry=US&MessageType=FATCA&MessageRefId=fS6KKHX7-FLiQ-WzfO-W2se-LtLo4m8cy3Cb&CorrMessageRefId=&ReportingPeriod=2020-12-31&Timestamp=2021-08-17T17:17:40&ResCountryCode=MU&IssuedBy=US&Value=AFWW1B.00000.SP.480&ReportingFIName=The Sumatra Trust&CountryCode=MU&PostCode=72201&City=Ebene&AddressFree=Level 10, NeXTeracom Tower 1&DocTypeIndic=FATCA1&DocRefId=20384038.aToxJyl1-tcQP-y5ZE-aoCF-95GgoNnICOcU&ResCountryCode=MU&FaSponsorName=Vuna Capital Trustees (Mauritius) Ltd&FilerCategory=FATCA609&NoAccountToReport=yes
    // json:
    //POST: http://localhost:8080/FAMU-1/FaReq
//    {"SendingCompanyIN":"20384038","TransmittingCountry":"MU","ReceivingCountry":"US","MessageType":"FATCA","MessageRefId":"fS6KKHX7-FLiQ-WzfO-W2se-LtLo4m8cy3Cb","CorrMessageRefId":"","ReportingPeriod":"2020-12-31","Timestamp":"2021-08-17T17:17:40","ResCountryCode":"MU","IssuedBy":"US","Value":"AFWW1B.00000.SP.480","ReportingFIName":"The Sumatra Trust","CountryCode":"MU","PostCode":"72201","City":"Ebene","AddressFree":"Level 10, NeXTeracom Tower 1","DocTypeIndic":"FATCA1","DocRefId":"20384038.aToxJyl1-tcQP-y5ZE-aoCF-95GgoNnICOcU","SponsorResCountryCode":"MU","FaSponsorName":"Vuna Capital Trustees (Mauritius) Ltd","FilerCategory":"FATCA609","NoAccountToReport":"yes"}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        System.out.println(jsonObject.getString("SendingCompanyIN"));
        try {
            JSONObject jsonObject = JsonReader.readJson(request);
            System.out.println(jsonObject);
            FATCA_OECD FATCA_OECD = new FATCA_OECD();
            FaMessageSpecs FaMessageSpecs = new FaMessageSpecs();
            // set messaging
            System.out.println(jsonObject.getString("SendingCompanyIN"));
            FaMessageSpecs.setSendingCompanyIN(jsonObject.getString("SendingCompanyIN"));
            FaMessageSpecs.setTransmittingCountry(jsonObject.getString("TransmittingCountry"));
            FaMessageSpecs.setReceivingCountry(jsonObject.getString("ReceivingCountry"));
            FaMessageSpecs.setMessageType(jsonObject.getString("MessageType"));
            FaMessageSpecs.setMessageRefId(jsonObject.getString("MessageRefId"));
            FaMessageSpecs.setCorrMessageRefId(jsonObject.getString("CorrMessageRefId"));
            FaMessageSpecs.setReportingPeriod(jsonObject.getString("ReportingPeriod"));
            FaMessageSpecs.setTimestamp(jsonObject.getString("Timestamp"));
            FATCA_OECD.setFaMessageSpecs(FaMessageSpecs);
            //set FATCA and Reporting
            FATCA FATCA = new FATCA();
            FaReportingFI FaReportingFI = new FaReportingFI();
            FaReportingFI.setResCountryCode(jsonObject.getString("ResCountryCode"));
            FaTin FaTin = new FaTin();
            FaTin.setIssuedBy(jsonObject.getString("IssuedBy"));
            FaTin.setValue(jsonObject.getString("Value"));
            FaReportingFI.setFaTin(FaTin);
            FaReportingFI.setName(jsonObject.getString("ReportingFIName"));
            FaAddress FaAddress = new FaAddress();
            FaAddress.setCountryCode(jsonObject.getString("CountryCode"));
            FaAddressFix FaAddressFix = new FaAddressFix();
            FaAddressFix.setPostCode(jsonObject.getString("PostCode"));
            FaAddressFix.setCity(jsonObject.getString("City"));
            FaAddress.setFaAddressFix(FaAddressFix);
            FaAddress.setAddressFree(jsonObject.getString("AddressFree"));
            FaReportingFI.setFaAddress(FaAddress);
            FaDocSpec FaDocSpec = new FaDocSpec();
            FaDocSpec.setDocTypeIndic(jsonObject.getString("DocTypeIndic"));
            FaDocSpec.setDocRefId(jsonObject.getString("DocRefId"));
            FaReportingFI.setFaDocSpec(FaDocSpec);

            FaReportingGroup FaReportingGroup = new FaReportingGroup();
            FaSponsor FaSponsor = new FaSponsor();
            FaSponsor.setResCountryCode(jsonObject.getString("SponsorResCountryCode"));
            FaSponsor.setFaTin(FaTin);
            FaSponsor.setName(jsonObject.getString("FaSponsorName"));
            FaSponsor.setFaAddress(FaAddress);
            FaSponsor.setFilerCategory(jsonObject.getString("FilerCategory"));
            FaSponsor.setFaDocSpec(FaDocSpec);
            FaReportingGroup.setFaSponsor(FaSponsor);

            FaNillReport FaNillReport = new FaNillReport();
            FaNillReport.setFaDocSpec(FaDocSpec);
            FaNillReport.setNoAccountToReport(jsonObject.getString("NoAccountToReport"));
            FaReportingGroup.setFaNillReport(FaNillReport);

            FATCA.setFaReportingFI(FaReportingFI);
            FATCA.setFaReportingGroup(FaReportingGroup);
            FATCA_OECD.setFatca(FATCA);
            FATCA_OECD.setVersion("2.0");
            FATCA_OECD.setSchemaLocation("urn:oecd:ties:fatca:v2 FatcaXML_v2.0.xsd");
            FATCA_OECD.setXsi("http://www.w3.org/2001/XMLSchema-instance");
            FATCA_OECD.setFtc("urn:oecd:ties:fatca:v2");
            FATCA_OECD.setSfa("urn:oecd:ties:stffatcatypes:v2");

            JAXBContext jaxbContext = JAXBContext.newInstance(FATCA_OECD.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(JAXB_ENCODING, StandardCharsets.UTF_8.toString());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(FATCA_OECD, writer);
            String xmlValue = writer.toString();
            System.out.println("Request Marshall");
            String finalXml = xmlValue.replaceAll("<value>", "").replaceAll("</value>", "");
            System.out.println(finalXml);
            // write dom document to a file
            try (FileOutputStream output
                    = new FileOutputStream("C:\\FATCAv1.2.xml")) {
                output.write(finalXml.getBytes("UTF-8"));
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JAXBException | JSONException ex) {
            Logger.getLogger(FaReq.class.getName()).log(Level.SEVERE, null, ex);
//            return Response
//                    .serverError().build();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
//    @POST
//    @Path("/post")
//    @Consumes(MediaType.APPLICATION_JSON)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
