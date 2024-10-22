import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int GENE_LENGTH = 20;
    static final int POPULATION_SIZE = 10;
    static final Random RANDOM = new Random();

    public static void main(String[] args) {

        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + POPULATION_SIZE);
        System.out.println("RANDOM: " + RANDOM.nextInt());

        Individual[] population = new Individual[POPULATION_SIZE];

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = new Individual(GENE_LENGTH);
            population[i].generateRandomGenes();
        }
        for (int i = 0; i < POPULATION_SIZE; i++) {
            System.out.println(Arrays.toString(population[i].genes));
        }
    }
}