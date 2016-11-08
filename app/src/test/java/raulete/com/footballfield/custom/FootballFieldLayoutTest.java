package raulete.com.footballfield.custom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by raulete on 8/11/16.
 */
public class FootballFieldLayoutTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void byDefaultHasZeroFieldPlayers() throws Exception
    {
        FootballFieldLayout ffl = new FootballFieldLayout(null);

        assertThat(ffl.getFieldPlayers().size(), equalTo(0));
    }

}