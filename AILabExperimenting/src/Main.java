import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static final int GENE_LENGTH = 20;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.print("Enter population size: ");
        int populationSize = scanner.nextInt();

        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + populationSize);

        // Initialize the population
        Population population = new Population(populationSize, GENE_LENGTH);

        // Print initial genes of the population
        printGenesInPopulation(population);

        // Perform crossover on two individuals
        Individual[] offspring = crossover(population.getIndividuals()[0], population.getIndividuals()[1]);

        // Update population with the offspring
        population.updateIndividual(0, offspring[0]);
        population.updateIndividual(1, offspring[1]);

        // Print the updated population
        System.out.println("\nUpdated Population:");
        printGenesInPopulation(population);

        // Print specific individual details (if needed)
        printIndividual(population, 0);

        System.out.println(population.getIndividuals()[0].compareTo(population.getIndividuals()[1]));

        printEvaluatedPopulation(population.evaluate());
    }

    public static void printGenesInPopulation(Population population) {
        for (Individual individual : population.getIndividuals()) {
            System.out.println(Arrays.toString(individual.genes));
        }
    }

    public static void printEvaluatedPopulation(int[][] evaluatedPopulation) {
        for (int[] individual : evaluatedPopulation) {
            System.out.println(Arrays.toString(individual));
        }
    }

    public static void printIndividual(Population population, int indexOfIndividual) {
        System.out.println(Arrays.toString(population.getIndividuals()[indexOfIndividual].genes));
    }

    public static Individual[] crossover(Individual parent1, Individual parent2) {
        Individual offspring1 = new Individual(GENE_LENGTH);
        Individual offspring2 = new Individual(GENE_LENGTH);
        int crossoverPoint = random.nextInt(GENE_LENGTH);
        System.out.println("crossover point: " + crossoverPoint);

        for (int i = 0; i < GENE_LENGTH; i++) {
            if (i < crossoverPoint) {
                offspring1.genes[i] = parent1.genes[i];
                offspring2.genes[i] = parent2.genes[i];
            } else {
                offspring1.genes[i] = parent2.genes[i];
                offspring2.genes[i] = parent1.genes[i];
            }
        }
        return new Individual[]{offspring1, offspring2};
    }
}
