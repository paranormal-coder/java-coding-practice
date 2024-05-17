import java.util.LinkedList;
import java.util.Queue;

public class Btree {
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

    static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int[] nodes) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);

    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);

    }

    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        inorder(root.right);
        System.out.print(root.data + " ");

    }

    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node currNode = q.remove();
            if (currNode == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.println(currNode.data + " ");
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
        }
    }

    public static int CountofNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return (1 + CountofNodes(root.left) + CountofNodes(root.right));
    }

    public static int NodeSummation(Node root) {
        if (root == null) {
            return 0;
        }
        int leftnode = NodeSummation(root.left);
        int rightnode = NodeSummation(root.right);
        return (root.data + leftnode + rightnode);
    }

    public static int HeightofTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftheight = HeightofTree(root.left);
        int rightheight = HeightofTree(root.right);
        int height = Math.max(leftheight, rightheight) + 1;
        return height;
    }

    public static int Diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int diam1 = Diameter(root.left);
        int diam2 = Diameter(root.right);
        int diam3 = HeightofTree(root.left) + HeightofTree(root.right) + 1;
        return Math.max(diam3, Math.max(diam2, diam1));
    }

    static class TreeInfo {
        int ht;
        int diam;

        TreeInfo(int ht, int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);
        int myheight = Math.max(left.ht, right.ht) + 1;

        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;

        int mydiam = Math.max(Math.max(diam1, diam2), diam3);
        TreeInfo myinfo = new TreeInfo(myheight, mydiam);
        return myinfo;
    }

    public static void main(String args[]) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.print("Root Node \n");
        System.out.println(root.data);

        System.out.print("Preorder traversal \n");
        preorder(root);

        System.out.print("Inorder traversal \n");
        inorder(root);

        System.out.print("Postorder tranversal \n");
        postorder(root);

        System.out.println("levelOrder \n");
        levelOrder(root);

        System.out.println(" Count of Nodes \n");
        System.out.println(CountofNodes(root));

        System.out.println("Sum of NOde values in the tree \n");
        System.out.println(NodeSummation(root));

        System.out.println("Height of Tree \n");
        System.out.println(HeightofTree(root));

        System.out.println("Diameter 1 \n");
        System.out.println(Diameter(root));

        System.out.println("Diameter 2 \n");
        System.out.println(diameter2(root).diam);
    }
}