package consoleinmemory.presentation;

import consoleinmemory.config.ConsoleAppModule;
import lombok.RequiredArgsConstructor;
import domain.registration.RegistrationFacade;
import domain.registration.api.RegistrationResult;

@RequiredArgsConstructor
public class ConsoleApp {

    private final RegistrationFacade registrationFacade;

    public ConsoleApp(ConsoleAppModule consoleAppModule) {
        this.registrationFacade = consoleAppModule.getRegistrationFacade();
    }

    public void start() {
        final RegistrationResult result = registrationFacade.register("USERNAME", "PAZZ");
        final String myId = result.getId();
        final String myToken = result.getToken();

        final RegistrationResult confirmResult = registrationFacade.confirm(myId, myToken);
        System.out.println(confirmResult);
    }
}