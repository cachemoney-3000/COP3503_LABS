package Labs;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numInputs;
        // Stores the number of inputs to be added to the heap
        numInputs = scanner.nextInt();
        median(numInputs);
    }

    private static void median(int numInputs){
        int data;
        double median;

        // Queue that stores the smaller_half half
        PriorityQueue<Integer> smaller_half = new PriorityQueue<>(Collections.reverseOrder());
        // Queue that stores the greater_half half
        PriorityQueue<Integer> greater_half = new PriorityQueue<>();

        // Stores the first element
        data = scanner.nextInt();
        // Add the first element into the queue
        smaller_half.add(data);
        // Make the first element the median
        median = data;
        // Prints out the first median
        System.out.println(median);

        // Scans and handles the next inputs
        for(int i = 1; i < numInputs; i++){
            int input;
            input = scanner.nextInt();

            // First case where the smaller half of the queue has greater value than its other half
            if(smaller_half.size() > greater_half.size()){
                median = first_case(input, median, smaller_half, greater_half);
            }
            // Second case where the smaller half and the greater half of the queue has the same size
            else if(smaller_half.size() == greater_half.size()){
                median = second_case(input, median, smaller_half, greater_half);
            }
            // Third case where the greater half of the queue has greater value than its other half
            else{
                median = third_case(input, median, smaller_half, greater_half);
            }
            // Prints out the nth median
            System.out.println(median);
        }
    }

    private static double first_case(int input, double median, PriorityQueue<Integer> smaller_half ,PriorityQueue<Integer> greater_half){
        // Rearrange both queue and make it balance then add the new input
        if(input < median){
            greater_half.add(smaller_half.remove());
            smaller_half.add(input);
        }
        else
            greater_half.add(input);

        // Find the median
        median = (double) (smaller_half.peek() + greater_half.peek()) / 2;
        return median;
    }

    private static double second_case(int input, double median, PriorityQueue<Integer> smaller_half ,PriorityQueue<Integer> greater_half){
        // Check where to put the new input then get the new median
        if(input < median){
            smaller_half.add(input);
            median = (double) smaller_half.peek();
        }
        else{
            greater_half.add(input);
            median = (double) greater_half.peek();
        }

        return median;
    }

    private static double third_case(int input, double median, PriorityQueue<Integer> smaller_half ,PriorityQueue<Integer> greater_half){
        // Rearrange both queue and make it balance then add the new input
        if(input > median){
            smaller_half.add(smaller_half.remove());
            greater_half.add(input);
        }
        else
            smaller_half.add(input);

        // Find the median
        median = (double) (smaller_half.peek() + greater_half.peek()) / 2;
        return median;
    }
}
