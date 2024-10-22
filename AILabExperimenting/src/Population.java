import java.util.Arrays;

public class Population {
    private Individual[] individuals;
    private int populationSize;

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

    public void sortIndividuals() {
        Arrays.sort(individuals);
    }
}
