package br.com.alura.rh.testeHMAC;

import java.time.Instant;
import java.util.Objects;

public class hmacTest {
    public static void main(String[] args) {
        String appKey = "tnAliIZNOWbpLQlRDyBLhnbtU2OLnPbA"; //link do site firstdata
        String secret = "xiFhFw3jiEuAd5SDAPQGAYKHcAU9QoJG6wcdGG2LfDf"; //link do site firstdata
        long dateEpochFormat = Instant.now().toEpochMilli();
        String clientRequestId = "xheunm345mXheoXhpqoeiurufm276"; //como gerar?
        Object postObj = new Object();
//        String json = objectMapper.writeValueAsString(postObj); //injetar o ObjectMapper do SpringRest
//        String messageSignature = appKey + clientRequestId + dateEpochFormat + json;
//        String encryptedMessageSignature = encrypHMAC(messageSignature, secret);

    }

    private static String encrypHMAC(String messageSignature, String secret) {
        return "";
    }
}
