/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReadFromSheet;

import com.xmlgenerator.xmlgenerator.FATCA.FATCA;
import com.xmlgenerator.xmlgenerator.FATCA.FATCA_OECD;
import com.xmlgenerator.xmlgenerator.FATCA.FaAccountReport;
import com.xmlgenerator.xmlgenerator.FATCA.FaAddress;
import com.xmlgenerator.xmlgenerator.FATCA.FaAddressFix;
import com.xmlgenerator.xmlgenerator.FATCA.FaMessageSpecs;
import com.xmlgenerator.xmlgenerator.FATCA.FaReportingFI;
import com.xmlgenerator.xmlgenerator.FATCA.FaReportingGroup;
import com.xmlgenerator.xmlgenerator.FATCA.FaSponsor;
import com.xmlgenerator.xmlgenerator.FATCA.FaTin;
import com.xmlgenerator.xmlgenerator.FATCA.FtcAccountHolder;
import com.xmlgenerator.xmlgenerator.FATCA.FtcBirthInfo;
import com.xmlgenerator.xmlgenerator.FATCA.FtcIndividual;
import com.xmlgenerator.xmlgenerator.FATCA.FtcName;
import com.xmlgenerator.xmlgenerator.MU.AccountBalance;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static javax.xml.bind.Marshaller.JAXB_ENCODING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author tusha
 */
public class ReadFromSheetTest {

    public static void main(String args[]) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File("C:\\Devtools\\FAMU\\FATCA_CRS_TEMPLATES\\fatca\\bulk_accountreports_sponsoring_template.xlsm"));
            System.out.println("found file");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            System.out.println("in workbook");
            XSSFSheet reortingFI = workbook.getSheet("ReportingFI");

            System.out.println("REPORTING FI SHEET");
            FaMessageSpecs faMessageSpecs = new FaMessageSpecs();
            FaReportingFI faReportingFI = new FaReportingFI();
            FaReportingGroup faReportingGroup = new FaReportingGroup();
            FaAddress faAddress = new FaAddress();
            FaAddressFix faAddressFix = new FaAddressFix();
            FaSponsor faSponsor = new FaSponsor();

            faMessageSpecs.setMessageType("FATCA");
            faMessageSpecs.setMessageRefId(UUID.randomUUID().toString());

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            faMessageSpecs.setReportingPeriod(simpleDateFormat.format(new Date()));
            for (int index = reortingFI.getFirstRowNum() + 1; index <= reortingFI.getLastRowNum(); index++) {
                Row row = reortingFI.getRow(index);
                for (int i = 1; i <= 8; i++) {
                    switch (i) {
                        case 1:
                            System.out.println("GIIN: " + row.getCell(i));
                            faMessageSpecs.setSendingCompanyIN(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 2:
                            System.out.println("TransmittingCountry: " + row.getCell(i));
                            faMessageSpecs.setTransmittingCountry(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 3:
                            System.out.println("ReceivingCountry: " + row.getCell(i));
                            faMessageSpecs.setReceivingCountry(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 4:
                            System.out.println("fi_res_country: " + row.getCell(i));
                            faReportingFI.setResCountryCode(row.getCell(i) != null ? returnValue(row, i) : "");
                            faAddress.setCountryCode(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 5:
                            System.out.println("fi_name: " + row.getCell(i));
                            faReportingFI.setName(row.getCell(i) != null ? returnValue(row, i) : "");

                            break;
                        case 6:
                            System.out.println("fi_country: " + row.getCell(i));
                            faAddress.setCountryCode(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 7:
                            System.out.println("fi_city: " + row.getCell(i));
                            faAddressFix.setCity(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 8:
                            System.out.println("fi_address_free: " + row.getCell(i));
                            faAddress.setAddressFree(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                    }

                }

            }
            System.out.println("___________________________________________");

            XSSFSheet sponsor = workbook.getSheet("Sponsor");
            if (sponsor != null) {
                FaTin faTinSponsor = new FaTin();
                FaAddress faSponsorAddress = new FaAddress();
                FaAddressFix faSponsorAddressFix = new FaAddressFix();
                System.out.println("SPONSOR SHEET");
                for (int index = sponsor.getFirstRowNum() + 1; index <= sponsor.getLastRowNum(); index++) {
                    Row row = sponsor.getRow(index);
                    for (int i = 1; i <= 8; i++) {
                        switch (i) {
                            case 1:
                                System.out.println("spon_name: " + row.getCell(i));
                                faSponsor.setName(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 2:
                                System.out.println("spon_tin: " + row.getCell(i));
                                faTinSponsor.setValue(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 3:
                                System.out.println("spon_tin_issued_by: " + row.getCell(i));
                                faTinSponsor.setIssuedBy(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 4:
                                System.out.println("spon_filer_category: " + row.getCell(i));
                                faSponsor.setFilerCategory(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 5:
                                System.out.println("spon_country: " + row.getCell(i));

                                faSponsorAddress.setCountryCode(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 6:
                                System.out.println("spon_city: " + row.getCell(i));
                                faSponsorAddressFix.setCity(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;
                            case 7:
                                System.out.println("spon_addressfree: " + row.getCell(i));
                                faSponsorAddress.setAddressFree(row.getCell(i) != null ? returnValue(row, i) : "");
                                break;

                        }

                    }

                }
            }
            System.out.println("___________________________________________");
            List<FaAccountReport> faAccountReportList = new ArrayList<>();
            XSSFSheet accountReportIndividual = workbook.getSheet("account_report_individual");
            System.out.println("account_report_individual SHEET");
            for (int index = accountReportIndividual.getFirstRowNum() + 1; index <= accountReportIndividual.getLastRowNum(); index++) {
                FaAccountReport faAccountReport = new FaAccountReport();
                AccountBalance accountBalance = new AccountBalance();
                FtcIndividual ftcIndividual = new FtcIndividual();
                FaTin accountIndividual = new FaTin();
                FtcName ftcName = new FtcName();
                FaAddress faAccountReportAddress = new FaAddress();
                FaAddressFix faAccountReportAddressFix = new FaAddressFix();
                FtcBirthInfo ftcAccountReportBirthInfo = new FtcBirthInfo();
                FtcAccountHolder ftcAccountHolder = new FtcAccountHolder();
                Row row = accountReportIndividual.getRow(index);
                for (int i = 1; i <= 18; i++) {
                    switch (i) {
                        case 1:
                            System.out.println("ind_ref: " + row.getCell(i));

                            break;
                        case 2:
                            System.out.println("Account Number: " + row.getCell(i));
                            faAccountReport.setAccountNumber(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 3:
                            System.out.println("Account Balance: " + row.getCell(i));
                            accountBalance.setValue(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 4:
                            accountBalance.setCurrCode(row.getCell(i) != null ? returnValue(row, i) : "");
                            System.out.println("Account Balance Currencyy: " + row.getCell(i));
                            break;
                        case 5:
                            System.out.println("First Name: " + row.getCell(i));
                            ftcName.setFirstName(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 6:
                            System.out.println("Last Name: " + row.getCell(i));
                            ftcName.setLastName(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 7:
                            System.out.println("Date of Birth (YYYY-MM-DD): " + row.getCell(i));
                            ftcAccountReportBirthInfo.setBirthDate(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 8:
                            System.out.println("TIN Issued By: " + row.getCell(i));
                            accountIndividual.setIssuedBy(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 9:
                            System.out.println("US TIN: " + row.getCell(i));
                            accountIndividual.setValue(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 10:
                            System.out.println("Nationality: " + row.getCell(i));
                            ftcIndividual.setNationality(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 11:
                            System.out.println("Country: " + row.getCell(i));
                            ftcIndividual.setFtcIndividual(row.getCell(i) != null ? returnValue(row, i) : "");
                            faAccountReportAddress.setCountryCode(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 12:
                            System.out.println("City: " + row.getCell(i));
                            faAccountReportAddressFix.setCity(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 13:
                            System.out.println("AddressFree: " + row.getCell(i));
                            faAccountReportAddress.setAddressFree(row.getCell(i) != null ? returnValue(row, i) : "");
                            break;
                        case 14:
                            System.out.println("FATCA501 - Dividends: " + row.getCell(i));
                            break;
                        case 15:
                            System.out.println("FATCA502 - Interest: " + row.getCell(i));
                            break;
                        case 16:
                            System.out.println("FATCA503 - Gross Proceeds/Redemptions: " + row.getCell(i));
                            break;
                        case 17:
                            System.out.println("FATCA504 - Other: " + row.getCell(i));
                            break;
                        case 18:
                            System.out.println("Payment Currency: " + row.getCell(i));
                            break;
                    }
                }
                System.out.println("___________________________________________- next account row");
                faAccountReportAddress.setFaAddressFix(faAccountReportAddressFix);
                ftcIndividual.setFtcName(ftcName);
                ftcIndividual.setFaAddress(faAccountReportAddress);
                ftcIndividual.setFtcBirthInfo(ftcAccountReportBirthInfo);
                ftcAccountHolder.setFtcIndividual(ftcIndividual);
                faAccountReport.setFtcAccountHolder(ftcAccountHolder);

                faAccountReportList.add(faAccountReport);
            }
            faReportingGroup.setFaAccountReport(faAccountReportList);

            FATCA fatca = new FATCA();
            fatca.setFaReportingFI(faReportingFI);
            fatca.setFaReportingGroup(faReportingGroup);
            FATCA_OECD finalObject = new FATCA_OECD();
            finalObject.setFaMessageSpecs(faMessageSpecs);
            finalObject.setFatca(fatca);
            finalObject.setVersion("2.0");
            finalObject.setSchemaLocation("urn:oecd:ties:fatca:v2 FatcaXML_v2.0.xsd");
            finalObject.setXsi("http://www.w3.org/2001/XMLSchema-instance");
            finalObject.setFtc("urn:oecd:ties:fatca:v2");
            finalObject.setSfa("urn:oecd:ties:stffatcatypes:v2");

            System.out.println("___________________________________________");

            XSSFSheet accountReportOrganisation = workbook.getSheet("account_report_organisation");
            System.out.println("got sheet accountReportOrganisation");
            XSSFSheet substantialOwnerInd = workbook.getSheet("substantial_owner_ind");
            System.out.println("got sheet substantialOwnerInd");
            XSSFSheet substantialOwnerOrg = workbook.getSheet("substantial_owner_org");
            System.out.println("got sheet substantialOwnerOrg");

            JAXBContext jaxbContext = JAXBContext.newInstance(FATCA_OECD.class);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(JAXB_ENCODING, StandardCharsets.UTF_8.toString());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(finalObject, writer);
            String xmlValue = writer.toString();
            System.out.println("Request Marshall");
            String finalXml = xmlValue.replaceAll("<value>", "").replaceAll("</value>", "");
            System.out.println(finalXml);
            // write dom document to a file
            try ( FileOutputStream output
                    = new FileOutputStream("C:\\Devtools\\xmlGenerator\\FATCA_fromXcelv1.1.xml")) {
                output.write(finalXml.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFromSheetTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFromSheetTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(ReadFromSheetTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadFromSheetTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static String returnValue(Row row, int i) {
        try {
            return row.getCell(i).getStringCellValue();
        } catch (Exception ex) {
            return String.valueOf(row.getCell(i).getNumericCellValue());
        }

    }
}
