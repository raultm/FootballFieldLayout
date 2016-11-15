package raulete.com.footballfield.custom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by raulete on 15/11/16.
 */
public class FieldPlayerCollectionTest {

    FieldPlayerCollection collection;

    @Before
    public void setUp() throws Exception
    {
        collection = new FieldPlayerCollection();
    }

    @After
    public void tearDown() throws Exception
    {
        collection = null;
    }

    @Test
    public void byDefaulthasZeroElements() throws Exception
    {
        assertThat(collection.size(), equalTo(0));
    }

    @Test
    public void canAddPlayerField() throws Exception
    {
        FieldPlayer player = Mockito.mock(FieldPlayer.class);

        collection.add(player);

        assertThat(collection.size(), equalTo(1));
    }

}