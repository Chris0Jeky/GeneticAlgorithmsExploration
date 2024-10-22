import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int GENE_LENGTH = 20;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter population size: ");
        int populationSize = scanner.nextInt();

        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + populationSize);

        // Initialize the population
        Population population = new Population(populationSize, GENE_LENGTH);

        printGenesInPopulation(population, populationSize);
    }

    public static void printGenesInPopulation(Population population, int populationSize) {
        for (int i = 0; i < populationSize; i++) {
            System.out.println(Arrays.toString(population.getIndividuals()[i].genes));
        }
    }
}
