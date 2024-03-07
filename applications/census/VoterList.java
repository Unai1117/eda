package applications.census;

import libraries.dataStructures.models.ListPOI;
import libraries.dataStructures.linear.LinkedListPOI;
import libraries.dataStructures.linear.SortedLinkedListPOI;

/**
 * VoterList: represents a list of residents registered in
 * the census, and, thus, voters
 *
 * @author EDA Professors
 * @version September 2023 (Translation Feb. 24)
 */

public class VoterList {

    private ListPOI<Resident> census;
    private int size;

    /**
     * Field getter methods
     */
    public ListPOI<Resident> getCensus() {
        return census;
    }

    public int getSize() {
        return size;
    }

    /**
     * Returns the String that represents a VoterList
     *
     * @return the String with the VoterList in the given textual format.
     */
    public String toString() {
        String res = "";
        if (size == 0)
            return res;
        census.begin();
        for (int pos = 0; pos <= census.size() - 2; pos++) {
            res += census.get() + ", \n";
            census.next();
        }
        res += census.get();
        return res;
    }

    /**
     * Creates a VoterList...
     *
     * @param sorted A boolean that shows whether the census must be
     *               sorted in ascending order (true) or not (false).
     * @param n      An int that shows the size (number of elements) of the list
     */
    public VoterList(boolean sorted, int n) {
        size = n;

        if (sorted) {
            census = new SortedLinkedListPOI<Resident>();
        } else {
            census = new LinkedListPOI<Resident>();
        }

        for (int i = 0; i < size; i++) {
            Resident newResident = new Resident();
            if (index(newResident) != -1) {
                i--;
                continue;
            } else {
                census.add(newResident);
            }
        }
    }

    /**
     * Returns the index or position of Resident r in a VoterList,
     * or -1 if r doesn't belong to the list.
     *
     * @param r a Resident
     * @return r's index in a census, an int, 0 or positive
     *         if r is in the census, or -1 otherwise.
     */
    protected int index(Resident r) {
        census.begin();
        for (int i = 0; i < census.size(); i++) {
            if (census.get().equals(r)) {
                return i;
            }
            census.next();
        }
        return -1;
    }

    public VoterList getLocalCensus(int pc1, int pc2) {
        census.begin();
        VoterList newCensus = new VoterList(false, 0);
        for (int i = 0; i < census.size(); i++) {
            Resident currResident = census.get();
            int resPC = currResident.getPC();
            if (pc1 <= resPC && pc2 >= resPC) {
                newCensus.getCensus().add(currResident);
                newCensus.size++;
            }
            census.next();
        }
        return newCensus;
    }

    public VoterList search(String prefix) {
        census.begin();
        VoterList censusSearch = new VoterList(false, 0);
        while (!this.census.isEnd()) {
            if (this.census.get().getSurname1().startsWith(prefix)
                    || this.census.get().getSurname2().startsWith(prefix)) {
                censusSearch.census.add(this.census.get());
                censusSearch.size++;
            }
            census.next();
        }
        return censusSearch;
    }
}
