import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Furkan_Özdemir_2018510117 {
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

        System.out.println("================== TRIAL #1 ==================");
        System.out.println("Computer's Greedy Approach result");
        long startTime = System.nanoTime();
        greedyApproach(chessPieceList, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL,
                GOLD_AMOUNT);
        long endTime = System.nanoTime();
        System.out.println("Greedy approach run time: " + (endTime - startTime) + " nanoseconds\n");

        System.out.println("User's Dynamic Programming results");
        long startTimeDynamic = System.nanoTime();
        dynamicApproach(chessPieceList, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL, GOLD_AMOUNT);
        long endTimeDynamic = System.nanoTime();
        System.out.println("Dynamic approach run time: " + (endTimeDynamic - startTimeDynamic) + " nanoseconds\n");

        System.out.println("================== TRIAL #2 ==================");
        System.out.println("Computer's Random Approach result");
        long startTimeRandom = System.nanoTime();
        randomApproach(chessPieceList, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL, GOLD_AMOUNT);
        long endTimeRandom = System.nanoTime();
        System.out.println("Random approach run time: " + (endTimeRandom - startTimeRandom) + " nanoseconds\n");

        System.out.println("User's Dynamic Programming results");
        setIsAvailable(MAX_LEVEL_ALLOWED);
        long startTimeDynamic2 = System.nanoTime();
        dynamicApproach(chessPieceList, NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL, GOLD_AMOUNT);
        long endTimeDynamic2 = System.nanoTime();
        System.out.println("Dynamic approach run time: " + (endTimeDynamic2 - startTimeDynamic2) + " nanoseconds");

    }

    public static void read_file(String path) throws FileNotFoundException, IOException {
        /*
         * the function reads the file line by line
         * then put all the pieces in to an arraylist
         */
        chessPieceList = new ArrayList<ChessPiece>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // skip the first line
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
        /*
         * this function sets the "isAvailable" attributes of the pieces according to
         * the MAX_LEVEL_ALLOWED.
         * for ex. if the MAX_LEVEL_ALLOWED == 1 then set all the pawns available
         * fields to true.
         * if the MAX_LEVEL_ALLOWED == 3 then set Pawn, Rook and Archer's fields to true
         */
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

    public static int listLength(List<ChessPiece> list) {
        /*
         * helper function
         * returns the length of the list
         */
        int counter = 0;
        for (ChessPiece chessPiece : list) {
            if (chessPiece != null) {
                counter++;
                // System.out.println(chessPiece.toString());
            }
        }
        return counter;
    }

    public static int frequency(List<ChessPiece> list, ChessPiece piece) {
        // gönderilen piecein type'ının freqcuency ini return edecek
        // parçanın listede kaç tane olduğuna bakıyor.
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

    public static void setIndividualAvailable(ChessPiece piece, List<ChessPiece> list) {
        // piece is used so its not available for that turn
        /*
         * bir tane pawn kullanıldıysa bir daha pawn alınamıyor. dolayısıyla fonksiyonu
         * tüm pawnları false yapacak şekilde değiştir.
         * argüman olarak list' de alacak. o listteki tüm aynı typetakileri falselayacak
         */
        String type = piece.type;
        Iterator<ChessPiece> iter = list.iterator();
        while (iter.hasNext()) {
            ChessPiece cPiece = iter.next();
            if (cPiece.type.equals(type))
                cPiece.isAvailable = false;
        }
    }

    public static void randomApproach(List<ChessPiece> allPieces, int numOfAvailablePieces, int goldAmount) {
        /*- Gold amountu, oyunun max leveli ve num of pieces önemli */
        /*
         * önce levele uygun olan parçalardan available sayısına göre listeye at
         * sonra paraya göre rastgele seç
         */
        int totalAttackPoints = 0;

        /*
         * pieces are available if they are in that level. ( for level 1 only pawn is
         * available)
         */
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

            // picks a random piece from the available pieces list
            randomChessPiece = availablePieces.get(randomizer.nextInt(availablePieces.size()));

            /*
             * if the randomPiece above is not available we pick again
             * since the picking is random, this loop can be an infinite loop so i put a
             * stop condition.
             * the loop runs for 10.000 iterations at max.
             */
            while (!randomChessPiece.isAvailable && i < 10000) {
                randomChessPiece = availablePieces.get(randomizer.nextInt(availablePieces.size()));
                i++;
                if (i == 10000) {
                    /* we couldn't find a piece so end the loop */
                    System.out.println("Total attack points: " + totalAttackPoints);
                    return;
                }
            }
            /*
             * found an available piece so print it and update the gold amount and the
             * total attack points
             */
            System.out.println(randomChessPiece.displayStats());
            goldAmount -= randomChessPiece.gold;
            totalAttackPoints += randomChessPiece.attackPoints;
            setIndividualAvailable(randomChessPiece, availablePieces);
        }
        System.out.println("Total attack points: " + totalAttackPoints);

        // while (iterator2.hasNext())
        // System.out.println(iterator2.next());
    }

    public static void greedyApproach(List<ChessPiece> allPieces, int numOfAvailablePieces, int goldAmount) {

        /*
         * önce sort edecek sonra elindeki golda , available piece sayısına
         * bakarak loop ile parçaları seçecek
         */
        /*
         * önce yine available pieceleri bir liste at ordan sonra attack puanına göre
         * sortla çünkü en çok attack puanı olacak şekilde seçmek istiyoruz
         */
        // Iterator<ChessPiece> iter = allPieces.iterator();
        // while (iter.hasNext())
        // System.out.println(iter.next());
        int totalAttackPoints = 0;

        List<ChessPiece> availablePieces = new ArrayList<ChessPiece>();
        Iterator<ChessPiece> iterator = allPieces.iterator();

        while (iterator.hasNext()) {
            ChessPiece piece = iterator.next();
            if (piece.isAvailable && numOfAvailablePieces > frequency(availablePieces, piece)) {
                availablePieces.add(piece);
            }
        }

        /* sorts the availablePieces list in descending attack points order */
        Collections.sort(availablePieces, new Comparator<ChessPiece>() {
            @Override
            public int compare(ChessPiece p1, ChessPiece p2) {
                if (p1.attackPoints > p2.attackPoints) {
                    return -1;
                } else if (p1.attackPoints < p2.attackPoints) {
                    return 1;
                } else
                    return 0;
            }
        });

        Iterator<ChessPiece> iterAvailable = availablePieces.iterator();
        while (iterAvailable.hasNext()) {
            ChessPiece piece = iterAvailable.next();
            if (goldAmount - piece.gold > 0 && piece.isAvailable) {
                goldAmount -= piece.gold;
                totalAttackPoints += piece.attackPoints;
                System.out.println(piece.displayStats());
                setIndividualAvailable(piece, availablePieces);
            }
        }
        System.out.println("Total attack points: " + totalAttackPoints);

        // while (iterAvailable.hasNext())
        // System.out.println(iterAvailable.next());
    }

    public static ChessPiece findPieceByAttackPoint(List<ChessPiece> list, int attackPoint) {
        Iterator<ChessPiece> iter = list.iterator();
        while (iter.hasNext()) {
            ChessPiece piece = iter.next();
            if (piece.attackPoints == attackPoint)
                return piece;
        }
        return null;
    }

    public static void dynamicApproach(List<ChessPiece> allPieces, int numOfAvailablePieces, int goldAmount) {
        List<ChessPiece> availablePieces = new ArrayList<ChessPiece>();
        Iterator<ChessPiece> iterator = allPieces.iterator();

        while (iterator.hasNext()) {
            ChessPiece piece = iterator.next();
            if (piece.isAvailable && numOfAvailablePieces > frequency(availablePieces, piece)) {
                availablePieces.add(piece);
            }
        }

        int[] wt = new int[listLength(availablePieces)];
        Iterator<ChessPiece> iterWT = availablePieces.iterator();
        Iterator<ChessPiece> iterVAL = availablePieces.iterator();
        int index = 0;
        while (iterWT.hasNext()) {
            wt[index++] = iterWT.next().gold;
        }
        index = 0;
        int[] val = new int[listLength(availablePieces)];
        while (iterVAL.hasNext()) {
            val[index++] = iterVAL.next().attackPoints;
        }

        /*
         * W-> GOLD_AMOUNT , wt->piecelerin goldu, val-> attack points
         * n-> attack points.length
         */
        // int[] dp = new int[goldAmount + 1];

        // for (int i = 1; i < val.length + 1; i++) {
        // for (int w = goldAmount; w >= 0; w--) {

        // if (wt[i - 1] <= w) {
        // // finding the maximum value
        // dp[w] = Math.max(dp[w], dp[w - wt[i - 1]] + val[i - 1]);

        // }

        // }
        // }
        // System.out.println("Total attack points: " + dp[goldAmount]); // end result

        /* ============================================================== */

        int i, w;
        int K[][] = new int[val.length + 1][goldAmount + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= val.length; i++) {
            for (w = 0; w <= goldAmount; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] +
                            K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        // stores the result of Knapsack
        int res = K[val.length][goldAmount];
        // System.out.println("Total attack points: " + res);
        List<Integer> maxAttackPoints = new ArrayList<>();

        w = goldAmount;
        for (i = val.length; i > 0 && res > 0; i--) {

            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if (res == K[i - 1][w])
                continue;
            else {

                // This item is included.
                // System.out.print(wt[i - 1] + " ");
                // System.out.print(val[i - 1] + " ");
                maxAttackPoints.add(val[i - 1]);

                /*
                 * total attack pointi yukarda yazdırmak yerine burda şu şekilde yazdır
                 * önce wt[i-1]'i yazdır sonra o golda sahip taşı availablePieceden bul ve onu
                 * yazdır
                 * ama burda yazdırırken aynı türden taş yazdırılmış ise onu pas geç ve total
                 * attack pointe onu ekleme
                 */

                // Since this weight is included its
                // value is deducted
                res = res - val[i - 1];
                w = w - wt[i - 1];
            }
        }
        /* sort the attack points from high to low */
        Collections.sort(maxAttackPoints, new Comparator<Integer>() {
            @Override
            public int compare(Integer p1, Integer p2) {
                if (p1 > p2) {
                    return -1;
                } else if (p1 < p2) {
                    return 1;
                } else
                    return 0;
            }
        });
        int totalAttackPoints = 0;
        Iterator<Integer> maxIterator = maxAttackPoints.iterator();
        while (maxIterator.hasNext()) {
            int maxAttackPoint = maxIterator.next();
            ChessPiece piece = findPieceByAttackPoint(availablePieces, maxAttackPoint);
            if (piece.isAvailable) {
                System.out.println(piece.displayStats());
                totalAttackPoints += piece.attackPoints;
                setIndividualAvailable(piece, availablePieces);
            }
        }
        System.out.println("Total attack points: " + totalAttackPoints);
    }
}
