import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
	public static int points = 0;

	
////////// Testing insertion
		@Test
		public void testSplits(){
			AATree<Integer> a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);
			a.insert(5);
			a.insert(6);
			a.insert(7);
		
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(4);
			values.add(2);
			values.add(1);
			values.add(3);
			values.add(6);
			values.add(5);
			values.add(7);
			levels.add(3);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}			
			points += 15;
		}		

		@Test
		public void testSkewsandSplits(){
			AATree<Integer> a = new AATree<Integer>();
			a.insert(7);
			a.insert(6);
			a.insert(5);
			a.insert(4);
			a.insert(3);
			a.insert(2);
			a.insert(1);
		
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(4);
			values.add(2);
			values.add(1);
			values.add(3);
			values.add(6);
			values.add(5);
			values.add(7);
			levels.add(3);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			points += 15;
		}		
		
		
		@Test
		public void testingStressInsertIncreasing(){
			AATree<Integer> a = new AATree<Integer>();
			for (int i = 1; i < 127; i++){
				a.insert(i);
			}
			assertEquals(6, a.root.getLevel());
			assertEquals(114, a.rotationCount);
			a.insert(127);
			assertEquals(7, a.root.getLevel());
			assertEquals(120, a.rotationCount);
			points += 5;
		}
		
		@Test
		public void testingStressInsertdecreasing(){
			AATree<Integer> a = new AATree<Integer>();
			for (int i = 127; i > 1; i--){
				a.insert(i);
			}
			assertEquals(6, a.root.getLevel());
			assertEquals(348, a.rotationCount);
			a.insert(1);
			assertEquals(7, a.root.getLevel());
			assertEquals(360, a.rotationCount);
			points += 5;
		}
	

////////// Testing removal
		@Test	
		public void testRemovalofLeafs() {
			AATree<Integer> a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);

			// testing removal of leaf, requiring no adjustments
			a.remove(4);
			
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(2);
			values.add(1);
			values.add(3);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}	
			points += 5;
		
			
//			// testing removal of leaf, requiring level adjustment on right and skew
			
			a.remove(new Integer(3));

			result = a.toArrayList();
			values = new ArrayList<Object>();
			levels = new ArrayList<Object>();
			values.add(1);
			values.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			
			points += 5;

			// testing removal of leaf, requiring level adjustment on left, but no skew
			a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);

			a.remove(1);

			result = a.toArrayList();
			values = new ArrayList<Object>();
			levels = new ArrayList<Object>();
			values.add(2);
			values.add(3);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
		
			points += 5;
		}	
		
		@Test
		public void testRemovalofNodesWithOneChild() {
			AATree<Integer> a = new AATree<Integer>();
// testing removal of node with one child, requiring no level adjustment
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);
			a.remove(3);
			
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(2);
			values.add(1);
			values.add(4);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			
			points += 5;
			
// testing removal of root which has no children
			a.remove(4);
			a.remove(2);
			a.remove(1);
			System.out.println(a);
			result = a.toArrayList();
			assertTrue(result.size() == 0);
			
			points += 5;
		}
		
		
// testing removal of nodes with two children
		@Test
		public void testRemovalofNodesWithTwoChildren() {
			AATree<Integer> a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);
			a.insert(5);
			a.remove(4);
			
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(2);
			values.add(1);
			values.add(3);
			values.add(5);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			
			points += 5;
		
//		// testing removal of root with two children
			a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);
			a.remove(2);
			
			result = a.toArrayList();
			values = new ArrayList<Object>();
			levels = new ArrayList<Object>();
			values.add(3);
			values.add(1);
			values.add(4);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			
			points += 5;
		}

		@Test
		public void testFigure19point63() {
			AATree<Integer> a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(5);
			a.insert(6);
			a.insert(7);
			a.insert(4);

			a.remove(1);
			
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(3);
			values.add(2);
			values.add(5);
			values.add(4);
			values.add(6);
			values.add(7);
		
			levels.add(2);
			levels.add(1);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(1);

			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			
			points += 5;
				
		//testing same fig but removal of 2
		a = new AATree<Integer>();
		a.insert(1);
		a.insert(2);
		a.insert(3);
		a.insert(5);
		a.insert(6);
		a.insert(7);
		a.insert(4);

		a.remove(2);
		
		result = a.toArrayList();
		values = new ArrayList<Object>();
		levels = new ArrayList<Object>();
		values.add(3);
		values.add(1);
		values.add(5);
		values.add(4);
		values.add(6);
		values.add(7);
	
		levels.add(2);
		levels.add(1);
		levels.add(2);
		levels.add(1);
		levels.add(1);
		levels.add(1);

		for (int i = 0; i < values.size(); i++){
			assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
			assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
		}
		points += 5;
	}

		@Test	
		public void testFigure2FromAAPaper() {
			AATree<Integer> a = new AATree<Integer>();
			a.insert(1);
			a.insert(2);
			a.insert(3);
			a.insert(4);
			a.insert(5);
			a.insert(6);
			a.insert(7);
			a.insert(8);
			a.insert(9);
			a.insert(10);
			a.insert(11);
			a.insert(12);
			a.insert(13);
			a.remove(7);
			a.insert(7);
	
			a.remove(1);
			
			ArrayList<Object> result = a.toArrayList();
			ArrayList<Object> values = new ArrayList<Object>();
			ArrayList<Object> levels = new ArrayList<Object>();
			values.add(6);
			values.add(4);
			values.add(2);
			values.add(3);
			values.add(5);
			values.add(10);
			values.add(8);
			values.add(7);
			values.add(9);
			values.add(12);
			values.add(11);
			values.add(13);
			
			levels.add(3);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(1);
			levels.add(3);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			levels.add(2);
			levels.add(1);
			levels.add(1);
			
			for (int i = 0; i < values.size(); i++){
				assertEquals(values.get(i), ((AATree.BinaryNode) result.get(i)).getElement());
				assertEquals(levels.get(i), ((AATree.BinaryNode) result.get(i)).getLevel());
			}
			points += 5;
		}
		
		@Test
		public void testingStressRemoveIncreasing(){
			AATree<Integer> a = new AATree<Integer>();
			for (int i = 1; i < 128; i++){
				a.insert(i);
			}
			
			for (int i = 1; i < 127; i++){
				a.remove(i);
			}
			assertEquals(1, a.root.getLevel());
			assertEquals(237, a.rotationCount);
			points += 5;
		}
		
		@Test
		public void testingStressRemovedecreasing(){
			AATree<Integer> a = new AATree<Integer>();
			for (int i = 1; i < 128; i++){
				a.insert(i);
			}
			for (int i = 127; i > 1; i--){
				a.remove(i);
			}
			assertEquals(1, a.root.getLevel());
			assertEquals(240, a.rotationCount);
			points += 5;
		}
		

    @Test
    public void testingLogBehaviorOfInsertionAndRemoval(){
	AATree<Integer> t = new AATree<Integer>();
	int nums = 2000000;
	int[] a = new int[nums];
	// populating array
	for (int i = 0; i < nums; i++){
	    a[i] = i;
	}
	int i1;
	int i2;
	int temp;
	// shuffling array
	for (int i = 0; i < nums; i++) {
	    i1 = (int) (Math.random() * nums);
	    i2 = (int) (Math.random() * nums);
	    temp = a[i1];
	    a[i1] = a[i2];
	    a[i2] = temp;
	}
	for (int i = 0; i < nums; i++) t.insert(a[i]);
	assertEquals(nums, t.size());

	for (int i = 0; i < nums; i++) t.remove(i);
	assertEquals(0, t.size());
    }

		
		@AfterClass	
		public static void testNothing(){
			System.out.println(points);
		}
		
}