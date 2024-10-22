import java.util.Random;

public class Individual implements Comparable<Individual> {
    int[] genes;
    int fitness = 0;

    // Constructor
    public Individual(int geneLength) {
        genes = new int[geneLength];
    }

    // Method to generate random genes
    public void generateRandomGenes() {
        Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextInt(10); // Random digit between 0-9
        }
    }

    // Method for comparing individuals based on fitness
    @Override
    public int compareTo(Individual other) {
        return Integer.compare(this.fitness, other.fitness);
    }

    // Updated evaluation method
    public int evaluate() {
        // Reset fitness before calculation
        fitness = 0;
        for (int gene : genes) {
            fitness += gene;
        }
        return fitness;
    }
}
