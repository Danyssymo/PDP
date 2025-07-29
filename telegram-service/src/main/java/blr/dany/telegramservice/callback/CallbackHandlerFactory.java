package blr.dany.telegramservice.callback;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CallbackHandlerFactory {

    private final List<CallbackHandler> handlers;

    public Optional<CallbackHandler> getHandler(String callbackData){
        return handlers.stream()
                .filter(handler -> handler.supports(callbackData))
                .findFirst();
    }

}
