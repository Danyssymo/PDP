package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.WeatherServiceClient;
import blr.dany.telegramservice.feign.response.CurrentWeatherResponse;
import blr.dany.telegramservice.feign.response.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CurrentWeatherCommand implements CommandHandler {

    private final WeatherServiceClient weatherServiceClient;
    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        TelegramUser user = userServiceClient.getUserByChatId(String.valueOf(chatId)).getBody();

        CurrentWeatherResponse currentWeatherResponse = weatherServiceClient.getCurrentWeather(user.getCountry());
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        StringBuilder weatherText = new StringBuilder();
        weatherText.append("🌦 *Текущая погода в ")
                .append(currentWeatherResponse.getLocation().getName())
                .append(", ")
                .append(currentWeatherResponse.getLocation().getCountry())
                .append("*\n\n");
        weatherText.append("🕒 *")
                .append(currentWeatherResponse.getLocation().getLocaltime())
                .append("*\n");
        weatherText.append("🌡 Температура: *")
                .append(currentWeatherResponse.getCurrent().getTempC())
                .append("°C* (ощущается как *")
                .append(currentWeatherResponse.getCurrent().getFeelslikeC())
                .append("°C*)\n");
        weatherText.append("☁ Состояние: *")
                .append(currentWeatherResponse.getCurrent().getCondition().getText())
                .append("*\n");
        weatherText.append("💨 Ветер: *")
                .append(currentWeatherResponse.getCurrent().getWindKph())
                .append(" км/ч* (")
                .append(currentWeatherResponse.getCurrent().getWindDir())
                .append("), порывы до *")
                .append(currentWeatherResponse.getCurrent().getGustKph())
                .append(" км/ч*\n");
        weatherText.append("💧 Влажность: *")
                .append(currentWeatherResponse.getCurrent().getHumidity())
                .append("%*\n")
                .append("🌧 Осадки: *")
                .append(currentWeatherResponse.getCurrent().getPrecipMm())
                .append(" мм*\n");
        weatherText.append("📊 Давление: *")
                .append(currentWeatherResponse.getCurrent().getPressureMb())
                .append(" мбар*\n")
                .append("👁 Видимость: *")
                .append(currentWeatherResponse.getCurrent().getVisKm())
                .append(" км*\n");

        message.setText(weatherText.toString());
        message.setParseMode("Markdown"); // Для форматирования текста
        return message;
    }
}
