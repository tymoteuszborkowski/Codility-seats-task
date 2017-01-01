import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Plane {

    public static void main(String[] args) {

        solution(2, "1A 2F 1C");
    }

    static int solution(int N, String S) {

        int possibilities = 0;

        List<String> allSeats = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            for (int j = 65; j < 76; j++) {
                if (j == 73) {
                    allSeats.add("" + (i + 1) + (char) (j + 1));
                } else if (j == 74) {
                    continue;
                } else {
                    allSeats.add("" + (i + 1) + (char) (j));
                }
            }

        }

        Pattern pattern = Pattern.compile("[0-9A-Za-z]+");
        Matcher matcher = pattern.matcher(S);

        List<String> busySeats = new ArrayList<>();

        while (matcher.find()) {
            busySeats.add(matcher.group());
        }


        allSeats.removeAll(busySeats);
        System.out.println(allSeats);

        Map<Integer, List<Integer>> freeSeats = new HashMap<>();


        List<Integer> seats = new ArrayList<>();
        String actualRow = "";
        for (String allSeat : allSeats) {
            Matcher rowsMatcher = Pattern.compile("[0-9]+").matcher(allSeat);
            Matcher seatsMatcher = Pattern.compile("[A-K]+").matcher(allSeat);

            while (rowsMatcher.find()) {
                while (seatsMatcher.find()) {
                    int convertedSeat = 0;
                    if(!actualRow.equals(rowsMatcher.group())){
                        seats = new ArrayList<>();
                    }
                    switch (seatsMatcher.group()) {
                        case "A":
                            convertedSeat = 1;
                            break;
                        case "B":
                            convertedSeat = 2;
                            break;
                        case "C":
                            convertedSeat = 3;
                            break;
                        case "D":
                            convertedSeat = 4;
                            break;
                        case "E":
                            convertedSeat = 5;
                            break;
                        case "F":
                            convertedSeat = 6;
                            break;
                        case "G":
                            convertedSeat = 7;
                            break;
                        case "H":
                            convertedSeat = 8;
                            break;
                        case "J":
                            convertedSeat = 9;
                            break;
                        case "K":
                            convertedSeat = 10;
                            break;
                    }
                    seats.add(convertedSeat);
                    freeSeats.put(Integer.parseInt(rowsMatcher.group()), seats);
                    actualRow = rowsMatcher.group();
                }
            }
        }

        System.out.println(freeSeats.values());

        for (List<Integer> listOfSeats: freeSeats.values()) {
            int actualSeat = listOfSeats.get(0);
            int matchThree = 0;

            for(int i = 1; i < listOfSeats.size(); i++){
                if(Math.abs(listOfSeats.get(i) - actualSeat) == 1){
                    matchThree++;
                }

                if(matchThree == 3){
                    possibilities++;
                    matchThree = 0;
                }

                actualSeat = listOfSeats.get(i);
            }
        }

        System.out.println(possibilities);

        return possibilities;


    }

}
