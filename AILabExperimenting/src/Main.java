import java.util.Arrays;
import java.util.Random;

public class Main {
    static final int GENE_LENGTH = 20;
    static final int POPULATION_SIZE = 10;
    static Random RANDOM = new Random();

    public static void main(String[] args) {

        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + POPULATION_SIZE);
        System.out.println("RANDOM: " + RANDOM.nextInt());

        // Initialize the population
        Population population = new Population(POPULATION_SIZE, GENE_LENGTH);

        // Display genes of each individual in the population
        for (int i = 0; i < POPULATION_SIZE; i++) {
            System.out.println(Arrays.toString(population.getIndividuals()[i].genes));
        }
    }
}
