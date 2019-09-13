
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class  PercolationTest {

    @Test
    public void constructorShouldCreateGirdWithAllBlockSites() {
        Percolation percolation = new Percolation(2);
        assertArrayEquals(new boolean[][]{{false, false}, {false, false}}, percolation.getMatrix());
    }

    @Test
    public void converts2dInput22to1d4whenMatrixSizeIs2() {
        Percolation Percolation = new Percolation(2);
        assertEquals(4, Percolation.convert2dTo1d(2, 2));
        assertEquals(2, Percolation.convert2dTo1d(1, 2));
        assertEquals(3, Percolation.convert2dTo1d(2, 1));
    }

    @Test
    public void openASiteShouldUpdateOpenCountAndMarkSiteAsOpenByChangingValueTo1() {
        Percolation Percolation = new Percolation(2);
        Percolation.open(1, 1);
        assertEquals(1, Percolation.numberOfOpenSites());
    }

    @Test
    public void shouldReturnTrueIfSiteIsOpen() {
        Percolation Percolation = new Percolation(2);
        Percolation.open(1, 1);
        assertTrue(Percolation.isOpen(1, 1));
    }


    @Test
    public void findAdjacentOpenSitesShouldReturn3EntryWhenMatrixIsOfSize2AndAllOpen() {
        Percolation Percolation = new Percolation(2);
        Percolation.open(1, 1);
        Percolation.open(1, 2);
        Percolation.open(2, 1);
        Percolation.open(2, 2);
        int[] expected = new int[]{3, 2, 0, 0};
        System.out.println(Arrays.toString(Percolation.findAdjacentOpenSites(2, 2)));
        assertArrayEquals(expected, Percolation.findAdjacentOpenSites(2, 2));
    }


    @Test
    public void aOneByOnePercolationMatrixShouldAlwaysPercolatesIfSiteIsOpen() {
        Percolation Percolation = new Percolation(1);
        Percolation.open(1, 1);
        assertTrue(Percolation.percolates());
    }


    @Test
    public void aTwoByTwoMatrixShouldPercolatesAfterTwoOpens() {
        Percolation Percolation = new Percolation(2);
        Percolation.open(1, 1);
        assertFalse(Percolation.percolates());
        Percolation.open(2, 1);
        assertTrue(Percolation.percolates());
    }

    @Test
    public void aThreeByThreeMatrixShouldPercolatesAfterThreeOpens() {
        Percolation Percolation = new Percolation(3);
        Percolation.open(1, 1);
        assertFalse(Percolation.percolates());
        Percolation.open(2, 1);
        Percolation.open(2, 3);
        assertFalse(Percolation.percolates());
        Percolation.open(3, 1);
        assertTrue(Percolation.percolates());
    }

    @Test
    public void testInput() {
        Percolation Percolation = new Percolation(6);
        Percolation.open(1, 6);
        Percolation.open(2, 6);
        Percolation.open(3, 6);
        Percolation.open(4, 6);
        Percolation.open(5, 6);
        Percolation.open(5,5);
        Percolation.open(4,4);
        assertFalse(Percolation.isFull(4,4));
    }

    @Test
    public void testInputBackWash() {
        Percolation Percolation = new Percolation(6);
        Percolation.open(1, 6);
        Percolation.open(2, 6);
        Percolation.open(3, 6);
        Percolation.open(4, 6);
        Percolation.open(5, 6);
        Percolation.open(6, 6);
        Percolation.open(6, 1);
        assertFalse(Percolation.isFull(6,1));
    }
}


