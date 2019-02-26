// B tree modified

package com.neu.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Array: ‘A’G’F’B’K’D’H’M'J'E'S'I'R'X'C'L'N'T'U'P'
public class Question3 {
	public static void main(String args[]) {

		BTree bTree = new BTree('A', 4);
		String entries = "GFBKDHMJESIRXCLNTUP";

		for (int i = 0; i < entries.length(); i++)
			bTree.insert(entries.charAt(i));
		System.out.println("Printing the tree in BFS manner");
		bTree.printTree();

		bTree.remove('E');
		bTree.printTree();
		bTree.remove('B');
		bTree.printTree();
		bTree.remove('U');
		bTree.printTree();
	}
}

class BTree {

	public Node root;
	private int order;
	private int index;
	private int min_nodes;

	public BTree(int value, int order) {
		root = new Node(value);
		this.order = order;
		index = (int) Math.ceil(order / 2) - 1;
		min_nodes = index;
	}

	public void printTree() {
		// Node a = root;
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(root);

		while (!queue.isEmpty()) {

			Node a = queue.remove();

			for (int i = 0; i < a.getKeySize(); i++) {
				System.out.print((char) a.getKey(i) + " -- ");
			}
			queue.addAll(a.getAllLinks());

		}
	}

	public void insert(int value) {
		Node node = search(value);
		/*
		 * System.out.println(value); System.out.println(node.getALLKeys());
		 */

		node.addKey(value);
		if (node.getALLKeys().size() > order - 1) {
			split(node);
		}
	}

	public void split(Node node) {

		if (node == root) {
			ArrayList<Node> newLinks = new ArrayList<Node>();
			ArrayList<Integer> newkeys = new ArrayList<Integer>();
			ArrayList<Node> links = node.getAllLinks();
			ArrayList<Integer> keys = node.getALLKeys();

			if (links.size() != 0) {
				for (int i = 0; i < index; i++) {
					newLinks.add(links.get(0));
					newkeys.add(keys.get(0));
					keys.remove(0);
					links.remove(0);
				}
				newLinks.add(links.get(0));
				links.remove(0);
			} else {
				for (int i = 0; i < index; i++) {
					newkeys.add(keys.get(0));
					keys.remove(0);
				}
			}

			Node newLeftNode = new Node(newkeys, newLinks);

			Node newRootNode = new Node(node.getALLKeys().get(0));
			keys.remove(0);

			node.setKey(keys);
			node.setLink(links);

			newRootNode.addLink(0, node);
			newRootNode.addLink(0, newLeftNode);

			this.root = newRootNode;
		} else if (node.getAllLinks().size() == 0) {
			Node parent = root.searchParent(node);
			Node newNode = new Node(node.getALLKeys().get(0));

			ArrayList<Node> newLinks = parent.getAllLinks();
			ArrayList<Integer> newkeys = new ArrayList<Integer>();
			ArrayList<Integer> keys = node.getALLKeys();

			for (int i = 0; i < index; i++) {
				newkeys.add(keys.get(0));
				keys.remove(0);
			}
			newNode.setKey(newkeys);

			int inserIndex = parent.addKey(keys.get(0));
			keys.remove(0);
			node.setKey(keys);

			newLinks.add(inserIndex, newNode);
			parent.setLink(newLinks);

			if (parent.getALLKeys().size() > order - 1)
				split(parent);
		} else {
			Node parent = root.searchParent(node);

			ArrayList<Integer> keys = node.getALLKeys();
			ArrayList<Node> links = node.getAllLinks();
			ArrayList<Integer> newKeys = new ArrayList<Integer>();
			ArrayList<Node> newLinks = new ArrayList<Node>();

			for (int i = 0; i < index; i++) {
				newKeys.add(keys.get(0));
				keys.remove(0);

				newLinks.add(links.get(0));
				links.remove(0);
			}
			newLinks.add(links.get(0));
			links.remove(0);

			Node newNode = new Node(newKeys, newLinks);

			int inserIndex = parent.addKey(keys.get(0));
			keys.remove(0);

			node.setKey(keys);
			node.setLink(links);

			parent.addLink(inserIndex, newNode);
			;

			if (parent.getALLKeys().size() > order - 1)
				split(parent);
		}
	}

	public void remove(int value) {
		Node node = root.searchNodeWithValue(value);

		if (node == null) {
		}
		// System.out.println("Key not Found.");
		else {
			int deleteIndex = node.searchKey(value, order);
			if (node.getAllLinks().size() == 0) {
				if (node.getKeySize() <= min_nodes) {
					Node parent = root.searchParent(node);
					int nodeIndex = parent.getIndex(node);

					if (nodeIndex < order - 1) {
						if (parent.getLink(nodeIndex + 1).getKeySize() > min_nodes)
							rotate("left", node, parent, parent.getLink(nodeIndex + 1), nodeIndex);
						else if (parent.getLink(nodeIndex - 1).getKeySize() > min_nodes) {
							rotate("right", node, parent, parent.getLink(nodeIndex - 1), nodeIndex);
						} else {
							if (deleteIndex > (node.getKeySize() - 1) / 2)
								node = merge("right", node, parent, parent.getLink(nodeIndex + 1), nodeIndex);
							else if (nodeIndex == 0)
								node = merge("right", node, parent, parent.getLink(nodeIndex + 1), nodeIndex);
							else
								node = merge("left", node, parent, parent.getLink(nodeIndex - 1), nodeIndex);
						}
					} else if (nodeIndex == order) {
						if (parent.getLink(nodeIndex + 1).getKeySize() > min_nodes)
							rotate("left", node, parent, parent.getLink(nodeIndex + 1), nodeIndex);
						else
							node = merge("left", node, parent, parent.getLink(nodeIndex - 1), nodeIndex);
					}
				}
				node.removeKey(deleteIndex);
				System.out.println("\n\nKey " + (char) value + " deleted.");
				System.out.println("Updated B-Tree:");
			} else {
				Node temp = node.getLink(deleteIndex + 1);
				boolean left = false;

				while (temp.getLinkSize() != 0)
					temp = temp.getLink(0);

				if (temp.getKeySize() <= min_nodes) {
					temp = node.getLink(deleteIndex);
					left = true;
					while (temp.getLinkSize() != 0)
						temp = temp.getLink(temp.getLinkSize() - 1);
				}

				if (temp.getKeySize() > min_nodes) {
					if (left) {
						node.replaceKey(deleteIndex, temp.getKey(temp.getLinkSize() - 1));
						temp.removeKey(temp.getLinkSize() - 1);
					} else {
						node.replaceKey(deleteIndex, temp.getKey(0));
						temp.removeKey(0);
					}
					System.out.println("\nKey " + (char) value + " deleted");
					System.out.println("Updated B-Tree:\n");
				} else {
					Node leftNode = node.getLink(deleteIndex);
					Node rightNode = node.getLink(deleteIndex + 1);

					for (int key : rightNode.getALLKeys()) {
						leftNode.addKey(key);
					}

					node.removeKey(deleteIndex);
					node.removeLink(deleteIndex + 1);
					System.out.println("Key not Found.");
				}
			}
		}
	}

	private Node merge(String direction, Node node, Node parent, Node helper, int nodeIndex) {
		helper.addKey(parent.getKey(nodeIndex));
		for (int key : node.getALLKeys())
			helper.addKey(key);
		parent.removeKey(nodeIndex);

		switch (direction) {
		case "left":
			;
			parent.removeLink(nodeIndex + 1);
			break;
		case "right":
			parent.removeLink(nodeIndex);
			break;
		}
		return helper;
	}

	private void rotate(String direction, Node node, Node parent, Node helper, int nodeIndex) {
		// TODO Auto-generated method stub
		switch (direction) {
		case "left":
			node.addKey(parent.getKey(nodeIndex));
			parent.removeKey(nodeIndex);
			parent.addKey(helper.getKey(0));
			helper.removeKey(0);
			break;
		case "right":
			node.addKey(parent.getKey(nodeIndex));
			parent.removeKey(nodeIndex);
			parent.addKey(helper.getKeySize() - 1);
			helper.removeKey(helper.getKeySize() - 1);
			break;
		}
	}

	public Node search(int value) {
		return root.searchNode(value);
	}

}

class Node {
	private ArrayList<Integer> key;
	private ArrayList<Node> links;

	public Node(ArrayList<Integer> key, ArrayList<Node> links) {
		this.key = key;
		this.links = links;
	}

	public Node(int key) {
		this.key = new ArrayList<>(Arrays.asList(key));
		links = new ArrayList<Node>();
	}

	public ArrayList<Integer> getALLKeys() {
		return key;
	}

	public ArrayList<Node> getAllLinks() {
		return links;
	}

	public void setKey(ArrayList<Integer> keys) {
		this.key = keys;
	}

	public void setLink(ArrayList<Node> links) {
		this.links = links;
	}

	public int addKey(int newValue) {
		int i;
		for (i = 0; i < key.size(); i++)
			if (newValue < key.get(i))
				break;
		key.add(i, newValue);
		return i;
	}

	public void removeKey(int index) {
		key.remove(index);
	}

	public void addLink(int index, Node link) {
		links.add(index, link);
	}

	public void removeLink(int index) {
		links.remove(index);
	}

	public int searchKey(int value, int order) {
		for (int i = 0; i < key.size(); i++)
			if (key.get(i) == value)
				return i;

		return order;
	}

	public Node searchNode(int value) {
		Node result = null;
		if (links.size() == 0) {
			return this;
		} else {
			for (int i = 0; i < key.size(); i++) {
				if (value <= key.get(i)) {
					result = links.get(i).searchNode(value);
					break;
				}
			}
			result = result == null ? links.get(key.size()).searchNode(value) : result;
			return result;
		}
	}

	public Node searchParent(Node node) {
		Node result = null;
		if (links.contains(node))
			return this;
		else {
			for (int i = 0; i < links.size() - 1; i++) {
				if (node.getALLKeys().get(0) < key.get(i))
					result = links.get(i).searchParent(node);
			}
			result = result == null ? links.get(key.size()).searchParent(node) : result;
			// System.out.print(result.getALLKeys());
			return result;
		}
	}

	public int getKeySize() {
		return key.size();
	}

	public int getIndex(Node node) {
		int i;
		for (i = 0; i < links.size(); i++) {
			if (links.get(i) == node)
				return i;
		}
		return i + 1;
	}

	public Node getLink(int index) {
		return links.get(index);
	}

	public int getLinkSize() {
		return links.size();
	}

	public int getKey(int index) {
		return key.get(index);
	}

	public void replaceKey(int index, int newKey) {
		key.set(index, newKey);
	}

	public Node searchNodeWithValue(int value) {
		int i = 0;
		for (i = 0; i < getALLKeys().size(); i++) {
			if (value == key.get(i))
				return this;
			else if (value < key.get(i) && links.size() != 0)
				return links.get(i).searchNodeWithValue(value);
		}
		if (links.size() != 0) {
			return links.get(i).searchNodeWithValue(value);
		}
		return null;
	}
}
