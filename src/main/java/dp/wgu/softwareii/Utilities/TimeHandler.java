package dp.wgu.softwareii.Utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeHandler {

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
}
