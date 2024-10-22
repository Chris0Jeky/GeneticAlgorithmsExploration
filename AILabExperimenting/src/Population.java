import java.util.Arrays;

public class Population {
    Individual[] population;
    int populationSize;

    public Population(int populationSize, int geneLength) {
        this.populationSize = populationSize;
        individuals = new Individual[populationSize];

        for (int i = 0; i < populationSize; i++) {
            individuals[i] = new Individual(geneLength);
            individuals[i].generateRandomGenes();
        }
    }

    public void sortIndividuals() {
        Arrays.sort(individuals);
    }
}
