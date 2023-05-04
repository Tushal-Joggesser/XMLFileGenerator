/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.famu.resources;

import java.io.IOException;
import java.util.Calendar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Tushal Joggesser
 */
public class POSTMAINFA {

    private static String FAURL = "http://localhost:8080/FAMU-1/FaReq";
    private static String jsonFA = "{\"SendingCompanyIN\":\"20384038\",\"TransmittingCountry\":\"MU\",\"ReceivingCountry\":\"US\",\"MessageType\":\"FATCA\",\"MessageRefId\":\"fS6KKHX7-FLiQ-WzfO-W2se-LtLo4m8cy3Cb\",\"CorrMessageRefId\":\"\",\"ReportingPeriod\":\"2020-12-31\",\"Timestamp\":\"2021-08-17T17:17:40\",\"ResCountryCode\":\"MU\",\"IssuedBy\":\"US\",\"Value\":\"AFWW1B.00000.SP.480\",\"ReportingFIName\":\"The Sumatra Trust\",\"CountryCode\":\"MU\",\"PostCode\":\"72201\",\"City\":\"Ebene\",\"AddressFree\":\"Level 10, NeXTeracom Tower 1\",\"DocTypeIndic\":\"FATCA1\",\"DocRefId\":\"20384038.aToxJyl1-tcQP-y5ZE-aoCF-95GgoNnICOcU\",\"SponsorResCountryCode\":\"MU\",\"FaSponsorName\":\"Vuna Capital Trustees (Mauritius) Ltd\",\"FilerCategory\":\"FATCA609\",\"NoAccountToReport\":\"yes\"}";

    public static void main(String args[]) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity entity = new StringEntity(FAURL, "UTF-8");
        HttpPost postRequest = new HttpPost(jsonFA);
//            LOGGER.info("accept: " + accept);
//            LOGGER.info("content-type: " + contentType);
//            postRequest.addHeader("accept", accept);
//            postRequest.addHeader("content-type", contentType);
        postRequest.setEntity(entity);
        long startTime = Calendar.getInstance().getTimeInMillis();
        HttpResponse response = httpClient.execute(postRequest);
//            String responseToString = EntityUtils.toString(response.getEntity());
        HttpEntity resentity = response.getEntity();
    }
}
