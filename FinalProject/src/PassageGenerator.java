import java.util.ArrayList;
import java.util.Random;

public class PassageGenerator {
    public static String getPassage() {
        ArrayList<String> Passages = new ArrayList<>();
        String pas1 = "I am the sword in the darkness. I am the watcher on the walls. I am the fire that burns against the cold, the light that brings the dawn, the horn that wakes the sleepers, the shield that guards the realms of men. I pledge my life and honor to the Night's Watch, for this night and all the nights to come. ";
        String pas2 = "Night gathers, and now my watch begins. It shall not end until my death. I shall take no wife, hold no lands, father no children. I shall wear no crowns and win no glory. I shall live and die at my post. ";
        String pas3 = "My name is Arya Stark, I want you to know that the last thing you're going to see is a Stark smiling down at you as you die. Leave one wolf alive and the sheep are never safe. When people ask you what happened here, tell them the North remembers. Tell them winter came for House of Frey. ";
        String pas4 = "Joffrey, Cercei, Wilder Fray, Meryn Trant, Tywin Lannister, The red woman, Beric Dondarrion, Ilyn Payne, The mountain. Would you shut up? I can't sleep until I say the names. The names of every person in Westeros. Only the ones I'm going to kill. ";
        String pas5 = "You stand in the presence of Daenerys Stormborn of House Targaryen, rightful heir to the Iron Throne, queen of the Andals and the first men, protector of the Seven Kingdoms, the mother of dragons, the Khaleesi of the great vast sea, the unburnt, the breaker of chains. ";
        String pas6 = "I'm not talking about the king in the north I'm talking about the king of the bloody Seven Kingdom. What are you talking about? Your mother was Lyanna Stark and your father, your real father was Rhaegar Targaryen you've never been the bastard you are Aegon Targaryen true heir to the Iron Throne. ";
        String pas7 = "I wish I was the monster you think I am. I wish I had enough poison for the whole pack. I would gladly give my life to watch you all swallow it. I will not give my life for Joffrey's murder and I know I'll get no justice here so I will let the gods decide my fate I demand a trial by combat. ";
        String pas8 = "I saved this city and all your worthless lives. I should have let Stannis kill you all. Do you wish to confess? Yes Father, I'm guilty, is that what you want to hear? You admit you poisoned the king? No, of that I'm innocent, I'm guilty of a far more monstrous crime. I'm guilty of being a dwarf.  ";
        String pas9 = "The King can do as he likes. The Mad King did as he liked. Has your uncle Jaime ever told you what happened to him. No one threatens his grace in the presence of the Kingsguard. I'm not threatening the King, I am educating my nephew, Bronn the next time ser Meryn speaks kill him. That was a threat see the difference. ";
        String pas10 = "Girls see more blood than boys or do you like girls who swoon Jon Snow Oh Oh a spider save me Jon Snow my dress is made of the purest silk from tralalalala. I'd like to see you in a silk dress. Would ya? So I could tear it off. You well you rip my pretty silk dress I'll blacken your eye. ";

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
            toReturn = toReturn + ".";
        }
        return toReturn;
    }
}
