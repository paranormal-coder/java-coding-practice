public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (root.data > val) {
            // left subtree
            root.left = insert(root.left, val);
        } else if (root.data < val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data > key) {
            return search(root.left, key);
        } else if (root.data < key) {
            return search(root.right, key);
        } else if (root.data == key) {
            return true;
        }
        return false;
    }

    public static Node Delete(Node root, int val) {
        if (root == null) {
            return null;
        }
        if (root.data > val) {
            root.left = Delete(root.left, val);
        } else if (root.data < val) {
            root.right = Delete(root.right, val);
        } else if (root.data == val) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right = Delete(root.right, IS.data);
        }
        return root;

    }

    public static Node inorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void inrange(Node root, int x, int y) {
        if (root == null) {
            return;
        }
        if (root.data >= x && root.data <= y) {
            inrange(root.left, x, y);
            System.out.print(root.data + " ");
            inrange(root.right, x, y);
        } else if (root.data >= y) {
            inrange(root.left, x, y);
        } else {
            inrange(root.right, x, y);
        }
    }

    public static void main(String args[]) {
        int[] values = { 5, 1, 3, 4, 2, 7 };
        Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
            System.out.print(values[i] + " ");
        }
        System.out.println("\nInorder traversal \n");
        inorder(root);
        System.out.println();

        System.out.print("Search avalue in key \n");
        if (search(root, 2)) {
            System.out.println("Found\n");
        } else {
            System.out.println("Not found \n");
        }

        System.out.println("Deleting node 4 from the constructed tree\n");
        inorder(root);
        System.out.println();
        Delete(root, 4);
        inorder(root);

        System.out.print("\nnodes lying in range 3 to 7\n");
        inrange(root, 3, 7);
    }
}
