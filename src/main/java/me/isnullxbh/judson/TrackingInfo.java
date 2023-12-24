package me.isnullxbh.judson;

/**
 * Contains information about the tracked package.
 * @since 0.1.0
 */
public class TrackingInfo
{
    /** Current status setting time */
    public String time;

    /** Delivery status */
    public String status;

    /** Tracking number */
    public String trackingNumber;

    /** Origin country */
    public String from;

    /** Destination country */
    public String to;

    public String toString()
    {
        return String.format("%s: %s", trackingNumber, status);
    }
}
