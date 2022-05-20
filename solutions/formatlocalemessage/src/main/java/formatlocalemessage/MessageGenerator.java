package formatlocalemessage;

import java.text.MessageFormat;

public class MessageGenerator {

    private static final String FORECAST_TEMPLATE = "Tomorrow expect {0} with {1}  \u00B0C temperature in {2}.";
    private static final String LOTTERY_TEMPLATE = "Yesterday {0} won a staggering sum of 500 M";

    public String generateForecastText(String weather, int degree, String place) {
        return MessageFormat.format(FORECAST_TEMPLATE, weather, degree, place);
    }

    public String generateLotteryAnnouncement(String name) {
        return MessageFormat.format(LOTTERY_TEMPLATE, name);
    }
}
