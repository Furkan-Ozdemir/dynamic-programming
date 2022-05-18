import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static int GOLD_AMOUNT, MAX_LEVEL_ALLOWED, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL;
    static List<ChessPiece> chessPieceList;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("GOLD AMOUNT: ");
        GOLD_AMOUNT = input.nextInt();
        System.out.print("MAX LEVEL ALLOWED: ");
        MAX_LEVEL_ALLOWED = input.nextInt();
        System.out.print("NUM OF AVAILABLE PIECES PER LEVEL: ");
        NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL = input.nextInt();
        input.close();

        read_file("input_1.csv");
        // System.out.println(listLength());
        // randomApproach(listLength());
        randomApproach(chessPieceList, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL, GOLD_AMOUNT);
    }

    public static void read_file(String path) throws FileNotFoundException, IOException {

        chessPieceList = new ArrayList<ChessPiece>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            for (String line; (line = br.readLine()) != null;) {
                String[] parameters = line.split(",");
                // System.out.println(line);
                // System.out.println(parameters[0] + " " + parameters[1] + " " + parameters[2]
                // + " " + parameters[3]);
                ChessPiece chessPiece = new ChessPiece(parameters[0], parameters[1],
                        Integer.parseInt(parameters[2]),
                        Integer.parseInt(parameters[3]));
                chessPieceList.add(chessPiece);
                setIsAvailable(MAX_LEVEL_ALLOWED);
                // System.out.println(chessPiece.toString());

            }
            // line is not visible here.
        }
    }

    public static void setIsAvailable(int maxLevelAllowed) {
        Iterator<ChessPiece> iterator = chessPieceList.iterator();

        switch (maxLevelAllowed) {
            case 1:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn")) {
                        piece.isAvailable = true;
                    }
                }
                break;

            case 2:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook")) {
                        piece.isAvailable = true;
                    }
                }
                break;

            case 3:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 4:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 5:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight") || piece.type.equals("Bishop")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 6:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight") || piece.type.equals("Bishop")
                            || piece.type.equals("Warship")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 7:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight") || piece.type.equals("Bishop")
                            || piece.type.equals("Warship") || piece.type.equals("Siege")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 8:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight") || piece.type.equals("Bishop")
                            || piece.type.equals("Warship") || piece.type.equals("Siege")
                            || piece.type.equals("Queen")) {
                        piece.isAvailable = true;
                    }
                }
                break;
            case 9:
                while (iterator.hasNext()) {
                    ChessPiece piece = iterator.next();
                    if (piece.type.equals("Pawn") || piece.type.equals("Rook") || piece.type.equals("Archer")
                            || piece.type.equals("Knight") || piece.type.equals("Bishop")
                            || piece.type.equals("Warship") || piece.type.equals("Siege")
                            || piece.type.equals("Queen") || piece.type.equals("King")) {
                        piece.isAvailable = true;
                    }
                }
                break;

            default:
                break;
        }

    }

    public static int listLength() {
        int counter = 0;
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece != null) {
                counter++;
                // System.out.println(chessPiece.toString());
            }
        }
        return counter;
    }

    public static int frequency(List<ChessPiece> list, ChessPiece piece) {
        // gönderilen piecein type'ının freqcuency cini return edecek
        int frequency = 0;
        Iterator<ChessPiece> iter = list.iterator();

        while (iter.hasNext()) {
            ChessPiece piece2 = iter.next();
            if (piece2.type.equals(piece.type)) {
                frequency++;
            }
        }
        return frequency;
    }

    public static void setIndividualAvailable(ChessPiece piece) {
        // piece is used so its not available for next turn
        piece.isAvailable = false;
    }

    public static void randomApproach(List<ChessPiece> allPieces, int numOfAvailablePieces, int goldAmount) {
        /*- Gold amountu, oyunun max leveli ve num of pieces önemli */
        // önce levele uygun olan parçalardan available sayısına göre listeye at
        // sonra paraya göre rastgele seç
        int totalAttackPoints = 0;
        List<ChessPiece> availablePieces = new ArrayList<ChessPiece>();
        Iterator<ChessPiece> iterator = allPieces.iterator();

        while (iterator.hasNext()) {
            ChessPiece piece = iterator.next();
            if (piece.isAvailable && numOfAvailablePieces > frequency(availablePieces, piece)) {
                availablePieces.add(piece);
            }
        }

        // Iterator<ChessPiece> iterator2 = availablePieces.iterator();

        Random randomizer = new Random();

        ChessPiece randomChessPiece = new ChessPiece("dummy", "type", 0, 100);

        int i = 0;
        while (goldAmount - randomChessPiece.gold > 0) {

            randomChessPiece = availablePieces.get(randomizer.nextInt(availablePieces.size()));

            while (!randomChessPiece.isAvailable && i < 10000) {
                randomChessPiece = availablePieces.get(randomizer.nextInt(availablePieces.size()));
                i++;
                if (i == 10000) {
                    System.out.println("Total attack points: " + totalAttackPoints);
                    return;
                }
            }

            System.out.println("Computer random approach: " + randomChessPiece.displayStats());
            goldAmount -= randomChessPiece.gold;
            totalAttackPoints += randomChessPiece.attackPoints;
            setIndividualAvailable(randomChessPiece);
        }
        System.out.println("Total attack points: " + totalAttackPoints);

        // while (iterator2.hasNext())
        // System.out.println(iterator2.next());
    }
}
