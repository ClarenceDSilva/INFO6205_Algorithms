package com.neu.algorithms;

//Array: 3,7,9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56
public class Question3c {

	public static void main(String args[]) {
		BTree bTree = new BTree(4);
		bTree.Insert(3);
		bTree.Show();
		bTree.Insert(7);
		bTree.Show();
		bTree.Insert(9);
		bTree.Show();
		bTree.Insert(23);
		bTree.Show();
		bTree.Insert(45);
		bTree.Show();

		bTree.Insert(1);
		bTree.Show();
		bTree.Insert(5);
		bTree.Show();
		bTree.Insert(14);
		bTree.Show();
		bTree.Insert(55);
		bTree.Show();
		bTree.Insert(24);
		bTree.Show();

		bTree.Insert(13);
		bTree.Show();
		bTree.Insert(11);
		bTree.Show();
		bTree.Insert(8);
		bTree.Show();
		bTree.Insert(19);
		bTree.Show();
		bTree.Insert(4);
		bTree.Show();

		bTree.Insert(31);
		bTree.Show();
		bTree.Insert(35);
		bTree.Show();
		bTree.Insert(36);
		bTree.Show();
	}

}

class BTree {

	private int T;

	public class Node {
		int n;
		int key[] = new int[2 * T - 1];
		Node child[] = new Node[2 * T];
		boolean leaf = true;

		public int Find(int k) {
			for (int i = 0; i < this.n; i++) {
				if (this.key[i] == k) {
					return i;
				}
			}
			return -1;
		};
	}

	public BTree(int _T) {
		T = _T;
		root = new Node();
		root.n = 0;
		root.leaf = true;
	}

	private Node root;

	private void Split(Node x, int pos, Node y) {
		Node z = new Node();
		z.leaf = y.leaf;
		z.n = T - 1;
		for (int j = 0; j < T - 1; j++) {
			z.key[j] = y.key[j + T];
		}
		if (!y.leaf) {
			for (int j = 0; j < T; j++) {
				z.child[j] = y.child[j + T];
			}
		}
		y.n = T - 1;
		for (int j = x.n; j >= pos + 1; j--) {
			x.child[j + 1] = x.child[j];
		}
		x.child[pos + 1] = z;

		for (int j = x.n - 1; j >= pos; j--) {
			x.key[j + 1] = x.key[j];
		}
		x.key[pos] = y.key[T - 1];
		x.n = x.n + 1;
	}

	public void Insert(final int key) {
		Node r = root;
		if (r.n == 2 * T - 1) {
			Node s = new Node();
			root = s;
			s.leaf = false;
			s.n = 0;
			s.child[0] = r;
			Split(s, 0, r);
			_Insert(s, key);
		} else {
			_Insert(r, key);
		}
	}

	final private void _Insert(Node x, int k) {

		if (x.leaf) {
			int i = 0;
			for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
				x.key[i + 1] = x.key[i];
			}
			x.key[i + 1] = k;
			x.n = x.n + 1;
		} else {
			int i = 0;
			for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
			}
			;
			i++;
			Node tmp = x.child[i];
			if (tmp.n == 2 * T - 1) {
				Split(x, i, tmp);
				if (k > x.key[i]) {
					i++;
				}
			}
			_Insert(x.child[i], k);
		}

	}

	public void Show() {
		Show(root);
	}

	private void Show(Node x) {
		assert (x == null);
		System.out.print(x.leaf + " " + x.n + ":");
		for (int i = 0; i < x.n; i++) {
			System.out.print(x.key[i] + " ");
		}
		System.out.println();
		if (!x.leaf) {
			for (int i = 0; i < x.n + 1; i++) {
				Show(x.child[i]);
			}
		}
	}
}
