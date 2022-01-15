package Labs;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab1 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numInputs;
        // Stores the number of inputs to be added to the heap
        numInputs = scanner.nextInt();
        median(numInputs);
    }

    private static void median(int numInputs){
        int data;
        double median;
        int input;

        // Queue that stores the smaller half
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        // Queue that stores the greater half
        PriorityQueue<Integer> greater = new PriorityQueue<>();

        // Stores the first element
        data = scanner.nextInt();
        // Add the first element into the queue
        smaller.add(data);
        // Make the first element the median
        median = data;
        // Prints out the first median
        System.out.println(median);

        // Scans and handles the next inputs
        for(int i = 1; i < numInputs; i++){
            input = scanner.nextInt();

            // First case where the smaller half of the queue has greater value than its other half
            if(smaller.size() > greater.size()){
                median = first_case(input, median, smaller, greater);
            }
            // Second case where the smaller half and the greater half of the queue has the same size
            else if(smaller.size() == greater.size()){
               median = second_case(input, median, smaller, greater);
            }
            // Third case where the greater half of the queue has greater value than its other half
            else{
               median = third_case(input, median, smaller, greater);
            }
            // Prints out the nth median
            System.out.println(median);
        }
    }

    private static double first_case(int input, double median, PriorityQueue<Integer> smaller ,PriorityQueue<Integer> greater){
        // Rearrange both queue and make it balance then add the new input
        if(input < median){
            greater.add(smaller.remove());
            smaller.add(input);
        }
        else
            greater.add(input);

        // Find the median
        median = (double) (smaller.peek() + greater.peek()) / 2;
        return median;
    }

    private static double second_case(int input, double median, PriorityQueue<Integer> smaller ,PriorityQueue<Integer> greater){
        // Check where to put the new input then get the new median
        if(input < median){
            smaller.add(input);
            median = (double) smaller.peek();
        }
        else{
            greater.add(input);
            median = (double) greater.peek();
        }

        return median;
    }

    private static double third_case(int input, double median, PriorityQueue<Integer> smaller ,PriorityQueue<Integer> greater){
        // Rearrange both queue and make it balance then add the new input
        if(input > median){
            smaller.add(greater.remove());
            greater.add(input);
        }
        else
            smaller.add(input);

        // Find the median
        median = (double) (smaller.peek() + greater.peek()) / 2;
        return median;
    }
}
