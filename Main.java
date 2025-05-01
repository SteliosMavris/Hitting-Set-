import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void firstExperiment(int[][] subsets, int n, int m, int c, int k) {
        double[][] averages = new double[4][30];
        boolean alg1ReachedPeak = false, alg2ReachedPeak = false, alg3ReachedPeak = false,
                alg4ReachedPeak = false;
        while (k - 1 < 30 && (!alg1ReachedPeak || !alg2ReachedPeak || !alg3ReachedPeak || !alg4ReachedPeak)) {
            int[] HS1 = null;
            int[] HS2 = null;
            int[] HS3 = null;
            int[] HS4 = null;
            double totalTime1 = 0, alg1Average = 0;
            double totalTime2 = 0, alg2Average = 0;
            double totalTime3 = 0, alg3Average = 0;
            double totalTime4 = 0, alg4Average = 0;
            for (int i = 0; i < 3 && !alg1ReachedPeak; i++) {
                int[][] arr1 = cloneArray(subsets);
                double alg1StartTime = System.nanoTime();
                HS1 = alg1(arr1, n, m, c, k, alg1StartTime);
                double alg1EndTime = System.nanoTime();
                if (((alg1EndTime - alg1StartTime) / 1000000.0) >= 3600000) {
                    alg1ReachedPeak = true;
                    System.out.println("Alg 1 reached peak at k = " + k);
                } else
                    totalTime1 += ((alg1EndTime - alg1StartTime) / 1000000.0);
            }
            if (!alg1ReachedPeak)
                alg1Average = totalTime1 / 3.0;
            averages[0][k - 1] = alg1Average;
            for (int i = 0; i < 3 && !alg2ReachedPeak; i++) {
                int[][] arr2 = cloneArray(subsets);
                double alg2StartTime = System.nanoTime();
                HS2 = alg2(arr2, n, m, c, k, alg2StartTime);
                double alg2EndTime = System.nanoTime();
                if (((alg2EndTime - alg2StartTime) / 1000000.0) >= 3600000) {
                    alg2ReachedPeak = true;
                    System.out.println("Alg 2 reached peak at k = " + k);
                } else
                    totalTime2 += ((alg2EndTime - alg2StartTime) / 1000000.0);
            }
            if (!alg2ReachedPeak)
                alg2Average = totalTime2 / 3.0;
            averages[1][k - 1] = alg2Average;
            for (int i = 0; i < 3 && !alg3ReachedPeak; i++) {
                int[][] arr3 = cloneArray(subsets);
                double alg3StartTime = System.nanoTime();
                HS3 = alg3(arr3, n, m, c, k, alg3StartTime);
                double alg3EndTime = System.nanoTime();
                if (((alg3EndTime - alg3StartTime) / 1000000.0) >= 3600000) {
                    alg3ReachedPeak = true;
                    System.out.println("Alg 3 reached peak at k = " + k);
                } else
                    totalTime3 += ((alg3EndTime - alg3StartTime) / 1000000.0);
            }
            if (!alg3ReachedPeak)
                alg3Average = totalTime3 / 3.0;
            averages[2][k - 1] = alg3Average;
            for (int i = 0; i < 3 && !alg4ReachedPeak; i++) {
                int[][] arr4 = cloneArray(subsets);
                double alg4StartTime = System.nanoTime();
                HS4 = alg4(arr4, n, m, c, k, alg4StartTime);
                double alg4EndTime = System.nanoTime();
                if (((alg4EndTime - alg4StartTime) / 1000000.0) >= 3600000) {
                    alg4ReachedPeak = true;
                    System.out.println("Alg 4 reached peak at k = " + k);
                } else
                    totalTime4 += ((alg4EndTime - alg4StartTime) / 1000000.0);
            }
            if (!alg4ReachedPeak)
                alg4Average = totalTime4 / 3.0;
            averages[3][k - 1] = alg4Average;
            presentResults(HS1, HS2, HS3, HS4, k);
            k++;
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Alg " + (i + 1) + ":");
            for (int j = 0; j < k - 1; j++) {
                System.out.print(averages[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void secondExperiment(int[][] subsets, int n, int m, int c, int k) {
        double[][] times = new double[4][10];
        int[] HS1, HS2, HS3, HS4;
        double start = 0, finish = 0;
        for (int i = 0; i < 10; i++) {
            int[][] arr1 = cloneArray(subsets);
            start = System.nanoTime();
            HS1 = alg1(arr1, n, m, c, k, start);
            finish = System.nanoTime();
            times[0][i] = (finish - start) / 1000000.0;

            int[][] arr2 = cloneArray(subsets);
            start = System.nanoTime();
            HS2 = alg2(arr2, n, m, c, k, start);
            finish = System.nanoTime();
            times[1][i] = (finish - start) / 1000000.0;

            int[][] arr3 = cloneArray(subsets);
            start = System.nanoTime();
            HS3 = alg3(arr3, n, m, c, k, start);
            finish = System.nanoTime();
            times[2][i] = (finish - start) / 1000000.0;

            int[][] arr4 = cloneArray(subsets);
            start = System.nanoTime();
            HS4 = alg4(arr4, n, m, c, k, start);
            finish = System.nanoTime();
            times[3][i] = (finish - start) / 1000000.0;
            presentResults(HS1, HS2, HS3, HS4, k);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Alg " + (i + 1) + ":");
            for (int j = 0; j < 10; j++) {
                System.out.print(times[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void presentResults(int[] HS1, int[] HS2, int[] HS3, int[] HS4, int k) {
        System.out.println("For k = " + k);
        System.out.println("------------Algorithm 1------------");
        if (HS1 == null) {
            System.out.println("No hitting set of size <= " + k);
        } else {
            for (int i = 0; i < HS1.length; i++) {
                System.out.print(HS1[i] + " ");
            }
            System.out.println();
        }
        System.out.println("------------Algorithm 2------------");
        if (HS2 == null) {
            System.out.println("No hitting set of size <= " + k);
        } else {
            for (int i = 0; i < HS2.length; i++) {
                System.out.print(HS2[i] + " ");
            }
            System.out.println();
        }
        System.out.println("------------Algorithm 3------------");
        if (HS3 == null) {
            System.out.println("No hitting set of size <= " + k);
        } else {
            for (int i = 0; i < HS3.length; i++) {
                System.out.print(HS3[i] + " ");
            }
            System.out.println();
        }
        System.out.println("------------Algorithm 4------------");
        if (HS4 == null) {
            System.out.println("No hitting set of size <= " + k);
        } else {
            for (int i = 0; i < HS4.length; i++) {
                System.out.print(HS4[i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void fileSubsetsReader(Scanner scan, int[][] subsets, int m, int c) {
        for (int i = 0; i < m; i++) {
            // create tokeniser that converts current line's numbers to tokens
            String subset = scan.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(subset, " ");
            // calculate current subset's size
            int plithos = tokenizer.countTokens();
            if (plithos > c) {
                System.out.println("Cannot have more than " + c + " elements in a subset!");
                return;
            }
            // fill in current subset with its elements by first converting elements to
            // integers
            for (int j = 0; j < plithos; j++) {
                subsets[i][j] = Integer.parseInt(tokenizer.nextToken());
            }

        }
    }

    public static void fileSubsetsWriter(File outFile, int[][] subsets, int m, int c) {
        try {
            FileWriter myWriter = new FileWriter(outFile);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < c && subsets[i][j] != 0; j++)
                    myWriter.write(subsets[i][j] + " ");
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file!");
            System.exit(1);
        }
    }

    public static void subsetGenerator(int[][] subsets, int n, int m, int c, int k, boolean guaranteeSuccess) {
        for (int i = 0; i < m; i++) {
            int subsetLength = (int) (Math.random() * c) + 1;
            int[] shuffled = new int[n];
            if (guaranteeSuccess == true) {
                int guaranteedElement;
                boolean guaranteedElementPlaced = false;
                if (i >= 0 && i <= 19)
                    guaranteedElement = 1;
                else if (i >= 21 && i <= 39)
                    guaranteedElement = 2;
                else if (i >= 40 && i <= 59)
                    guaranteedElement = 3;
                else if (i >= 60 && i <= 79)
                    guaranteedElement = 4;
                else if (i >= 80 && i <= 99)
                    guaranteedElement = 5;
                else if (i >= 100 && i < 119)
                    guaranteedElement = 6;
                else if (i >= 120 && i <= 139)
                    guaranteedElement = 7;
                else
                    guaranteedElement = 8;
                for (int j = 0; j < n; j++)
                    shuffled[j] = j + 1;
                shuffleSubset(shuffled, n);
                for (int j = 0; j < subsetLength + 1; j++) {
                    if (j == subsetLength) {
                        if (!guaranteedElementPlaced)
                            subsets[i][j - 1] = guaranteedElement;
                        continue;
                    }
                    subsets[i][j] = shuffled[j];
                    if (shuffled[j] == guaranteedElement)
                        guaranteedElementPlaced = true;
                }
            } else {
                for (int j = 0; j < n; j++)
                    shuffled[j] = j + 1;
                shuffleSubset(shuffled, n);
                for (int j = 0; j < subsetLength; j++) {
                    subsets[i][j] = shuffled[j];
                }
            }
        }
    }

    public static int[][] cloneArray(int[][] arr) {
        int[][] clone = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length && arr[i][j] != 0; j++) {
                clone[i][j] = arr[i][j];
            }
        }
        return clone;
    }

    public static int[] trimmedHS(int[] HS, int k) {
        int finalHittingSetSize = 0;
        for (int i = k - 1; i >= 0 && HS[i] != 0; i--)
            finalHittingSetSize++;
        int[] retHS = new int[finalHittingSetSize];

        for (int i = 0; i < finalHittingSetSize; i++)
            retHS[i] = HS[k - finalHittingSetSize + i];
        return retHS;
    }

    public static int[] calcCriticality(int[][] arr, int[] subset, int selectedSubsetSize) {
        int[] criticality = new int[selectedSubsetSize];
        for (int i = 0; i < selectedSubsetSize; i++) {
            int selectedElement = subset[i];
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr[j].length && arr[j][k] != 0; k++) {
                    if (arr[j][k] == selectedElement)
                        criticality[i]++;
                }
            }
        }
        return criticality;
    }

    public static int[] calcAllSubsetSizes(int[][] arr) {

        int[] sizes = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length && arr[i][j] != 0; j++) {
                sizes[i]++;
            }
        }
        return sizes;
    }

    public static void bubblesort(int[][] arr, int[] subset, int[] decisionFactor, int selectedSubsetSize,
            String factor) {

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            switch (factor) {
                case "criticality":
                    for (int i = 0; i < selectedSubsetSize - 1; i++) {
                        // sort in decreasing order
                        if (decisionFactor[i] < decisionFactor[i + 1]) {
                            // sort criticality of elements
                            int tmpFactor = decisionFactor[i];
                            decisionFactor[i] = decisionFactor[i + 1];
                            decisionFactor[i + 1] = tmpFactor;
                            // sort elements in selected subset
                            int tmpSubset = subset[i];
                            subset[i] = subset[i + 1];
                            subset[i + 1] = tmpSubset;
                            sorted = false;
                        }
                    }
                    break;

                case "size":
                    for (int i = 0; i < arr.length - 1; i++) {
                        // sort in increasing order
                        if (decisionFactor[i] > decisionFactor[i + 1]) {
                            // sort sizes of subsets
                            int tmpFactor = decisionFactor[i];
                            decisionFactor[i] = decisionFactor[i + 1];
                            decisionFactor[i + 1] = tmpFactor;
                            // sort subsets
                            int[] tmpSubset = arr[i];
                            arr[i] = arr[i + 1];
                            arr[i + 1] = tmpSubset;
                            sorted = false;
                        }
                    }
                    break;
            }
        }
    }

    public static void shuffleSubset(int[] subset, int selectedSubsetSize) {
        for (int i = selectedSubsetSize - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = subset[i];
            subset[i] = subset[j];
            subset[j] = tmp;
        }
    }

    public static int[][] reduceProblem(int[][] arr, int selectedElement, int m, int c) {
        // temporary 2D array to store only the subsets that don't contain the selected
        // element
        int[][] tmp = new int[m][c];
        int newSubsetPosition = 0, removedSubsets = 0;
        for (int i = 0; i < m; i++) {
            int currentElementPos = 0;
            boolean keepSubset = true;
            // if the subset is empty, remove it by default
            if (arr[i][0] == 0) {
                keepSubset = false;
                removedSubsets++;
            }
            while (currentElementPos < c && arr[i][currentElementPos] != 0) {
                if (arr[i][currentElementPos] == selectedElement) {
                    // mark the first subset to not copy to reduced problem
                    keepSubset = false;
                    removedSubsets++;
                    break;
                }
                currentElementPos++;
            }
            if (keepSubset) {
                for (int j = 0; j < arr[i].length; j++)
                    tmp[newSubsetPosition][j] = arr[i][j];
                newSubsetPosition++;
            }
        }
        // return null if all subsets have been deleted
        if (m - removedSubsets == 0)
            return null;
        // create new 2D array with reduced size and copy contents of tmp
        int[][] ret = new int[m - removedSubsets][c];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < c; j++)
                ret[i][j] = tmp[i][j];
        }
        return ret;
    }

    // random subset and random element
    public static int[] alg1(int[][] arr, int n, int m, int c, int k, double startTime) {
        // if an hour has passed, time limit has passed and assume failure
        if (((System.nanoTime() - startTime) / 1000000.0) >= 3600000) {
            return null;
        }
        int selectedSubset, selectedElement, currentElementPos = 0, selectedSubsetSize = 0;
        int[] HS = new int[k], smallerHS = null;
        // failed to find hitting set of size <=k
        if (k == 0 && arr.length > 0)
            return null;
        // choose random subset
        selectedSubset = (int) (Math.random() * arr.length);
        // calculate range of elements of selected subset
        while (currentElementPos < c && arr[selectedSubset][currentElementPos++] != 0)
            selectedSubsetSize++;
        shuffleSubset(arr[selectedSubset], selectedSubsetSize);
        for (int i = 0; i < selectedSubsetSize; i++) {
            selectedElement = arr[selectedSubset][i];
            // remove all subsets containing the selected element
            int[][] reducedSubsets = reduceProblem(arr, selectedElement, arr.length, c);
            // if by the removal of the selected element all subsets get removed, add it to
            // the HS and return
            if (reducedSubsets == null) {
                HS[k - 1] = selectedElement;
                return trimmedHS(HS, k);
            }
            smallerHS = alg1(reducedSubsets, n, reducedSubsets.length, c, k - 1, startTime);
            // if current element failed to find a HS of a smaller size continue to next
            // element
            if (smallerHS == null)
                continue;
            // else add the element to the current sub problem's HS
            HS[k - 1] = selectedElement;
            // add the solution of the previous recursion to the current one
            for (int pos = k - 2, smallerPos = smallerHS.length - 1; pos >= 0 && smallerPos >= 0; pos--, smallerPos--) {
                HS[pos] = smallerHS[smallerPos];
            }
            break;
        }
        // if all elements have failed return a null HS
        if (smallerHS == null)
            return null;
        // return the HS with the exact size to fit the specific solution
        return trimmedHS(HS, k);
    }

    // random subset and most critical element
    public static int[] alg2(int[][] arr, int n, int m, int c, int k, double startTime) {
        // if an hour has passed, time limit has passed and assume failure
        if (((System.nanoTime() - startTime) / 1000000.0) >= 3600000) {
            return null;
        }
        int selectedSubset, selectedElement, currentElementPos = 0, selectedSubsetSize = 0;
        int[] HS = new int[k], smallerHS = null, critical = null;
        // failed to find hitting set of size <=k
        if (k == 0 && arr.length > 0)
            return null;
        // choose random subset
        selectedSubset = (int) (Math.random() * arr.length);
        // calculate range of elements of selected subset
        while (currentElementPos < c && arr[selectedSubset][currentElementPos++] != 0)
            selectedSubsetSize++;
        // find each element's criticality and then sort them in decreasing order
        critical = calcCriticality(arr, arr[selectedSubset], selectedSubsetSize);
        bubblesort(null, arr[selectedSubset], critical, selectedSubsetSize, "criticality");
        // iterate through all the elements of the selected subset from most critical to
        // least critical
        for (int i = 0; i < selectedSubsetSize; i++) {
            selectedElement = arr[selectedSubset][i];
            // remove all subsets containing the selected element
            int[][] reducedSubsets = reduceProblem(arr, selectedElement, arr.length, c);
            // if by the removal of the selected element all subsets get removed, add it to
            // the HS and return
            if (reducedSubsets == null) {
                HS[k - 1] = selectedElement;
                return trimmedHS(HS, k);
            }
            smallerHS = alg2(reducedSubsets, n, reducedSubsets.length, c, k - 1, startTime);
            // if current element failed to find a HS of a smaller size continue to next
            // element
            if (smallerHS == null)
                continue;
            // else add the element to the current sub problem's HS
            HS[k - 1] = selectedElement;
            // add the solution of the previous recursion to the current one
            for (int pos = k - 2, smallerPos = smallerHS.length - 1; pos >= 0 && smallerPos >= 0; pos--, smallerPos--) {
                HS[pos] = smallerHS[smallerPos];
            }
            break;
        }
        // if all elements have failed return a null HS
        if (smallerHS == null)
            return null;
        // return the HS with the exact size to fit the specific solution
        return trimmedHS(HS, k);

    }

    // smallest subset and random element
    public static int[] alg3(int[][] arr, int n, int m, int c, int k, double startTime) {
        // if an hour has passed, time limit has passed and assume failure
        if (((System.nanoTime() - startTime) / 1000000.0) >= 3600000) {
            return null;
        }
        int selectedSubset, selectedElement;
        int[] HS = new int[k], smallerHS = null;
        // failed to find hitting set of size <=k
        if (k == 0 && arr.length > 0)
            return null;
        // store sizes of all subsets and sort them in increasing order
        int[] sizes = calcAllSubsetSizes(arr);
        bubblesort(arr, null, sizes, -1, "size");
        // will always pick the first subset because the first will always be the
        // smallest
        selectedSubset = 0;
        shuffleSubset(arr[selectedSubset], sizes[selectedSubset]);
        for (int i = 0; i < sizes[selectedSubset]; i++) {
            selectedElement = arr[selectedSubset][i];
            // remove all subsets containing the selected element
            int[][] reducedSubsets = reduceProblem(arr, selectedElement, arr.length, c);
            // if by the removal of the selected element all subsets get removed, add it to
            // the HS and return
            if (reducedSubsets == null) {
                HS[k - 1] = selectedElement;
                return trimmedHS(HS, k);
            }
            smallerHS = alg3(reducedSubsets, n, reducedSubsets.length, c, k - 1, startTime);
            // if current element failed to find a HS of a smaller size continue to next
            // element
            if (smallerHS == null)
                continue;
            // else add the element to the current sub problem's HS
            HS[k - 1] = selectedElement;
            // add the solution of the previous recursion to the current one
            for (int pos = k - 2, smallerPos = smallerHS.length - 1; pos >= 0
                    && smallerPos >= 0; pos--, smallerPos--) {
                HS[pos] = smallerHS[smallerPos];
            }
            break;
        }
        // if all elements have failed return a null HS
        if (smallerHS == null)
            return null;
        // return the HS with the exact size to fit the specific solution
        return trimmedHS(HS, k);
    }

    // smallest subset and most critical element
    public static int[] alg4(int[][] arr, int n, int m, int c, int k, double startTime) {
        // if an hour has passed, time limit has passed and assume failure
        if (((System.nanoTime() - startTime) / 1000000.0) >= 3600000) {
            return null;
        }
        int selectedSubset, selectedElement;
        int[] HS = new int[k], smallerHS = null, critical = null;
        // failed to find hitting set of size <=k
        if (k == 0 && arr.length > 0)
            return null;
        // store sizes of all subsets and sort them in increasing order
        int[] sizes = calcAllSubsetSizes(arr);
        bubblesort(arr, null, sizes, -1, "size");
        // will always pick the first subset because the first will always be the
        // smallest
        selectedSubset = 0;
        // find each element's criticality and then sort them in decreasing order
        critical = calcCriticality(arr, arr[selectedSubset], sizes[selectedSubset]);
        bubblesort(null, arr[selectedSubset], critical, sizes[selectedSubset], "criticality");
        // iterate through all the elements of the selected subset from most critical to
        // least critical
        for (int i = 0; i < sizes[selectedSubset]; i++) {
            selectedElement = arr[selectedSubset][i];
            // remove all subsets containing the selected element
            int[][] reducedSubsets = reduceProblem(arr, selectedElement, arr.length, c);
            // if by the removal of the selected element all subsets get removed, add it to
            // the HS and return
            if (reducedSubsets == null) {
                HS[k - 1] = selectedElement;
                return trimmedHS(HS, k);
            }
            smallerHS = alg4(reducedSubsets, n, reducedSubsets.length, c, k - 1, startTime);
            // if current element failed to find a HS of a smaller size continue to next
            // element
            if (smallerHS == null)
                continue;
            // else add the element to the current sub problem's HS
            HS[k - 1] = selectedElement;
            // add the solution of the previous recursion to the current one
            for (int pos = k - 2, smallerPos = smallerHS.length - 1; pos >= 0
                    && smallerPos >= 0; pos--, smallerPos--) {
                HS[pos] = smallerHS[smallerPos];
            }
            break;
        }
        // if all elements have failed return a null HS
        if (smallerHS == null)
            return null;
        // return the HS with the exact size to fit the specific solution
        return trimmedHS(HS, k);
    }

    public static void main(String[] args) {
        int n = 0, m = 0, c = 0, k = 0;
        Scanner scan = null;
        int[][] subsets = null;
        // flag = 0 -> correctness test, flag = 1 -> experimental evaluation
        int flag = 0;
        File inFile = new File("sets.dat");
        File outFile = new File("stigmiotypo.dat");

        // read elements from sets.dat
        if (flag == 0) {
            // create 2D array according to values read to store each subset's elements
            try {
                scan = new Scanner(inFile);
                n = scan.nextInt();
                m = scan.nextInt();
                c = scan.nextInt();
                k = scan.nextInt();
                scan.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                return;
            }
            subsets = new int[m][c];
            fileSubsetsReader(scan, subsets, m, c);

            int[][] arr1 = cloneArray(subsets);
            int[][] arr2 = cloneArray(subsets);
            int[][] arr3 = cloneArray(subsets);
            int[][] arr4 = cloneArray(subsets);

            int[] HS1 = alg1(arr1, n, m, c, k, System.nanoTime());
            int[] HS2 = alg2(arr2, n, m, c, k, System.nanoTime());
            int[] HS3 = alg3(arr3, n, m, c, k, System.nanoTime());
            int[] HS4 = alg4(arr4, n, m, c, k, System.nanoTime());
            presentResults(HS1, HS2, HS3, HS4, k);
            scan.close();
            return;
        }

        // generate elements randomly
        if (flag == 1) {

            n = 500;
            m = 100;
            c = 100;
            boolean guaranteeSuccess = false;

            subsets = new int[m][c];
            subsetGenerator(subsets, n, m, c, k, guaranteeSuccess);
            fileSubsetsWriter(outFile, subsets, m, c);
            k = 1;
            firstExperiment(subsets, n, m, c, k);
            guaranteeSuccess = true;
            subsets = new int[m][c];
            subsetGenerator(subsets, n, m, c, k, guaranteeSuccess);
            fileSubsetsWriter(outFile, subsets, m, c);
            k = 5;
            secondExperiment(subsets, n, m, c, k);
        }
    }
}
