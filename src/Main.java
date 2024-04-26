import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int blocksCount = sc.nextInt();
        sc.nextLine();

        List<String> blocksOutputs = new ArrayList<>();
        for(int z = 0; z < blocksCount; z++){
            int contestantsCount = sc.nextInt();
            sc.nextLine();

            List<Integer> contestantsWeights = new ArrayList<>();
            for(int i = 0; i < contestantsCount; i++){
                try{
                    contestantsWeights.add(Integer.parseInt(sc.nextLine()));
                }catch(NumberFormatException e){
                    System.out.println("please enter a numeral!");
                    i--;
                }
            }

            for(int i = 0; i < contestantsCount - 1; i++){
                for(int j = i + 1; j < contestantsCount; j++){
                    if(contestantsWeights.get(j) > contestantsWeights.get(i)){
                        int cross;
                        cross = contestantsWeights.get(j);
                        contestantsWeights.set(j, contestantsWeights.get(i));
                        contestantsWeights.set(i, cross);
                    }
                }
            }

            int extensiveIndex = 0;
            if(contestantsCount % 2 == 1)
                extensiveIndex = 1;

            int firstTeamSummWeight = 0;
            int secondTeamSummWeight = 0;

            for(int i = 0; i < contestantsCount - extensiveIndex; i+=2){
                int firstVariaty, secondVariaty;
                int firstContestant = contestantsWeights.get(i);
                int secondContestant = contestantsWeights.get(i + 1);
                firstVariaty = Math.abs((firstTeamSummWeight + firstContestant) - (secondTeamSummWeight + secondContestant));
                secondVariaty = Math.abs((firstTeamSummWeight + secondContestant) - (secondTeamSummWeight + firstContestant));
                if(firstVariaty < secondVariaty){
                    firstTeamSummWeight += firstContestant;
                    secondTeamSummWeight += secondContestant;
                }else{
                    firstTeamSummWeight += secondContestant;
                    secondTeamSummWeight += firstContestant;
                }
            }

            if(extensiveIndex == 1){
                int firstVariaty, secondVariaty;
                int contestant = contestantsWeights.get(contestantsCount - 1);
                firstVariaty = firstTeamSummWeight + contestant;
                secondVariaty = secondTeamSummWeight + contestant;
                if(firstVariaty < secondVariaty){
                    firstTeamSummWeight += contestant;
                }else{
                    secondTeamSummWeight += contestant;
                }
            }
            blocksOutputs.add(firstTeamSummWeight + " " + secondTeamSummWeight);
        }

        for(String block : blocksOutputs)
            System.out.println(block);

    }
}
