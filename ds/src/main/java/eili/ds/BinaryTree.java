package eili.ds;

import java.util.*;
import java.util.function.Function;

public class BinaryTree<T extends Comparable<T>> {

    private int size;
    private Node root;

    public int size() {
        return size;
    }

    public void add(T data) {
        size++;
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node parent = root;
        while (true) {
            int compare = data.compareTo(parent.data);
            if (compare <= 0 && parent.lNode == null) {
                parent.lNode = new Node(data);
                return;
            } else if (compare <= 0) {
                parent = parent.lNode;
            } else if (parent.rNode == null) {
                parent.rNode = new Node(data);
                return;
            } else {
                parent = parent.rNode;
            }
        }
    }

    public void add(T... data) {
        for (T item : data) add(item);
    }


    /**
     * Removes data by moving child nodes up to the deleted node's parent.
     * This preserves any references that are pointing to the moved nodes,
     * but ugh.
     *
     * @param data
     * @return
     */
    public T remove(T data) {
        Node parent = null;
        Node runner = root;
        boolean isRightKid = false;
        while (runner != null) {
            int compare = data.compareTo(runner.data);
            if (compare == 0) {
                if (runner.lNode != null && runner.rNode != null) {
                    removeKidWithBothChildren(runner, parent, isRightKid);
                } else if (runner.lNode != null) {
                    removeKidWithLeftChild(runner, parent, isRightKid);
                } else if (runner.rNode != null) {
                    removeKidWithRightChild(runner, parent, isRightKid);
                } else {
                    removeKidWithNoChildren(parent, isRightKid);
                }

                size--;
                return runner.data;
            } else if (compare < 0) {
                isRightKid = false;
                parent = runner;
                runner = runner.lNode;
            } else {
                isRightKid = true;
                parent = runner;
                runner = runner.rNode;
            }
        }

        return null;
    }


    private void removeKidWithNoChildren(Node parent, boolean isRightKid) {
//        System.out.println("removeKidWithNoChildren.  parent=" + parent + " isRightKid="+isRightKid);

        if (parent == null) {
            root = null;
        } else if (isRightKid) {
            parent.rNode = null;
        } else {
            parent.lNode = null;
        }
    }


    private void removeKidWithLeftChild(Node toDelete, Node parent, boolean isRightKid) {
//        System.out.println("removeKidWithLeftChild.  toDelete="+ toDelete + " parent=" + parent + " isRightKid="+isRightKid);
        if (parent == null) {
            root = toDelete.lNode;
        } else if (isRightKid) {
            parent.rNode = toDelete.lNode;
        } else {
            parent.lNode = toDelete.lNode;
        }
    }

    private void removeKidWithRightChild(Node toDelete, Node parent, boolean isRightKid) {
//        System.out.println("removeKidWithRightChild.  toDelete="+ toDelete + " parent=" + parent + " isRightKid="+isRightKid);
        if (parent == null) {
            root = toDelete.rNode;
        } else if (isRightKid) {
            parent.rNode = toDelete.rNode;
        } else {
            parent.lNode = toDelete.rNode;
        }
    }

    private void removeKidWithBothChildren(Node toDelete, Node parent, boolean isRightKid) {
//        System.out.println("removeKidWithBothChildren.  toDelete="+ toDelete.data + " parent=" + parent + " isRightKid="+isRightKid);
        Node biggestParent = toDelete;
        Node biggestInLeft = toDelete.lNode;
        while (biggestInLeft.rNode != null) {
            biggestParent = biggestInLeft;
            biggestInLeft = biggestInLeft.rNode;
        }

//        System.out.println("biggestInLeft="+ biggestInLeft.data + " biggestParent=" + biggestParent.data);
        // detach the replacement element from its parent
        if (biggestParent == toDelete) {
            biggestParent.lNode = biggestParent.lNode.lNode;
        } else {
            biggestParent.rNode = null;
        }

        // now copy the value into the node to delete
        // which is much easier than moving pointers
        toDelete.data = biggestInLeft.data;
    }


    /**
     * This is a bit tricky -
     *
     * We want to run down the left tree as much as possible
     * to find the first node to visit.  Once that is done,
     * we visit the right node (if any) and go to it's left-
     * most child.  If there isn't a right child, then we
     * pop off whatever is on the stack.  This would have
     * been the parent of the left most child.  So we visit
     * that and then go right if any and repeat the algo.
     *
     * @param visitor
     * @param <R>
     * @return
     */
    public <R> List<R> visitInOrder(Function<T, R> visitor) {

        List<R> results = new ArrayList<R>();
        ArrayDeque<Node> toVisit = new ArrayDeque<>();

        Node visiting = root;
        while (visiting != null || !toVisit.isEmpty()) {

            // traverse lNode until we can go no further
            while (visiting != null) {
                toVisit.push(visiting);
                visiting = visiting.lNode;
            }

            // visit the next item (lNode, parent, or rNode)
            visiting = toVisit.pop();
            results.add(visitor.apply(visiting.data));

            // now traverse rNode
            visiting = visiting.rNode;
        }

        return results;
    }


    /**
     * This one is easy - we just visit the parent and then push
     * the children onto the stack (right then left since we'll
     * pop them back off in the reverse order)
     *
     * @param visitor
     * @param <R>
     * @return
     */
    public <R> List<R> visitPreOrder(Function<T, R> visitor) {

        List<R> results = new ArrayList<R>();
        ArrayDeque<Node> toVisit = new ArrayDeque<>();

        if (root != null) toVisit.push(root);
        while (!toVisit.isEmpty()) {

            Node visiting = toVisit.pop();
            results.add(visitor.apply(visiting.data));

            if (visiting.rNode != null) {
                toVisit.push(visiting.rNode);
            }

            if (visiting.lNode != null) {
                toVisit.push(visiting.lNode);
            }
        }

        return results;
    }


    /**
     * Really cool 2 stack solution!
     *
     * @param visitor
     * @param <R>
     * @return
     */
    public <R> List<R> visitPostOrder(Function<T, R> visitor) {

        List<R> results = new ArrayList<R>();
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();

        if (root != null) stack1.push(root);

        while (!stack1.isEmpty()) {
            Node visiting = stack1.pop();
            stack2.push(visiting);

            if (visiting.lNode != null) stack1.push(visiting.lNode);
            if (visiting.rNode != null) stack1.push(visiting.rNode);
        }

        while (!stack2.isEmpty()) {
            Node visiting = stack2.pop();
            results.add(visitor.apply(visiting.data));
        }

        return results;
    }


    /**
     * Adapts the in-order traversal to run across next calls.
     *
     * @return
     */
    public Iterator<T> iterator() {

        final Stack<Node> stack = new LinkedStack<Node>();
        final Object[] nextRoot = new Object[1];
        nextRoot[0] = root;


        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return nextRoot[0] != null || stack.size() > 0;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    // Traverse down to the lNode most child
                    // to find the next item.
                    Node visiting = (Node)nextRoot[0];
                    while (visiting != null) {
                        stack.push(visiting);
                        visiting = visiting.lNode;
                    }

                    // This is either the item we just saved (above)
                    // or previously saved when rNode is null (below)
                    Node next = stack.pop();

                    // Save the rNode child, if any, otherwise,
                    // next time will traverse up to the next
                    // saved lNode item.
                    nextRoot[0] = next.rNode;
                    return next.data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }


    class Node {
        Node lNode;
        Node rNode;
        T data;

        Node(T data) {
            this.data = data;
        }
    }
}
