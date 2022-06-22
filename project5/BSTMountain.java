package project5;
import java.util.ArrayList;
/**
 * This class is a binary search tree that uses AVL tree algorithms rotations to balance.
 * It represent the mountain itself that stores RestStop class as nodes.
 * During the process of tree creation, root, left, right branches, height of the tree is accessible, and can be updated.
 * During the process of tree balancing, individual LL, RR, LR, RL methods are written to perform AVL tree algorithms rotations.
 * 
 * @author Dingxin Zhang
 *
 */
public class BSTMountain {
	/**
	 * RSNode class is a private node class inside class BSTMountain.
	 * It stores RestStop as its Nodes.
	 * It also contains BST reference that can access the Node on the left , right, and the height of the tree
	 * @param <T> 
	 * @implement Comparable<E>
	 */
	private class RSNode <T extends Comparable <RestStop>> implements Comparable<RSNode<RestStop>>
    {
        public RestStop data;
        private RSNode<RestStop> left;
        private RSNode<RestStop> right;
        private int height;
        /*
         * The default constructor of RSNode class
         */
        private RSNode()
        {
            data = new RestStop();
            height = 0;
        }
        /*
         * The initialize constructor of RSNode class
         */
        public RSNode(RestStop data)
        {
            this.data = data;
        }
        /*
         * A method that can get the height of BST
         */
        public int getHeight()
        {
            return height;
        }
        /*
         * A method that can set the height of BST
         */
        public void setHeight(int heightx)
        {
            height = heightx;
        }
        /**
         * 
         * A method that overrides the compareTo method so RSNodes can be compared accordingly.
         * @Override compareTo method in the Comparable method
         */
        @Override
        public int compareTo(RSNode<RestStop> other) 
        {
            return this.data.compareTo(other.data);
        }
    }
	/*
	 *declare a new variable as the root of BST.
	 */
    RSNode root;
    /**
     * An adding method that can initialize the node and add to the root.
     * If root is full,then using the addRSNode method to add it either to the left or right, 
     * and then checking the balance to see if the original root should be updated due to the new balance.
     * @param data , a RestStop
     */
    public void add(RestStop data)
    {
        if(root == null)
        {
            root = new RSNode(data);
            root.setHeight(0);
            return;
        }
        addRSNode(data,root);
        root = checkBalance(root);
    }
    /**
     * An adding method that can check whether a RSNode should be add to the left or right.
     * Height is always updated if a new branch is extended.
     * It uses the check balance method to see if the BST is balanced.
     * 
     * @param data, RestStop class
     * @param node, RestStop class
     */
    private void addRSNode(RestStop data, RSNode node)
    {
        if(data.compareTo(node.data)<0)
        {
            if(node.left==null)
            {
                node.left = new RSNode(data);
            }
            else 
            {
            	addRSNode(data, node.left);
                node.left = checkBalance(node.left);
            }
            updateHeight(node);
        }
        else if(data.compareTo(node.data)>0)
        {
            if(node.right==null)
            {
                node.right = new RSNode(data);
            }
            else
            {
            	addRSNode(data, node.right);
                node.right = checkBalance(node.right);
            }
            updateHeight(node);
        }
        else
        {
            return;
        }
    }
    /**
     * The method to check if a BST is balanced.
     * It compares the balance factor with numbers -2 and 2, to see if the factor is in the range -1, 0, 1
     * If the balance factor is smaller than -2 or greater than 2, then it is unbalanced, and calls the balancing methods.
     * It calls the balanceFactor method to get the balance factor of current RSNode.
     * It calls 4 ways of balancing: LL LR RR RL, depending on the situation.
     * If it is already balanced, return the current RSNode.
     * 
     * @return RSNode class type
     */
    public RSNode checkBalance(RSNode node)
    {
        if (balanceFactor(node) <= -2) 
        {
            if (balanceFactor(node.left) < 0) 
            {
                return balanceLL(node);
            } else 
            {
                return balanceLR(node);
            }
        } 
        else if (balanceFactor(node) >= 2) 
        {
            if (balanceFactor(node.right) > 0) 
            {
                return balanceRR(node);
            } else 
            {
                return balanceRL(node);
            }
        }
        else
        {
            return node;
        }
    }
    /**
     * A helper method that can get the maximum height level of the BST
     * 
     * @param root
     * @return integer of maximum height level
     */
    public int getMaxLevel(RSNode root)
    {
        if(root == null)
        {
            return 0;
        }

        int leftDepth = getMaxLevel(root.left);
        int rightDepth = getMaxLevel(root.right);

        if (leftDepth > rightDepth)
            return (leftDepth + 1);
        else
            return (rightDepth + 1);
    }
    /**
     * The method that check if a hiker is able to climb the mountain and record all possible ways.
     * 
     * @return ArrayList of all possible ways.
     */
    public ArrayList<String[]> findWay()
    {
        int maxLeVel = this.getMaxLevel(this.root);
        int curLevel = 0;
        
        if(this.root == null)
        {
            return new ArrayList<>();
        }
        //The hiker starts at the root.
        Hiker hiker = new Hiker(this.root.data);
        ArrayList<String[]> arr = new ArrayList<>();
        String[] str = new String[maxLeVel];
        
        //It calls itself from the root, with the hiker current level and information, as well as empty list and array.
        findWay(this.root, curLevel, arr, str, hiker);
        //return all possible ways of solution.
        return arr;
    }
    /**
     * 
     * @param rs, RSNode
     * @param currentLevel, integer
     * @param aRR, ArrayList<String[]>, it stores a path a hiker could conquer the mountain.
     * @param sTr, String[], it stores labels of rest stops as hiker climbing the mountain
     * @param hiker, Hiker Class
     */
    private void findWay(RSNode rs, int currentLevel, ArrayList<String[]> aRR, String[] sTr, Hiker hiker)
    {
    	//if this path is possible
        if(currentLevel == (sTr.length-1))
        {
        	
            if((hiker.getAxe()<0)||(hiker.getRaft()<0))
            {
                return;
            }
            //Store the current path
            sTr[currentLevel] = (rs.data.getLabel());
            String[] x = new String[sTr.length];
            for (int i=0; i<x.length; i++)
            {
                x[i] = sTr[i];
            }

            aRR.add(x);
            return;
        }
        //If the hiker has no more food or an axe or raft is needed on the way.
        if ((hiker.getFood()==0)||(hiker.getAxe()<0)||(hiker.getRaft()<0))
        {
            return;
        }
        //If the hiker reaches to the end of a tree.
        if((rs.left == null)&&(rs.right == null))
        {
            return;
        }
        //when the hiker consumes 1 unit of food as moving forward, current level +1
        sTr[currentLevel] = (rs.data.getLabel());
        hiker.setFood(hiker.getFood()-1);
        currentLevel++;
        
        // if the rest stop has something on its left, the hiker moves left. Clear the path if it is already "rested on the left"
        if(rs.left!=null)
        {
        	hiker.restAt(rs.left.data);
            findWay(rs.left,currentLevel,aRR,sTr,hiker);
            hiker.noRestAt(rs.left.data);
        }
        // when all left searching is done.
        // check if the rest stop has something on its right, the hiker moves tight. Clear the path if it is already "rested on the right"
        if(rs.right!=null)
        {
        	hiker.restAt(rs.right.data);
            findWay(rs.right,currentLevel,aRR,sTr,hiker);
            hiker.noRestAt(rs.right.data);
        }
        //when the path is not working out, rest the path storage, food, and current level.
        sTr[currentLevel] = "";
        hiker.setFood(hiker.getFood()+1);
        currentLevel--;

        return;
    }
    /**
     * A helper method that can get the balance factor of a certain node by subtracting its left and its right nodes.
     * If both its left and right is null, the balance factor is 0
     * 
     * @param node, RSNode
     * @return integer, balance factor of the current node 
     */
    public int balanceFactor (RSNode node)
    {
        if(node == null){
            return 0;
        }
        if (node.right == null) 
        {
            if(node.left==null)
            {
                return 0;
            }
            else{
                return -node.left.height-1;
            }
        }
        if (node.left == null) {
            return node.right.height+1;
        }
        return node.right.height - node.left.height;
    }
    /**
     * A helper method of AVL balancing methods that update the current height of current node after balancing.
     * @param node, RSNode
     */
    public void updateHeight (RSNode node) 
    {
        if((node.left == null) && (node.right == null))
        {
            node.height = 0;
        } 
        else if (node.left == null) 
        {
            node.height = node.right.height + 1;
        } 
        else if (node.right == null) 
        {
            node.height = node.left.height + 1;
        } 
        else
        {
            node.height = Math.max(node.right.height, node.left.height) + 1;
        }
    }
    /**
     * The LL rotation of AVL. Re-balance Node A and B and update changed height.
     * @param A, RSNode
     * @return B, RSNode
     */
    public RSNode balanceLL (RSNode A)
    {
        RSNode B = A.left;
        A.left = B.right;
        B.right = A;
        updateHeight (A);
        updateHeight (B);
        return B;
    }
    /**
     *  The RR rotation of AVL. Re-balance Node A and B and update changed height.
     * @param A, RSNode
     * @return B, RSNode
     */
    public RSNode balanceRR (RSNode A)
    {
        RSNode B = A.right;
        A.right = B.left;
        B.left = A;
        updateHeight (A);
        updateHeight (B);
        return B;
    }
    /**
     * The LR rotation of AVL. Re-balance Node A and B and C and update changed height.
     * @param A, RSNode
     * @return C, RSNode
     */
    public RSNode balanceLR (RSNode A)
    {
        RSNode B = A.left;
        RSNode C = B.right;
        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;
        updateHeight (A);
        updateHeight (B);
        updateHeight (C);
        return C;
    }
    /**
     *  The RL rotation of AVL. Re-balance Node A and B and C and update changed height.
     * @param A, RSNode
     * @return C, RSNode
     */
    public RSNode balanceRL (RSNode A)
    {
        RSNode B = A.right;
        RSNode C = B.left;
        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;
        updateHeight (A);
        updateHeight (B);
        updateHeight (C);
        return C;
    }

    
}
