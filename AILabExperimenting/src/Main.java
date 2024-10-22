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

        System.out.print("Enter mutation rate (e.g., 0.01 for 1%): ");
        double mutationRate = scanner.nextDouble();

        System.out.print("Enter number of generations: ");
        int maxGenerations = scanner.nextInt();

        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + populationSize);

        // Initialize the population
        Population population = new Population(populationSize, GENE_LENGTH);

        // Variables to keep track of the best individual
        Individual bestIndividual = null;
        int bestFitness = Integer.MAX_VALUE;

        // Start the evolution process
        for (int generation = 0; generation < maxGenerations; generation++) {
            // Evaluate the population
            int[] evaluatedPopulation = population.evaluate();

            // Keep track of the best individual
            for (int i = 0; i < populationSize; i++) {
                if (population.getIndividuals()[i].fitness < bestFitness) {
                    bestFitness = population.getIndividuals()[i].fitness;
                    bestIndividual = new Individual(GENE_LENGTH);
                    System.arraycopy(population.getIndividuals()[i].genes, 0, bestIndividual.genes, 0, GENE_LENGTH);
                    bestIndividual.fitness = population.getIndividuals()[i].fitness;
                }
            }

            // Output the current generation's best fitness
            System.out.println("Generation " + generation + ": Best Fitness = " + bestFitness);

            // Create a new population
            Individual[] newIndividuals = new Individual[populationSize];

            // Elitism: carry over the best individual to the new population
            newIndividuals[0] = bestIndividual;

            // Generate new individuals through crossover and mutation
            for (int i = 1; i < populationSize; i += 2) {
                // Selection
                Individual parent1 = selectParent(population);
                Individual parent2 = selectParent(population);

                // Crossover
                Individual[] offspring = crossover(parent1, parent2);

                // Mutation
                offspring[0].mutate(mutationRate, random);
                if (i + 1 < populationSize) {
                    offspring[1].mutate(mutationRate, random);
                    newIndividuals[i + 1] = offspring[1];
                }

                newIndividuals[i] = offspring[0];
            }

            // Update the population with the new individuals
            population.setIndividuals(newIndividuals);
        }

        // Output the best individual after all generations
        System.out.println("\nBest Individual After " + maxGenerations + " Generations:");
        System.out.println("Genes: " + Arrays.toString(bestIndividual.genes));
        System.out.println("Fitness: " + bestFitness);
    }

    public static void printGenesInPopulation(Population population) {
        for (Individual individual : population.getIndividuals()) {
            System.out.println(Arrays.toString(individual.genes));
        }
    }

    public static void printEvaluatedPopulation(int[] evaluatedPopulation) {
        for (int i = 0; i < evaluatedPopulation.length; i++) {
            System.out.println("Individual " + i + " fitness: " + evaluatedPopulation[i]);
        }
    }

    public static Individual[] crossover(Individual parent1, Individual parent2) {
        Individual offspring1 = new Individual(GENE_LENGTH);
        Individual offspring2 = new Individual(GENE_LENGTH);
        int crossoverPoint = random.nextInt(GENE_LENGTH);
        // System.out.println("Crossover point: " + crossoverPoint);

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

    // Selection method (Tournament Selection)
    public static Individual selectParent(Population population) {
        int tournamentSize = 3;
        Individual[] tournament = new Individual[tournamentSize];

        // Randomly select individuals for the tournament
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = random.nextInt(population.getIndividuals().length);
            tournament[i] = population.getIndividuals()[randomIndex];
        }

        // Find the best individual in the tournament
        Individual best = tournament[0];
        for (int i = 1; i < tournamentSize; i++) {
            if (tournament[i].fitness < best.fitness) {
                best = tournament[i];
            }
        }

        return best;
    }
}
