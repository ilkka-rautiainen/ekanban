package fi.aalto.ekanban.models.db.games;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class GameTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Game.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }

}
