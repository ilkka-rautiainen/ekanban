package fi.aalto.ekanban.models;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class DiceCastActionTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(DiceCastAction.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }

}
