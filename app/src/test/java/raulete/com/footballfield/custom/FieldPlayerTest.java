package raulete.com.footballfield.custom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by raulete on 16/11/16.
 */
public class FieldPlayerTest {

    FieldPlayer fieldPlayer;

    @Before
    public void setUp()
    {
        fieldPlayer = Mockito.mock(FieldPlayer.class);
    }

    @After
    public void tearDown()
    {
        fieldPlayer = null;
    }

    @Test
    public void chekcHasNoPosition()
    {
        assertThat(fieldPlayer.isPositioned(), is(false));
    }

}