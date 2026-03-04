package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author duxingzhe520
 * */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node () {
            key = null;
            value = null;
        }
    }

    private Node root;
    private BSTMap<K, V> leftTree;
    private BSTMap<K, V> rightTree;

    public BSTMap() {
        root = new Node(null, null);
        leftTree = null;
        rightTree = null;
    }

    @Override
    public void clear() {
        root = new Node(null, null);
        leftTree = null;
        rightTree = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root.key == null || key == null) {
            return false;
        }
        int compareOutcome = key.compareTo(root.key);
        if (compareOutcome == 0) {
            return true;
        } else if (compareOutcome < 0) {
            if (leftTree == null) {
                return false;
            }
            return leftTree.containsKey(key);
        } else {
            if (rightTree == null) {
                return false;
            }
            return rightTree.containsKey(key);
        }
    }

    @Override
    public V get(K key) {
        if (root.key == null || key == null) {
            return null;
        }
        int compareOutcome = key.compareTo(root.key);
        if (compareOutcome == 0) {
            return root.value;
        } else if (compareOutcome < 0) {
            if (leftTree == null) {
                return null;
            }
            return leftTree.get(key);
        } else {
            if (rightTree == null) {
                return null;
            }
            return rightTree.get(key);
        }
    }

    @Override
    public int size() {
        if (root == null || root.key == null) {
            return 0;
        }
        int leftSize = 0;
        int rightSize = 0;
        if (leftTree != null) {
            leftSize = leftTree.size();
        }
        if (rightTree != null) {
            rightSize = rightTree.size();
        }
        return 1 + leftSize + rightSize;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        if (root.key == null) {
            root.key = key;
            root.value = value;
            return;
        }
        int compareOutcome = key.compareTo(root.key);
        if (compareOutcome == 0) {
            root.value = value;
        } else if (compareOutcome < 0) {
            if (leftTree == null) {
                leftTree = new BSTMap<>();
                root.left = leftTree.root;
            }
            leftTree.put(key, value);
        } else {
            if (rightTree == null) {
                rightTree = new BSTMap<>();
                root.right = rightTree.root;
            }
            rightTree.put(key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        if (root == null || root.key == null) {
            return s;
        }
        s.add(root.key);
        if (leftTree != null) {
            s.addAll(leftTree.keySet());
        }
        if (rightTree != null) {
            s.addAll(rightTree.keySet());
        }
        return s;
    }

    /**
     * Returns the Node of the map, whose key is key (in args).
     * If there is no such key, returns null.
     * @param key the key of the Node wanted.
     * */
    private BSTMap<K, V> find(K key) {
        if (root == null || key == null || root.key == null) {
            return null;
        }
        int compareOutcome = key.compareTo(root.key);
        if (compareOutcome == 0) {
            return this;
        } else if (compareOutcome < 0) {
            if (leftTree == null) {
                return null;
            }
            return leftTree.find(key);
        } else {
            if (rightTree == null) {
                return null;
            }
            return rightTree.find(key);
        }
    }

    /*private Node findParent(K key) {
        if (root == null || key == null) {
            return null;
        }
        if (key.compareTo(root.key) == 0) {
            return null;
        }
        if (root.left != null && root.left.key.compareTo(key) == 0) {
            return root;
        } else if (root.right != null && root.right.key.compareTo(key) == 0) {
            return root;
        } else {
            if (root.left != null) {
                Node leftFind = leftTree.findParent(key);
                if (leftFind != null) {
                    return leftFind;
                }
            } else if (root.right != null) {
                Node rightFind = rightTree.findParent(key);
                if (rightFind != null) {
                    return rightFind;
                }
            }
            return null;
        }
    }*/

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
        /*BSTMap<K, V> toDelete = find(key);
        if (toDelete == null) {
            return null;
        }
        V toReturn = toDelete.root.value;
        boolean hasLeft = (toDelete.leftTree != null);
        boolean hasRight = (toDelete.rightTree != null);
        if (!hasLeft && !hasRight) {
            toDelete = null;
            //copyNode(toDelete, new BSTMap<>());
            return toReturn;
        } else if (hasLeft && !hasRight) {
            toDelete = toDelete.leftTree;
            //copyNode(toDelete, toDelete.leftTree);
            return toReturn;
        } else if (hasRight && !hasLeft) {
            toDelete = toDelete.rightTree;
            //copyNode(toDelete, toDelete.rightTree);
            return toReturn;
        } else {
            BSTMap<K, V> predecessor = toDelete.leftTree;
            predecessor = findPredecessor(predecessor);
            copyNodeKeyAndValue(toDelete, predecessor);
            toDelete.leftTree.remove(key);
            return toReturn;
        }*/
    }

    /**
     * Copy the BSTMap's root provide 's key and value to the Node first.
     * @param first the root accept new key and value.
     * @param provide the root provide its own key and value.
     * */
    private void copyNodeKeyAndValue(BSTMap<K, V> first, BSTMap<K, V> provide) {
        first.root.key = provide.root.key;
        first.root.value = provide.root.value;
    }

    /**
     * Copy the BSTMap's root provide 's all stuff to the Node first.
     * @param first the root accept new key and value.
     * @param provide the root provide its own key and value.
     * */
    private void copyNode(BSTMap<K, V> first, BSTMap<K, V> provide) {
        first.root.key = provide.root.key;
        first.root.value = provide.root.value;
        first.leftTree = provide.leftTree;
        first.rightTree = provide.rightTree;
    }

    private BSTMap<K, V> findPredecessor(BSTMap<K, V> start) {
        if (start.rightTree != null) {
            return findPredecessor(start.rightTree);
        }
        return start;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    /** Print all the key included in the BSTMap. */
    public void printInOrder() {
        if (root == null) {
            System.out.println('\n');
            return;
        }
        if (leftTree != null) {
            leftTree.printInOrder();
        }
        System.out.print(root.key + ": " + root.value + ", ");
        if (rightTree != null) {
            rightTree.printInOrder();
        }
    }

    /** The class to create an iterator of an instance of BSTMap. */
    private class BSTMapIterator implements Iterator<K> {
        Stack<Node> nodeStack = new Stack<>();

        public BSTMapIterator() {
            if (root == null) {
                return;
            }
            Node tmp = root;
            nodeStack.push(root);
            while (tmp.left != null) {
                nodeStack.push(tmp.left);
                tmp = tmp.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.empty();
        }

        @Override
        public K next() {
            Node now = nodeStack.pop();
            if (now.right == null) {
                return now.key;
            }
            Node tmp = now.right;
            nodeStack.push(tmp);
            while (tmp.left != null) {
                nodeStack.push(tmp.left);
                tmp = tmp.left;
            }
            return now.key;
        }
    }
}
