import com.accenture.jive.pokedex.Move;
import com.accenture.jive.pokedex.pokemon.Pokemon;

public class MainArray {
    /***
     * Test project to learn java
     * */

    public Pokemon createPokemon(String name, int hitPoints, int combatPoints, String type, Move move1, Move move2) {
        Pokemon pokemon = new Pokemon();
        pokemon.name = name;
        pokemon.hitPoints = hitPoints;
        pokemon.combatPoints = combatPoints;
        pokemon.type = type;
        //QUESTION: ginge das auch irgendwie leichter mit den moves im moveset array??
//        pokemon.moveset[0] = move1;
//        pokemon.moveset[1] = move2;
        return pokemon;
    }

    public Move createMove(String moveName, int movePower) {
        Move move = new Move();
        move.name = moveName;
        move.power = movePower;
        return move;
    }

    public void run() {
        //das erste com.accenture.jive.pokedex.Pokemon in meinem Pokedex wird angelegt und alle Eigenschaften vergeben.

        //QUESTION: wie nennen wir das? Instanz eines Objektes initialisieren?
        //ANSWER: com.accenture.jive.pokedex.Pokemon.java ist die Klasse new com.accenture.jive.pokedex.Pokemon initialisiert das Objekt, neue Instanz - ich bekomme ein Objekt/eine Instanz
        //Creating the moves to fill the move sets
        Move vineWhip = createMove("Vine Whip", 45);
        Move tackle = createMove("Tackle", 33);
        Move fireFang = createMove("Fire Fang", 74);


        Pokemon p1 = createPokemon("Bisasam", 23, 155, "Grass", vineWhip, tackle);
        Pokemon p2 = createPokemon("Charmander", 77, 88, "Fire", tackle, fireFang);

        p2.sound();
        //QUESTION: Wie würde ich damit umgehen das manche com.accenture.jive.pokedex.Pokemon weniger Moves haben als andere? Das hatte zu einer Exception geführt
        //ANSWER: Arraylist


        Pokemon[] pokeObjs = new Pokemon[]{p1, p2};

        for (Pokemon pokemon : pokeObjs) {
            System.out.println(pokemon.name + " is the type " + pokemon.type);
            System.out.println("This com.accenture.jive.pokedex.Pokemon knows the moves: ");
//            for (com.accenture.jive.pokedex.Move move : pokemon.moveset) {
//                System.out.println(move.name);
//            }
            //QUESTION: warum hat arraystoSTring bei Moveset nicht geklappt?
            //ANSWER: Array mit Objekten - deshalb war unklar was genau abgebildet werden soll.
        }
    }


    public static void main(String[] args) {
        MainArray main = new MainArray();
        main.run();
    }


}