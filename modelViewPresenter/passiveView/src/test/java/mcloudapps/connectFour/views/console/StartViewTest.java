package mcloudapps.connectFour.views.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import mcloudapps.utils.views.Console;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StartViewTest {
    
        @Mock
        private Console console;
        private StartView startView;

        @BeforeEach
        public void beforeEach() {
            this.startView = new StartView();
        }
    
        @Test
        void testGivenStartViewWhenInteractThenInteract() {
            try (MockedStatic<Console> console = mockStatic(Console.class)) {
                console.when(Console::getInstance).thenReturn(this.console);
                this.startView.write();
                verify(this.console).writeln("******** CONNECT FOUR ********");
            }
        }
    

    
}
