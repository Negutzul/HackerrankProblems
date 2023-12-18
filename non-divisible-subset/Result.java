import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Result {

    public static int checkMapAndReturnBestChoiceLen(int nr,Map<Integer,Integer> remindersCounter,int k){
        if(!remindersCounter.containsKey(nr) && !remindersCounter.containsKey(k-nr)){
            return 0;
        }
        if(remindersCounter.containsKey(nr) && !remindersCounter.containsKey(k-nr)){
            return remindersCounter.get(nr);
        }

        if(!remindersCounter.containsKey(nr) && remindersCounter.containsKey(k-nr)){
            return remindersCounter.get(k-nr);
        }
        if(remindersCounter.get(nr) > remindersCounter.get(k-nr)){
            return remindersCounter.get(nr);
        }
        return remindersCounter.get(k-nr);
    }


    public static int nonDivisibleSubset(int k, List<Integer> s) {
        if(k==1){
            if(s.size() > 0) {
                return 1;
            }
        }
        Map<Integer,Integer> remindersCounter = new HashMap();
        s.stream().forEach(nr -> {
            if(remindersCounter.containsKey(nr%k)){
                remindersCounter.put(nr%k,(remindersCounter.get(nr%k) + 1));
            }
            else{
                remindersCounter.put(nr%k,1);
            }
        });

        int isOdd = 0;

        if(k%2 == 1)
            isOdd =1;


        int result = IntStream.range(1,(k/2 + isOdd)).reduce(0,(partRes,nr) ->
                partRes+checkMapAndReturnBestChoiceLen(nr, remindersCounter,k));

        if(isOdd == 0 && remindersCounter.containsKey(k/2)){
            result++;
        }

        if(remindersCounter.containsKey(0)){
            result++;
        }

        return result;
    }


}
