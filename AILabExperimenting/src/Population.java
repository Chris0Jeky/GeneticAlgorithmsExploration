import java.util.Arrays;

public class Population {
    private Individual[] individuals;
    private final int populationSize;

    public Population(int populationSize, int geneLength) {
        this.populationSize = populationSize;
        individuals = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            individuals[i] = new Individual(geneLength);
            individuals[i].generateRandomGenes();
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    // Updated method to update specific individuals in the population
    public void updateIndividual(int index, Individual individual) {
        if (index >= 0 && index < populationSize) {
            individuals[index] = individual;
        }
    }

    public void sortIndividuals() {
        Arrays.sort(individuals);
    }
}
