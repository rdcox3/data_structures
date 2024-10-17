import java.io.*;

public class QuicksortAdvanced {
    public static void main(String[] args) {
        int[] data = loadData("input.txt");
        quicksortMedianPivot(data, 0, data.length - 1);
        saveData("output_median.txt", data);
    }

    public static void quicksortInsertionThreshold(int[] data, int low, int high, int threshold) {
        if (high - low + 1 <= threshold) {
            insertionSort(data, low, high);
        } else if (low < high) {
            int pivotIndex = partitionFirstPivot(data, low, high);
            quicksortInsertionThreshold(data, low, pivotIndex - 1, threshold);
            quicksortInsertionThreshold(data, pivotIndex + 1, high, threshold);
        }
    }

    public static void quicksortMedianPivot(int[] data, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionMedianPivot(data, low, high);
            quicksortMedianPivot(data, low, pivotIndex - 1);
            quicksortMedianPivot(data, pivotIndex + 1, high);
        }
    }

    private static int partitionFirstPivot(int[] data, int low, int high) {
        int pivot = data[low];
        int left = low + 1;
        int right = high;
        while (true) {
            while (left <= right && data[left] <= pivot) left++;
            while (left <= right && data[right] >= pivot) right--;
            if (left <= right) {
                swap(data, left, right);
            } else {
                break;
            }
        }
        swap(data, low, right);
        return right;
    }

    private static int partitionMedianPivot(int[] data, int low, int high) {
        int mid = (low + high) / 2;
        if (data[low] > data[mid]) swap(data, low, mid);
        if (data[low] > data[high]) swap(data, low, high);
        if (data[mid] > data[high]) swap(data, mid, high);
        swap(data, mid, low);
        return partitionFirstPivot(data, low, high);
    }

    public static void insertionSort(int[] data, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= low && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public static int[] loadData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

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

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
