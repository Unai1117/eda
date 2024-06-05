// Source code is decompiled from a .class file using FernFlower decompiler.
package libraries.dataStructures.hierarchical;

import libraries.dataStructures.models.UFSet;

public class ForestUFSet implements UFSet {
    protected int[] elArray;

    public ForestUFSet(int var1) {
        this.elArray = new int[var1];

        for (int var2 = 0; var2 < this.elArray.length; ++var2) {
            this.elArray[var2] = -1;
        }

    }

    public int find(int var1) {
        return this.elArray[var1] < 0 ? var1 : (this.elArray[var1] = this.find(this.elArray[var1]));
    }

    public void union(int var1, int var2) {
        if (this.elArray[var1] == this.elArray[var2]) {
            this.elArray[var1] = var2;
            int var10002 = this.elArray[var2]--;
        } else if (this.elArray[var1] < this.elArray[var2]) {
            this.elArray[var2] = var1;
        } else {
            this.elArray[var1] = var2;
        }

    }

    public String toString() {
        String var1 = "";

        for (int var2 = 0; var2 < this.elArray.length; ++var2) {
            var1 = var1 + String.format("%3d : %3d\n", var2, this.elArray[var2]);
        }

        return var1;
    }
}
