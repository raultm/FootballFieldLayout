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

    FootballFieldLayout ffl;

    @Before
    public void setUp() throws Exception
    {
        ffl = new FootballFieldLayout(null);
    }

    @After
    public void tearDown() throws Exception
    {
        ffl = null;
    }

    @Test
    public void byDefaultHasZeroFieldPlayers() throws Exception
    {
        assertThat(ffl.getFieldPlayers().size(), equalTo(0));
    }

    @Test
    public void canLinkAFieldPlayerCollection() throws Exception
    {
        FieldPlayerCollection fpc = new FieldPlayerCollection();

        ffl.linkFieldPlayerCollection(fpc);

        assertThat(ffl.getFieldPlayers(), equalTo(fpc));
    }

}