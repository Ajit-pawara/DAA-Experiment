/*
Fractional Knapsack (Greedy)
Time: O(n log n) due to sorting by value/weight ratio
Space: O(1) extra (ignoring input arrays)
CO1
*/
import java.util.Arrays;
public class KnapsackGreedy {
static class Item { double v,w; Item(double v,double w){this.v=v;this.w=w;} }
public static double fractionalKnapsack(Item[] items, double W) {
Arrays.sort(items,(a,b)-> Double.compare(b.v/a.w, a.v/a.w));
double value = 0.0;
for (Item it: items) {
if (W==0) break;
if (it.w <= W) { value += it.v; W -= it.w; }
else { value += it.v * (W / it.w); W = 0; }
}
return value;
}
public static void main(String[] args) {
Item[] items = { new Item(60,10), new Item(100,20), new Item(120,30) };
double W = 50;
System.out.println("Max value: " + fractionalKnapsack(items, W));
}
}
