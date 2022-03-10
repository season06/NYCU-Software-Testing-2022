import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import org.mockito.*;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {
    private StrangeGame game = new StrangeGame();
    // Spy on real instance: avoid test run the `imprisonment`.
    @Spy
    Prison prison;
    // Mock the return value
    @Mock
    GAMEDb db;
    @Mock
    Hour hour;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // Override object with mock or spy.
        game.prison = prison;
        game.db = db;
        game.hour = hour;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        prison = null;
        db = null;
        hour = null;
    }

    // a. If a notorious player enter the game on 0:00 - 11:59, verify that prison doesn’t do any imprisonment.
    @org.junit.jupiter.api.Test
    void testA() throws InterruptedException {
        when(hour.getHour()).thenReturn(1);
        assertEquals("The game is not yet open!", game.enterGame(new Player()));
        verify(prison, times(0)).imprisonment(new Player());
    }

    // b. If a notorious player enter the game on 12:00 - 23:59, assert the output correct.
    @org.junit.jupiter.api.Test
    void testB() throws InterruptedException {
        when(hour.getHour()).thenReturn(13);

        // For reputation < 0
        Player player = new Player();
        lenient().doNothing().when(prison).imprisonment(player);
        assertEquals("After a long period of punishment, the player can leave! :)", game.enterGame(player));

        // For reputation >= 0
        assertEquals("Have a nice day!", game.enterGame(new Player("310551019", 1)));
    }

    // c. Suppose 3 players go to the prison. Verify prisonerLog in prison will record prisoner’s playerid with spy method. Don’t stub getLog function.
    @org.junit.jupiter.api.Test
    void testC() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Player player = new Player(Integer.toString(i), -1);

            lenient().doNothing().when(prison).imprisonment(player);
            prison.crime(player);

            assertEquals(player.getPlayerId(), prison.getLog().get(i));
            verify(prison, times(1)).crime(player);
        }
    }

    // d. Use stub method to test getScore function (PlayerID = your StudentID) to avoid connection to outer database.
    @org.junit.jupiter.api.Test
    void testD() {
        String id = "310551019";
        when(db.getScore(id)).thenReturn(100);
        assertEquals(100, game.getScore(id));
        verify(db, times(1)).getScore(id);
    }

    // e. Implement paypalService interface as a fake object to test donate function.
    @org.junit.jupiter.api.Test
    void testE() {
        assertEquals("Thank you", game.donate(new PaypalServiceFake()));
    }
    final public class PaypalServiceFake implements paypalService {
        public String doDonate() {
            return "Success";
        }
    }
}