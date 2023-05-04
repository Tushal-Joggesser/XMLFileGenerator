/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rest.famu.resources;

import com.xmlgenerator.xmlgenerator.MU.AccountBalance;
import com.xmlgenerator.xmlgenerator.MU.AccountHolder;
import com.xmlgenerator.xmlgenerator.MU.AccountNumber;
import com.xmlgenerator.xmlgenerator.MU.AccountReport;
import com.xmlgenerator.xmlgenerator.MU.Address;
import com.xmlgenerator.xmlgenerator.MU.AddressFix;
import com.xmlgenerator.xmlgenerator.MU.BirthInfo;
import com.xmlgenerator.xmlgenerator.MU.CRS_OECD;
import com.xmlgenerator.xmlgenerator.MU.ControllingPerson;
import com.xmlgenerator.xmlgenerator.MU.CountryInfo;
import com.xmlgenerator.xmlgenerator.MU.CrsBody;
import com.xmlgenerator.xmlgenerator.MU.DocSpec;
import com.xmlgenerator.xmlgenerator.MU.In;
import com.xmlgenerator.xmlgenerator.MU.Individual;
import com.xmlgenerator.xmlgenerator.MU.MessageSpec;
import com.xmlgenerator.xmlgenerator.MU.Name;
import com.xmlgenerator.xmlgenerator.MU.Organisation;
import com.xmlgenerator.xmlgenerator.MU.Payment;
import com.xmlgenerator.xmlgenerator.MU.PaymentAmnt;
import com.xmlgenerator.xmlgenerator.MU.ReportingFI;
import com.xmlgenerator.xmlgenerator.MU.ReportingGroup;
import com.xmlgenerator.xmlgenerator.MU.Tin;
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
import org.json.JSONObject;

/**
 *
 * @author Tushal Joggesser
 */
public class MuReq extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //http://localhost:8080/FAMU-1/MuReq?SendingCompanyIN=20384038&TransmittingCountry=MU&ReceivingCountry=US&MessageType=CRS&MessageTypeIndic=CRS701&ReportingPeriod=2019-12-31&ResCountryCode=MU&INvalue=20384038&INissuedBy=MU&INName=Vuna Capital Trustess (Mauritius) Ltd&AddressCountryCode=MU&AddressPostCode=72201&AddressCity=Ebene&AddressFree=Level 10, NeXTeracom Tower 1&DocSpecDocTypeIndic=OECD1&DocSpecDocRefId=MU2019MUYh3tBGSs-7Q7t-THDn-3ih8-w2aBXjGt0g7E&ReportingGroupAcctNumberType=OECD602&ReportingGroupAcctNumber=140046823512001&AccountHolderResCountryCode=MU&AccountHolderName=Vuna Capital Trustess (Mauritius) Ltd&AccountHolderType=CRS101&ControllingPersonResCountryCode=DK&ControllingPersonIb=ZA&ControllingPersonFirstName=Oomar&ControllingPersonLastName=Akhilesh&ControllingPersonBirthDate=1975-03-16&ControllingPersonCity=Parkview&ControllingPersonCountryCode=ES&ControllingPersonPersonType=CRS804&AccountBalanceCurrCode=USD&AccountBalanceValue=119&PaymentType=CRS504&PaymentCurrCode=USD&PaymentValue=151600
//POST: http://localhost:8080/FAMU-1/MuReq
//{"SendingCompanyIN":"20384038","TransmittingCountry":"MU","ReceivingCountry":"US","MessageType":"CRS","MessageRefId":"MU2019MU20384038.KHgbEIx9-M7WC-hnkt-X7aG-Pe1wwWMn0rko","MessageTypeIndic":"CRS701","ReportingPeriod":"2019-12-31","Timestamp":"2021-08-18T17:20:50","ResCountryCode":"MU","INvalue":"20384038","INissuedBy":"MU","INName":"Vuna Capital Trustess (Mauritius) Ltd","AddressCountryCode":"MU","AddressPostCode":"72201","AddressCity":"Ebene","AddressFree":"Level 10, NeXTeracom Tower 1","DocSpecDocTypeIndic":"OECD1","DocSpecDocRefId":"MU2019MUYh3tBGSs-7Q7t-THDn-3ih8-w2aBXjGt0g7E","ReportingGroupAcctNumberType":"OECD602","ReportingGroupAcctNumber":"140046823512001","AccountHolderResCountryCode":"MU","AccountHolderName":"Vuna Capital Trustess (Mauritius) Ltd","AccountHolderType":"CRS101","ControllingPersonResCountryCode":"DK","ControllingPersonIb":"ZA","ControllingPersonFirstName":"Oomar","ControllingPersonLastName":"Akhilesh","ControllingPersonBirthDate":"1975-03-16","ControllingPersonCity":"Parkview","ControllingPersonCountryCode":"ES","ControllingPersonPersonType":"CRS804","AccountBalanceCurrCode":"USD","AccountBalanceValue":"119","PaymentType":"CRS504","PaymentCurrCode":"USD","PaymentValue":"151600"}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            JSONObject jsonObject = JsonReader.readJson(request);
            System.out.println(jsonObject);
            CRS_OECD cRS_OECD = new CRS_OECD();
            cRS_OECD.setXmlns("urn:oecd:ties:crs:v2");
            cRS_OECD.setXsi("http://www.w3.org/2001/XMLSchema-instance");
            cRS_OECD.setCrs("urn:oecd:ties:crs:v2");
            cRS_OECD.setFtc("urn:oecd:ties:fatca:v1");
            cRS_OECD.setCfc("urn:oecd:ties:commontypesfatcacrs:v2");
            cRS_OECD.setStf("urn:oecd:ties:crsstf:v5");
            cRS_OECD.setIso("urn:oecd:ties:isocrstypes:v1");
            cRS_OECD.setVersion("2");

            MessageSpec messageSpec = new MessageSpec();
            messageSpec.setSendingCompanyIN(jsonObject.getString("SendingCompanyIN"));
            messageSpec.setTransmittingCountry(jsonObject.getString("TransmittingCountry"));
            messageSpec.setReceivingCountry(jsonObject.getString("ReceivingCountry"));
            messageSpec.setMessageType(jsonObject.getString("MessageType"));
            messageSpec.setMessageRefId(jsonObject.getString("MessageRefId"));
            messageSpec.setMessageTypeIndic(jsonObject.getString("MessageTypeIndic"));
            messageSpec.setReportingPeriod(jsonObject.getString("ReportingPeriod"));
            messageSpec.setTimestamp(jsonObject.getString("Timestamp"));
            cRS_OECD.setMessageSpec(messageSpec);

            CrsBody crsBody = new CrsBody();
            ReportingFI reportingFI = new ReportingFI();
            reportingFI.setResCountryCode(jsonObject.getString("ResCountryCode"));
            In In = new In();
            In.setValue(jsonObject.getString("INvalue"));
            In.setIssuedBy(jsonObject.getString("INissuedBy"));
            reportingFI.setIn(In);
            reportingFI.setName(jsonObject.getString("INName"));
            //address
            Address address = new Address();
            AddressFix addressFix = new AddressFix();
            addressFix.setPostCode(jsonObject.getString("AddressPostCode"));
            addressFix.setCity(jsonObject.getString("AddressCity"));
            address.setCountryCode(jsonObject.getString("AddressCountryCode"));
            address.setAddressFix(addressFix);
            address.setAddressFree(jsonObject.getString("AddressFree"));
            reportingFI.setAddress(address);
            //DocSpec
            DocSpec docSpec = new DocSpec();
            docSpec.setDocTypeIndic(jsonObject.getString("DocSpecDocTypeIndic"));
            docSpec.setDocRefId(jsonObject.getString("DocSpecDocRefId"));
            reportingFI.setDocSpec(docSpec);
            crsBody.setReportingFI(reportingFI);
            cRS_OECD.setCrsBody(crsBody);

            //Reporting Group to CRS Body
            ReportingGroup reportingGroup = new ReportingGroup();
            AccountReport accountReport = new AccountReport();
            accountReport.setDocSpec(docSpec);
            AccountNumber accountNumber = new AccountNumber();
            accountNumber.setAcctNumberType(jsonObject.getString("ReportingGroupAcctNumberType"));
            accountNumber.setUndocumentedAccount("false");
            accountNumber.setDormantAccount("false");
            accountNumber.setClosedAccount("false");
            accountNumber.setValue(jsonObject.getString("ReportingGroupAcctNumber"));
            accountReport.setAccountNumber(accountNumber);
            //account holder
            AccountHolder accountHolder = new AccountHolder();
            Organisation organisation = new Organisation();
            organisation.setResCountryCode("MU");
            organisation.setIn(In);
            organisation.setName(jsonObject.getString("AccountHolderName"));
            organisation.setAddress(address);
            accountHolder.setAcctHolderType(jsonObject.getString("AccountHolderType"));
            accountHolder.setOrganisation(organisation);
            accountReport.setAccountHolder(accountHolder);

            // ControllingPerson
            ControllingPerson controllingPerson = new ControllingPerson();
            Individual individual = new Individual();
            individual.setResCountryCode(jsonObject.getString("ControllingPersonResCountryCode"));
            Tin tin = new Tin();
            tin.setIssuedBy(jsonObject.getString("ControllingPersonIb"));
            tin.setValue("0");
            individual.setTin(tin);
            Name name = new Name();
            name.setFirstName(jsonObject.getString("ControllingPersonFirstName"));
            name.setLastName(jsonObject.getString("ControllingPersonLastName"));
            individual.setName(name);
            individual.setAddress(address);
            BirthInfo birthInfo = new BirthInfo();
            birthInfo.setBirthDate(jsonObject.getString("ControllingPersonBirthDate"));
            birthInfo.setCity(jsonObject.getString("ControllingPersonCity"));
            CountryInfo countryInfo = new CountryInfo();
            countryInfo.setCountryCode(jsonObject.getString("ControllingPersonCountryCode"));
            birthInfo.setCountryInfo(countryInfo);
            controllingPerson.setIndividual(individual);
            controllingPerson.setCtrlgPersonType(jsonObject.getString("ControllingPersonPersonType"));
            //account Balance
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setCurrCode(jsonObject.getString("AccountBalanceCurrCode"));
            accountBalance.setValue(jsonObject.getString("AccountBalanceValue"));
            accountReport.setAccountBalance(accountBalance);
            //Payment
            Payment payment = new Payment();
            payment.setType(jsonObject.getString("PaymentType"));
            PaymentAmnt paymentAmnt = new PaymentAmnt();
            paymentAmnt.setCurrCode(jsonObject.getString("PaymentCurrCode"));
            paymentAmnt.setValue(jsonObject.getString("PaymentValue"));
            payment.setPaymentAmnt(paymentAmnt);

            accountReport.setControllingPerson(controllingPerson);
            accountReport.setPayment(payment);
            reportingGroup.setAccountReport(accountReport);
            crsBody.setReportingGroup(reportingGroup);

            JAXBContext jaxbContext = JAXBContext.newInstance(CRS_OECD.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(JAXB_ENCODING, StandardCharsets.UTF_8.toString());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cRS_OECD, writer);
            String xmlValue = writer.toString();
            System.out.println("Request Marshall");
            String finalXml = xmlValue.replaceAll("<value>", "").replaceAll("</value>", "");
            System.out.println(finalXml);
            // write dom document to a file
            try (FileOutputStream output
                    = new FileOutputStream("C:\\CRS_MUv1.3.xml")) {
                output.write(finalXml.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException ex) {
            Logger.getLogger(MuReq.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
