import java.util.ArrayList;
import java.util.Random;

public class MCPassageGenerator {

    public static String getPassage() {
        ArrayList<String> Passages = new ArrayList<>();

        String pas1 = "In the realm of Minecraft, a vast and blocky world awaits exploration and creativity. As the sun rises over pixelated landscapes, players emerge into a realm of endless possibilities. Towering mountains, dense forests, and shimmering oceans beckon adventurers to carve out their own destinies.";
        String pas2 = "Survival mode challenges players to gather resources, craft tools, and build shelter to withstand the nocturnal onslaught of pixelated creatures. The rhythmic symphony of pickaxes meeting stone and the gentle rustle of wheat fields sway in the breeze create a harmonious melody, signaling the heartbeat of this digital universe.";
        String pas3 = "For those who seek artistic expression, Creative mode offers boundless freedom. Skies become the canvas for colossal structures, intricate redstone contraptions, and fantastical landscapes. The hum of innovation resonates through the air as players sculpt, design, and experiment with the endless array of blocks at their disposal.";
        String pas4 = "Minecraft is a canvas where friendships are forged in cooperative builds and alliances formed to conquer the perilous depths of strongholds. The thrill of uncovering hidden temples and the camaraderie of collaborative projects define the communal spirit of this sandbox realm.";
        String pas5 = "In the pixelated twilight, Minecraft is not just a game; it's a boundless frontier where imagination reigns supreme, and every block placed is a brushstroke in the grand tapestry of exploration, creation, and adventure.";
        String pas6 = "Embarking on epic expeditions, Minecraft adventurers traverse diverse biomes, from the arid deserts where temples lie buried beneath the sands to the icy tundras where frosty fortresses guard secrets. The thrill of discovery fuels the desire to conquer new horizons and unravel the mysteries hidden within the voxelated landscapes.";
        String pas7 = "Within Minecraft's enchanted realms, magic intertwines with reality. Mysterious villages dot the landscape, inhabited by peculiar villagers engaged in their unique trades. Enchanting tables hum with arcane power, empowering gear with magical properties, while Nether portals beckon the brave to enter the fiery domain where danger and treasure intertwine.";
        String pas8 = "Redstone, the virtual magic of Minecraft, empowers players to engineer intricate machines and contraptions. From automated farms that cultivate crops with precision to colossal piston doors that guard secret lairs, the mastery of redstone opens doors to a world of ingenuity. Engineers revel in the art of automation and invention, pushing the boundaries of what's possible.";
        String pas9 = "In the expansive world of Minecraft, builders reach for the skies, constructing towering skyscrapers, floating islands, and majestic castles that pierce the clouds. The skyline becomes a testament to architectural prowess as players ascend to new heights, challenging gravity and redefining the very limits of what can be built.";
        String pas10 = "The journey through Minecraft culminates in the ultimate test of courage: facing the Ender Dragon in the End dimension. Adventurers armed with enchanted gear and strategic prowess confront the mighty dragon, its breath destructive, and the obsidian pillars challenging. Victory yields not just experience but a gateway to further adventures and the elusive allure of Minecraft's ever-expanding realms.";

        Passages.add(pas1);
        Passages.add(pas2);
        Passages.add(pas3);
        Passages.add(pas4);
        Passages.add(pas5);
        Passages.add(pas6);
        Passages.add(pas7);
        Passages.add(pas8);
        Passages.add(pas9);
        Passages.add(pas10);

        return getString(Passages);
    }

    private static String getString(ArrayList<String> Passages) {
        Random rand = new Random();
        int place = (rand.nextInt(10));

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

