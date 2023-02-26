package dp.wgu.softwareii.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles time conversions and formatting for GUI.
 */
public class TimeHandler {

    // static data representing common formats used in the GUI display
     public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm MM-dd-yyyy ");
     public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

     // static data representing provided business hours of 8AM-10PM EST
     public static ZoneId businessZone = ZoneId.of("America/New_York");
     public static int openHour = 8;
     public static int closeHour = 22;

    /**
     * Takes a localDateTime and returns a ZonedDateTime obj with UTC offsett
     * @param ldt LocalDateTime of the user's system
     * @return
     */
    public static ZonedDateTime getZonedDateTimeUTC(LocalDateTime ldt) {
        ZoneId userZoneID = ZoneId.systemDefault();
        ZoneId utcZoneID = ZoneId.of("UTC");
        // create a zonedDateTime obj in the context of the user's zone
        ZonedDateTime zdt = ZonedDateTime.of(ldt, userZoneID);
        // standardize to UTC offset
        ZonedDateTime utc = ZonedDateTime.ofInstant(zdt.toInstant(), utcZoneID);

        return utc;
    }

    /**
     * Takes a ZonedDateTime of utc offset and returns a ZonedDateTime obj using system default zone
     * @param utc ZonedDateTime with UTC-offset
     * @return
     */
    public static ZonedDateTime utcToLocalOffset(ZonedDateTime utc) {
        return ZonedDateTime.ofInstant(utc.toInstant(), ZoneId.systemDefault());
    }
}
