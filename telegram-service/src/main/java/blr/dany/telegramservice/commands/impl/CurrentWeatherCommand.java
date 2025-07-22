package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.feign.WeatherServiceClient;
import blr.dany.telegramservice.feign.response.CurrentWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CurrentWeatherCommand implements CommandHandler {

    private final WeatherServiceClient weatherServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        CurrentWeatherResponse currentWeatherResponse = weatherServiceClient.getCurrentWeather("minsk");

        SendMessage message = new SendMessage();
        StringBuilder sb = new StringBuilder();
        sb.append("Погода сейчас")
                .append("\n")
                .append(currentWeatherResponse.getLocation().getName()).append("/")
                .append(currentWeatherResponse.getLocation().getCountry()).append(" ")
                .append(currentWeatherResponse.getLocation().getLocaltime())
                .append("\n")
                .append(currentWeatherResponse.getCurrent().getTempC()).append(" ощущаеся как ").append(currentWeatherResponse.getCurrent().getFeelslikeC())
                .append("\n")
                .append("скорость ветра ")
                .append(currentWeatherResponse.getCurrent().getWindKph())
                .append(" км/ч")
                .append("\n")
                .append("влажность ")
                .append(currentWeatherResponse.getCurrent().getHumidity()).append("%")
                .append("\n")
                .append("осадки ")
                .append(currentWeatherResponse.getCurrent().getPressureMb());
        String text = sb.toString();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }
}
