import java.util.ArrayList;
import java.util.Random;

public class MCPassageGenerator {

    public static String getPassage() {
        ArrayList<String> Passages = new ArrayList<>();
        String pas1= "Armed with a diamond sword and enchanted armor, I entered the Nether's fiery abyss. Ghastly wails echoed, challenging my survival in Minecraft's perilous dimension. The blazing landscapes tested my courage, as each step marked a perilous dance between life and the pixelated unknown. ";
        String pas2= "As I ventured into the uncharted depths of Minecraft, I discovered a hidden stronghold, its stone walls resonating with the eerie sounds of zombies and skeletons. With my trusty pickaxe in hand, I delved deeper into the mysterious structure, eager to uncover its secrets. ";
        String pas3= "As the pixelated sun dipped, I sought refuge in my cozy Minecraft shelter. The fireplace crackled, casting a warm glow on chests filled with precious ores, mementos from treacherous cave explorations. Surrounding sounds - farm animals, distant crickets - created a serene atmosphere. Reflecting on the day's adventures, I marveled at the carefully crafted haven, a mere block arrangement in the vast beauty of Minecraft's pixelated landscapes. ";
        String pas4= "In the heart of my pixelated realm, the Minecraft sunrise painted the sky with hues of orange and pink as I surveyed my thriving farm and bustling animal pens, a testament to countless hours spent cultivating and nurturing the virtual landscape with pixelated precision.  ";
        String pas5= "As dawn broke in Minecraft, the landscape bathed in a soft, pixelated glow. My towering castle stood proudly, surrounded by flourishing gardens and a bustling village. In this blocky universe, each meticulously placed block told a story of creativity, resilience, and the joy of building pixelated dreams. ";

        Passages.add(pas1);
        Passages.add(pas2);
        Passages.add(pas3);
        Passages.add(pas4);
        Passages.add(pas5);


        return getString(Passages);
    }

    private static String getString(ArrayList<String> Passages) {
        Random rand = new Random();
        int place = (rand.nextInt(5));

        String fullSentence = Passages.get(place);

        int endIndex = Math.min(fullSentence.length(), 600);
        while (endIndex > 0 && fullSentence.charAt(endIndex - 1) != '.') {
            endIndex--;
        }

        String toReturn = fullSentence.substring(0, endIndex);
        if (endIndex > 0 && toReturn.length() == TypingTest.passage.length()){
            toReturn = toReturn.strip();
            toReturn = toReturn + "."; // Adding a full stop at the last instead of a space
        }
        return toReturn;
    }
}

