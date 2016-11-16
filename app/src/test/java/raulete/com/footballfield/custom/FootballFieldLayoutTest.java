package raulete.com.footballfield.custom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test
    public void respondsWhenAFieldPlayerIsAddedToCollection() throws Exception
    {
        FootballFieldLayout ffl = Mockito.mock(FootballFieldLayout.class, Mockito.CALLS_REAL_METHODS);

        FieldPlayerCollection fpc = new FieldPlayerCollection();

        ffl.linkFieldPlayerCollection(fpc);

        fpc.add(Mockito.mock(FieldPlayer.class));

        verify(ffl, times(1)).collectionsChanges();
    }

}