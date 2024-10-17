import java.io.*;

public class Quicksort {
    // These variables track the number of comparisons and exchanges made during sorting.
    private static int comparisons = 0;
    private static int exchanges = 0;

    // The main method where the program starts execution.
    public static void main(String[] args) {
        // Check if the input filename is provided as a command-line argument.
        if (args.length == 0) {
            System.out.println("Please provide the input filename.");
            return;
        }

        // Set the input and output filenames based on the command-line arguments.
        String inputFilename = args[0];
        String outputFilename = args.length > 1 ? args[1] : "output.txt";

        // Load the data from the input file into an array.
        int[] data = loadData(inputFilename);
        
        // If no valid data was found, print a message and exit.
        if (data == null || data.length == 0) {
            System.out.println("No valid data found in the file.");
            return;
        }

        // Perform the Quicksort algorithm using the first element as the pivot.
        quicksortFirstPivot(data, 0, data.length - 1);
        
        // Save the sorted data to the output file.
        saveData(outputFilename, data);

        // Print the number of comparisons and exchanges made during sorting.
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Exchanges: " + exchanges);

        // For files of size 50, ensure the sorted data is saved.
        if (data.length == 50) {
            saveData(outputFilename, data);  // Save sorted data for size 50
        }
    }

    // Quicksort method that sorts the array using the first element as the pivot.
    public static void quicksortFirstPivot(int[] data, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index.
            int pivotIndex = partitionFirstPivot(data, low, high);
            // Recursively sort the left and right sub-arrays.
            quicksortFirstPivot(data, low, pivotIndex - 1);
            quicksortFirstPivot(data, pivotIndex + 1, high);
        }
    }

    // Partition method that organizes the array based on the pivot.
    private static int partitionFirstPivot(int[] data, int low, int high) {
        int pivot = data[low];  // Choose the first element as the pivot.
        int left = low + 1;
        int right = high;

        // Continue until the left and right pointers cross.
        while (true) {
            // Move the left pointer to the right until an element larger than the pivot is found.
            while (left <= right && data[left] <= pivot) {
                left++;
                comparisons++;  // Increment the comparison counter.
            }
            // Move the right pointer to the left until an element smaller than the pivot is found.
            while (left <= right && data[right] >= pivot) {
                right--;
                comparisons++;  // Increment the comparison counter.
            }
            // If the left and right pointers have not crossed, swap the elements.
            if (left <= right) {
                swap(data, left, right);
                exchanges++;  // Increment the exchange counter.
            } else {
                break;
            }
        }
        // Swap the pivot element with the element at the right pointer.
        swap(data, low, right);
        exchanges++;  // Increment the exchange counter.
        return right;  // Return the pivot index.
    }

    // Swap method to exchange the positions of two elements in the array.
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // Method to load data from the input file into an integer array.
    public static int[] loadData(String fileName) {
        int[] data = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int count = 0;

            // First pass: count the total number of integers in the file.
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {  // Skip empty lines.
                    count += line.trim().split("\\s+").length;
                }
            }

            // Initialize the array with the correct size based on the count.
            data = new int[count];
            reader.close();

            // Second pass: fill the array with the integers from the file.
            try (BufferedReader reader2 = new BufferedReader(new FileReader(fileName))) {
                int index = 0;
                while ((line = reader2.readLine()) != null) {
                    if (!line.trim().isEmpty()) {  // Skip empty lines.
                        String[] numbers = line.trim().split("\\s+");
                        for (String num : numbers) {
                            data[index++] = Integer.parseInt(num);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the case where a non-numeric value is found in the file.
            System.out.println("Error: Invalid number format in file " + fileName);
            e.printStackTrace();
        }
        return data;  // Return the filled array.
    }

    // Method to save the sorted data to the output file.
    public static void saveData(String fileName, int[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int value : data) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
