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
public class POSTMAINCRS {

    private static String CRSURL = "http://localhost:8080/FAMU-1/MuReq";
    private static String jsonCRS = "{\"SendingCompanyIN\":\"20384038\",\"TransmittingCountry\":\"MU\",\"ReceivingCountry\":\"US\",\"MessageType\":\"CRS\",\"MessageRefId\":\"MU2019MU20384038.KHgbEIx9-M7WC-hnkt-X7aG-Pe1wwWMn0rko\",\"MessageTypeIndic\":\"CRS701\",\"ReportingPeriod\":\"2019-12-31\",\"Timestamp\":\"2021-08-18T17:20:50\",\"ResCountryCode\":\"MU\",\"INvalue\":\"20384038\",\"INissuedBy\":\"MU\",\"INName\":\"Vuna Capital Trustess (Mauritius) Ltd\",\"AddressCountryCode\":\"MU\",\"AddressPostCode\":\"72201\",\"AddressCity\":\"Ebene\",\"AddressFree\":\"Level 10, NeXTeracom Tower 1\",\"DocSpecDocTypeIndic\":\"OECD1\",\"DocSpecDocRefId\":\"MU2019MUYh3tBGSs-7Q7t-THDn-3ih8-w2aBXjGt0g7E\",\"ReportingGroupAcctNumberType\":\"OECD602\",\"ReportingGroupAcctNumber\":\"140046823512001\",\"AccountHolderResCountryCode\":\"MU\",\"AccountHolderName\":\"Vuna Capital Trustess (Mauritius) Ltd\",\"AccountHolderType\":\"CRS101\",\"ControllingPersonResCountryCode\":\"DK\",\"ControllingPersonIb\":\"ZA\",\"ControllingPersonFirstName\":\"Oomar\",\"ControllingPersonLastName\":\"Akhilesh\",\"ControllingPersonBirthDate\":\"1975-03-16\",\"ControllingPersonCity\":\"Parkview\",\"ControllingPersonCountryCode\":\"ES\",\"ControllingPersonPersonType\":\"CRS804\",\"AccountBalanceCurrCode\":\"USD\",\"AccountBalanceValue\":\"119\",\"PaymentType\":\"CRS504\",\"PaymentCurrCode\":\"USD\",\"PaymentValue\":\"151600\"}";

    public static void main(String args[]) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity entity = new StringEntity(jsonCRS, "UTF-8");
        HttpPost postRequest = new HttpPost(CRSURL);
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
