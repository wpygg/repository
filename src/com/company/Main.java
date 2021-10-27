package com.company;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int[] a = new int[]{3,9,20,15,7};
        int[] b = new int[]{9,3,15,20,7};

        main.buildTree(a,b);
    }


    static int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root,0);
        return max;
    }
    public void getMax(TreeNode root,int value){
        if (root == null){
            if (value > max){
                max = value;
            }
        }
        getMax(root.left,value + root.val);
        getMax(root.right,value + root.val);
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map= new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            for (int j = 0; j < preorder.length; j++) {
                if (preorder[j]== inorder[i]){
                    map.put(i,j);
                    break;
                }
            }
        }
        return getNode(preorder,inorder,0,inorder.length-1,map);
    }

    public TreeNode getNode(int[] preorder, int[] inorder, int left, int right, Map<Integer,Integer> map){
        if (left > right){
            return null;
        }
        int index = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            Integer integer = map.get(i);
            index =Math.min(index,integer);
        }
        int value = preorder[index];
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == value){
                index = i;
                break;
            }
        }
        TreeNode node = new TreeNode(inorder[index]);
        node.left = getNode(preorder,inorder,left,index-1,map);
        node.right = getNode(preorder,inorder,index+1,right,map);
        return node;
    }

    public int maxDepth(TreeNode root) {
        return getMaxDepth(root,0);
    }

    public int getMaxDepth(TreeNode root,int depth){
        if (root == null){
            return depth;
        }
        depth++;
        return  Math.max(getMaxDepth(root.right,depth),getMaxDepth(root.right,depth));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> floors = new ArrayList<>();
        bianli(root,floors);
        return floors;
    }

    public void bianli(TreeNode root,List<List<Integer>> floor){
        ArrayDeque<TreeNode> deque = new ArrayDeque();
        deque.add(root);
        int index = 1;
        List<Integer> list = new ArrayList<>();
        while(index != 0){
            TreeNode treeNode = deque.peekFirst();
            list.add(root.val);
            index--;
            if (treeNode.left != null){
                deque.addLast(treeNode.left);
            }
            if (treeNode.right != null){
                deque.addLast(treeNode.right);
            }
            if (index == 0){
                floor.add(list);
                list = new ArrayList<>();
                index = deque.size();
            }
        }
    }


    public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }

    public boolean check(TreeNode r1, TreeNode r2){
        if (r1 == null && r2 == null){
            return true;
        }
        if (r1 == null || r2 == null){
            return false;
        }
        return r1.val == r2.val && check(r1.left,r2.right) && check(r1.right,r2.left);
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return false;
        }
        return isValidBST(root,root.val);
    }

    public boolean isValidBST(TreeNode root,int value) {
        if (root.left != null){
            if (root.left.val >= root.val || root.left.val >= value){
                return false;
            }
            if (!isValidBST(root.left)){
                return false;
            }
        }
        if (root.right != null){
            if (root.right.val <= root.val || root.right.val <= value){
                return false;
            }
            if (!isValidBST(root.right)){
                return false;
            }
        }
        return true;
    }

}
