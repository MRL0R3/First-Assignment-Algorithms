public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        // Initialize indices to -1 (not found)
        int firstI = -1, secondI = -1;

        // Brute-force search through all possible pairs
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                // Skip same-index pairs and check product
                if (i != j && values[i] * values[j] == target) {
                    firstI = i;
                    secondI = j;
                    break; // Exit inner loop once a pair is found
                }
            }
            // Exit outer loop early if a pair is found
            if (firstI != -1) {
                break;
            }
        }

        // Return indices in ascending order
        if (firstI > secondI) {
            return new int[]{secondI, firstI};
        } else if (firstI < secondI) {
            return new int[]{firstI, secondI};
        }

        // Return null if no valid pair found
        return null;
    }


    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] spiralTraversal = new int[rows * cols];
        // Index for the result array
        int index = 0;

        // making borders for changing the path
        int topRow = 0, bottomRow = rows - 1;
        int leftCol = 0, rightCol = cols - 1;


        while (topRow <= bottomRow && leftCol <= rightCol) {
            // Traverse from left to right comes the top row
            for (int i = leftCol; i <= rightCol; i++) {
                spiralTraversal[index++] = values[topRow][i];
            }
            // Move the top boundary down
            topRow++;

            // Traverse from top to bottom comes the right column
            for (int i = topRow; i <= bottomRow; i++) {
                spiralTraversal[index++] = values[i][rightCol];
            }
            // Move the right boundary left
            rightCol--;

            // If there is still a bottom row to traverse
            if (topRow <= bottomRow) {
                // Traverse from right to left along the bottom row
                for (int i = rightCol; i >= leftCol; i--) {
                    spiralTraversal[index++] = values[bottomRow][i];
                }
                bottomRow--; // Move the bottom boundary up
            }

            // If there is still a left column to traverse
            if (leftCol <= rightCol) {
                // Traverse from bottom to top comes the left column
                for (int i = bottomRow; i >= topRow; i--) {
                    spiralTraversal[index++] = values[i][leftCol];
                }
                // Move the left boundary right
                leftCol++;
            }
        }
        if(topRow > bottomRow) {
            return spiralTraversal;}
        return null;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        // Calculate the total number of partitions for 'n'
        int maxPartitions = countPartitions(n, n);
        // Initialize the result array to hold all partitions
        int[][] result = new int[maxPartitions][];

        // Temporary array to store a single partition during construction
        int[] partition = new int[n];
        // Start the recursive partitioning process
        storePartitions(n, n, partition, 0, result, new int[]{0});
        return result;
    }

    static void storePartitions(int n, int max, int[] partition, int partitionSize, int[][] result, int[] index) {
        // Base case: if nothing left to partition, store the current partition
        if (n == 0) {
            // Copy the valid partition into the result array
            result[index[0]] = new int[partitionSize];
            System.arraycopy(partition, 0, result[index[0]], 0, partitionSize);
            index[0]++; // Move to the next position in the result array
            return;
        }

        // Recursive case: try all possible values <= min(max, n)
        for (int i = Math.min(max, n); i >= 1; i--) {
            // Add current value to the partition
            partition[partitionSize] = i;
            // Recurse with reduced 'n' and updated constraints
            storePartitions(n - i, i, partition, partitionSize + 1, result, index);
        }
    }

    static int countPartitions(int n, int max) {
        // Base case: one valid partition (empty) when n=0
        if (n == 0) return 1;

        int count = 0;
        // Sum counts for all possible partition paths
        for (int i = Math.min(max, n); i >= 1; i--) {
            count += countPartitions(n - i, i);
        }
        return count;
    }


    public static void main(String[] args) {

    }
}
