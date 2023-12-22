package me.isnullxbh.judson.couriers.cainiao;

import com.google.gson.JsonParser;
import me.isnullxbh.judson.TrackingInfo;
import me.isnullxbh.judson.TrackingServiceClient;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Optional;

/**
 * A package tracking client for the Cainiao courier.
 * @since 0.1.0
 */
public class CainiaoTrackingServiceClient implements TrackingServiceClient
{
    private final CloseableHttpClient client;

    /**
     * Creates an instance of client with the default parameters.
     */
    CainiaoTrackingServiceClient() {
        client = HttpClientBuilder.create().build();
    }

    @Override
    public Optional<TrackingInfo> track(String trackingNumber)
    {
        try
        {
            final var request =
                RequestBuilder.create("GET")
                    .setUri("https://global.cainiao.com/global/detail.json")
                    .addParameter("mailNos", trackingNumber)
                    .addHeader("User-Agent", "Judson/0.1.0")
                    .build();

            final var response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            {
                return Optional.empty();
            }

            final var content = EntityUtils.toString(response.getEntity());

            var json =
                JsonParser.parseString(content)
                    .getAsJsonObject()
                    .getAsJsonArray("module").get(0)
                    .getAsJsonObject();

            var traceInfo = json.getAsJsonObject("latestTrace");

            var trackingInfo = new TrackingInfo();
            trackingInfo.time = traceInfo.get("timeStr").getAsString();
            trackingInfo.status = traceInfo.get("desc").getAsString();
            trackingInfo.trackingNumber = json.get("mailNo").getAsString();
            trackingInfo.from = json.get("originCountry").getAsString();
            trackingInfo.to = json.get("destCountry").getAsString();

            return Optional.of(trackingInfo);
        }
        catch (Exception ex)
        {
            return Optional.empty();
        }
    }
}
