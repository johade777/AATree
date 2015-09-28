import java.util.ArrayList;

public class AATree<T extends Comparable<? super T>> {

	public BinaryNode root;
	public int rotationCount;
	public boolean inserted = false;
	public boolean removed = false;
	int size;

	public boolean insert(T obj) {
		if (obj == null) {
			inserted = false;
			throw new IllegalArgumentException();
		}

		if (root == null) {
			root = new BinaryNode(obj);
			inserted = true;
			return inserted;
		}
		root = root.insert(obj);
		return inserted;
	}

	public Boolean remove(T obj) {
		if (obj == null) {
			removed = false;
			throw new IllegalArgumentException();
		}

		if (root == null) {
			return removed;
		}

		root = root.remove(obj);
		return removed;
	}

	public ArrayList<Object> toArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		if (root == null) {
			return list;
		}
		return root.toArrayList(list);
	}

	public int size() {
		return this.size;
	}

	class BinaryNode {
		private T data;
		private BinaryNode left;
		private BinaryNode right;
		private int level;

		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}

		public BinaryNode(T obj) {
			this.data = obj;
			this.left = null;
			this.right = null;
			this.level = 1;
		}

		public BinaryNode remove(T e) {

			if (data.compareTo(e) > 0) {
				if (left == null) {
					removed = false;
					return this;
				}
				left = left.remove(e);
			} else if (data.compareTo(e) < 0) {
				if (right == null) {
					removed = false;
					return this;
				}
				right = right.remove(e);
			}
			int leftLevel = 0;
			int rightLevel = 0;
			if(this.left != null){
				leftLevel = left.level;
			}
			if(this.right != null){
				rightLevel = right.level;
			}
			if(this.data.compareTo(e) == 0){
				this.data = null;
				level = 0;
				return this;
			}
			
			
			if ((leftLevel < this.level - 1)
					|| (rightLevel < this.level - 1)) {
				removed = true;
				BinaryNode temp = right.getMin();
				if (right.level < level - 1) {
					level -= 1;
					if (right.level > level) {
						right.level = level;
					}
					skew();
					right.skew();
					right.right.skew();
					split();
					right.split();
				}
			}
			return this;
		}

		public BinaryNode insert(T obj) {
			if (this.data.compareTo(obj) == 0) {
				inserted = false;
				return this;
			} else if (this.data.compareTo(obj) > 0) {
				if (this.left == null) {
					this.left = new BinaryNode(obj);
					inserted = true;
				}
				this.left = this.left.insert(obj);
			} else if (this.data.compareTo(obj) < 0) {
				if (this.right == null) {
					this.right = new BinaryNode(obj);
					inserted = true;
				}
				this.right = this.right.insert(obj);
				BinaryNode temp = this.skew();
				temp = temp.split();
				return temp;
			}
			BinaryNode temp = this.skew();
			return temp.split();
		}

		public ArrayList<Object> toArrayList(ArrayList<Object> list) {
			if (left == null && right == null) {
				list.add(this);
				return list;
			}
			list.add(this);
			if (left != null) {
				left.toArrayList(list);
			}
			if (right != null) {
				right.toArrayList(list);
			}
			return list;
		}

		public T getElement() {
			return this.data;
		}

		public int getLevel() {
			return this.level;
		}

		public BinaryNode skew() {
			if (this.left != null) {
				if (this.level == this.left.level) {
					return this.singleRightRotation();
				}
			}
			return this;
		}

		public BinaryNode split() {
			if (this.right != null) {
				if (this.right.right != null) {
					if (this.right.right.level == this.level) {
						this.right.level++;
						return this.singleLeftRotation();
					}
				}
			}
			return this;
		}

		public BinaryNode getMin() {
			BinaryNode temp = this;
			while (temp.left != null) {
				temp = temp.left;
			}
			return temp;
		}

		private BinaryNode singleRightRotation() {
			BinaryNode temp = this.left;
			if (temp != null) {
				this.left = temp.right;
			}
			temp.right = this;
			rotationCount++;
			return temp;
		}

		private BinaryNode singleLeftRotation() {
			BinaryNode temp = this.right;
			if (temp != null) {
				this.right = temp.left;
			}
			temp.left = this;
			rotationCount++;
			return temp;
		}
	}
}
