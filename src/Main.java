import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static int max = 10, min = 1;

    public static int getRandomInt(int mins, int maxs) {
        Random random = new Random();
        return random.nextInt((maxs - mins) + 1) + mins;
    }

    public static ArrayList<Integer> getRandomNonRepeatingIntegers(int mins, int maxs) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        while (numbers.size() < maxs-mins+1) {
            int random = getRandomInt(mins, maxs);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }

    public static List<Integer> noIdInList(List<Integer> list, int id){
        List<Integer> ids = new ArrayList<Integer>();

        for(Integer i:list) {
            if(i != id){
                ids.add(i);
            }else{
                ids.add(list.size()+1);
            }
        }
        return ids;
    }

    public static List<Integer> insideDependencies(List<Integer> deps, int id) {
        System.out.println("Scripts inside main \n");
        for (Integer i : deps) {
            if(i != id) {
                deps = getRandomNonRepeatingIntegers(min, max);
                VulnerabilityScript scr = new VulnerabilityScript(i, noIdInList(deps, i));
                System.out.println(scr.toString() + " dependency " + i);
            }else{
                deps = getRandomNonRepeatingIntegers(min, deps.size()+1);
                VulnerabilityScript scr = new VulnerabilityScript(deps.size(), noIdInList(deps, deps.size()));
                System.out.println(scr.toString() + " dependency " + deps.size());
            }
        }
        return deps;
    }


    public static void main(String[] args) {
        int ID = 1;
        List<Integer> scriptIds = new ArrayList<Integer>();
        scriptIds = getRandomNonRepeatingIntegers(min, max);

        VulnerabilityScript script = new VulnerabilityScript(ID, noIdInList(insideDependencies(scriptIds, ID),ID));
        System.out.println("\n" + script.toString() + " main script");
    }
}
