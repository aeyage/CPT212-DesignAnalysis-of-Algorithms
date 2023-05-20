import java.util.LinkedList;

public class radixSorting 
{
    /* Function to get the largest element from an array 
       consists of integers */
    private static int getMax(int[] arr) 
    {
        // Find the maximum element in the array
        int mx = arr[0];
        for (int i = 1; i < arr.length; i++) 
        {
            if (arr[i] > mx) 
            {
                mx = arr[i];
            }
        }
        return mx;
    }

    /* Function to get the largest element from an array 
       consists of floating point numbers */
    private static int getMaxFloating(float[] arr) 
    {
        int mx = 1;
        for (int i = 0; i < arr.length; i++) 
        {
            // Convert the floating point number to a string
            String value = Float.toString(Math.abs(arr[i]));
            // Find the index of the decimal point
            int decimalPlace = value.indexOf(".");
            /* Intialise variable decimal
               to get the number of digits after the decimal point */
            int dec = value.length() - decimalPlace - 1;

            if (dec > mx) 
            {
                mx = dec;
            }
        }
        return mx;
    }

    // Print the elements of the array, separated by commas
    private static void printArray(int[] arr) 
    {
        for (int num : arr) 
        {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    /* Function for counting sort algorithm 
       according to the digit represented by exponent */
    private static void countingSort(int[] arr, int exp) 
    {
        /* An array of lists to store the elements of the array, 
        grouped by their least significant digit */
        LinkedList<Integer>[] array = new LinkedList[10];
        
        // Initialise each list in the array
        for (int i = 0; i < 10; i++) 
        {
            array[i] = new LinkedList<>();
        }
    
        for (int j = 0; j < arr.length; j++) 
        {
            // Get the least significant digit of the element
            int index = (arr[j] / exp) % 10;
            // Add the element to the list corresponding to its least significant digit
            array[index].add(arr[j]);
        }
        
        // Iterate over the lists in the array, and copy the elements back to the original array
        int count = 0;
        for (int i = 0; i < 10; i++) 
        {
            for (int k = 0; k < array[i].size(); k++) 
            {
                if (count < arr.length) 
                {
                    arr[count++] = array[i].get(k);
                }
            }
        }
    }

    // Main function to implement radix sort for integers
    public static void radixSort(int[] arr) 
    {
        System.out.println("\n\n$$(_________ Radix Sort (Integer) ___________)$$");
        
        // Find the maximum element in the array
        String num = Integer.toString(getMax(arr));
        // Get the number of digits in the maximum element
        int max = num.length();
        // Set the exponent to 1
        int exp = 1;
    
        for (int i = 0; i < max; i++) 
        {
            // Sort the elements of the array based on their least significant digit
            countingSort(arr, exp *= 10);
            System.out.println("\n[" + (i + 1) + "]-Pass");
            printArray(arr);
        }
    
        System.out.println("\nSorted List: ");
        printArray(arr);
    }
    
    // Modified radixSort function to sort the floating point numbers
    public static void radixSort(float[] arr) 
    {
        System.out.println("\n\n$$(_________ Radix Sort (Floating-Point) ___________)$$");
        
        // Find the maximum element in the array
        int digit = getMaxFloating(arr);
        // Get the radix of the maximum element
        int radix = (int) Math.pow(10, digit);
        // An array to store the integers corresponding to the floating point numbers in the array
        int[] intArr = new int[arr.length];
        
        // Convert the floating point numbers to integers
        for (int j = 0; j < arr.length; j++) 
        {
            intArr[j] = (int) (arr[j] * radix);
        }
        
        // Convert the maximum element in the array to a string
        String num = Integer.toString(getMax(intArr));
        // Get the number of digits in the maximum element
        int max = num.length();
        int exp = 1;
    
        for (int i = 0; i < max; i++) 
        {
            // Sort the elements of the array based on their least significant digit
            countingSort(intArr, exp *= 10);
            System.out.println("\n[" + (i + 1) + "]-Pass");
            printArray(intArr);
        }
    
        System.out.println("\nSorted List: ");
        for (int i = 0; i < intArr.length; i++) 
        {
            // Convert the sorted integers back to floating point numbers
            arr[i] = intArr[i] / (float) radix;
            System.out.print(arr[i] + ", ");
        }
    }
    
    // Driver code
    public static void main(String[] args)
    {
        int[] arr = {275, 87, 426, 61, 409, 170, 677, 503};
        float[] arr2 = {24.05f, 3.8211f, 11.133f, 92.69f, 43.535f, 76.9f, 31.24f, 55.223f, 82.2023f, 2.739f};
        radixSort(arr);
        radixSort(arr2);
    }
}