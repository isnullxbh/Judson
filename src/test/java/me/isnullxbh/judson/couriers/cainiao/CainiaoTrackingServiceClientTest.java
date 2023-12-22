package me.isnullxbh.judson.couriers.cainiao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CainiaoTrackingServiceClientTest
{
    @Test
    void track()
    {
        var client = new CainiaoTrackingServiceClient();
        var trackingInfoOpt = client.track("RLEM00077403");
        assertTrue(trackingInfoOpt.isPresent());
        var trackingInfo = trackingInfoOpt.get();
        assertEquals(trackingInfo.trackingNumber, "RLEM00077403");
        assertEquals(trackingInfo.from, "Mainland China");
        assertEquals(trackingInfo.to, "Russia");
        assertEquals(trackingInfo.status, "Out for delivery");
    }
}
