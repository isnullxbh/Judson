package me.isnullxbh.judson;

import java.util.Optional;

/**
 * A tracking service client interface.
 * @since 0.1.0
 */
public interface TrackingServiceClient
{
    /**
     * Gets tracking information for a package with the specified tracking number.
     * @param  trackingNumber Tracking number.
     * @return If information is retrieved - a non-empty option, otherwise - empty.
     * @todo   Use Result instead of Optional.
     */
    Optional<TrackingInfo> track(String trackingNumber);
}
