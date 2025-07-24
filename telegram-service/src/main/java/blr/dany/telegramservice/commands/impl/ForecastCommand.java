package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.WeatherServiceClient;
import blr.dany.telegramservice.feign.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class ForecastCommand implements CommandHandler {

    private final WeatherServiceClient weatherServiceClient;
    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        TelegramUser telegramUser = userServiceClient.getUserByChatId(String.valueOf(chatId)).getBody();
        ForecastResponse forecastResponse = weatherServiceClient.getForecast(telegramUser.getCountry());
        StringBuilder sb = new StringBuilder();

        LocationResponse location = forecastResponse.getLocation();
        sb.append("📍 *Погода на 7 дней для*: *")
                .append(location.getName())
                .append(", ")
                .append(location.getRegion())
                .append("* (")
                .append(location.getCountry())
                .append(")\n\n");

        for (ForecastDayResponse day : forecastResponse.getForecast().getForecastDay()) {
            DayResponse dayInfo = day.getDay();
            Condition condition = dayInfo.getCondition();

            sb.append("📅 *")
                    .append(day.getDate())
                    .append("*\n")
                    .append("🌤️ ")
                    .append(condition.getText()).append("\n")
                    .append("🌡️ Мин: ").append(dayInfo.getMinTempC()).append("°C / Макс: ").append(dayInfo.getMaxTempC()).append("°C\n")
                    .append("💧 Влажность: ").append(dayInfo.getAvgHumidity()).append("%\n")
                    .append("🌧️ Шанс дождя: ").append(dayInfo.getDailyChanceOfRain()).append("%\n")
                    .append("🌬️ Ветер: ").append(dayInfo.getMaxWindKph()).append(" км/ч\n")
                    .append("☀️ Восход: ").append(day.getAstro().getSunrise())
                    .append(" | 🌇 Закат: ").append(day.getAstro().getSunset())
                    .append("\n\n");
        }

        return SendMessage.builder()
                .parseMode("Markdown")
                .text(sb.toString())
                .chatId(chatId)
                .build();
    }
}

