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

        System.out.println("\n--- Genetic Algorithm Parameters ---");
        System.out.println("GENE_LENGTH: " + GENE_LENGTH);
        System.out.println("POPULATION_SIZE: " + populationSize);
        System.out.println("MUTATION_RATE: " + mutationRate);
        System.out.println("GENERATIONS: " + maxGenerations);
        System.out.println("------------------------------------\n");

        // Initialize the population
        System.out.println("Initializing population...");
        Population population = new Population(populationSize, GENE_LENGTH);
        printGenesInPopulation(population);

        // Variables to keep track of the best individual
        Individual bestIndividual = null;
        int bestFitness = Integer.MAX_VALUE;

        // Start the evolution process
        for (int generation = 0; generation < maxGenerations; generation++) {
            System.out.println("\n=== Generation " + generation + " ===");

            // Evaluate the population
            System.out.println("Evaluating population...");
            int[] evaluatedPopulation = population.evaluate();
            printEvaluatedPopulation(evaluatedPopulation);

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
            System.out.println("Best Fitness in Generation " + generation + ": " + bestFitness);
            System.out.println("Best Individual's Genes: " + Arrays.toString(bestIndividual.genes));

            // Create a new population
            Individual[] newIndividuals = new Individual[populationSize];

            // Elitism: carry over the best individual to the new population
            System.out.println("Carrying over the best individual to the new population.");
            newIndividuals[0] = bestIndividual;

            // Generate new individuals through crossover and mutation
            for (int i = 1; i < populationSize; i += 2) {
                // Selection
                System.out.println("\nSelecting parents for crossover...");
                Individual parent1 = selectParent(population);
                Individual parent2 = selectParent(population);
                System.out.println("Selected Parent 1 Fitness: " + parent1.fitness);
                System.out.println("Selected Parent 2 Fitness: " + parent2.fitness);

                // Crossover
                System.out.println("Performing crossover...");
                Individual[] offspring = crossover(parent1, parent2);
                System.out.println("Offspring 1 Genes Before Mutation: " + Arrays.toString(offspring[0].genes));
                if (i + 1 < populationSize) {
                    System.out.println("Offspring 2 Genes Before Mutation: " + Arrays.toString(offspring[1].genes));
                }

                // Mutation
                System.out.println("Applying mutation to offspring...");
                offspring[0].mutate(mutationRate, random);
                System.out.println("Offspring 1 Genes After Mutation: " + Arrays.toString(offspring[0].genes));
                if (i + 1 < populationSize) {
                    offspring[1].mutate(mutationRate, random);
                    System.out.println("Offspring 2 Genes After Mutation: " + Arrays.toString(offspring[1].genes));
                    newIndividuals[i + 1] = offspring[1];
                }
                newIndividuals[i] = offspring[0];
            }

            // Update the population with the new individuals
            population.setIndividuals(newIndividuals);
            System.out.println("Population updated for the next generation.");
        }

        // Output the best individual after all generations
        System.out.println("\n=== Final Best Individual After " + maxGenerations + " Generations ===");
        System.out.println("Best Individual's Genes: " + Arrays.toString(bestIndividual.genes));
        System.out.println("Best Fitness: " + bestFitness);
    }

    public static void printGenesInPopulation(Population population) {
        System.out.println("Current Genes in Population:");
        for (int i = 0; i < population.getIndividuals().length; i++) {
            System.out.println("Individual " + i + " Genes: " + Arrays.toString(population.getIndividuals()[i].genes));
        }
    }

    public static void printEvaluatedPopulation(int[] evaluatedPopulation) {
        System.out.println("Fitness of each individual:");
        for (int i = 0; i < evaluatedPopulation.length; i++) {
            System.out.println("Individual " + i + " Fitness: " + evaluatedPopulation[i]);
        }
    }

    public static Individual[] crossover(Individual parent1, Individual parent2) {
        Individual offspring1 = new Individual(GENE_LENGTH);
        Individual offspring2 = new Individual(GENE_LENGTH);
        int crossoverPoint = random.nextInt(GENE_LENGTH - 1) + 1; // Ensure crossoverPoint is at least 1
        System.out.println("Crossover point: " + crossoverPoint);

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
        System.out.println("Selecting individuals for the tournament...");
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = random.nextInt(population.getIndividuals().length);
            tournament[i] = population.getIndividuals()[randomIndex];
            System.out.println("Tournament Individual " + i + " (Index " + randomIndex + ") Fitness: " + tournament[i].fitness);
        }

        // Find the best individual in the tournament
        Individual best = tournament[0];
        for (int i = 1; i < tournamentSize; i++) {
            if (tournament[i].fitness < best.fitness) {
                best = tournament[i];
            }
        }

        System.out.println("Selected Parent Fitness: " + best.fitness);
        return best;
    }
}
